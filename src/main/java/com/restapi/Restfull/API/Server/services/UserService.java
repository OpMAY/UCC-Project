package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class UserService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private PenaltyDao penaltyDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private PortfolioCommentDao portfolioCommentDao;

    @Autowired
    private BoardCommentDao boardCommentDao;

    @Autowired
    private LoudSourcingEntryDao loudSourcingEntryDao;

    @Autowired
    private EntryCommentDao entryCommentDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity loginUser(User user) {
        try {
            String basic_profile_img = "http://www.mvsolutions.co.kr/static/image/profile_img_basic.png";
            Message message = new Message();
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            /** 회원 없을 때 회원가입 **/
            if (userDao.loginUser(user) == null) {
                /** SET User **/

                user.set_artist(false);
                user.setProfile_img(basic_profile_img);
                user.set_user_private(false);
                user.setReg_date(Time.TimeFormatDay());
                user.setPush(true);
                user.setComment_push(true);
                user.setFankok_push(true);
                userDao.registerUser(user);

                if (user.getName() == null) {
                    String basicName = "유저";
                    user.setName(basicName + user.getUser_no());
                }
                userDao.updateUser(user);

                user.set_register(true);
                message.put("user", user);
            } else { /** 회원 있으면 로그인 **/
                User login_user = userDao.loginUser(user);
                // FCM TOKEN UPDATE
                login_user.setFcm_token(user.getFcm_token());
                userDao.updateUser(login_user);

                // USER SET MESSAGE
                login_user.set_register(false);
                message.put("user", login_user);
                /** 아티스트의 경우 아티스트 정보 반환 **/
                int user_no = login_user.getUser_no();

                if (artistDao.getArtistByUserNo(user_no) != null) {
                    Artist artist = artistDao.getArtistByUserNo(user_no);
                    message.put("artist", artist);
                }
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOGIN_SUCCESS, message.getHashMap("Login()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deleteUser(int user_no) {
        userDao.setSession(sqlSession);
        loudSourcingEntryDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        Artist artist = artistDao.getArtistByUserNo(user_no);
        if (userDao.selectUserByUserNo(user_no) != null) {
            if(artist != null){
                List<LoudSourcingEntry> loudSourcingEntryList = loudSourcingEntryDao.getEntryListByArtistNo(artist.getArtist_no());
                if(!loudSourcingEntryList.isEmpty()){
                    for(LoudSourcingEntry loudSourcingEntry : loudSourcingEntryList){
                        loudSourcingEntry.setArtist_name("탈퇴한 유저");
                        loudSourcingEntry.setArtist_profile_img("http://www.mvsolutions.co.kr/static/image/profile_img_basic.png");
                        loudSourcingEntryDao.updateEntry(loudSourcingEntry);
                    }
                }
            }
            userDao.deleteUser(user_no);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.WITHDRAW_SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.NO_USER_DETECTED), HttpStatus.OK);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity selectUserByUserNo(int user_no) {
        try {
            Message message = new Message();
            userDao.setSession(sqlSession);
            User user = userDao.selectUserByUserNo(user_no);
            message.put("user_no", user.getUser_no());
            message.put("name", user.getName());
            if (user.getEmail() != null)
                message.put("email", user.getEmail());
            else
                message.put("email", "");
            message.put("profile_img", user.getProfile_img());
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.USER_INFO_ACCESS, message.getHashMap("ChangeArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity checkUserPrivate(int user_no) {
        try {
            Message message = new Message();
            userDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            boolean userCheck = userDao.selectUserByUserNo(user_no).is_user_private();
            log.info(userCheck);

            if(userCheck){
                Penalty penalty = penaltyDao.getPenaltyListByUserNo(user_no).get(0);
                message.put("penalty", penalty);
            }

            message.put("user_no", user_no);
            message.put("is_user_private", userCheck);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.CHECK_USER_PRIVATE_SUCCESS, message.getHashMap("CheckUserPrivate()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetUserSpecificInfo(int user_no) {
        try {
            Message message = new Message();
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);

            if (artistDao.getArtistByUserNo(user_no) != null) {
                Artist artist = artistDao.getArtistByUserNo(user_no);
                if (artist.getHashtag() != null) {
                    ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                    artist.setHashtag_list(hashtagList);
                    log.info(hashtagList);
                }
                message.put("artist", artist);
            }
            User user = userDao.selectUserByUserNo(user_no);

            message.put("user", user);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_MY_PAGE_INFO, message.getHashMap("GetUserInfo()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity UpdateUserInfo(Artist artist, User user, List<Upload> uploads) {
        try {
            Message message = new Message();
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);

            log.info(user);
            log.info(artist);

            if (artist != null) {
                Artist origin_artist = artistDao.getArtistByArtistNo(artist.getArtist_no());
                if(artist.getArtist_profile_img() == null) {
                    artist.setArtist_profile_img(origin_artist.getArtist_profile_img());
                }
                if(artist.getMain_img() == null) {
                    artist.setMain_img(origin_artist.getMain_img());
                }
                artist.setFan_number(origin_artist.getFan_number());
                artist.setVisit_today(origin_artist.getVisit_today());
                String d = Time.TimeFormatHMS();
                artist.setRecent_act_date(d);
                artistDao.updateArtist(artist);
                Artist resArtist = artistDao.getArtistByArtistNo(artist.getArtist_no());
                if (resArtist.getHashtag() != null) {
                    ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(resArtist.getHashtag().split(", ")));
                    resArtist.setHashtag_list(hashtagList);
                    log.info(hashtagList);
                }
                message.put("artist", resArtist);

                List<Board> boardList = boardDao.getBoardListByArtistNo(artist.getArtist_no());
                List<Portfolio> portfolioList = portfolioDao.getPortfolioListByArtistNo(artist.getArtist_no());
                List<LoudSourcingEntry> loudSourcingEntryList = loudSourcingEntryDao.getEntryListByArtistNo(artist.getArtist_no());
                for(Board board : boardList){
                    board.setArtist_profile_img(artist.getArtist_profile_img());
                    board.setArtist_name(artist.getArtist_name());
                    boardDao.updateBoard(board);
                }

                for(Portfolio portfolio : portfolioList){
                    portfolio.setArtist_profile_img(artist.getArtist_profile_img());
                    portfolio.setArtist_name(artist.getArtist_name());
                    portfolioDao.updatePortfolio(portfolio);
                }

                for(LoudSourcingEntry loudSourcingEntry : loudSourcingEntryList){
                    loudSourcingEntry.setArtist_profile_img(artist.getArtist_profile_img());
                    loudSourcingEntry.setArtist_name(artist.getArtist_name());
                    loudSourcingEntryDao.updateEntry(loudSourcingEntry);
                }
            }

            User origin_user = userDao.selectUserByUserNo(user.getUser_no());
            if (uploads.size() <= 0)
                user.setProfile_img(origin_user.getProfile_img());
            user.set_artist(origin_user.is_artist());
            userDao.updateUser(user);
            User resUser = userDao.selectUserByUserNo(user.getUser_no());
            message.put("user", resUser);
            List<BoardComment> boardCommentList = boardCommentDao.getCommentListByUserNo(user.getUser_no());
            List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByUserNo(user.getUser_no());
            List<EntryComment> entryCommentList = entryCommentDao.getCommentListByUserNo(user.getUser_no());
            if(artist != null){
                for(BoardComment boardComment : boardCommentList){
                    boardComment.setCommenter_name(artist.getArtist_name());
                    boardComment.setProfile_img(artist.getArtist_profile_img());
                    boardCommentDao.updateComment(boardComment);
                }

                for(PortfolioComment portfolioComment : portfolioCommentList){
                    portfolioComment.setCommenter_name(artist.getArtist_name());
                    portfolioComment.setProfile_img(artist.getArtist_profile_img());
                    portfolioCommentDao.updateComment(portfolioComment);
                }

                for(EntryComment entryComment : entryCommentList){
                    entryComment.setCommenter_name(artist.getArtist_name());
                    entryComment.setProfile_img(artist.getArtist_profile_img());
                    entryCommentDao.updateComment(entryComment);
                }
            }else {
                for (BoardComment boardComment : boardCommentList) {
                    boardComment.setCommenter_name(user.getName());
                    boardComment.setProfile_img(user.getProfile_img());
                    boardCommentDao.updateComment(boardComment);
                }

                for (PortfolioComment portfolioComment : portfolioCommentList) {
                    portfolioComment.setCommenter_name(user.getName());
                    portfolioComment.setProfile_img(user.getProfile_img());
                    portfolioCommentDao.updateComment(portfolioComment);
                }

                for (EntryComment entryComment : entryCommentList) {
                    entryComment.setCommenter_name(user.getName());
                    entryComment.setProfile_img(user.getProfile_img());
                    entryCommentDao.updateComment(entryComment);
                }
            }


            if (uploads.size() > 0) {
                message.put("files", uploads);
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UPDATE_MY_PAGE_INFO, message.getHashMap("UpdateUserInfo()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateUserPush(int user_no, String push) {
        try {
            userDao.setSession(sqlSession);
            Message message = new Message();
            User user = userDao.selectUserByUserNo(user_no);
            if(user == null)
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETED_USER, ResMessage.NO_USER_DETECTED), HttpStatus.OK);
            switch (push) {
                case "all":
                    if (user.isPush()) {
                        user.setPush(false);
                        message.put("push", false);
                    }
                    else {
                        user.setPush(true);
                        message.put("push", true);
                    }
                    break;
                case "comment":
                    if (user.isComment_push()) {
                        user.setComment_push(false);
                        message.put("comment_push", false);
                    }
                    else {
                        user.setComment_push(true);
                        message.put("comment_push", true);
                    }
                    break;
                case "fankok":
                    if (user.isFankok_push()) {
                        user.setFankok_push(false);
                        message.put("fankok_push", false);
                    }
                    else {
                        user.setFankok_push(true);
                        message.put("fankok_push", true);
                    }
                    break;
            }
            userDao.updateUser(user);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UPDATE_PUSH_SETTING, message.getHashMap("updateUserPush()")), HttpStatus.OK);
        } catch (JSONException e){
            throw new BusinessException(e);
        }
    }
}
