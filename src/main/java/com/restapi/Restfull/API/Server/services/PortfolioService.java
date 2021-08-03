package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.utility.FirebaseMessagingSnippets;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class PortfolioService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private PortfolioCommentDao portfolioCommentDao;

    @Autowired
    private PortfolioLikeDao portfolioLikeDao;

    @Autowired
    private UserDao userdao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    private PenaltyDao penaltyDao;

    @Autowired
    private SubscribeDao subscribeDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetPortfolio(int user_no, int portfolio_no, int last_index) {
        try {
            Message message = new Message();
            /** required Data
             * 1. Portfolio - DONE
             * 2. Portfolio Comment - DONE
             * 3. Portfolio Like - DONE
             * **/
            // Setting SQL SESSION
            portfolioDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            portfolioLikeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);

            if (portfolioDao.getPortfolioByPortfolioNo(portfolio_no) == null) {
                log.info("Portfolio_no : " + portfolio_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }


            if (last_index > -1) {
                if(last_index == 0){
                    List<PortfolioComment> commentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolio_no);
                    Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                    for (PortfolioComment portfolioComment : commentList) {
                        int commentWriter = portfolioComment.getUser_no();
                        if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(commentWriter, portfolio.getArtist_no()) != null)
                            portfolioComment.set_fankoked(true);
                        else
                            portfolioComment.set_fankoked(false);
                    }

                    log.info(commentList);
                    message.put("comment_number", portfolio.getComment_number());
                    message.put("portfolio_comment", commentList);
                    if(commentList.size() > 0){
                        message.put("last_index", commentList.get(commentList.size() - 1).getComment_no());
                    }
                } else {
                    PortfolioComment portfolioComment1 = portfolioCommentDao.getCommentByCommentNo(last_index);
                    if(portfolioComment1 == null){
                        return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                    }
                    List<PortfolioComment> commentList = portfolioCommentDao.getCommentListByPortfolioNoRefresh(portfolio_no, portfolioComment1);
                    Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                    for (PortfolioComment portfolioComment : commentList) {
                        int commentWriter = portfolioComment.getUser_no();
                        if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(commentWriter, portfolio.getArtist_no()) != null)
                            portfolioComment.set_fankoked(true);
                        else
                            portfolioComment.set_fankoked(false);
                    }
                    log.info(commentList);
                    message.put("comment_number", portfolio.getComment_number());
                    message.put("portfolio_comment", commentList);
                    if(commentList.size() > 0){
                        message.put("last_index", commentList.get(commentList.size() - 1).getComment_no());
                    }
                }
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_COMMENT_SUCCESS, message.getHashMap("GetPortfolioComment()")), HttpStatus.OK);
            } else {
                // GET DATA FROM DB
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                portfolio.setVisit_number(portfolio.getVisit_number() + 1); // 조회수 증가
                boolean portfolioLike = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;

                Artist artist = artistDao.getArtistByArtistNo(portfolio.getArtist_no());

                if (portfolio.getType().equals(PortfolioType.FILE)) {
                    String jsonString = portfolio.getFile();
                    Gson gson = new Gson();
                    FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                    ArrayList<Upload> uploads = new ArrayList<>();
                    for (FileJson json : fileJson) {
                        uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
                    }
                    message.put("files", uploads);
                } else if (portfolio.getType().equals(PortfolioType.IMAGE)) {
                    if (portfolio.getFile() != null) {
                        String images = portfolio.getFile().replace("[", "");
                        images = images.replace("]", "");
                        ArrayList<String> filelist = new ArrayList<>(Arrays.asList(images.split(", ")));
                        portfolio.setImage_list(filelist);
                        log.info(filelist);
                    }
                }


                // RESPONSE MESSAGE SET

                portfolio.setUser_no(artist.getUser_no());
                message.put("portfolio", portfolio);
                message.put("portfolio_like", portfolioLike);
                portfolioDao.updatePortfolioByVisit(portfolio);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetPortfolio()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Portfolio> getPortfolioListByArtistNo(int artist_no) {
        portfolioDao.setSession(sqlSession);
        return portfolioDao.getPortfolioListByArtistNo(artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getPortfolioListByTypeVOD(String type, String sort, int last_index) {
        try {
            portfolioDao.setSession(sqlSession);
            Message message = new Message();
            List<Portfolio> vodList;
            if (last_index == 0) {
                vodList = portfolioDao.getPortfolioListByTypeVODSort(type, sort);
            } else {
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(last_index);
                if (portfolio == null) {
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                }
                vodList = portfolioDao.getPortfolioListByTypeVODSortRefresh(type, sort, portfolio);
            }

            message.put("vod", vodList);
            message.put("sort", sort);
            message.put("last_index", vodList.get(vodList.size() - 1).getPortfolio_no());

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.VOD_LIST_CALL_SUCCESS, message.getHashMap("GetVODList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Portfolio getPortfolioByPortfolioNo(int portfolio_no) {
        portfolioDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        return portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getPortfolioForEdit(int portfolio_no, int artist_no) {
        try {
            portfolioDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();

            if (portfolioDao.getPortfolioByPortfolioNo(portfolio_no) == null) {
                log.info("Portfolio_no : " + portfolio_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
            List<Penalty> penaltyList = penaltyDao.getPenaltyListByArtistNo(artist_no);
            if (penaltyList != null && !penaltyList.isEmpty()) {
                Penalty penalty = penaltyList.get(0);
                String now = Time.TimeFormatHMS();
                Date nowDate = Time.StringToDateFormat(now);
                if (nowDate.before(Time.StringToDateFormat(penalty.getPenalty_end_date())) && nowDate.after(Time.StringToDateFormat(penalty.getPenalty_start_date()))) {
                    message.put("penalty", penalty);
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAN_ARTIST, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetPortfolioForEdit()")), HttpStatus.OK);
                }
            }

            if (portfolio.getType().equals(PortfolioType.FILE)) {
                String jsonString = portfolio.getFile();
                Gson gson = new Gson();
                FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                ArrayList<Upload> uploads = new ArrayList<>();
                for (FileJson json : fileJson) {
                    uploads.add(new Upload(json.getName(), json.getUrl()));
                }
                message.put("files", uploads);
            } else if (portfolio.getType().equals(PortfolioType.IMAGE)) {
                if (portfolio.getFile() != null) {
                    String images = portfolio.getFile().replace("[", "");
                    images = images.replace("]", "");
                    ArrayList<String> filelist = new ArrayList<>(Arrays.asList(images.split(", ")));
                    portfolio.setImage_list(filelist);
                    log.info(filelist);
                }
            }
            portfolio.setUser_no(artistDao.getArtistByArtistNo(portfolio.getArtist_no()).getUser_no());

            message.put("portfolio", portfolio);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetPortfolioForEdit()")), HttpStatus.OK);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertPortfolio(Portfolio portfolio) {
        portfolioDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        notificationDao.setSession(sqlSession);
        subscribeDao.setSession(sqlSession);
        Artist artist = artistDao.getArtistByArtistNo(portfolio.getArtist_no());
        portfolio.setFan_number(artist.getFan_number());
        portfolioDao.insertPortfolio(portfolio);
        artist.setRecent_act_date(Time.TimeFormatHMS());
        artistDao.updateArtist(artist);

        // 해당 아티스트를 팬콕한 유저들에게 등록 알림
        FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
        List<Subscribe> subscribeList = subscribeDao.getSubscribeListByArtistNo(artist.getArtist_no());
        List<User> userList = new ArrayList<>();
        if (subscribeList != null && !subscribeList.isEmpty()) {
            for (Subscribe subscribe : subscribeList) {
                User user = userDao.selectUserByUserNo(subscribe.getUser_no());
                userList.add(user);
            }

            if (!userList.isEmpty()) {
                for (User user : userList) {
                    //FCM MESSAGE SEND
                    if (user.getFcm_token() != null && user.isFankok_push()) {
                        NotificationNext notificationNext = new NotificationNext(NotificationType.CONTENT_UPLOADED, NotificationType.CONTENT_TYPE_PORTFOLIO, portfolio.getType(), portfolio.getPortfolio_no(), null, portfolio.getArtist_no());
                        firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.CONTENT_UPLOADED_FCM, "회원님이 팬콕한 " + artist.getArtist_name() + "님이 새로운 포트폴리오를 업로드 하였습니다.", new Gson().toJson(notificationNext));
                    } else {
                        if (user.getFcm_token() == null)
                            log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                        else if (user.isFankok_push())
                            log.info("this user's push alarm off");
                    }
                    //NOTIFICATION SET
                    Notification notification = new Notification();
                    notification.setUser_no(user.getUser_no());
                    notification.setType(NotificationType.CONTENT_UPLOADED);
                    notification.setContent("회원님이 팬콕한 " + artist.getArtist_name() + "님이 새로운 포트폴리오를 업로드 하였습니다.");
                    notification.setReg_date(Time.TimeFormatHMS());
                    NotificationNext notificationNext = new NotificationNext(NotificationType.CONTENT_UPLOADED, NotificationType.CONTENT_TYPE_PORTFOLIO, portfolio.getType(), portfolio.getPortfolio_no(), null, portfolio.getArtist_no());
                    notification.setNext(new Gson().toJson(notificationNext));
                    ;
                    notificationDao.insertNotification(notification);
                }
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertFiles(Portfolio portfolio) {
        portfolioDao.setSession(sqlSession);
        portfolioDao.insertFiles(portfolio);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updatePortfolio(Portfolio portfolio) {
        try {
            portfolioDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();

            Portfolio origin_portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio.getPortfolio_no());

            if (portfolioDao.getPortfolioByPortfolioNo(portfolio.getPortfolio_no()) == null) {
                log.info("Portfolio_no : " + portfolio.getPortfolio_no());
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            // Update Method
            String d = Time.TimeFormatHMS();
            portfolio.setRevise_date(d);
            portfolio.setArtist_name(origin_portfolio.getArtist_name());
            portfolio.setArtist_profile_img(origin_portfolio.getArtist_profile_img());
            portfolioDao.updatePortfolio(portfolio);

            Portfolio portfolio1 = portfolioDao.getPortfolioByPortfolioNo(portfolio.getPortfolio_no());

            Artist artist = artistDao.getArtistByArtistNo(portfolio1.getArtist_no());
            artist.setRecent_act_date(Time.TimeFormatHMS());
            artistDao.updateArtist(artist);

            portfolio1.setUser_no(artist.getUser_no());


            message.put("portfolio", portfolio1);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.EDIT_PORTFOLIO_SUCCESS, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deletePortfolio(int portfolio_no) {
        try {
            portfolioDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            Message message = new Message();

            if (portfolioDao.getPortfolioByPortfolioNo(portfolio_no) == null) {
                log.info("Portfolio_no : " + portfolio_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            Artist artist = artistDao.getArtistByArtistNo(portfolioDao.getPortfolioByPortfolioNo(portfolio_no).getArtist_no());

            List<Penalty> penaltyList = penaltyDao.getPenaltyListByArtistNo(artist.getArtist_no());
            if (penaltyList != null && !penaltyList.isEmpty()) {
                Penalty penalty = penaltyList.get(0);
                String now = Time.TimeFormatHMS();
                Date nowDate = Time.StringToDateFormat(now);
                if (nowDate.before(Time.StringToDateFormat(penalty.getPenalty_end_date())) && nowDate.after(Time.StringToDateFormat(penalty.getPenalty_start_date()))) {
                    message.put("penalty", penalty);
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAN_ARTIST, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("DeletePortfolio()")), HttpStatus.OK);
                }
            }

            artist.setRecent_act_date(Time.TimeFormatHMS());
            artistDao.updateArtist(artist);
            portfolioDao.deletePortfolio(portfolio_no);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.DELETE_PORTFOLIO_SUCCESS, message.getHashMap("DeletePortfolio()")), HttpStatus.OK);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }

    }

    /**
     * COMMENT, LIKE 추가 시 숫자 증가, DELETE 시 number = -1, 추가 시 number = 1
     **/
    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 500))
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updatePortfolioByComment(PortfolioComment portfolioComment, String method) {
        try {
            portfolioCommentDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            userdao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            Message message = new Message();


            if (portfolioDao.getPortfolioByPortfolioNo(portfolioComment.getPortfolio_no()) == null) {
                log.info("Portfolio_no : " + portfolioComment.getPortfolio_no());
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            if (method.equals("UPDATE")) {
                // DB SET
                portfolioComment.setComment_private(false);
                User user = userdao.selectUserByUserNo(portfolioComment.getUser_no());
                Artist artist = artistDao.getArtistByUserNo(portfolioComment.getUser_no());
                if (artist != null) {
                    portfolioComment.setCommenter_name(artist.getArtist_name());
                    portfolioComment.setProfile_img(artist.getArtist_profile_img());
                } else {
                    portfolioComment.setCommenter_name(user.getName());
                    portfolioComment.setProfile_img(user.getProfile_img());
                }
                String d = Time.TimeFormatHMS();
                portfolioComment.setReg_date(d);
                portfolioCommentDao.insertComment(portfolioComment);

                // Portfolio SET
                portfolioDao.updatePortfolioByComment(portfolioComment.getPortfolio_no(), portfolioCommentDao.getCommentNumberByPortfolioNo(portfolioComment.getPortfolio_no()).size());

                // Response Message SET
                List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolioComment.getPortfolio_no());
                List<PortfolioComment> resCommentList = new ArrayList<>();
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolioComment.getPortfolio_no());
                for (PortfolioComment portfolioComment1 : portfolioCommentList) {
                    int commentWriter = portfolioComment1.getUser_no();
                    if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(commentWriter, portfolio.getArtist_no()) != null)
                        portfolioComment1.set_fankoked(true);
                    else
                        portfolioComment1.set_fankoked(false);

                    resCommentList.add(portfolioComment1);
                }
                message.put("comment_number", portfolio.getComment_number());
                message.put("portfolio_comment", resCommentList);

                Artist portfolio_artist = artistDao.getArtistByArtistNo(portfolio.getArtist_no());
                User portfolio_artist_user = userDao.selectUserByUserNo(portfolio_artist.getUser_no());

                //FCM MESSAGE SEND
                FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

                String comment_short;
                if (portfolioComment.getContent().length() < 20) {
                    comment_short = portfolioComment.getContent();
                } else {
                    comment_short = portfolioComment.getContent().substring(0, 20);
                }

                if (portfolioComment.getUser_no() == portfolio_artist_user.getUser_no()) {
                    portfolio_artist.setRecent_act_date(Time.TimeFormatHMS());
                    artistDao.updateArtist(portfolio_artist);
                    List<PortfolioComment> portfolioCommentList1 = portfolioCommentDao.getCommentNumberByPortfolioNo(portfolio.getPortfolio_no());
                    List<User> userList = new ArrayList<>();
                    if (portfolioCommentList1 != null && !portfolioCommentList1.isEmpty()) {
                        for (PortfolioComment portfolioComment1 : portfolioCommentList1) {
                            if (portfolioComment1.getUser_no() != portfolio_artist_user.getUser_no()) {
                                User user1 = userDao.selectUserByUserNo(portfolioComment1.getUser_no());
                                userList.add(user1);
                            }
                        }

                        if (!userList.isEmpty()) {
                            for (User comment_user : userList) {
                                //FCM MESSAGE SEND
                                if (comment_user.getFcm_token() != null && comment_user.isComment_push()) {
                                    NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_ARTIST, NotificationType.CONTENT_TYPE_PORTFOLIO, portfolio.getType(), portfolio.getPortfolio_no(), null, portfolio_artist.getArtist_no());
                                    firebaseMessagingSnippets.push(comment_user.getFcm_token(), NotificationType.COMMENT_ARTIST_FCM, "댓글을 작성한 '" + portfolio.getTitle() + "'에 작성한 아티스트가 댓글을 남겼습니다. '" + comment_short + "...'", new Gson().toJson(notificationNext));
                                } else {
                                    if (comment_user.getFcm_token() == null)
                                        log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                                    else if (comment_user.isFankok_push())
                                        log.info("this user's push alarm off");
                                }
                                //NOTIFICATION SET
                                Notification notification = new Notification();
                                notification.setUser_no(comment_user.getUser_no());
                                notification.setType(NotificationType.COMMENT_ARTIST);
                                notification.setContent("댓글을 작성한 '" + portfolio.getTitle() + "'에 작성한 아티스트가 댓글을 남겼습니다. '" + comment_short + "...'");
                                notification.setReg_date(Time.TimeFormatHMS());
                                NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_ARTIST, NotificationType.CONTENT_TYPE_PORTFOLIO, portfolio.getType(), portfolio.getPortfolio_no(), null, portfolio_artist.getArtist_no());
                                notification.setNext(new Gson().toJson(notificationNext));
                                notificationDao.insertNotification(notification);
                            }
                        }
                    }
                } else {
                    NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_OTHERS, NotificationType.CONTENT_TYPE_PORTFOLIO, portfolio.getType(), portfolio.getPortfolio_no(), null, portfolio_artist.getArtist_no());
                    if (portfolio_artist_user.getFcm_token() != null && portfolio_artist_user.isComment_push())
                        firebaseMessagingSnippets.push(portfolio_artist_user.getFcm_token(), NotificationType.COMMENT_OTHERS_FCM, "'"+ portfolio.getTitle() + "'에 새로운 댓글이 등록되었습니다. '" + comment_short + "...'", new Gson().toJson(notificationNext));
                    Notification notification = new Notification();
                    notification.setUser_no(portfolio_artist_user.getUser_no());
                    notification.setReg_date(Time.TimeFormatHMS());
                    notification.setContent("'"+ portfolio.getTitle() + "'에 새로운 댓글이 등록되었습니다. '" + comment_short + "...'");
                    notification.setType(NotificationType.COMMENT_OTHERS);
                    notification.setNext(new Gson().toJson(notificationNext));
                    notificationDao.insertNotification(notification);
                }

                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.PORTFOLIO_COMMENT_INSERT_SUCCESS, message.getHashMap("InsertPortfolioComment()")), HttpStatus.OK);
            } else {
                // DB SET
                portfolioCommentDao.deleteComment(portfolioComment.getComment_no());

                // Portfolio SET

                portfolioDao.updatePortfolioByComment(portfolioComment.getPortfolio_no(), portfolioCommentDao.getCommentNumberByPortfolioNo(portfolioComment.getPortfolio_no()).size());

                List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolioComment.getPortfolio_no());
                List<PortfolioComment> resCommentList = new ArrayList<>();
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolioComment.getPortfolio_no());
                for (PortfolioComment portfolioComment1 : portfolioCommentList) {
                    int commentWriter = portfolioComment1.getUser_no();
                    if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(commentWriter, portfolio.getArtist_no()) != null)
                        portfolioComment1.set_fankoked(true);
                    else
                        portfolioComment1.set_fankoked(false);

                    resCommentList.add(portfolioComment1);
                }
                message.put("comment_number", portfolio.getComment_number());
                message.put("portfolio_comment", resCommentList);

                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.PORTFOLIO_COMMENT_DELETE_SUCCESS, message.getHashMap("DeletePortfolioComment()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 500))
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updatePortfolioByLike(int portfolio_no, int user_no) {
        try {
            portfolioDao.setSession(sqlSession);
            portfolioLikeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();

            if (portfolioDao.getPortfolioByPortfolioNo(portfolio_no) == null) {
                log.info("Portfolio_no : " + portfolio_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            if (portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null) {
                // 좋아요 한 게시물일 때 -> 좋아요 취소
                portfolioLikeDao.deleteLike(portfolio_no, user_no);

                // Portfolio SET
                portfolioDao.updatePortfolioByLike(portfolio_no, portfolioLikeDao.getPortfolioLikeByPortfolioNo(portfolio_no).size());

                // Response Message SET
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                boolean portfolioLike = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;
                portfolio.setUser_no(artistDao.getArtistByArtistNo(portfolio.getArtist_no()).getUser_no());

                message.put("like_number", portfolio.getLike_number());
                message.put("portfolio_like", portfolioLike);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_LIKE_PORTFOLIO_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            } else {
                // Portfolio Like SET - 좋아요 하지 않은 게시물일 때 -> 좋아요
                PortfolioLike portfolioLike = new PortfolioLike();
                portfolioLike.setPortfolio_no(portfolio_no);
                portfolioLike.setUser_no(user_no);
                String d = Time.TimeFormatHMS();
                portfolioLike.setReg_date(d);
                portfolioLikeDao.insertLike(portfolioLike);

                // Portfolio SET
                portfolioDao.updatePortfolioByLike(portfolio_no, portfolioLikeDao.getPortfolioLikeByPortfolioNo(portfolio_no).size());

                // Response Message SET
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                boolean portfolioLikeBool = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;
                portfolio.setUser_no(artistDao.getArtistByArtistNo(portfolio.getArtist_no()).getUser_no());
                message.put("like_number", portfolio.getLike_number());
                message.put("portfolio_like", portfolioLikeBool);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LIKE_PORTFOLIO_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getPortfolioList(int artist_no, String sort, int last_index) {
        try {
            Message message = new Message();
            portfolioDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            if (artist == null) {
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETED_USER, ResMessage.NO_ARTIST_DETECTED), HttpStatus.OK);
            }

            if(last_index == 0){
                List<Portfolio> portfolioList = portfolioDao.getPortfolioListSort(artist_no, sort);
                List<Portfolio> resPortfolioList = new ArrayList<>();
                for (Portfolio portfolio : portfolioList) {
                    portfolio.setUser_no(artist.getUser_no());
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
                            ArrayList<String> filelist = new ArrayList<>(Arrays.asList(portfolio.getFile().split(", ")));
                            portfolio.setImage_list(filelist);
                            log.info(filelist);
                        }
                    }
                    resPortfolioList.add(portfolio);
                }

                message.put("portfolios", resPortfolioList);
                if(resPortfolioList.size() > 0)
                    message.put("last_index", resPortfolioList.get(resPortfolioList.size() - 1).getPortfolio_no());
            } else {
                Portfolio portfolio1 = portfolioDao.getPortfolioByPortfolioNo(last_index);
                if(portfolio1 == null){
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                }
                List<Portfolio> portfolioList = portfolioDao.getPortfolioListSortRefresh(artist_no, sort, portfolio1);
                List<Portfolio> resPortfolioList = new ArrayList<>();
                for (Portfolio portfolio : portfolioList) {
                    portfolio.setUser_no(artist.getUser_no());
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
                            ArrayList<String> filelist = new ArrayList<>(Arrays.asList(portfolio.getFile().split(", ")));
                            portfolio.setImage_list(filelist);
                            log.info(filelist);
                        }
                    }
                    resPortfolioList.add(portfolio);
                }

                message.put("portfolios", resPortfolioList);
                if(resPortfolioList.size() > 0)
                    message.put("last_index", resPortfolioList.get(resPortfolioList.size() - 1).getPortfolio_no());
            }

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LIKE_PORTFOLIO_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }
}
