package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
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

import java.sql.Date;
import java.util.ArrayList;
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
    public ResponseEntity GetBoard(int user_no, int board_no, int start_index) {
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

            if(start_index > -1){
                List<BoardComment> commentList = boardCommentDao.getCommentListByBoardNo(board_no, start_index);
                Board board = boardDao.getBoardByBoardNo(board_no);
                List<BoardComment> resCommentList = new ArrayList<>();
                for(BoardComment boardComment : commentList){
                    int commentWriter = boardComment.getUser_no();
                    if(!sponDao.getSponByArtistNoANDUserNo(commentWriter, board.getArtist_no()).isEmpty())
                        boardComment.set_sponned(true);
                    else
                        boardComment.set_sponned(false);

                    resCommentList.add(boardComment);
                }
                message.put("board_comment", resCommentList);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_COMMENT_SUCCESS, message.getHashMap("GetBoardComment()")), HttpStatus.OK);
            }else {
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
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Board> getBoardList(String sort, int start_index) {
        boardDao.setSession(sqlSession);
        return boardDao.getBoardList(sort, start_index);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Board getBoardByBoardNo(int board_no) {
        boardDao.setSession(sqlSession);
        return boardDao.getBoardByBoardNo(board_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBoard(Board board) {
        boardDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        Artist artist = artistDao.getArtistByArtistNo(board.getArtist_no());
        board.setFan_number(artist.getFan_number());
        boardDao.insertBoard(board);

        // Update Artist recent_act_date
        artist.setRecent_act_date(Time.TimeFormatHMS());
        artistDao.updateArtist(artist);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateBoard(Board board, Message file_msg) {
        try {
            boardDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);

            Message message = new Message();
            // Update Method
            String d = Time.TimeFormatHMS();
            board.setRevise_date(d);
            boardDao.updateBoard(board);

            Board board1 = boardDao.getBoardByBoardNo(board.getBoard_no());

            // Update Artist recent_act_date
            Artist artist = artistDao.getArtistByArtistNo(board1.getArtist_no());
            artist.setRecent_act_date(Time.TimeFormatHMS());
            artistDao.updateArtist(artist);

            message.put("files", file_msg.getMap());
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
            Board board = boardDao.getBoardByBoardNo(board_no);
            Message message = new Message();

            // DELETE Board
            boardDao.deleteBoard(board_no);

            // Update Artist recent_act_date
            Artist artist = artistDao.getArtistByArtistNo(board.getArtist_no());
            artist.setRecent_act_date(Time.TimeFormatHMS());
            artistDao.updateArtist(artist);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.DELETE_BOARD_SUCCESS, message.getHashMap("DeletePortfolio()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateBoardByComment(BoardComment boardComment, String method) {
        try {
            boardDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            sponDao.setSession(sqlSession);

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
                boardDao.updateBoardByComment(boardComment.getBoard_no(), 1);

                // Response Message SET
                List<BoardComment> boardCommentList = boardCommentDao.getCommentListByBoardNo(boardComment.getBoard_no(), 0);

                ArrayList<BoardComment> resCommentList = new ArrayList<>();
                Board board = boardDao.getBoardByBoardNo(boardComment.getBoard_no());
                for(BoardComment boardComment1 : boardCommentList){
                    int commentWriter = boardComment1.getUser_no();
                    if(!sponDao.getSponByArtistNoANDUserNo(commentWriter, board.getArtist_no()).isEmpty())
                        boardComment1.set_sponned(true);
                    else
                        boardComment1.set_sponned(false);
                    resCommentList.add(boardComment1);
                }
                message.put("comment_number", board.getComment_number());
                message.put("board_comment", resCommentList);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_COMMENT_INSERT_SUCCESS, message.getHashMap("InsertBoardComment()")), HttpStatus.OK);
            } else {
                // DB SET
                boardCommentDao.deleteComment(boardComment.getComment_no());

                // Board SET
                boardDao.updateBoardByComment(boardComment.getBoard_no(), -1);

                List<BoardComment> boardCommentList = boardCommentDao.getCommentListByBoardNo(boardComment.getBoard_no(), 0);

                ArrayList<BoardComment> resCommentList = new ArrayList<>();
                Board board = boardDao.getBoardByBoardNo(boardComment.getBoard_no());
                for(BoardComment boardComment1 : boardCommentList){
                    int commentWriter = boardComment1.getUser_no();
                    if(!sponDao.getSponByArtistNoANDUserNo(commentWriter, board.getArtist_no()).isEmpty())
                        boardComment1.set_sponned(true);
                    else
                        boardComment1.set_sponned(false);
                    resCommentList.add(boardComment1);
                }

                message.put("comment_number", board.getComment_number());
                message.put("board_comment", resCommentList);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_COMMENT_DELETE_SUCCESS, message.getHashMap("DeleteBoardComment()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateBoardByLike(int board_no, int user_no) {
        try {
            boardDao.setSession(sqlSession);
            boardLikeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();
            if (boardLikeDao.getBoardLike(board_no, user_no) != null) {
                // 좋아요 한 게시물 일 때 -> 좋아요 취소
                boardLikeDao.deleteLike(board_no, user_no);

                // Board Set
                boardDao.updateBoardByLike(board_no, -1);

                // Response Message Set
                Board board = boardDao.getBoardByBoardNo(board_no);
                boolean boardLike = boardLikeDao.getBoardLike(board_no, user_no) != null;
                board.setUser_no(artistDao.getArtistByArtistNo(board.getArtist_no()).getUser_no());
                message.put("like_number", board.getLike_number());
                message.put("board_like", boardLike);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_LIKE_BOARD_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            } else {
                // BoardLike Set - 좋아요 하지 않은 게시물일 때 -> 좋아요
                BoardLike boardLike = new BoardLike();
                boardLike.setBoard_no(board_no);
                boardLike.setUser_no(user_no);
                String d = Time.TimeFormatHMS();
                boardLike.setReg_date(d);
                boardLikeDao.insertLike(boardLike);

                // Board Set
                boardDao.updateBoardByLike(board_no, 1);

                // Response Message Set
                Board board = boardDao.getBoardByBoardNo(board_no);
                boolean boardLikeBool = boardLikeDao.getBoardLike(board_no, user_no) != null;
                board.setUser_no(artistDao.getArtistByArtistNo(board.getArtist_no()).getUser_no());
                message.put("like_number", board.getLike_number());
                message.put("board_like", boardLikeBool);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LIKE_BOARD_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

}
