package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
                    message.put("Boards", boardList);
                }
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetBoard()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetBoard(int user_no, int board_no) {
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

            // GET DATA FROM DB
            Board board = boardDao.getBoardByBoardNo(board_no);
            int visit_number = board.getVisit_number();
            board.setVisit_number((visit_number + 1));
            List<BoardComment> commentList = boardCommentDao.getCommentListByBoardNo(board_no);
            boolean boardLike = boardLikeDao.getBoardLike(board_no, user_no) != null;

            // RESPONSE MESSAGE SET
            board.setBoardCommentList(commentList);
            message.put("Board", board);
            message.put("BoardLike", boardLike);
            boardDao.updateBoardByVisit(board);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_SUCCESS, message.getHashMap("GetBoard()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Board> getBoardList() {
        boardDao.setSession(sqlSession);
        return boardDao.getBoardList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Board getBoardByBoardNo(int board_no) {
        boardDao.setSession(sqlSession);
        return boardDao.getBoardByBoardNo(board_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBoard(Board board) {
        boardDao.setSession(sqlSession);
        boardDao.insertBoard(board);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateBoard(Board board) {
        try {
            boardDao.setSession(sqlSession);
            Message message = new Message();
            // Update Method
            boardDao.updateBoard(board);

            Board board1 = boardDao.getBoardByBoardNo(board.getBoard_no());

            message.put("Board", board1);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.EDIT_BOARD_SUCCESS, message.getHashMap("EditBoard()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deleteBoard(int board_no) {
        try {
            boardDao.setSession(sqlSession);
            Message message = new Message();
            boardDao.deleteBoard(board_no);
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
                boardCommentDao.insertComment(boardComment);

                // Board SET
                boardDao.updateBoardByComment(boardComment.getBoard_no(), 1);

                // Response Message SET
                List<BoardComment> boardCommentList = boardCommentDao.getCommentListByBoardNo(boardComment.getBoard_no());
                Board board = boardDao.getBoardByBoardNo(boardComment.getBoard_no());
                board.setBoardCommentList(boardCommentList);
                message.put("Board", board);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_COMMENT_INSERT_SUCCESS, message.getHashMap("InsertBoardComment()")), HttpStatus.OK);
            } else {
                // DB SET
                boardCommentDao.deleteComment(boardComment.getComment_no());

                // Board SET
                boardDao.updateBoardByComment(boardComment.getBoard_no(), -1);

                // Response Message Set
                List<BoardComment> boardCommentList = boardCommentDao.getCommentListByBoardNo(boardComment.getBoard_no());
                Board board = boardDao.getBoardByBoardNo(boardComment.getBoard_no());
                board.setBoardCommentList(boardCommentList);
                message.put("Board", board);
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
            Message message = new Message();
            if (boardLikeDao.getBoardLike(board_no, user_no) != null) {
                // 좋아요 한 게시물 일 때 -> 좋아요 취소
                boardLikeDao.deleteLike(board_no, user_no);

                // Board Set
                boardDao.updateBoardByLike(board_no, -1);

                // Response Message Set
                Board board = boardDao.getBoardByBoardNo(board_no);
                boolean boardLike = boardLikeDao.getBoardLike(board_no, user_no) != null;
                message.put("Board", board);
                message.put("BoardLike", boardLike);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_LIKE_BOARD_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            } else {
                // BoardLike Set - 좋아요 하지 않은 게시물일 때 -> 좋아요
                BoardLike boardLike = new BoardLike();
                boardLike.setBoard_no(board_no);
                boardLike.setUser_no(user_no);
                boardLikeDao.insertLike(boardLike);

                // Board Set
                boardDao.updateBoardByLike(board_no, 1);

                // Response Message Set
                Board board = boardDao.getBoardByBoardNo(board_no);
                boolean boardLikeBool = boardLikeDao.getBoardLike(board_no, user_no) != null;
                message.put("Board", board);
                message.put("BoardLike", boardLikeBool);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LIKE_BOARD_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetBoardListByArtistNo(int artist_no, int start_index){
        try{
            boardDao.setSession(sqlSession);
            Message message = new Message();
            List<Board> boardList = boardDao.getBoardListByArtistNo(artist_no);
            List<Board> response_boardList = new ArrayList<Board>();
            for(int i = 0; i < start_index + 20; i++){
                response_boardList.add(boardList.get(i));
                if(boardList.size() == i + 1)
                    break;
            }
            message.put("Boards", response_boardList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_LIST_SUCCESS, message.getHashMap("GetArtistBoardList()")), HttpStatus.OK);
        }catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
