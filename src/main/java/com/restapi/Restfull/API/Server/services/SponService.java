package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
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
            Artist artist = artistDao.getArtistByArtistNo(artist_no);

            if (artist == null) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.NO_ARTIST_DETECTED), HttpStatus.OK);
            }
            if (artist.getUser_no() == spon.getUser_no()) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.CANNOT_SPON_YOURSELF), HttpStatus.OK);
            }
            Message message = new Message();
            String d = Time.TimeFormatHMS();
            spon.setSpon_date(d);
            spon.setStatus(false);
            spon.setPurchase_status(false);
            spon.setVerify_status(3);
            if (spon.getBoard_no() != 0) {
                spon.setType(SponType.BOARD_SPON);
                // DB Set
                sponDao.insertSpon(spon);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_SPON_SUCCESS, message.getHashMap("Spon()")), HttpStatus.OK);
            } else {
                spon.setType(SponType.Artist_SPON);
                // DB Set
                sponDao.insertSpon(spon);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_SPON_SUCCESS, message.getHashMap("Spon()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    public ResponseEntity androidPurchaseValidate(AndroidVerificationRequest request) {
        try {
            Message message = new Message();
            GPResponseModel response = GPVerification.getInstance().verify(request.getProduct_id(), request.getPurchase_token());
            // 0 : 성공 / 1 : 취소됨 / 2 : 보류 중
            if (response.getPurchaseState() == 0) {
                sponDao.setSession(sqlSession);
                notificationDao.setSession(sqlSession);
                artistDao.setSession(sqlSession);
                userDao.setSession(sqlSession);
                boardDao.setSession(sqlSession);
                boardCommentDao.setSession(sqlSession);
                FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

                Spon spon = sponDao.getSponByPurchaseToken(request.getPurchase_token());
                spon.setVerify_status(0);
                sponDao.updateSponByPurchaseUpdate(spon);
                if (spon.getType().equals(SponType.Artist_SPON)) {
                    Artist artist = artistDao.getArtistByArtistNo(spon.getArtist_no());
                    if (artist != null) {
                        User artistUser = userDao.selectUserByUserNo(artist.getUser_no());
                        User sponUser = userDao.selectUserByUserNo(spon.getUser_no());

                        //FCM SET
                        NotificationNext notificationNext = new NotificationNext(NotificationType.ARTIST_SPON, null, null, 0, null, spon.getArtist_no());
                        firebaseMessagingSnippets.push(artistUser.getFcm_token(), NotificationType.ARTIST_SPON_FCM, "'" + sponUser.getName() + "'님이 회원님께 '" + spon.getPrice() + spon.getCurrency() + "'의 금액을 후원했습니다.", new Gson().toJson(notificationNext));

                        //Notification SET
                        Notification notification = new Notification();
                        notification.setUser_no(artistUser.getUser_no());
                        notification.setType(NotificationType.ARTIST_SPON);
                        notification.setContent("'" + sponUser.getName() + "'님이 회원님께 '" + spon.getPrice() + spon.getCurrency() + "'의 금액을 후원했습니다.");
                        notification.setReg_date(Time.TimeFormatHMS());
                        notification.setNext(new Gson().toJson(notificationNext));
                        notificationDao.insertNotification(notification);
                    }
                } else if (spon.getType().equals(SponType.BOARD_SPON)) {
                    Artist artist = artistDao.getArtistByArtistNo(spon.getArtist_no());
                    Board board = boardDao.getBoardByBoardNo(spon.getBoard_no());
                    if (board != null) {
                        User artistUser = userDao.selectUserByUserNo(artist.getUser_no());
                        User sponUser = userDao.selectUserByUserNo(spon.getUser_no());
                        Artist userArtist = artistDao.getArtistByUserNo(sponUser.getUser_no());
                        BoardComment boardSponComment = new BoardComment();
                        boardSponComment.setBoard_no(spon.getBoard_no());
                        boardSponComment.setUser_no(spon.getUser_no());
                        if (userArtist != null) {
                            boardSponComment.setCommenter_name(artist.getArtist_name());
                            boardSponComment.setProfile_img(artist.getArtist_profile_img());
                        } else {
                            boardSponComment.setCommenter_name(sponUser.getName());
                            boardSponComment.setProfile_img(sponUser.getProfile_img());
                        }
                        boardSponComment.setType(BoardCommentType.SPON_COMMENT);
                        boardSponComment.setContent(spon.getPrice() + spon.getCurrency());
                        boardSponComment.setComment_private(false);
                        String date = Time.TimeFormatHMS();
                        boardSponComment.setReg_date(date);
                        boardCommentDao.insertComment(boardSponComment);

                        //FCM SET
                        NotificationNext notificationNext = new NotificationNext(NotificationType.ARTIST_SPON, NotificationType.CONTENT_TYPE_BOARD, null, board.getBoard_no(), null, spon.getArtist_no());
                        firebaseMessagingSnippets.push(artistUser.getFcm_token(), NotificationType.ARTIST_SPON_FCM, "'" + sponUser.getName() + "'님이 회원님의 게시글 '" + board.getTitle() + "'에 '" + spon.getPrice() + spon.getCurrency() + "'의 금액을 후원했습니다.", new Gson().toJson(notificationNext));

                        //Notification SET
                        Notification notification = new Notification();
                        notification.setUser_no(artistUser.getUser_no());
                        notification.setType(NotificationType.ARTIST_SPON);
                        notification.setContent("'" + sponUser.getName() + "'님이 회원님의 게시글 '" + board.getTitle() + "'에 '" + spon.getPrice() + spon.getCurrency() + "'의 금액을 후원했습니다.");
                        notification.setReg_date(Time.TimeFormatHMS());
                        notification.setNext(new Gson().toJson(notificationNext));
                        notificationDao.insertNotification(notification);
                    }
                }
            }
            message.put("status", response.getPurchaseState());
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_SPON_SUCCESS, message.getHashMap("BoardSpon()")), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }
}
