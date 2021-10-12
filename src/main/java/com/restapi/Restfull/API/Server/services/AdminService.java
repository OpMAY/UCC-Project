package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.AdminException;
import com.restapi.Restfull.API.Server.exceptions.BadRequestException;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.NotificationType;
import com.restapi.Restfull.API.Server.response.PortfolioType;
import com.restapi.Restfull.API.Server.utility.ASVerification;
import com.restapi.Restfull.API.Server.utility.FirebaseMessagingSnippets;
import com.restapi.Restfull.API.Server.utility.GPVerification;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

@Log4j2
@Service
public class AdminService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private InquiryDao inquiryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoudSourcingDao loudSourcingDao;

    @Autowired
    private LoudSourcingApplyDao loudSourcingApplyDao;

    @Autowired
    private LoudSourcingEntryDao loudSourcingEntryDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private SponDao sponDao;

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private FAQDao faqDao;

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private PenaltyDao penaltyDao;

    @Autowired
    private BannerAdDao bannerAdDao;

    @Autowired
    private BoardCommentDao boardCommentDao;

    @Autowired
    private PortfolioCommentDao portfolioCommentDao;

    @Autowired
    private EntryCommentDao entryCommentDao;

    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    private CurrencyService currencyService;

    private ModelAndView modelAndView;

    @Transactional(propagation = Propagation.REQUIRED)
    public Admin loginAdmin(String id, String password) {
        adminDao.setSession(sqlSession);
        return adminDao.loginAdmin(id, password);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getAdminMain() {
        try {
            adminDao.setSession(sqlSession);
            inquiryDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            modelAndView = new ModelAndView("index");
            List<Inquiry> notAnsweredInquiryList = inquiryDao.getInquiryListByAnswerStatus(false);
            for (Inquiry inquiry : notAnsweredInquiryList) {
                User user = userDao.selectUserByUserNo(inquiry.getUser_no());
                inquiry.setUser_name(user.getName());
                inquiry.setReg_date(inquiry.getReg_date().substring(0, inquiry.getReg_date().lastIndexOf(".")));
            }

            String date = Time.TimeFormatDay();

            List<User> kakaoUserList = userDao.selectUserBySNSAndRegDate("KAKAO", date);
            List<User> naverUserList = userDao.selectUserBySNSAndRegDate("NAVER", date);
            List<User> googleUserList = userDao.selectUserBySNSAndRegDate("GOOGLE", date);
            List<User> appleUserList = userDao.selectUserBySNSAndRegDate("APPLE", date);

            List<LoudSourcing> loudSourcingList = loudSourcingDao.getRecentLSAdminMain();
            for (LoudSourcing loudSourcing : loudSourcingList) {
                int apply_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no()).size();
                loudSourcing.setApplied_artist_num(apply_num);
            }

            modelAndView.addObject("InquiryList", notAnsweredInquiryList);
            modelAndView.addObject("KakaoUser", kakaoUserList.size());
            modelAndView.addObject("NaverUser", naverUserList.size());
            modelAndView.addObject("GoogleUser", googleUserList.size());
            modelAndView.addObject("AppleUser", appleUserList.size());
            modelAndView.addObject("LoudsourcingList", loudSourcingList);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String getUserSNSByDate(String date) {
        try {
            if (date == null) {
                throw new AdminException(new Exception("No Date Error"));
            }
            userDao.setSession(sqlSession);
            List<User> kakaoUserList = userDao.selectUserBySNSAndRegDate("KAKAO", date);
            List<User> naverUserList = userDao.selectUserBySNSAndRegDate("NAVER", date);
            List<User> googleUserList = userDao.selectUserBySNSAndRegDate("GOOGLE", date);
            List<User> appleUserList = userDao.selectUserBySNSAndRegDate("APPLE", date);
            SnsUser snsUser = new SnsUser();
            snsUser.setKakaoUser(kakaoUserList.size());
            snsUser.setNaverUser(naverUserList.size());
            snsUser.setAppleUser(appleUserList.size());
            snsUser.setGoogleUser(googleUserList.size());
            return new Gson().toJson(snsUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int changeArtistName(int artist_no) {
        try {
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            UUID uid = UUID.randomUUID();
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            artist.setArtist_name("부적절한 사용자" + uid.toString().substring(0, 8));
            artistDao.updateArtist(artist);
            User user = userDao.selectUserByUserNo(artist.getUser_no());

            boardDao.updateContentProfile(artist.getArtist_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            portfolioDao.updateContentProfile(artist.getArtist_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            loudSourcingEntryDao.updateContentProfile(artist.getArtist_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            boardCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            portfolioCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            entryCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());

            if (user.getFcm_token() != null && user.isPush()) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.ADMIN_FCM, "아티스트님의 닉네임이 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 이름을 기본 이름으로 변경하였습니다.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            //NOTIFICATION SET
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.ADMIN);
            notification.setContent("아티스트님의 닉네임이 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 이름을 기본 이름으로 변경하였습니다.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int changeArtistProfileImg(int artist_no) {
        try {
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            String basic_profile_img_link = "https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/profile_img_basic.png";
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            artist.setArtist_profile_img(basic_profile_img_link);
            artist.setProfile_blur_img(basic_profile_img_link);
            artistDao.updateArtist(artist);
            User user = userDao.selectUserByUserNo(artist.getUser_no());

            boardDao.updateContentProfile(artist.getArtist_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            portfolioDao.updateContentProfile(artist.getArtist_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            loudSourcingEntryDao.updateContentProfile(artist.getArtist_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            boardCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            portfolioCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());
            entryCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());

            if (user.getFcm_token() != null && user.isPush()) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.ADMIN_FCM, "아티스트님의 프로필 이미지가 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 프로필 이미지를 기본 이미지로 변경하였습니다.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            //NOTIFICATION SET
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.ADMIN);
            notification.setContent("아티스트님의 프로필 이미지가 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 프로필 이미지를 기본 이미지로 변경하였습니다.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int changeUserProfileImg(int user_no) {
        try {
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            User user = userDao.selectUserByUserNo(user_no);
            user.setProfile_img("https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/profile_img_basic.png");
            userDao.updateUser(user);
            Artist artist = artistDao.getArtistByUserNo(user_no);

            if (artist == null) {
                boardCommentDao.updateAllCommentUserInfo(user.getUser_no(), user.getName(), user.getProfile_img());
                portfolioCommentDao.updateAllCommentUserInfo(user.getUser_no(), user.getName(), user.getProfile_img());
                entryCommentDao.updateAllCommentUserInfo(user.getUser_no(), user.getName(), user.getProfile_img());

                if (user.getFcm_token() != null && user.isPush()) {
                    NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                    firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.ADMIN_FCM, "회원의 프로필 이미지가 부적절한 내용이 포함되어 있다고 판단하여 관리자가 회원님의 유저 프로필 이미지를 기본 이미지로 변경하였습니다.", new Gson().toJson(notificationNext));
                } else {
                    log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                }
                //NOTIFICATION SET
                Notification notification = new Notification();
                notification.setUser_no(user.getUser_no());
                notification.setType(NotificationType.ADMIN);
                notification.setContent("회원님의 프로필 이미지가 부적절한 내용이 포함되어 있다고 판단하여 관리자가 회원님의 유저 프로필 이미지를 기본 이미지로 변경하였습니다.");
                notification.setReg_date(Time.TimeFormatHMS());
                NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                notification.setNext(new Gson().toJson(notificationNext));
                notificationDao.insertNotification(notification);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int changeUserName(int user_no) {
        try {
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            UUID uid = UUID.randomUUID();
            User user = userDao.selectUserByUserNo(user_no);
            user.setName("부적절한 사용자" + uid.toString().substring(0, 8));
            userDao.updateUser(user);
            Artist artist = artistDao.getArtistByUserNo(user_no);

            if (artist == null) {
                boardCommentDao.updateAllCommentUserInfo(user.getUser_no(), user.getName(), user.getProfile_img());
                portfolioCommentDao.updateAllCommentUserInfo(user.getUser_no(), user.getName(), user.getProfile_img());
                entryCommentDao.updateAllCommentUserInfo(user.getUser_no(), user.getName(), user.getProfile_img());

                if (user.getFcm_token() != null && user.isPush()) {
                    NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                    firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.ADMIN_FCM, "회원님의 닉네임이 부적절한 내용이 포함되어 있다고 판단하여 관리자가 회원님의 닉네임을 기본 이름으로 변경하였습니다.", new Gson().toJson(notificationNext));
                } else {
                    log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                }
                //NOTIFICATION SET
                Notification notification = new Notification();
                notification.setUser_no(user.getUser_no());
                notification.setType(NotificationType.ADMIN);
                notification.setContent("회원님의 닉네임이 부적절한 내용이 포함되어 있다고 판단하여 관리자가 회원님의 닉네임을 기본 이름으로 변경하였습니다.");
                notification.setReg_date(Time.TimeFormatHMS());
                NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                notification.setNext(new Gson().toJson(notificationNext));
                notificationDao.insertNotification(notification);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int changeArtistMainImg(int artist_no) {
        try {
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            String main_img_url = "https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png";
            artist.setMain_img(main_img_url);
            artist.setMain_blur_img(main_img_url);
            artistDao.updateArtist(artist);
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            if (user.getFcm_token() != null && user.isPush()) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.ADMIN_FCM, "아티스트님의 메인 이미지가 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 메인 이미지를 기본 이미지로 변경하였습니다.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            //NOTIFICATION SET
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.ADMIN);
            notification.setContent("아티스트님의 메인 이미지가 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 메인 이미지를 기본 이미지로 변경하였습니다.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int resetArtistExplain(int artist_no) {
        try {
            artistDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            artist.setExplain("");
            artistDao.updateArtist(artist);
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            if (user.getFcm_token() != null && user.isPush()) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.ADMIN_FCM, "아티스트님의 아티스트 설명이 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 아티스트 설명을 초기화하였습니다.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            //NOTIFICATION SET
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.ADMIN);
            notification.setContent("아티스트님의 아티스트 설명에 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 아티스트 설명을 초기화하였습니다.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int resetArtistHashTag(int artist_no) {
        try {
            artistDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            artist.setHashtag(null);
            artistDao.updateArtist(artist);
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            if (user.getFcm_token() != null && user.isPush()) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.ADMIN_FCM, "아티스트님의 해시태그가 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 해시태그를 초기화하였습니다.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            //NOTIFICATION SET
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.ADMIN);
            notification.setContent("아티스트님의 해시태그가 부적절한 내용이 포함되어 있다고 판단하여 관리자가 아티스트님의 해시태그를 초기화하였습니다.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int resetUserPenalty(int user_no) {
        try {
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            User user = userDao.selectUserByUserNo(user_no);
            Artist artist = artistDao.getArtistByUserNo(user_no);
            List<Penalty> penaltyList = penaltyDao.getPenaltyListByUserNo(user_no);
            user.set_user_private(false);
            if (artist != null) {
                artist.setArtist_private(false);
                artistDao.updateArtist(artist);
                List<Board> boardList = boardDao.getBoardListByArtistNo(artist.getArtist_no());
                for (Board board : boardList) {
                    board.setBoard_private(false);
                    boardDao.updateBoardByPenalty(board);
                }
            }
            Penalty penalty = penaltyList.get(0);
            penaltyDao.deletePenalty(penalty.getPenalty_no());
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView customMessagePage() {
        modelAndView = new ModelAndView("push_message");
        return modelAndView;
    }

    @Transactional(readOnly = true)
    public int sendCustomMessage(String title, String content, String send_to) {
        try {
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            List<User> userList = new ArrayList<>();
            if (send_to.equals("user")) {
                userList = userDao.getAllUserList();
            } else if (send_to.equals("artist")) {
                List<Artist> artistList = artistDao.getAllArtists();
                for (Artist artist : artistList) {
                    int user_no = artist.getUser_no();
                    User user = userDao.selectUserByUserNo(user_no);
                    userList.add(user);
                }
            } else {
                throw new AdminException(new Exception("wrong send_to"));
            }
            NotificationNext notificationNext = new NotificationNext(NotificationType.ADMIN, null, null, 0, NotificationType.ADMIN, 0);
            for (User user : userList) {
                if (user.getFcm_token() != null && user.isPush()) {
                    log.info("Sending Message To user_no : " + user.getUser_no());
                    firebaseMessagingSnippets.push(user.getFcm_token(), title, content, new Gson().toJson(notificationNext));
                } else if (user.getFcm_token() == null) {
                    log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                } else {
                    log.info("User Push Setting is OFF");
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(readOnly = true)
    public ModelAndView getCommentDetail(int comment_no, int type) {
        try {
            modelAndView = new ModelAndView("comment_detail");

            if (type == 1) {
                portfolioCommentDao.setSession(sqlSession);
                PortfolioComment portfolioComment = portfolioCommentDao.getCommentByCommentNo(comment_no);
                if (portfolioComment == null) throw new Exception("Bad Request");
                modelAndView.addObject("comment", portfolioComment);
            } else if (type == 2) {
                boardCommentDao.setSession(sqlSession);
                BoardComment boardComment = boardCommentDao.getCommentByCommentNo(comment_no);
                if (boardComment == null) throw new Exception("Bad Request");
                modelAndView.addObject("comment", boardComment);
            } else if (type == 3) {
                entryCommentDao.setSession(sqlSession);
                EntryComment entryComment = entryCommentDao.getEntryCommentByCommentNo(comment_no);
                if (entryComment == null) throw new Exception("Bad Request");
                modelAndView.addObject("comment", entryComment);
            } else {
                throw new Exception("Bad Request");
            }

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateSpon(int spon_no) {
        try{
            sponDao.setSession(sqlSession);
            Spon spon = sponDao.getSponBySponNo(spon_no);
            String platform = spon.getPlatform();
            if(platform.equals("Android")){
                GPResponseModel response = GPVerification.getInstance().verify(spon.getProduct_id(), spon.getPurchase_token());
                spon.setVerify_status(response.getPurchaseState());
                sponDao.updateSponByPurchaseUpdate(spon);
            } else if (platform.equals("IOS")){
                AppleVerifyRequest request = new AppleVerifyRequest(spon.getReceipt_id(), null, false);
                AppleVerifyResponse response = ASVerification.getInstance().verify(request);
                if(response.getStatus_explain().equals("SUCCESS")){
                    spon.setVerify_status(0);
                    sponDao.updateSponByPurchaseUpdate(spon);
                } else {
                    throw new Exception(response.getStatus_explain());
                }
            } else {
                throw new Exception("Platform Error");
            }
            return 0;
        } catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }


    @Data
    class SnsUser {
        private int kakaoUser;
        private int naverUser;
        private int googleUser;
        private int appleUser;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getUser() {
        try {
            userDao.setSession(sqlSession);
            modelAndView = new ModelAndView("user");
            List<User> userList = userDao.getAllUserList();
            modelAndView.addObject("UserList", userList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getArtist() {
        try {
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            modelAndView = new ModelAndView("artist");
            List<Artist> artistList = artistDao.getAllArtists();
            modelAndView.addObject("artistList", artistList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getComments(String query) {
        try {
            boardCommentDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            int user_no = Integer.parseInt(query);
            if (userDao.selectUserByUserNo(user_no) == null) {
                throw new Exception("Bad Request");
            }
            modelAndView = new ModelAndView("comments");
            List<AdminComment> adminCommentList = new ArrayList<>();
            List<BoardComment> boardCommentList = boardCommentDao.getCommentListByUserNo(user_no);
            List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByUserNo(user_no);
            List<EntryComment> entryCommentList = entryCommentDao.getCommentListByUserNo(user_no);
            for (BoardComment boardComment : boardCommentList) {
                AdminComment adminComment = new AdminComment();
                adminComment.setUser_no(boardComment.getUser_no());
                adminComment.setComment_no(boardComment.getComment_no());
                adminComment.set_private(boardComment.isComment_private());
                adminComment.setType("게시글");
                adminComment.setContent(boardComment.getContent());
                adminComment.setWriter_name(boardComment.getCommenter_name());
                adminComment.setReg_date(boardComment.getReg_date().substring(0, boardComment.getReg_date().lastIndexOf(".")));
                adminCommentList.add(adminComment);
            }
            for (PortfolioComment portfolioComment : portfolioCommentList) {
                AdminComment adminComment = new AdminComment();
                adminComment.setComment_no(portfolioComment.getComment_no());
                adminComment.setUser_no(portfolioComment.getUser_no());
                adminComment.set_private(portfolioComment.isComment_private());
                adminComment.setType("포트폴리오");
                adminComment.setContent(portfolioComment.getContent());
                adminComment.setWriter_name(portfolioComment.getCommenter_name());
                adminComment.setReg_date(portfolioComment.getReg_date().substring(0, portfolioComment.getReg_date().lastIndexOf(".")));
                adminCommentList.add(adminComment);
            }
            for (EntryComment entryComment : entryCommentList) {
                AdminComment adminComment = new AdminComment();
                adminComment.setComment_no(entryComment.getEntry_comment_no());
                adminComment.setUser_no(entryComment.getUser_no());
                adminComment.set_private(entryComment.isComment_private());
                adminComment.setType("크라우드");
                adminComment.setContent(entryComment.getContent());
                adminComment.setWriter_name(entryComment.getCommenter_name());
                adminComment.setReg_date(entryComment.getReg_date().substring(0, entryComment.getReg_date().lastIndexOf(".")));
                adminCommentList.add(adminComment);
            }

            adminCommentList.sort((o1, o2) -> {
                String ds1 = o1.getReg_date();
                String ds2 = o2.getReg_date();
                Date d1 = new Date();
                Date d2 = new Date();
                try {
                    d1 = Time.StringToDateTimeFormat(ds1);
                    d2 = Time.StringToDateTimeFormat(ds2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (d1.after(d2))
                    return -1;
                if (d1.before(d2))
                    return 1;
                else
                    return 0;
            });
            modelAndView.addObject("commentList", adminCommentList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteComment(int comment_no, String type) {
        boardCommentDao.setSession(sqlSession);
        portfolioCommentDao.setSession(sqlSession);
        entryCommentDao.setSession(sqlSession);


        int result = 0;
        switch (type) {
            case "게시글":
                boardCommentDao.deleteComment(comment_no);
                result = 1;
                break;
            case "포트폴리오":
                portfolioCommentDao.deleteComment(comment_no);
                result = 1;
                break;
            case "크라우드":
                entryCommentDao.deleteComment(comment_no);
                result = 1;
                break;
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int setCommentPrivate(int comment_no, String type) {
        boardCommentDao.setSession(sqlSession);
        portfolioCommentDao.setSession(sqlSession);
        entryCommentDao.setSession(sqlSession);

        int result = 0;

        switch (type) {
            case "게시글":
                BoardComment boardComment = boardCommentDao.getCommentByCommentNo(comment_no);
                if (!boardComment.isComment_private()) {
                    boardComment.setComment_private(true);
                    result = 1;
                } else {
                    boardComment.setComment_private(false);
                    result = 2;
                }
                boardCommentDao.updateComment(boardComment);
                break;
            case "포트폴리오":
                PortfolioComment portfolioComment = portfolioCommentDao.getCommentByCommentNo(comment_no);
                if (!portfolioComment.isComment_private()) {
                    portfolioComment.setComment_private(true);
                    result = 1;
                } else {
                    portfolioComment.setComment_private(false);
                    result = 2;
                }
                portfolioCommentDao.updateComment(portfolioComment);
                break;
            case "크라우드":
                EntryComment entryComment = entryCommentDao.getEntryCommentByCommentNo(comment_no);
                if (!entryComment.isComment_private()) {
                    entryComment.setComment_private(true);
                    result = 1;
                } else {
                    entryComment.setComment_private(false);
                    result = 2;
                }
                entryCommentDao.updateComment(entryComment);
                break;
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getUserDetail(String query) {
        try {
            userDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            sponDao.setSession(sqlSession);
            modelAndView = new ModelAndView("user_detail");
            int user_no = Integer.parseInt(query);
            User user = userDao.selectUserByUserNo(user_no);
            if (user == null) throw new Exception("Bad Request");
            List<Penalty> penaltyList = penaltyDao.getPenaltyListByUserNo(user_no);
            modelAndView.addObject("penalty_num", penaltyList.size());
            modelAndView.addObject("User", user);
            if (user.is_user_private() && penaltyList.size() > 0) {
                modelAndView.addObject("penalty", penaltyList.get(0));
            }
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getArtistDetail(String query) {
        try {
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            modelAndView = new ModelAndView("artist_detail");

            int artist_no = Integer.parseInt(query);
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            List<Penalty> penaltyList = penaltyDao.getPenaltyListByArtistNo(artist_no);

            if (artist.isArtist_private() && penaltyList.size() > 0) {
                modelAndView.addObject("penalty", penaltyList.get(0));
            }
            if (artist.getHashtag() != null) {
                ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                ArrayList<String> modified_HashtagList = new ArrayList<>(hashtagList);
                artist.setHashtag(new Gson().toJson(modified_HashtagList));
            }
            if (artist.getArtist_profile_img().equals("default")) {
                artist.setArtist_profile_img("https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/profile_img_basic.png");
            }
            if (artist.getMain_img().equals("default")) {
                artist.setMain_img("https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png");
            }

            modelAndView.addObject("penalty_num", penaltyList.size());
            modelAndView.addObject("User", user);
            modelAndView.addObject("Artist", artist);

            return modelAndView;
        } catch (Exception e) {
            throw new AdminException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getPortfolioList(String query, String type) {
        try {
            artistDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            modelAndView = new ModelAndView("portfolio_list");

            int artist_no = Integer.parseInt(query);
            if (artistDao.getArtistByArtistNo(artist_no) == null) {
                throw new Exception("Bad Request");
            }

            List<Portfolio> res = new ArrayList<>();

            switch (type) {
                case "all":
                    List<Portfolio> portfolioList = portfolioDao.getPortfolioListByArtistNo(artist_no);
                    for (Portfolio portfolio : portfolioList) {
                        portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                        portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                        res.add(portfolio);
                    }
                    break;
                case "vod":
                    List<Portfolio> vodPortfolioList = portfolioDao.getPortfolioByTypeAdmin(artist_no, "vod");
                    for (Portfolio portfolio : vodPortfolioList) {
                        portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                        portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                        res.add(portfolio);
                    }
                    break;
                case "image":
                    List<Portfolio> imgPortfolioList = portfolioDao.getPortfolioByTypeAdmin(artist_no, "image");
                    for (Portfolio portfolio : imgPortfolioList) {
                        portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                        portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                        res.add(portfolio);
                    }
                    break;
                case "text":
                    List<Portfolio> textPortfolioList = portfolioDao.getPortfolioByTypeAdmin(artist_no, "text");
                    for (Portfolio portfolio : textPortfolioList) {
                        portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                        portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                        res.add(portfolio);
                    }
                    break;
                case "file":
                    List<Portfolio> filePortfolioList = portfolioDao.getPortfolioByTypeAdmin(artist_no, "file");
                    for (Portfolio portfolio : filePortfolioList) {
                        portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                        portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                        res.add(portfolio);
                    }
                    break;
                default:
                    throw new BadRequestException(new Exception("Wrong Portfolio Type"));
            }

            modelAndView.addObject("portfolioList", res);
            modelAndView.addObject("artist_no", artist_no);
            modelAndView.addObject("type", type);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getBoardList(String query) {
        try {
            boardDao.setSession(sqlSession);
            sponDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            modelAndView = new ModelAndView("board_list");
            int artist_no = Integer.parseInt(query);
            if (artistDao.getArtistByArtistNo(artist_no) == null)
                throw new Exception("Bad Request");

            List<Board> boardList = boardDao.getBoardListByArtistNo(artist_no);
            for (Board board : boardList) {
                board.setReg_date(Time.MsToSecond(board.getReg_date()));
                board.setRevise_date(Time.MsToSecond(board.getRevise_date()));
            }

            modelAndView.addObject("boardList", boardList);

            return modelAndView;
        } catch (Exception e) {
            throw new AdminException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getPortfolioDetail(String query) {
        try {
            artistDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            modelAndView = new ModelAndView("portfolio_detail");

            int portfolio_no = Integer.parseInt(query);
            Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
            if (portfolio.getType().equals(PortfolioType.FILE)) {
                String jsonString = portfolio.getFile();
                Gson gson = new Gson();
                FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                ArrayList<Upload> uploads = new ArrayList<>();
                for (FileJson json : fileJson) {
                    uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
                }
                portfolio.setFile_list(uploads);
            } else if (portfolio.getType().equals(PortfolioType.IMAGE)) {
                if (portfolio.getFile() != null) {
                    String images = portfolio.getFile().replace("[", "");
                    images = images.replace("]", "");
                    ArrayList<String> filelist = new ArrayList<>(Arrays.asList(images.split(", ")));
                    portfolio.setImage_list(filelist);
                }
            }
            portfolio.setRevise_date(portfolio.getRevise_date().substring(0, portfolio.getRevise_date().lastIndexOf(".")));

            modelAndView.addObject("portfolio", portfolio);

            return modelAndView;
        } catch (Exception e) {
            throw new AdminException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deletePortfolio(int portfolio_no) {
        try {
            portfolioDao.setSession(sqlSession);
            portfolioDao.deletePortfolio(portfolio_no);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getBoardDetail(String query) {
        try {
            boardDao.setSession(sqlSession);
            sponDao.setSession(sqlSession);
            modelAndView = new ModelAndView("board_detail");
            int board_no = Integer.parseInt(query);

            Board board = boardDao.getBoardByBoardNo(board_no);
            if (board == null) {
                throw new BadRequestException(new Exception("Bad Request"));
            }
            board.setRevise_date(board.getRevise_date().substring(0, board.getRevise_date().lastIndexOf(".")));

            modelAndView.addObject("board", board);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteBoard(int board_no) {
        try {
            boardDao.setSession(sqlSession);
            boardDao.deleteBoard(board_no);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingRecruitmentPage() {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            modelAndView = new ModelAndView("loudsourcing_recruitment_list");

            List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusAdmin("recruitment");

            for (LoudSourcing loudSourcing : loudSourcingList) {
                int applied_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no()).size();
                loudSourcing.setApplied_artist_num(applied_num);
            }

            modelAndView.addObject("loudsourcingList", loudSourcingList);

            return modelAndView;
        } catch (Exception e) {
            throw new AdminException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingProcessPage() {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);

            modelAndView = new ModelAndView("loudsourcing_process_list");

            List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusAdmin("process");

            for (LoudSourcing loudSourcing : loudSourcingList) {
                int applied_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no()).size();
                loudSourcing.setApplied_artist_num(applied_num);
            }

            modelAndView.addObject("loudsourcingList", loudSourcingList);

            return modelAndView;
        } catch (Exception e) {
            throw new AdminException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingJudgePage() {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);

            modelAndView = new ModelAndView("loudsourcing_judge_list");

            List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusAdmin("judge");

            for (LoudSourcing loudSourcing : loudSourcingList) {
                List<LoudSourcingApply> loudSourcingApplyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no());
                for (LoudSourcingApply loudSourcingApply : loudSourcingApplyList) {
                    if (loudSourcingApply.is_pre_selected()) {
                        loudSourcing.setSelected_artist_num(loudSourcing.getSelected_artist_num() + 1);
                    }
                }
                loudSourcing.setJudge_date(Time.DatePlusOneDay(loudSourcing.getProcess_end_date()));
            }

            modelAndView.addObject("loudsourcingList", loudSourcingList);

            return modelAndView;
        } catch (Exception e) {
            throw new AdminException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingEndPage() {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);

            modelAndView = new ModelAndView("loudsourcing_end_list");

            List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusAdmin("end");

            for (LoudSourcing loudSourcing : loudSourcingList) {
                int applied_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no()).size();
                loudSourcing.setApplied_artist_num(applied_num);
                int final_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoSelected(loudSourcing.getLoudsourcing_no()).size();
                loudSourcing.setTotal_selected_artist_num(final_num);
            }

            modelAndView.addObject("loudsourcingList", loudSourcingList);

            return modelAndView;
        } catch (Exception e) {
            throw new AdminException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getRecruitmentApplyListPage(String query) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            int loudsourcing_no = Integer.parseInt(query);
            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no) == null) {
                throw new BadRequestException(new Exception("Bad Request"));
            }

            modelAndView = new ModelAndView("loudsourcing_recruitment_apply_list");

            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudsourcing_no);
            List<RecruitArtist> artistList = new ArrayList<>();
            for (LoudSourcingApply loudSourcingApply : applyList) {
                int artist_no = loudSourcingApply.getArtist_no();
                Artist artist = artistDao.getArtistByArtistNo(artist_no);
                RecruitArtist recruitArtist = new RecruitArtist();
                recruitArtist.setArtist_no(artist_no);
                recruitArtist.setLoudsourcing_no(loudsourcing_no);
                recruitArtist.setArtist_name(artist.getArtist_name());
                recruitArtist.setEmail(artist.getEmail());
                recruitArtist.setPhone(artist.getArtist_phone());
                recruitArtist.setApply_date(loudSourcingApply.getReg_date());
                artistList.add(recruitArtist);
            }

            modelAndView.addObject("artistList", artistList);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int sendProcessStartMessageToAll(int loudsourcing_no) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudsourcing_no);
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            List<User> userList = new ArrayList<>();
            if (applyList != null && !applyList.isEmpty()) {
                for (LoudSourcingApply loudSourcingApply : applyList) {
                    Artist artist = artistDao.getArtistByArtistNo(loudSourcingApply.getArtist_no());
                    User user = userDao.selectUserByUserNo(artist.getUser_no());
                    userList.add(user);
                }

                if (!userList.isEmpty()) {
                    for (User user : userList) {
                        //FCM MESSAGE SEND
                        Artist artist = artistDao.getArtistByUserNo(user.getUser_no());
                        if (user.getFcm_token() != null && artist.isLoudsourcing_push()) {
                            NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                            firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.LOUDSOURCING_NOTIFICATION_FCM, "[" + loudSourcing.getName() + "] 아티스트님이 '모집' 신청하신 크라우드 공고가 '진행'시작되었습니다. 확인해주시고 출품작을 업로드 하여 주세요.", new Gson().toJson(notificationNext));
                        } else {
                            log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                        }
                        //NOTIFICATION SET
                        Notification notification = new Notification();
                        notification.setUser_no(user.getUser_no());
                        notification.setType(NotificationType.LOUDSOURCING_NOTIFICATION);
                        notification.setContent("[" + loudSourcing.getName() + "] 아티스트님이 '모집' 신청하신 크라우드 공고가 '진행'시작되었습니다. 확인해주시고 출품작을 업로드 하여 주세요.");
                        notification.setReg_date(Time.TimeFormatHMS());
                        NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                        notification.setNext(new Gson().toJson(notificationNext));
                        notificationDao.insertNotification(notification);
                    }
                }
            }

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingDetailPage(String query, String type) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            if (type.equals("Detail"))
                modelAndView = new ModelAndView("loudsourcing_detail");
            else if (type.equals("Edit"))
                modelAndView = new ModelAndView("loudsourcing_detail_edit");
            else
                throw new Exception("Bad Request");
            int loudsourcing_no = Integer.parseInt(query);
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            if (loudSourcing == null) {
                throw new Exception("Bad Request");
            }
            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudsourcing_no);
            loudSourcing.setApplied_artist_num(applyList.size());
            String jsonString = loudSourcing.getFiles();
            Gson gson = new Gson();
            FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
            ArrayList<Upload> uploads = new ArrayList<>();
            if (fileJson != null) {
                for (FileJson json : fileJson) {
                    uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
                }
            }
            modelAndView.addObject("files", uploads);
            if (loudSourcing.getHashtag() != null && !loudSourcing.getHashtag().equals("")) {
                ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(loudSourcing.getHashtag().split(",")));
                ArrayList<String> modified_HashtagList = new ArrayList<>(hashtagList);
                loudSourcing.setHashtag(new Gson().toJson(modified_HashtagList));
            }
            modelAndView.addObject("Loudsourcing", loudSourcing);
            if (loudSourcing.getStatus().equals("judge")) {
                int preSize = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoPreSelected(loudsourcing_no).size();
                int unPreSize = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoUnPreSelected(loudsourcing_no).size();
                modelAndView.addObject("preSelectedNum", preSize);
                modelAndView.addObject("unPreSelectedNum", unPreSize);

            } else if (loudSourcing.getStatus().equals("end")) {
                int finalSelected = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoSelected(loudsourcing_no).size();
                modelAndView.addObject("final_selected_num", finalSelected);
            }


            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateLoudsourcing(LoudSourcing loudSourcing) {
        try {
            loudSourcingDao.setSession(sqlSession);
            Gson gson = new Gson();
            LoudSourcing original_loudsourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudSourcing.getLoudsourcing_no());
            String og_jsonString = original_loudsourcing.getFiles();
            String updated_jsonString = loudSourcing.getFiles();
            FileJson[] og_files = gson.fromJson(og_jsonString, FileJson[].class);
            FileJson[] updated_files = gson.fromJson(updated_jsonString, FileJson[].class);
            for (FileJson fileJson : updated_files) {
                for (FileJson original : og_files) {
                    if (fileJson.getUrl().equals(original.getUrl())) {
                        fileJson.setName(original.getName());
                    }
                }
            }
            String files = gson.toJson(updated_files);
            loudSourcing.setFiles(files);
            if (loudSourcing.getImg() == null)
                loudSourcing.setImg(original_loudsourcing.getImg());
            if (loudSourcing.getHashtag() != null) {
                loudSourcing.setHashtag(loudSourcing.getHashtag().replace("[", "").replace("]", "").replace("\"", ""));
            }
            loudSourcing.setType(original_loudsourcing.getType());
            loudSourcing.setHost_profile_img(original_loudsourcing.getHost_profile_img());
            loudSourcing.setRevise_date(Time.TimeFormatHMS());
            loudSourcingDao.updateLoudSourcing(loudSourcing);
            log.info("LoudSourcing Updated Successfully");
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingUploadPage() {
        modelAndView = new ModelAndView("loudsourcing_make");
        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int uploadLoudSourcing(LoudSourcing loudSourcing) {
        try {
            loudSourcingDao.setSession(sqlSession);
            String time = Time.TimeFormatHMS();
            if (loudSourcing.getHashtag() != null) {
                loudSourcing.setHashtag(loudSourcing.getHashtag().replace("[", "").replace("]", "").replace("\"", ""));
            }
            loudSourcing.setReg_date(time);
            loudSourcing.setRevise_date(time);
            loudSourcing.setType("default");
            loudSourcing.setHost_profile_img("http://www.weart-page.com/static/image/profile_img_basic.png");
            loudSourcingDao.insertLoudSourcing(loudSourcing);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteLoudSourcing(int loudsourcing_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingDao.deleteLoudSourcing(loudsourcing_no);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingAdvertiserInfo(int loudsourcing_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            modelAndView = new ModelAndView("loudsourcing_advertiser_info");

            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            if (loudSourcing == null) {
                throw new Exception("Bad Request");
            }
            loudSourcing.setReg_date(loudSourcing.getReg_date().substring(0, loudSourcing.getReg_date().lastIndexOf(".")));
            loudSourcing.setRevise_date(loudSourcing.getRevise_date().substring(0, loudSourcing.getRevise_date().lastIndexOf(".")));
            modelAndView.addObject("LoudSourcing", loudSourcing);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingAdvertiserForEdit(int loudsourcing_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            modelAndView = new ModelAndView("loudsourcing_advertiser_info_edit");

            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            if (loudSourcing == null) {
                throw new Exception("Bad Request");
            }
            loudSourcing.setReg_date(loudSourcing.getReg_date().substring(0, loudSourcing.getReg_date().lastIndexOf(".")));
            loudSourcing.setRevise_date(loudSourcing.getRevise_date().substring(0, loudSourcing.getRevise_date().lastIndexOf(".")));
            modelAndView.addObject("LoudSourcing", loudSourcing);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int advertiserEdit(AdvertiserEditRequest request) {
        try {
            loudSourcingDao.setSession(sqlSession);
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(request.getLoudsourcing_no());
            loudSourcing.setAdvertiser_name(request.getAdvertiser_name());
            loudSourcing.setAdvertiser_phone(request.getAdvertiser_phone());
            loudSourcing.setAdvertiser_email(request.getAdvertiser_email());
            loudSourcing.setAdvertiser_bank_name(request.getAdvertiser_bank_name());
            loudSourcing.setAdvertiser_bank_account(request.getAdvertiser_bank_account());
            loudSourcing.setAdvertiser_bank_owner(request.getAdvertiser_bank_owner());
            loudSourcing.setRevise_date(Time.TimeFormatHMS());
            loudSourcingDao.updateAdvertiser(loudSourcing);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingProcessArtistList(int loudsourcing_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no) == null) {
                throw new Exception("Bad Request");
            }
            modelAndView = new ModelAndView("loudsourcing_process_apply_list");
            List<EntryProcessListRequest> requestList = new ArrayList<>();
            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudsourcing_no);
            for (LoudSourcingApply apply : applyList) {
                EntryProcessListRequest request = new EntryProcessListRequest();
                Artist artist = artistDao.getArtistByArtistNo(apply.getArtist_no());
                LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artist.getArtist_no(), loudsourcing_no);
                request.setArtist_no(artist.getArtist_no());
                request.setArtist_name(artist.getArtist_name());
                request.setPhone(artist.getArtist_phone());
                request.setEmail(artist.getEmail());
                request.setLoudsourcing_no(loudsourcing_no);
                if (entry != null) {
                    request.setEntry_no(entry.getEntry_no());
                    request.setUpload_date(entry.getReg_date().substring(0, entry.getReg_date().lastIndexOf(".")));
                    request.setVote_number(entry.getVote_number());
                } else {
                    request.setUpload_date("미업로드");
                }
                requestList.add(request);
            }
            modelAndView.addObject("artistList", requestList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getEntryDetail(int loudsourcing_no, int artist_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            modelAndView = new ModelAndView("entry_detail");
            LoudSourcingApply apply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artist_no, loudsourcing_no);
            if (apply == null) {
                throw new Exception("Bad Request");
            }
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            if (artist == null) {
                throw new Exception("Bad Request");
            }
            if (artist.getHashtag() != null) {
                ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                ArrayList<String> modified_HashtagList = new ArrayList<>(hashtagList);
                artist.setHashtag(new Gson().toJson(modified_HashtagList));
            }
            if (artist.getArtist_profile_img().equals("default")) {
                artist.setArtist_profile_img("https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/profile_img_basic.png");
            }
            if (artist.getMain_img().equals("default")) {
                artist.setMain_img("https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png");
            }
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artist_no, loudsourcing_no);
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            List<Penalty> penaltyList = penaltyDao.getPenaltyListByArtistNo(artist_no);
            if (artist.isArtist_private() && penaltyList.size() > 0) {
                modelAndView.addObject("penalty", penaltyList.get(0));
            }
            modelAndView.addObject("status", loudSourcing.getStatus());
            modelAndView.addObject("penalty_num", penaltyList.size());
            modelAndView.addObject("Artist", artist);
            modelAndView.addObject("loudsourcing_no", loudsourcing_no);
            modelAndView.addObject("preSelected", apply.is_pre_selected());
            if (entry != null) {
                entry.setReg_date(entry.getReg_date().substring(0, entry.getReg_date().lastIndexOf(".")));
                modelAndView.addObject("entry", entry);
            }
            modelAndView.addObject("User", user);


            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int sendProcessStartMessage(int loudsourcing_no, int artist_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);

            if (user.getFcm_token() != null && artist.isLoudsourcing_push()) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.LOUDSOURCING_NOTIFICATION_FCM, "[" + loudSourcing.getName() + "] 아티스트님이 '모집' 신청하신 크라우드 공고가 '진행'시작되었습니다. 확인해주시고 출품작을 업로드 하여 주세요.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.LOUDSOURCING_NOTIFICATION);
            notification.setContent("[" + loudSourcing.getName() + "] 아티스트님이 '모집' 신청하신 크라우드 공고가 '진행'시작되었습니다. 확인해주시고 출품작을 업로드 하여 주세요.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getUnknownEntryList(int loudsourcing_no) {
        try {
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            modelAndView = new ModelAndView("loudsourcing_process_withdraw_list");
            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no) == null) {
                throw new Exception("Bad Request");
            }
            List<LoudSourcingEntry> entryList = loudSourcingEntryDao.getEntryListByLoudSourcingNoAdmin(loudsourcing_no);
            List<LoudSourcingEntry> resEntryList = new ArrayList<>();
            if (entryList != null && !entryList.isEmpty()) {
                for (LoudSourcingEntry entry : entryList) {
                    if (entry.getArtist_no() == 0) {
                        entry.setReg_date(entry.getReg_date().substring(0, entry.getReg_date().lastIndexOf(".")));
                        resEntryList.add(entry);
                    }
                }
            }

            modelAndView.addObject("entryList", resEntryList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getUnknownEntryDetail(int loudsourcing_no, int entry_no) {
        try {
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            modelAndView = new ModelAndView("unknown_entry_detail");
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            if (loudSourcing == null) {
                throw new Exception("Bad Request");
            }
            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);
            entry.setReg_date(entry.getReg_date().substring(0, entry.getReg_date().lastIndexOf(".")));
            modelAndView.addObject("entry", entry);
            modelAndView.addObject("status", loudSourcing.getStatus());
            modelAndView.addObject("loudsourcing_no", loudsourcing_no);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteUnknownEntry(int entry_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);
            if (entry != null) {
                if (entry.getArtist_no() != 0) {
                    throw new BusinessException(new Exception("Not Dummy Entry. Not Valid Access"));
                } else {
                    log.info("Dummy Entry exist, Deleting..");
                    loudSourcingEntryDao.deleteEntry(entry_no);
                }
            } else {
                throw new BusinessException(new Exception("No Entry in given entry_no. Not Valid Access"));
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteApply(int artist_no, int loudsourcing_no) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

            LoudSourcingApply apply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artist_no, loudsourcing_no);
            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artist_no, loudsourcing_no);
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            if (apply != null) {
                loudSourcingApplyDao.deleteLoudSourcingApply(artist_no, loudsourcing_no);
                if (entry != null) {
                    loudSourcingEntryDao.deleteEntry(entry.getEntry_no());
                }
                if (user.getFcm_token() != null && artist.isLoudsourcing_push()) {
                    NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                    firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.LOUDSOURCING_NOTIFICATION_FCM, "[" + loudSourcing.getName() + "] 아티스트님이 신청하신 크라우드 공고에서 관리자에 의해 참가 취소되었습니다.", new Gson().toJson(notificationNext));
                } else {
                    log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                }
                Notification notification = new Notification();
                notification.setUser_no(user.getUser_no());
                notification.setType(NotificationType.LOUDSOURCING_NOTIFICATION);
                notification.setContent("[" + loudSourcing.getName() + "] 아티스트님이 신청하신 크라우드 공고에서 관리자에 의해 참가 취소되었습니다.");
                notification.setReg_date(Time.TimeFormatHMS());
                NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                notification.setNext(new Gson().toJson(notificationNext));
                notificationDao.insertNotification(notification);
            } else {
                throw new BusinessException(new Exception("Not Applied Artist. Invalid Access"));
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getSelectedEntryList(int loudsourcing_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            modelAndView = new ModelAndView("loudsourcing_selected_list");
            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no) == null) {
                throw new Exception("Bad Request");
            }
            List<EntryProcessListRequest> requestList = new ArrayList<>();
            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoPreSelected(loudsourcing_no);
            for (LoudSourcingApply apply : applyList) {
                EntryProcessListRequest request = new EntryProcessListRequest();
                Artist artist = artistDao.getArtistByArtistNo(apply.getArtist_no());
                LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artist.getArtist_no(), loudsourcing_no);
                request.setArtist_no(artist.getArtist_no());
                request.setArtist_name(artist.getArtist_name());
                request.setPhone(artist.getArtist_phone());
                request.setEmail(artist.getEmail());
                request.setLoudsourcing_no(loudsourcing_no);
                request.setEntry_no(entry.getEntry_no());
                request.setUpload_date(entry.getReg_date().substring(0, entry.getReg_date().lastIndexOf(".")));
                request.setVote_number(entry.getVote_number());
                requestList.add(request);
            }
            modelAndView.addObject("artistList", requestList);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getUnSelectedEntryList(int loudsourcing_no) {
        try {
            log.info(loudsourcing_no);
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            modelAndView = new ModelAndView("loudsourcing_unselected_list");
            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no) == null) {
                throw new Exception("Bad Request");
            }
            List<EntryProcessListRequest> requestList = new ArrayList<>();
            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoUnPreSelected(loudsourcing_no);
            for (LoudSourcingApply apply : applyList) {
                EntryProcessListRequest request = new EntryProcessListRequest();
                Artist artist = artistDao.getArtistByArtistNo(apply.getArtist_no());
                LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artist.getArtist_no(), loudsourcing_no);
                request.setArtist_no(artist.getArtist_no());
                request.setArtist_name(artist.getArtist_name());
                request.setPhone(artist.getArtist_phone());
                request.setEmail(artist.getEmail());
                request.setLoudsourcing_no(loudsourcing_no);
                request.setEntry_no(entry.getEntry_no());
                request.setUpload_date(entry.getReg_date().substring(0, entry.getReg_date().lastIndexOf(".")));
                request.setVote_number(entry.getVote_number());
                requestList.add(request);
            }
            modelAndView.addObject("artistList", requestList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                e.printStackTrace();
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int sendSelectedMessage(int artist_no, int loudsourcing_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);

            if (user.getFcm_token() != null && artist.isLoudsourcing_push()) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.LOUDSOURCING_NOTIFICATION_FCM, "[" + loudSourcing.getName() + "] 아티스트님이 출품하신 작품이 투표를 통해 순위권에 들어갔습니다. 확인 부탁드립니다.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.LOUDSOURCING_NOTIFICATION);
            notification.setContent("[" + loudSourcing.getName() + "] 아티스트님이 출품하신 작품이 투표를 통해 순위권에 들어갔습니다. 확인 부탁드립니다.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int sendUnSelectedMessage(int artist_no, int loudsourcing_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            User user = userDao.selectUserByUserNo(artist.getUser_no());
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);

            if (user.getFcm_token() != null && artist.isLoudsourcing_push()) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.LOUDSOURCING_NOTIFICATION_FCM, "[" + loudSourcing.getName() + "] 아티스트님이 출품하신 작품은 투표 순위에 들어가지 못 했습니다. 순위에 들어가지 못 하셨지만 앞으로도 많은 참여 부탁드립니다.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.LOUDSOURCING_NOTIFICATION);
            notification.setContent("[" + loudSourcing.getName() + "] 아티스트님이 출품하신 작품은 투표 순위에 들어가지 못 했습니다. 순위에 들어가지 못 하셨지만 앞으로도 많은 참여 부탁드립니다.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int sendAllSelectedMessage(int loudsourcing_no) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoPreSelected(loudsourcing_no);
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            List<User> userList = new ArrayList<>();
            if (applyList != null && !applyList.isEmpty()) {
                for (LoudSourcingApply loudSourcingApply : applyList) {
                    Artist artist = artistDao.getArtistByArtistNo(loudSourcingApply.getArtist_no());
                    User user = userDao.selectUserByUserNo(artist.getUser_no());
                    userList.add(user);
                }

                if (!userList.isEmpty()) {
                    for (User user : userList) {
                        //FCM MESSAGE SEND
                        Artist artist = artistDao.getArtistByUserNo(user.getUser_no());
                        if (user.getFcm_token() != null && artist.isLoudsourcing_push()) {
                            NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                            firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.LOUDSOURCING_NOTIFICATION_FCM, "[" + loudSourcing.getName() + "] 아티스트님이 출품하신 작품이 투표를 통해 순위권에 들어갔습니다. 확인 부탁드립니다.", new Gson().toJson(notificationNext));
                        } else {
                            log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                        }
                        //NOTIFICATION SET
                        Notification notification = new Notification();
                        notification.setUser_no(user.getUser_no());
                        notification.setType(NotificationType.LOUDSOURCING_NOTIFICATION);
                        notification.setContent("[" + loudSourcing.getName() + "] 아티스트님이 출품하신 작품이 투표를 통해 순위권에 들어갔습니다. 확인 부탁드립니다.");
                        notification.setReg_date(Time.TimeFormatHMS());
                        NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                        notification.setNext(new Gson().toJson(notificationNext));
                        notificationDao.insertNotification(notification);
                    }
                }
            }

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int sendAllUnSelectedMessage(int loudsourcing_no) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoPreSelected(loudsourcing_no);
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
            List<User> userList = new ArrayList<>();
            if (applyList != null && !applyList.isEmpty()) {
                for (LoudSourcingApply loudSourcingApply : applyList) {
                    Artist artist = artistDao.getArtistByArtistNo(loudSourcingApply.getArtist_no());
                    User user = userDao.selectUserByUserNo(artist.getUser_no());
                    userList.add(user);
                }

                if (!userList.isEmpty()) {
                    for (User user : userList) {
                        //FCM MESSAGE SEND
                        Artist artist = artistDao.getArtistByUserNo(user.getUser_no());
                        if (user.getFcm_token() != null && artist.isLoudsourcing_push()) {
                            NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                            firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.LOUDSOURCING_NOTIFICATION_FCM, "[" + loudSourcing.getName() + "] 아티스트님이 출품하신 작품은 투표 순위에 들어가지 못 했습니다. 순위에 들어가지 못 하셨지만 앞으로도 많은 참여 부탁드립니다.", new Gson().toJson(notificationNext));
                        } else {
                            log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                        }
                        //NOTIFICATION SET
                        Notification notification = new Notification();
                        notification.setUser_no(user.getUser_no());
                        notification.setType(NotificationType.LOUDSOURCING_NOTIFICATION);
                        notification.setContent("[" + loudSourcing.getName() + "] 아티스트님이 출품하신 작품은 투표 순위에 들어가지 못 했습니다. 순위에 들어가지 못 하셨지만 앞으로도 많은 참여 부탁드립니다.");
                        notification.setReg_date(Time.TimeFormatHMS());
                        NotificationNext notificationNext = new NotificationNext(NotificationType.LOUDSOURCING_NOTIFICATION, null, null, 0, NotificationType.LOUDSOURCING_NOTIFICATION, 0);
                        notification.setNext(new Gson().toJson(notificationNext));
                        notificationDao.insertNotification(notification);
                    }
                }
            }

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int changeSelect(int artist_no, int loudsourcing_no) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            LoudSourcingApply apply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artist_no, loudsourcing_no);
            if (apply != null) {
                if (apply.is_pre_selected()) {
                    apply.set_pre_selected(false);
                } else {
                    apply.set_pre_selected(true);
                }
                loudSourcingApplyDao.updateApplyForJudge(apply);
            } else {
                throw new BusinessException(new Exception("Invalid Access"));
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getFinalSelectedList(int loudsourcing_no) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            modelAndView = new ModelAndView("loudsourcing_final_list");
            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no) == null) {
                throw new Exception("Bad Request");
            }
            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNoSelected(loudsourcing_no);
            List<EntryProcessListRequest> requestList = new ArrayList<>();
            for (LoudSourcingApply apply : applyList) {
                EntryProcessListRequest request = new EntryProcessListRequest();
                Artist artist = artistDao.getArtistByArtistNo(apply.getArtist_no());
                LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artist.getArtist_no(), loudsourcing_no);
                request.setArtist_no(artist.getArtist_no());
                request.setArtist_name(artist.getArtist_name());
                request.setPhone(artist.getArtist_phone());
                request.setEmail(artist.getEmail());
                request.setLoudsourcing_no(loudsourcing_no);
                request.setEntry_no(entry.getEntry_no());
                request.setUpload_date(entry.getReg_date().substring(0, entry.getReg_date().lastIndexOf(".")));
                request.setVote_number(entry.getVote_number());
                requestList.add(request);
            }
            modelAndView.addObject("artistList", requestList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getArtistLoudSourcingList(int artist_no) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            modelAndView = new ModelAndView("artist_loudsourcing_list");
            artistDao.setSession(sqlSession);
            if (artistDao.getArtistByArtistNo(artist_no) == null) {
                throw new Exception("Bad Request");
            }
            List<AdminArtistLoudSourcing> loudSourcingList = new ArrayList<>();
            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByArtistNo(artist_no);
            for (LoudSourcingApply apply : applyList) {
                AdminArtistLoudSourcing sourcing = new AdminArtistLoudSourcing();
                LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(apply.getLoudsourcing_no());
                sourcing.setArtist_no(artist_no);
                sourcing.setLoudsourcing_no(loudSourcing.getLoudsourcing_no());
                sourcing.setLoudsourcing_name(loudSourcing.getName());
                sourcing.setHost(loudSourcing.getHost());
                sourcing.setStatus(loudSourcing.getStatus());
                sourcing.setStart_date(loudSourcing.getStart_date());
                sourcing.setEnd_date(loudSourcing.getEnd_date());
                sourcing.setReg_date(apply.getReg_date().substring(0, apply.getReg_date().lastIndexOf(" ")));
                loudSourcingList.add(sourcing);
            }
            modelAndView.addObject("loudsourcingList", loudSourcingList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getNoticeList() {
        try {
            noticeDao.setSession(sqlSession);
            modelAndView = new ModelAndView("notice_list");

            List<Notice> noticeList = noticeDao.getNoticeForCDN();
            modelAndView.addObject("noticeList", noticeList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteNotice(int notice_no) {
        try {
            noticeDao.setSession(sqlSession);
            noticeDao.deleteNotice(notice_no);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getNoticeDetail(int notice_no) {
        try {
            noticeDao.setSession(sqlSession);
            modelAndView = new ModelAndView("notice_detail");

            Notice notice = noticeDao.getNoticeByNoticeNo(notice_no);
            if (notice == null) {
                throw new Exception("Bad Request");
            }

            modelAndView.addObject("notice", notice);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getNoticeMake() {
        modelAndView = new ModelAndView("notice_make");
        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public NoticeMakeResponse makeNotice(Notice notice) {
        NoticeMakeResponse response = new NoticeMakeResponse();
        try {
            noticeDao.setSession(sqlSession);
            String time = Time.TimeFormatHMS();
            notice.setReg_date(time);
            notice.setRevise_date(time);
            noticeDao.insertNotice(notice);
            response.setStatus(0);
            response.setNotice_no(notice.getNotice_no());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            return response;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int editNotice(Notice notice) {
        try {
            noticeDao.setSession(sqlSession);
            String time = Time.TimeFormatHMS();
            notice.setRevise_date(time);
            noticeDao.updateNotice(notice);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getFAQList() {
        try {
            faqDao.setSession(sqlSession);
            modelAndView = new ModelAndView("faq_list");
            List<FAQ> faqList = faqDao.getFAQForCDN();
            modelAndView.addObject("faqList", faqList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getFAQDetail(int faq_no) {
        try {
            faqDao.setSession(sqlSession);
            modelAndView = new ModelAndView("faq_detail");
            FAQ faq = faqDao.getFAQByFAQNo(faq_no);
            if (faq == null) {
                throw new Exception("Bad Request");
            }
            modelAndView.addObject("faq", faq);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteFAQ(int faq_no) {
        try {
            faqDao.setSession(sqlSession);
            faqDao.deleteFAQ(faq_no);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int editFAQ(FAQ faq) {
        try {
            faqDao.setSession(sqlSession);
            faqDao.updateFAQ(faq);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int makeFAQ(FAQ faq) {
        try {
            faqDao.setSession(sqlSession);
            String time = Time.TimeFormatHMS();
            faq.setReg_date(time);
            faqDao.insertFAQ(faq);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getFAQMake() {
        modelAndView = new ModelAndView("faq_make");
        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getBannerList() {
        try {
            bannerAdDao.setSession(sqlSession);
            modelAndView = new ModelAndView("banner_list");
            List<BannerAd> activeList = bannerAdDao.getActiveBannerList();
            List<BannerAd> disableList = bannerAdDao.getDisableBannerList();
            List<BannerAd> bannerList = new ArrayList<>();
            bannerList.addAll(activeList);
            bannerList.addAll(disableList);

            modelAndView.addObject("bannerList", bannerList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getBannerMake() {
        modelAndView = new ModelAndView("banner_make");
        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getBannerDetail(int banner_ad_no) {
        try {
            bannerAdDao.setSession(sqlSession);
            modelAndView = new ModelAndView("banner_detail");
            BannerAd bannerAd = bannerAdDao.getBannerAdByBannerAdNo(banner_ad_no);
            if (bannerAd == null) {
                throw new Exception("Bad Request");
            }
            modelAndView.addObject("banner", bannerAd);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteBanner(int banner_ad_no) {
        try {
            bannerAdDao.setSession(sqlSession);
            int bannerSize = bannerAdDao.getBannerForCDN().size();
            if (bannerSize <= 1) {
                return 2;
            } else {
                bannerAdDao.deleteBanner(banner_ad_no);
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int activeBanner(int banner_ad_no) {
        try {
            bannerAdDao.setSession(sqlSession);
            int active = bannerAdDao.getActiveBannerList().size();
            if (active == 4) { // 배너광고는 최대 4개까지 활성화 가능
                return 2;
            } else {
                String time = Time.TimeFormatHMS();
                BannerAd bannerAd = bannerAdDao.getBannerAdByBannerAdNo(banner_ad_no);
                bannerAd.setStatus(true);
                bannerAd.setRevise_date(time);
                bannerAdDao.updateBanner(bannerAd);
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int disableBanner(int banner_ad_no) {
        try {
            bannerAdDao.setSession(sqlSession);
            int active = bannerAdDao.getActiveBannerList().size();
            if (active <= 1) { // 배너광고는 최소 하나 이상 활성화 되어있어야 하기 때문
                return 2;
            } else {
                String time = Time.TimeFormatHMS();
                BannerAd bannerAd = bannerAdDao.getBannerAdByBannerAdNo(banner_ad_no);
                bannerAd.setStatus(false);
                bannerAd.setRevise_date(time);
                bannerAdDao.updateBanner(bannerAd);
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int makeBanner(BannerAd bannerAd) {
        try {
            bannerAdDao.setSession(sqlSession);
            bannerAdDao.insertBanner(bannerAd);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int editBanner(BannerAd bannerAd) {
        try {
            bannerAdDao.setSession(sqlSession);
            BannerAd originalBannerAd = bannerAdDao.getBannerAdByBannerAdNo(bannerAd.getBanner_ad_no());
            bannerAd.setStatus(originalBannerAd.isStatus());
            String time = Time.TimeFormatHMS();
            bannerAd.setRevise_date(time);
            bannerAdDao.updateBanner(bannerAd);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getInquiryList(String type) {
        try {
            inquiryDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            switch (type) {
                case "loudsourcing":
                    modelAndView = new ModelAndView("inquiry_list");
                    List<Inquiry> l_notAnsweredList = inquiryDao.getInquiryListByAnswerStatusAndType(false, "loudsourcing");
                    List<Inquiry> l_answeredList = inquiryDao.getInquiryListByAnswerStatusAndType(true, "loudsourcing");
                    List<Inquiry> l_inquiryList = new ArrayList<>();
                    l_inquiryList.addAll(l_notAnsweredList);
                    l_inquiryList.addAll(l_answeredList);
                    for (Inquiry inquiry : l_inquiryList) {
                        User user = userDao.selectUserByUserNo(inquiry.getUser_no());
                        Artist artist = artistDao.getArtistByUserNo(inquiry.getUser_no());
                        if (artist != null) {
                            inquiry.setUser_name(artist.getArtist_name());
                        } else {
                            inquiry.setUser_name(user.getName());
                        }
                        inquiry.setReg_date(inquiry.getReg_date().substring(0, inquiry.getReg_date().lastIndexOf(".")));
                    }
                    modelAndView.addObject("inquiryList", l_inquiryList);
                    break;
                case "report":
                    modelAndView = new ModelAndView("inquiry_report_list");
                    List<Inquiry> r_notAnsweredList = inquiryDao.getInquiryListByAnswerStatusAndType(false, "report");
                    List<Inquiry> r_answeredList = inquiryDao.getInquiryListByAnswerStatusAndType(true, "report");
                    List<Inquiry> r_inquiryList = new ArrayList<>();
                    r_inquiryList.addAll(r_notAnsweredList);
                    r_inquiryList.addAll(r_answeredList);
                    for (Inquiry inquiry : r_inquiryList) {
                        User user = userDao.selectUserByUserNo(inquiry.getUser_no());
                        Artist artist = artistDao.getArtistByUserNo(inquiry.getUser_no());
                        if (artist != null) {
                            inquiry.setUser_name(artist.getArtist_name());
                        } else {
                            inquiry.setUser_name(user.getName());
                        }
                        User reported_user = userDao.selectUserByUserNo(inquiry.getReported_user_no());
                        Artist reported_artist = artistDao.getArtistByUserNo(inquiry.getReported_user_no());
                        if (reported_artist != null) {
                            inquiry.setReported_user_name(reported_artist.getArtist_name());
                        } else if (reported_user != null) {
                            inquiry.setReported_user_name(reported_user.getName());
                        }
                        inquiry.setReg_date(inquiry.getReg_date().substring(0, inquiry.getReg_date().lastIndexOf(".")));
                    }
                    modelAndView.addObject("inquiryList", r_inquiryList);
                    break;
                case "normal":
                    modelAndView = new ModelAndView("inquiry_list");
                    List<Inquiry> notAnsweredList = inquiryDao.getInquiryListByAnswerStatusAndType(false, "normal");
                    List<Inquiry> answeredList = inquiryDao.getInquiryListByAnswerStatusAndType(true, "normal");
                    List<Inquiry> inquiryList = new ArrayList<>();
                    inquiryList.addAll(notAnsweredList);
                    inquiryList.addAll(answeredList);
                    for (Inquiry inquiry : inquiryList) {
                        User user = userDao.selectUserByUserNo(inquiry.getUser_no());
                        Artist artist = artistDao.getArtistByUserNo(inquiry.getUser_no());
                        if (artist != null) {
                            inquiry.setUser_name(artist.getArtist_name());
                        } else {
                            inquiry.setUser_name(user.getName());
                        }
                        inquiry.setReg_date(inquiry.getReg_date().substring(0, inquiry.getReg_date().lastIndexOf(".")));
                    }
                    modelAndView.addObject("inquiryList", inquiryList);
                    break;
                default:
                    throw new Exception("Bad Request");
            }
            modelAndView.addObject("type", type);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getInquiryDetail(int inquiry_no) {
        try {
            inquiryDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            modelAndView = new ModelAndView("inquiry_detail");
            Inquiry inquiry = inquiryDao.getInquiryByInquiryNo(inquiry_no);
            if (inquiry == null)
                throw new Exception("Bad Request");
            switch (inquiry.getType()) {
                case "loudsourcing":
                    User l_user = userDao.selectUserByUserNo(inquiry.getUser_no());
                    Artist l_artist = artistDao.getArtistByUserNo(inquiry.getUser_no());
                    if (l_artist != null) {
                        inquiry.setUser_name(l_artist.getArtist_name());
                    } else {
                        inquiry.setUser_name(l_user.getName());
                    }
                    inquiry.setReg_date(inquiry.getReg_date().substring(0, inquiry.getReg_date().lastIndexOf(".")));
                    String jsonString = inquiry.getFile();
                    Gson gson = new Gson();
                    FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                    ArrayList<Upload> uploads = new ArrayList<>();
                    for (FileJson json : fileJson) {
                        uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
                    }
                    modelAndView.addObject("files", uploads);
                    break;
                case "report":
                    User r_user = userDao.selectUserByUserNo(inquiry.getUser_no());
                    Artist r_artist = artistDao.getArtistByUserNo(inquiry.getUser_no());
                    if (r_artist != null) {
                        inquiry.setUser_name(r_artist.getArtist_name());
                    } else {
                        inquiry.setUser_name(r_user.getName());
                    }
                    User reported_user = userDao.selectUserByUserNo(inquiry.getReported_user_no());
                    Artist reported_artist = artistDao.getArtistByUserNo(inquiry.getReported_user_no());
                    if (reported_artist != null) {
                        inquiry.setReported_user_name(reported_artist.getArtist_name());
                    } else if (reported_user != null) {
                        inquiry.setReported_user_name(reported_user.getName());
                    }
                    inquiry.setReg_date(inquiry.getReg_date().substring(0, inquiry.getReg_date().lastIndexOf(".")));
                    break;
                case "normal":
                    User user = userDao.selectUserByUserNo(inquiry.getUser_no());
                    Artist artist = artistDao.getArtistByUserNo(inquiry.getUser_no());
                    if (artist != null) {
                        inquiry.setUser_name(artist.getArtist_name());
                    } else {
                        inquiry.setUser_name(user.getName());
                    }
                    inquiry.setReg_date(inquiry.getReg_date().substring(0, inquiry.getReg_date().lastIndexOf(".")));
                    break;
            }
            if (inquiry.is_answered()) {
                inquiry.setAnswer_date(inquiry.getAnswer_date().substring(0, inquiry.getAnswer_date().lastIndexOf(".")));
            }
            modelAndView.addObject("inquiry", inquiry);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteInquiry(int inquiry_no) {
        try {
            inquiryDao.setSession(sqlSession);
            inquiryDao.deleteInquiry(inquiry_no);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int answerInquiry(Inquiry inquiry) {
        try {
            inquiryDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
            String time = Time.TimeFormatHMS();
            inquiry.setAnswer_date(time);
            inquiry.set_answered(true);
            User user = userDao.selectUserByUserNo(inquiry.getUser_no());
            Artist artist = artistDao.getArtistByUserNo(inquiry.getUser_no());
            if (artist != null)
                inquiry.setUser_name(artist.getArtist_name());
            else
                inquiry.setUser_name(user.getName());

            if (inquiry.getType().equals("loudsourcing")) {
                inquiry.setAnswer_content("크라우드 문의 보내주신 \"" + inquiry.getUser_name() + "\"님께 감사드립니다.\n" +
                        "보내주신 문의 내용과 첨부파일을 확인했습니다.\n" +
                        "며칠 안으로 입력하신 연락처 혹은 이메일로 연락드리겠습니다.\n" +
                        "앞으로도 많은 문의 보내주세요.");
            }
            inquiryDao.answerInquiry(inquiry);
            // 문의 답변 완료 알림

            if (user.getFcm_token() != null) {
                NotificationNext notificationNext = new NotificationNext(NotificationType.INQUIRY, null, null, inquiry.getInquiry_no(), NotificationType.INQUIRY, 0);
                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.INQUIRY_FCM, "회원님이 남긴 문의에 답변이 등록되었습니다.", new Gson().toJson(notificationNext));
            } else {
                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
            }
            //NOTIFICATION SET
            Notification notification = new Notification();
            notification.setUser_no(user.getUser_no());
            notification.setType(NotificationType.INQUIRY);
            notification.setContent("회원님이 남긴 문의에 답변이 등록되었습니다.");
            notification.setReg_date(Time.TimeFormatHMS());
            NotificationNext notificationNext = new NotificationNext(NotificationType.INQUIRY, null, null, inquiry.getInquiry_no(), NotificationType.INQUIRY, 0);
            notification.setNext(new Gson().toJson(notificationNext));
            notificationDao.insertNotification(notification);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getPenalty(int user_no) {
        try {
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            modelAndView = new ModelAndView("penalty");
            User user = userDao.selectUserByUserNo(user_no);
            if (user == null)
                throw new Exception("Bad Request");
            Artist artist = artistDao.getArtistByUserNo(user_no);
            if (artist != null) {
                modelAndView.addObject("artist_name", artist.getArtist_name());
            }
            modelAndView.addObject("user", user);

            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int penaltyUser(Penalty penalty) {
        try {
            penaltyDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            String now = Time.TimeFormatDay();
            Date nowDate = Time.StringToDateFormat(now);
            Date penalty_start_date = Time.StringToDateFormat(penalty.getPenalty_start_date());
            if (penalty.getPenalty_end_date().equals("영구 정지")) {
                penalty.setPenalty_end_date("2099-12-31");
            } else {
                penalty.setPenalty_end_date(Time.DateMinusOneDay(penalty.getPenalty_end_date()));
            }
            penalty.setReg_date(Time.TimeFormatDay());
            User user = userDao.selectUserByUserNo(penalty.getUser_no());
            Artist artist = artistDao.getArtistByUserNo(penalty.getUser_no());
            if (artist != null) {
                penalty.setArtist_no(artist.getArtist_no());
            }
            penaltyDao.insertPenalty(penalty);
            if (nowDate.compareTo(penalty_start_date) == 0) {
                user.set_user_private(true);
                userDao.updateUser(user);
                if (artist != null) {
                    artist.setArtist_private(true);
                    artistDao.updateArtist(artist);
                    List<Board> boardList = boardDao.getBoardListByArtistNo(artist.getArtist_no());
                    for (Board board : boardList) {
                        board.setBoard_private(true);
                        boardDao.updateBoardByPenalty(board);
                    }
                    List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByArtistNo(artist.getArtist_no());
                    List<LoudSourcing> loudSourcingList = new ArrayList<>();
                    for (LoudSourcingApply apply : applyList) {
                        LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(apply.getLoudsourcing_no());
                        loudSourcingList.add(loudSourcing);
                    }

                    for (LoudSourcing loudSourcing : loudSourcingList) {
                        int loudsourcing_no = loudSourcing.getLoudsourcing_no();
                        int artist_no = artist.getArtist_no();
                        switch (loudSourcing.getStatus()) {
                            case "recruitment":
                                loudSourcingApplyDao.deleteLoudSourcingApply(artist_no, loudsourcing_no);
                                break;
                            case "process":
                                loudSourcingApplyDao.deleteLoudSourcingApply(artist_no, loudsourcing_no);
                                LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artist_no, loudsourcing_no);
                                if (entry != null) {
                                    loudSourcingEntryDao.deleteEntry(entry.getEntry_no());
                                }
                                break;
                            case "judge":
                                LoudSourcingApply apply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artist_no, loudsourcing_no);
                                apply.set_pre_selected(false);
                                loudSourcingApplyDao.updateApplyForJudge(apply);
                                break;
                        }
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getSponList(String status) {
        try {
            sponDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            modelAndView = new ModelAndView("spon_list");
            List<Spon> sponList = new ArrayList<>();
            switch (status) {
                case "purchase":
                    //TODO 결제 오류 내역
                    sponList.addAll(sponDao.getSponListStatusPurchase());
                    break;
                case "apply":
                    //TODO 결제 OK, 미승인 내역
                    sponList.addAll(sponDao.getSponListStatusApply());
                    break;
                case "send":
                    //TODO 결제 OK, 승인 OK, 미입금 내역
                    sponList.addAll(sponDao.getSponListStatusSend());
                    break;
                case "complete":
                    //TODO 모두 완료 내역
                    sponList.addAll(sponDao.getSponListStatusComplete());
                    break;
                default:
                    throw new Exception("Bad Request");
            }
            for (Spon spon : sponList) {
                if (spon.getUser_no() != 0) {
                    User user = userDao.selectUserByUserNo(spon.getUser_no());
                    Artist sponArtist = artistDao.getArtistByUserNo(spon.getUser_no());
                    if (sponArtist != null) {
                        spon.setUser_name(sponArtist.getArtist_name());
                    } else {
                        spon.setUser_name(user.getName());
                    }
                } else {
                    spon.setUser_name("탈퇴한 유저");
                }
                if (spon.getArtist_no() != 0) {
                    Artist artist = artistDao.getArtistByArtistNo(spon.getArtist_no());
                    spon.setArtist_name(artist.getArtist_name());
                } else {
                    spon.setArtist_name("탈퇴한 아티스트");
                }
            }
            modelAndView.addObject("sponList", sponList);
            modelAndView.addObject("status", status);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getSponDetail(int spon_no, String prevStatus) {
        try {
            sponDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            modelAndView = new ModelAndView("spon_detail");
            Spon spon = sponDao.getSponBySponNo(spon_no);
            if (spon == null) {
                throw new Exception("Bad Request");
            }
            spon.setPrice_send(spon.getPrice());
            if (spon.getUser_no() != 0) {
                User user = userDao.selectUserByUserNo(spon.getUser_no());
                Artist sponArtist = artistDao.getArtistByUserNo(spon.getUser_no());
                if (sponArtist != null) {
                    spon.setUser_name(sponArtist.getArtist_name());
                    user.setEmail(sponArtist.getEmail());
                } else {
                    spon.setUser_name(user.getName());
                }
                modelAndView.addObject("user", user);
            } else {
                spon.setUser_name("탈퇴한 유저");
            }
            if (spon.getArtist_no() != 0) {
                Artist artist = artistDao.getArtistByArtistNo(spon.getArtist_no());
                spon.setArtist_name(artist.getArtist_name());
                modelAndView.addObject("artist", artist);
            } else {
                spon.setArtist_name("탈퇴한 아티스트");
            }
            if(spon.getPlatform().equals("IOS")){
                String applePrice = calculateAppleVat(spon.getPrice());
                modelAndView.addObject("applePrice", applePrice);
            }
            String currencyRate = currencyService.getCurrencyRate(spon.getSpon_date(), spon.getCurrency());
            int resultPrice = Long.valueOf(currencyService.calculateCurrency(spon.getPrice(), spon.getCurrency(), spon.getSpon_date())).intValue();
            if(resultPrice == 0 || currencyRate.equals("")){
                throw new Exception("Currency Error");
            }
            modelAndView.addObject("currencyRate", currencyRate);
            modelAndView.addObject("resultPrice", resultPrice);
            int tax = resultPrice * 10 / 100;
            int sendPrice = resultPrice - tax;
            modelAndView.addObject("sendPrice", sendPrice);
            modelAndView.addObject("tax", tax);
            modelAndView.addObject("spon", spon);
            modelAndView.addObject("prevStatus", prevStatus);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    private String calculateAppleVat(String money){
        String[] part = money.split("(?<=\\D)(?=\\d)");
        String clearMoneyValue = money.replaceAll("[^0-9.]", "");
        double vatMoney = Double.parseDouble(clearMoneyValue) * 0.85;
        long vatMoneyRound = Math.round(vatMoney);
        return part[0] + vatMoneyRound;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int applySpon(int spon_no) {
        try {
            sponDao.setSession(sqlSession);
            Spon spon = sponDao.getSponBySponNo(spon_no);
            if(spon.getVerify_status() != 0){
                return 2;
            }
            spon.setApply_date(Time.TimeFormatDay());
            sponDao.updateSponByApply(spon);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int sendSpon(int spon_no) {
        try {
            sponDao.setSession(sqlSession);
            Spon spon = sponDao.getSponBySponNo(spon_no);
            if (!spon.isStatus()) {
                return 2;
            }
            spon.setSend_date(Time.TimeFormatDay());
            sponDao.updateSponBySend(spon);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteSpon(int spon_no) {
        try {
            sponDao.setSession(sqlSession);
            sponDao.deleteSpon(spon_no);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getHashTagEdit() {
        try {
            searchDao.setSession(sqlSession);
            modelAndView = new ModelAndView("search_hashtag");
            List<Search> searchList = searchDao.getKeywords();
            List<String> keywordList = new ArrayList<>();
            for (Search search : searchList) {
                keywordList.add(search.getWord());
            }
            String hashtagList = new Gson().toJson(keywordList);
            modelAndView.addObject("hashtagList", hashtagList);
            return modelAndView;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().equals("Bad Request")) {
                throw new BadRequestException(e);
            } else {
                throw new AdminException(e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int editHashTag(String hashtag) {
        try {
            searchDao.setSession(sqlSession);
            adminDao.setSession(sqlSession);
            Admin admin = adminDao.loginAdmin("root", "root");
            List<Search> originalSearchList = searchDao.getKeywords();
            List<String> originalKeywordList = new ArrayList<>();
            for (Search search : originalSearchList) {
                originalKeywordList.add(search.getWord());
            }
            String replaced = hashtag.replace("[", "").replace("]", "").replace("\"", "");
            System.out.println(replaced);
            List<String> keywordList = new ArrayList<>(Arrays.asList(replaced.split(",")));
            System.out.println(keywordList);
            for (String originalWord : originalKeywordList) {
                if (!keywordList.contains(originalWord)) {
                    log.info("KeyWord Deleted : " + originalWord);
                    searchDao.deleteByWord(originalWord);
                }
            }
            for (String keyWord : keywordList) {
                if (!originalKeywordList.contains(keyWord)) {
                    log.info("New Keyword Inserted : " + keyWord);
                    Search search = new Search();
                    search.setAdmin_no(admin.getAdmin_no());
                    search.setReg_date(Time.TimeFormatHMS());
                    search.setWord(keyWord);
                    searchDao.insertKeyword(search);
                }
            }

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
