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
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class BoardService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private BoardCommentDao boardCommentDao;

    @Autowired
    private BoardLikeDao boardLikeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    private PenaltyDao penaltyDao;

    @Autowired
    private SponDao sponDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getBoardListByUserSubscribe(int user_no) {
        try {
            boardDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);
            Message message = new Message();
            if (subscribeDao.getSubscribeListByUserNo(user_no) != null) {
                List<Subscribe> subscribeList = subscribeDao.getSubscribeListByUserNo(user_no);
                for (Subscribe subscribe : subscribeList) {
                    // 구독되어있는 아티스트의 글 들을 가져옴
                    int artist_no = subscribe.getArtist_no();
                    List<Board> boardList = boardDao.getBoardListByArtistNo(artist_no);
                    message.put("boards", boardList);
                }
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetBoard()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetBoard(int user_no, int board_no, int last_index) {
        try {
            Message message = new Message();
            /** required Data
             * 1. Portfolio - DONE
             * 2. Portfolio Comment - DONE
             * 3. Portfolio Like - DONE
             * **/
            // Setting SQL SESSION
            boardDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            boardLikeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            sponDao.setSession(sqlSession);

            if (boardDao.getBoardByBoardNo(board_no) == null) {
                log.info("board_no : " + board_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            if (last_index > -1) {
                if(last_index == 0){
                    List<BoardComment> commentList = boardCommentDao.getCommentListByBoardNo(board_no);
                    Board board = boardDao.getBoardByBoardNo(board_no);
                    List<BoardComment> resCommentList = new ArrayList<>();
                    for (BoardComment boardComment : commentList) {
                        int commentWriter = boardComment.getUser_no();
                        if (!sponDao.getSponByArtistNoANDUserNo(commentWriter, board.getArtist_no()).isEmpty())
                            boardComment.set_sponned(true);
                        else
                            boardComment.set_sponned(false);

                        resCommentList.add(boardComment);
                    }
                    message.put("comment_number", board.getComment_number());
                    message.put("board_comment", resCommentList);
                    if(resCommentList.size() > 0)
                        message.put("last_index", resCommentList.get(resCommentList.size() - 1).getComment_no());
                } else {
                    BoardComment boardComment1 = boardCommentDao.getCommentByCommentNo(last_index);
                    if(boardComment1 == null){
                        return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                    }
                    List<BoardComment> commentList = boardCommentDao.getCommentListByBoardNoRefresh(board_no, boardComment1);
                    Board board = boardDao.getBoardByBoardNo(board_no);
                    List<BoardComment> resCommentList = new ArrayList<>();
                    for (BoardComment boardComment : commentList) {
                        int commentWriter = boardComment.getUser_no();
                        if (!sponDao.getSponByArtistNoANDUserNo(commentWriter, board.getArtist_no()).isEmpty())
                            boardComment.set_sponned(true);
                        else
                            boardComment.set_sponned(false);

                        resCommentList.add(boardComment);
                    }
                    message.put("comment_number", board.getComment_number());
                    message.put("board_comment", resCommentList);
                    if(resCommentList.size() > 0)
                        message.put("last_index", resCommentList.get(resCommentList.size() - 1).getComment_no());
                }
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_COMMENT_SUCCESS, message.getHashMap("GetBoardComment()")), HttpStatus.OK);
            } else {
                // GET DATA FROM DB
                Board board = boardDao.getBoardByBoardNo(board_no);
                int visit_number = board.getVisit_number();
                board.setVisit_number((visit_number + 1));
                boolean boardLike = boardLikeDao.getBoardLike(board_no, user_no) != null;
                Artist artist = artistDao.getArtistByArtistNo(board.getArtist_no());
                board.setUser_no(artist.getUser_no());
                // RESPONSE MESSAGE SET
                message.put("board", board);
                message.put("board_like", boardLike);
                boardDao.updateBoardByVisit(board);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_SUCCESS, message.getHashMap("GetBoard()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getBoardList(String sort, int last_index) {
        try {
            boardDao.setSession(sqlSession);
            Message message = new Message();

            if(last_index == 0){
                List<Board> boardList = boardDao.getBoardList(sort);
                message.put("boards", boardList);
                if(boardList.size() > 0)
                    message.put("last_index", boardList.get(boardList.size() - 1).getBoard_no());
            } else {
                Board board = boardDao.getBoardByBoardNo(last_index);
                if(board == null){
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                }
                List<Board> boardList = boardDao.getBoardListRefresh(sort, board);
                message.put("boards", boardList);
                if(boardList.size() > 0)
                    message.put("last_index", boardList.get(boardList.size() - 1).getBoard_no());
            }
            message.put("sort", sort);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_LIST_SUCCESS, message.getHashMap("GetBoardList()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getBoardByBoardNo(int board_no, int artist_no) {
        try {
            boardDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            Message message = new Message();

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

            if (boardDao.getBoardByBoardNo(board_no) == null) {
                log.info("board_no : " + board_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            // Board SET
            Board board = boardDao.getBoardByBoardNo(board_no);
            // Response Message SET
            message.put("board", board);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_SUCCESS, message.getHashMap("GetBoardForEdit()")), HttpStatus.OK);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Board getBoard(int board_no) {
        boardDao.setSession(sqlSession);
        return boardDao.getBoardByBoardNo(board_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBoard(Board board) {
        boardDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        subscribeDao.setSession(sqlSession);
        userDao.setSession(sqlSession);
        notificationDao.setSession(sqlSession);
        Artist artist = artistDao.getArtistByArtistNo(board.getArtist_no());
        board.setFan_number(artist.getFan_number());
        boardDao.insertBoard(board);

        // Update Artist recent_act_date
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
                        NotificationNext notificationNext = new NotificationNext(NotificationType.CONTENT_UPLOADED, NotificationType.CONTENT_TYPE_BOARD, null, board.getBoard_no(), null, board.getArtist_no());
                        firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.CONTENT_UPLOADED_FCM, "회원님이 팬콕한 " + artist.getArtist_name() + "님이 새로운 게시글을 업로드 하였습니다.", new Gson().toJson(notificationNext));
                    } else {
                        log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                    }
                    //NOTIFICATION SET
                    Notification notification = new Notification();
                    notification.setUser_no(user.getUser_no());
                    notification.setType(NotificationType.CONTENT_UPLOADED);
                    notification.setContent("회원님이 팬콕한 " + artist.getArtist_name() + "님이 새로운 게시글을 업로드 하였습니다.");
                    notification.setReg_date(Time.TimeFormatHMS());
                    NotificationNext notificationNext = new NotificationNext(NotificationType.CONTENT_UPLOADED, NotificationType.CONTENT_TYPE_BOARD, null, board.getBoard_no(), null, board.getArtist_no());
                    notification.setNext(new Gson().toJson(notificationNext));
                    notificationDao.insertNotification(notification);
                }
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateBoard(Board board) {
        try {
            boardDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);

            Message message = new Message();
            Board origin_board = boardDao.getBoardByBoardNo(board.getBoard_no());

            if (origin_board == null) {
                log.info("board_no : " + board.getBoard_no());
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }
            Artist artist = artistDao.getArtistByArtistNo(origin_board.getArtist_no());

            // Update Method
            board.setArtist_name(artist.getArtist_name());
            board.setArtist_profile_img(artist.getArtist_profile_img());
            String d = Time.TimeFormatHMS();
            board.setRevise_date(d);
            boardDao.updateBoard(board);

            // Update Artist recent_act_date

            artist.setRecent_act_date(Time.TimeFormatHMS());
            artistDao.updateArtist(artist);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.EDIT_BOARD_SUCCESS, message.getHashMap("EditBoard()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deleteBoard(int board_no) {
        try {
            boardDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);
            Board board = boardDao.getBoardByBoardNo(board_no);
            Message message = new Message();

            if (boardDao.getBoardByBoardNo(board_no) == null) {
                log.info("board_no : " + board_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            log.info(board_no);
            log.info(board);

            Artist artist = artistDao.getArtistByArtistNo(board.getArtist_no());

            List<Penalty> penaltyList = penaltyDao.getPenaltyListByArtistNo(artist.getArtist_no());
            if (penaltyList != null && !penaltyList.isEmpty()) {
                Penalty penalty = penaltyList.get(0);
                String now = Time.TimeFormatHMS();
                Date nowDate = Time.StringToDateFormat(now);
                if (nowDate.before(Time.StringToDateFormat(penalty.getPenalty_end_date())) && nowDate.after(Time.StringToDateFormat(penalty.getPenalty_start_date()))) {
                    message.put("penalty", penalty);
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAN_ARTIST, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetPortfolioForEdit()")), HttpStatus.OK);
                }
            }

            // DELETE Board
            boardDao.deleteBoard(board_no);

            // Update Artist recent_act_date

            artist.setRecent_act_date(Time.TimeFormatHMS());
            artistDao.updateArtist(artist);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.DELETE_BOARD_SUCCESS, message.getHashMap("DeletePortfolio()")), HttpStatus.OK);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }

    }

    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 500))
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateBoardByComment(BoardComment boardComment, String method) {
        try {
            boardDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            sponDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);

            if (boardDao.getBoardByBoardNo(boardComment.getBoard_no()) == null) {
                log.info("board_no : " + boardComment.getBoard_no());
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            Message message = new Message();
            if (method.equals("UPDATE")) {

                // DB SET
                boardComment.setComment_private(false);
                boardComment.setType(BoardCommentType.NORMAL_COMMENT);
                User user = userDao.selectUserByUserNo(boardComment.getUser_no());
                Artist artist = artistDao.getArtistByUserNo(boardComment.getUser_no());
                if (artist != null) {
                    boardComment.setCommenter_name(artist.getArtist_name());
                    boardComment.setProfile_img(artist.getArtist_profile_img());
                } else {
                    boardComment.setCommenter_name(user.getName());
                    boardComment.setProfile_img(user.getProfile_img());
                }
                String d = Time.TimeFormatHMS();
                boardComment.setReg_date(d);
                boardCommentDao.insertComment(boardComment);

                // Board SET
                boardDao.updateBoardByComment(boardComment.getBoard_no(), boardCommentDao.getCommentNumberByBoardNo(boardComment.getBoard_no()).size());

                // Response Message SET
                List<BoardComment> boardCommentList = boardCommentDao.getCommentListByBoardNo(boardComment.getBoard_no());

                ArrayList<BoardComment> resCommentList = new ArrayList<>();
                Board board = boardDao.getBoardByBoardNo(boardComment.getBoard_no());
                for (BoardComment boardComment1 : boardCommentList) {
                    int commentWriter = boardComment1.getUser_no();
                    if (!sponDao.getSponByArtistNoANDUserNo(commentWriter, board.getArtist_no()).isEmpty())
                        boardComment1.set_sponned(true);
                    else
                        boardComment1.set_sponned(false);
                    resCommentList.add(boardComment1);
                }
                message.put("comment_number", board.getComment_number());
                message.put("board_comment", resCommentList);
                if(resCommentList.size() > 0)
                    message.put("last_index", resCommentList.get(resCommentList.size() - 1).getComment_no());

                //FCM MESSAGE SEND
                FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

                String comment_short;
                if (boardComment.getContent().length() < 20) {
                    comment_short = boardComment.getContent();
                } else {
                    comment_short = boardComment.getContent().substring(0, 20);
                }

                Artist board_artist = artistDao.getArtistByArtistNo(board.getArtist_no());
                User board_artist_user = userDao.selectUserByUserNo(board_artist.getUser_no());

                if (boardComment.getUser_no() == board_artist_user.getUser_no()) {
                    board_artist.setRecent_act_date(Time.TimeFormatHMS());
                    artistDao.updateArtist(board_artist);
                    List<BoardComment> boardCommentList1 = boardCommentDao.getCommentNumberByBoardNo(board.getBoard_no());
                    List<User> userList = new ArrayList<>();
                    for (BoardComment boardComment1 : boardCommentList1) {
                        if (boardComment1.getUser_no() != board_artist_user.getUser_no()) {
                            User user1 = userDao.selectUserByUserNo(boardComment1.getUser_no());
                            if(!userList.contains(user1)) {
                                userList.add(user1);
                            }
                        }
                    }

                    if (!userList.isEmpty()) {
                        for (User comment_user : userList) {
                            //FCM MESSAGE SEND
                            if (comment_user.getFcm_token() != null && comment_user.isComment_push()) {
                                NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_ARTIST, NotificationType.CONTENT_TYPE_BOARD, null, board.getBoard_no(), null, board_artist.getArtist_no());
                                firebaseMessagingSnippets.push(comment_user.getFcm_token(), NotificationType.COMMENT_ARTIST_FCM, "댓글을 작성한 게시글 '" + board.getTitle() + "'에 작성한 아티스트가 댓글을 남겼습니다. '" + comment_short + "...'", new Gson().toJson(notificationNext));
                            } else {
                                log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                            }
                            //NOTIFICATION SET
                            Notification notification = new Notification();
                            notification.setUser_no(comment_user.getUser_no());
                            notification.setType(NotificationType.COMMENT_ARTIST);
                            notification.setContent("댓글을 작성한 게시글 '" + board.getTitle() + "'에 작성한 아티스트가 댓글을 남겼습니다. '" + comment_short + "...'");
                            notification.setReg_date(Time.TimeFormatHMS());
                            NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_ARTIST, NotificationType.CONTENT_TYPE_BOARD, null, board.getBoard_no(), null, board_artist.getArtist_no());
                            notification.setNext(new Gson().toJson(notificationNext));
                            ;
                            notificationDao.insertNotification(notification);
                        }
                    }
                } else {
                    NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_OTHERS, NotificationType.CONTENT_TYPE_BOARD, null, board.getBoard_no(), null, board_artist.getArtist_no());
                    if (board_artist_user.isComment_push() && board_artist_user.getFcm_token() != null) {
                        firebaseMessagingSnippets.push(board_artist_user.getFcm_token(), NotificationType.COMMENT_OTHERS_FCM, "게시글 '" + board.getTitle() + "'에 새로운 댓글이 등록되었습니다. '" + comment_short + "...'", new Gson().toJson(notificationNext));
                    }
                    Notification notification = new Notification();
                    notification.setUser_no(board_artist_user.getUser_no());
                    notification.setReg_date(Time.TimeFormatHMS());
                    notification.setContent("게시글 '" + board.getTitle() + "'에 새로운 댓글이 등록되었습니다. '" + comment_short + "...'");
                    notification.setType(NotificationType.COMMENT_OTHERS);
                    notification.setNext(new Gson().toJson(notificationNext));
                    notificationDao.insertNotification(notification);
                }
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_COMMENT_INSERT_SUCCESS, message.getHashMap("InsertBoardComment()")), HttpStatus.OK);
            } else {
                // DB SET
                boardCommentDao.deleteComment(boardComment.getComment_no());

                // Board SET
                boardDao.updateBoardByComment(boardComment.getBoard_no(), boardCommentDao.getCommentNumberByBoardNo(boardComment.getBoard_no()).size());

                List<BoardComment> boardCommentList = boardCommentDao.getCommentListByBoardNo(boardComment.getBoard_no());

                ArrayList<BoardComment> resCommentList = new ArrayList<>();
                Board board = boardDao.getBoardByBoardNo(boardComment.getBoard_no());
                for (BoardComment boardComment1 : boardCommentList) {
                    int commentWriter = boardComment1.getUser_no();
                    if (!sponDao.getSponByArtistNoANDUserNo(commentWriter, board.getArtist_no()).isEmpty())
                        boardComment1.set_sponned(true);
                    else
                        boardComment1.set_sponned(false);
                    resCommentList.add(boardComment1);
                }

                message.put("comment_number", board.getComment_number());
                message.put("board_comment", resCommentList);
                if(resCommentList.size() > 0)
                    message.put("last_index", resCommentList.get(resCommentList.size() - 1).getComment_no());
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_COMMENT_DELETE_SUCCESS, message.getHashMap("DeleteBoardComment()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 500))
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateBoardByLike(int board_no, int user_no) {
        try {
            boardDao.setSession(sqlSession);
            boardLikeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();

            if (boardDao.getBoardByBoardNo(board_no) == null) {
                log.info("board_no : " + board_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            if (boardLikeDao.getBoardLike(board_no, user_no) != null) {


                // 좋아요 한 게시물 일 때 -> 좋아요 취소
                boardLikeDao.deleteLike(board_no, user_no);

                // Board Set
                boardDao.updateBoardByLike(board_no, boardLikeDao.getBoardLikeByBoardNo(board_no).size());

                // Response Message Set
                Board board = boardDao.getBoardByBoardNo(board_no);
                boolean boardLike = boardLikeDao.getBoardLike(board_no, user_no) != null;
                message.put("like_number", board.getLike_number());
                message.put("board_like", boardLike);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_LIKE_BOARD_SUCCESS, message.getHashMap("PressBoardLike()")), HttpStatus.OK);
            } else {
                // BoardLike Set - 좋아요 하지 않은 게시물일 때 -> 좋아요
                BoardLike boardLike = new BoardLike();
                boardLike.setBoard_no(board_no);
                boardLike.setUser_no(user_no);
                String d = Time.TimeFormatHMS();
                boardLike.setReg_date(d);
                boardLikeDao.insertLike(boardLike);

                // Board Set
                boardDao.updateBoardByLike(board_no, boardLikeDao.getBoardLikeByBoardNo(board_no).size());

                // Response Message Set
                Board board = boardDao.getBoardByBoardNo(board_no);
                boolean boardLikeBool = boardLikeDao.getBoardLike(board_no, user_no) != null;
                board.setUser_no(artistDao.getArtistByArtistNo(board.getArtist_no()).getUser_no());
                message.put("like_number", board.getLike_number());
                message.put("board_like", boardLikeBool);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LIKE_BOARD_SUCCESS, message.getHashMap("PressBoardLike()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertFiles(Board board) {
        boardDao.setSession(sqlSession);
        boardDao.insertFiles(board);
    }
}
