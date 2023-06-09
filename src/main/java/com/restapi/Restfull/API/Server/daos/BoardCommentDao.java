package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.BoardCommentMapper;
import com.restapi.Restfull.API.Server.models.BoardComment;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardCommentDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<BoardComment> getCommentListByBoardNoRefresh(int board_no, BoardComment boardComment) {
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return boardCommentMapper.getCommentListByBoardNoRefresh(board_no, boardComment.getReg_date(), boardComment.getComment_no());
    }

    public List<BoardComment> getCommentListByBoardNo(int board_no){
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return boardCommentMapper.getCommentListByBoardNo(board_no);
    }

    public List<BoardComment> getCommentListByUserNo(int user_no) {
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return boardCommentMapper.getCommentListByUserNo(user_no);
    }

    public void insertComment(BoardComment boardComment) {
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        boardCommentMapper.insertComment(boardComment);
    }

    public void deleteComment(int comment_no) {
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        boardCommentMapper.deleteComment(comment_no);
    }

    public BoardComment getCommentByCommentNo(int comment_no) {
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return boardCommentMapper.getCommentByCommentNo(comment_no);
    }

    public void updateComment(BoardComment boardComment) {
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        boardCommentMapper.updateComment(boardComment);
    }

    public List<BoardComment> getCommentNumberByBoardNo(int board_no) {
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return boardCommentMapper.getCommentNumberByBoardNo(board_no);
    }

    public void updateAllCommentUserInfo(int user_no, String commenter_name, String profile_img){
        BoardCommentMapper boardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        boardCommentMapper.updateAllCommentUserInfo(user_no, commenter_name, profile_img);
    }
}
