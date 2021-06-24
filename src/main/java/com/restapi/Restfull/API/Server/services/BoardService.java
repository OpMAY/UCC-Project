package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.BoardDao;
import com.restapi.Restfull.API.Server.models.Board;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class BoardService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private BoardDao boardDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Board> getBoardListByArtistNo(int artist_no) {
        boardDao.setSession(sqlSession);
        return boardDao.getBoardListByArtistNo(artist_no);
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
    public void updateBoard(Board board) {
        boardDao.setSession(sqlSession);
        boardDao.updateBoard(board);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBoard(int board_no) {
        boardDao.setSession(sqlSession);
        boardDao.deleteBoard(board_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBoardByComment(int board_no, int number) {
        boardDao.setSession(sqlSession);
        boardDao.updateBoardByComment(board_no, number);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBoardByLike(int board_no, int number) {
        boardDao.setSession(sqlSession);
        boardDao.updateBoardByLike(board_no, number);
    }
}
