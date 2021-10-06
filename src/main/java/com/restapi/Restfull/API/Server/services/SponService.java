package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.utility.ASVerification;
import com.restapi.Restfull.API.Server.utility.FirebaseMessagingSnippets;
import com.restapi.Restfull.API.Server.utility.GPVerification;
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

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class SponService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private SponDao sponDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private BoardCommentDao boardCommentDao;

    @Autowired
    private NotificationDao notificationDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity insertSpon(Spon spon) {
        try{
            if(spon.getPlatform().equals("Android")){
                GPResponseModel gpResponse = GPVerification.getInstance().verify(spon.getProduct_id(), spon.getPurchase_token());
                if(gpResponse.isPurchaseState()){
                    spon.setVerify_status(true);
                } else {
                    spon.setVerify_status(false);
                }
            } else if(spon.getPlatform().equals("IOS")){
                AppleVerifyRequest request = new AppleVerifyRequest(spon.getReceipt_id(), null, false);
                AppleVerifyResponse asResponse = ASVerification.getInstance().verify(request);
                if(asResponse.getStatus_explain().equals("SUCCESS")){
                    spon.setVerify_status(true);
                } else {
                    spon.setVerify_status(false);
                }
            } else {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.SPON_PLATFORM_ERROR), HttpStatus.OK);
            }
        } catch (GeneralSecurityException | IOException e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
        try {
            sponDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);

            int artist_no = spon.getArtist_no();

            if (artist_no == 0) {
                artist_no = boardDao.getBoardByBoardNo(spon.getBoard_no()).getArtist_no();
                spon.setArtist_no(artist_no);
            }
            Artist sponned_artist = artistDao.getArtistByArtistNo(artist_no);
            User user = userDao.selectUserByUserNo(spon.getUser_no());
            Artist artist = artistDao.getArtistByUserNo(user.getUser_no());

            FirebaseMessagingSnippets firebaseMessagingSnippets= new FirebaseMessagingSnippets();

            if (sponned_artist.getUser_no() == spon.getUser_no()) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.CANNOT_SPON_YOURSELF), HttpStatus.OK);
            }
            Message message = new Message();
            String d = Time.TimeFormatHMS();
            // Spon Data Set

            spon.setSpon_date(d);
            spon.setStatus(false);
            spon.setPurchase_status(false);
            if (spon.getBoard_no() != 0) {
                spon.setType(SponType.BOARD_SPON);
                // DB Set
                sponDao.insertSpon(spon);
                // BoardDao, BoardCommentDao SET

                BoardComment boardSponComment = new BoardComment();
                boardSponComment.setBoard_no(spon.getBoard_no());
                boardSponComment.setUser_no(spon.getUser_no());
                if (artist != null) {
                    boardSponComment.setCommenter_name(artist.getArtist_name());
                    boardSponComment.setProfile_img(artist.getArtist_profile_img());
                } else {
                    boardSponComment.setCommenter_name(user.getName());
                    boardSponComment.setProfile_img(user.getProfile_img());
                }
                boardSponComment.setType(BoardCommentType.SPON_COMMENT);
                boardSponComment.setContent(spon.getPrice() + spon.getCurrency());
                boardSponComment.setComment_private(false);
                String date = Time.TimeFormatHMS();
                boardSponComment.setReg_date(date);
                boardCommentDao.insertComment(boardSponComment);

                List<BoardComment> boardCommentList = boardCommentDao.getCommentListByBoardNo(spon.getBoard_no());
                Board board = boardDao.getBoardByBoardNo(spon.getBoard_no());

                ArrayList<BoardComment> resCommentList = new ArrayList<>();
                for (BoardComment boardComment1 : boardCommentList) {
                    int commentWriter = boardComment1.getUser_no();
                    if (!sponDao.getSponByArtistNoANDUserNo(commentWriter, board.getArtist_no()).isEmpty())
                        boardComment1.set_sponned(true);
                    else
                        boardComment1.set_sponned(false);
                    resCommentList.add(boardComment1);
                }

                // Message Set
                message.put("spon", spon);
                message.put("comment_number", board.getComment_number());
                message.put("board_comment", resCommentList);
                if(resCommentList.size() > 0)
                    message.put("last_index", resCommentList.get(resCommentList.size() - 1).getComment_no());

                //FCM SET
                NotificationNext notificationNext = new NotificationNext(NotificationType.ARTIST_SPON, NotificationType.CONTENT_TYPE_BOARD, null, board.getBoard_no(), null, spon.getArtist_no());
                firebaseMessagingSnippets.push(userDao.selectUserByUserNo(sponned_artist.getUser_no()).getFcm_token(), NotificationType.ARTIST_SPON_FCM,"'" + user.getName() + "'님이 회원님의 게시글 '" + board.getTitle() + "'에 '" + spon.getPrice() + "'의 금액을 후원했습니다.", new Gson().toJson(notificationNext));

                //Notification SET
                Notification notification = new Notification();
                notification.setUser_no(sponned_artist.getUser_no());
                notification.setType(NotificationType.ARTIST_SPON);
                notification.setContent("'" + user.getName() + "'님이 회원님의 게시글 '" + board.getTitle() + "'에 '" + spon.getPrice() + "'의 금액을 후원했습니다.");
                notification.setReg_date(Time.TimeFormatHMS());
                notification.setNext(new Gson().toJson(notificationNext));
                notificationDao.insertNotification(notification);
                log.info("After : " + spon);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_SPON_SUCCESS, message.getHashMap("ArtistSpon()")), HttpStatus.OK);
            } else {
                spon.setType(SponType.Artist_SPON);
                // DB Set
                sponDao.insertSpon(spon);

                //FCM SET
                NotificationNext notificationNext = new NotificationNext(NotificationType.ARTIST_SPON, null, null, 0, null, spon.getArtist_no());
                firebaseMessagingSnippets.push(userDao.selectUserByUserNo(sponned_artist.getUser_no()).getFcm_token(), NotificationType.ARTIST_SPON_FCM,"'" + user.getName() + "'님이 회원님께 '" + spon.getPrice() + "'원을 후원했습니다.", new Gson().toJson(notificationNext));

                //Notification SET
                Notification notification = new Notification();
                notification.setUser_no(sponned_artist.getUser_no());
                notification.setType(NotificationType.ARTIST_SPON);
                notification.setContent("'" + user.getName() + "'님이 회원님께 '" + spon.getPrice() + "'의 금액을 후원했습니다.");
                notification.setReg_date(Time.TimeFormatHMS());
                notification.setNext(new Gson().toJson(notificationNext));
                notificationDao.insertNotification(notification);

                // Message Set
                message.put("spon", spon);
                log.info("After : " + spon);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_SPON_SUCCESS, message.getHashMap("BoardSpon()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

}
