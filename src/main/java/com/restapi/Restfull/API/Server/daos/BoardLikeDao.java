package com.restapi.Restfull.API.Server.daos;


import com.restapi.Restfull.API.Server.interfaces.mappers.BoardLikeMapper;
import com.restapi.Restfull.API.Server.models.BoardLike;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardLikeDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertLike(BoardLike boardLike) {
        BoardLikeMapper boardLikeMapper = sqlSession.getMapper(BoardLikeMapper.class);
        boardLikeMapper.insertLike(boardLike);
    }

    public void deleteLike(int board_no, int user_no) {
        BoardLikeMapper boardLikeMapper = sqlSession.getMapper(BoardLikeMapper.class);
        boardLikeMapper.deleteLike(board_no, user_no);
    }

    public List<BoardLike> getBoardLikeByBoardNo(int board_no) {
        BoardLikeMapper boardLikeMapper = sqlSession.getMapper(BoardLikeMapper.class);
        return boardLikeMapper.getBoardLikeByBoardNo(board_no);
    }

    public List<BoardLike> getBoardLikeByUserNo(int user_no) {
        BoardLikeMapper boardLikeMapper = sqlSession.getMapper(BoardLikeMapper.class);
        return boardLikeMapper.getBoardLikeByUserNo(user_no);
    }

    public BoardLike getBoardLike(int board_no, int user_no) {
        BoardLikeMapper boardLikeMapper = sqlSession.getMapper(BoardLikeMapper.class);
        return boardLikeMapper.getBoardLike(board_no, user_no);
    }
}
