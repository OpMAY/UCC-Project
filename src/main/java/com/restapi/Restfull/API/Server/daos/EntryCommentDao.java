package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.BoardCommentMapper;
import com.restapi.Restfull.API.Server.interfaces.mappers.EntryCommentMapper;
import com.restapi.Restfull.API.Server.models.EntryComment;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryCommentDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<EntryComment> getCommentListByEntryNoRefresh(int entry_no, EntryComment entryComment) {
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        return entryCommentMapper.getCommentListByEntryNoRefresh(entry_no, entryComment.getReg_date(), entryComment.getEntry_comment_no());
    }

    public List<EntryComment> getCommentListByUserNo(int user_no) {
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        return entryCommentMapper.getCommentListByUserNo(user_no);
    }

    public void insertComment(EntryComment entryComment) {
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        entryCommentMapper.insertComment(entryComment);
    }

    public void deleteComment(int entry_comment_no) {
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        entryCommentMapper.deleteComment(entry_comment_no);
    }

    public EntryComment getEntryCommentByCommentNo(int entry_comment_no) {
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        return entryCommentMapper.getEntryCommentByCommentNo(entry_comment_no);
    }

    public void updateComment(EntryComment entryComment) {
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        entryCommentMapper.updateComment(entryComment);
    }

    public List<EntryComment> getCommentNumberByEntryNo(int entry_no) {
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        return entryCommentMapper.getCommentNumberByEntryNo(entry_no);
    }

    public List<EntryComment> getCommentListByEntryNo(int entry_no) {
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        return entryCommentMapper.getCommentListByEntryNo(entry_no);
    }

    public void updateAllCommentUserInfo(int user_no, String commenter_name, String profile_img){
        EntryCommentMapper entryCommentMapper = sqlSession.getMapper(EntryCommentMapper.class);
        entryCommentMapper.updateAllCommentUserInfo(user_no, commenter_name, profile_img);
    }
}
