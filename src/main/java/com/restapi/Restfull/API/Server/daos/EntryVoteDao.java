package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.EntryVoteMapper;
import com.restapi.Restfull.API.Server.models.EntryVote;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryVoteDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertVote(EntryVote entryVote) {
        EntryVoteMapper entryVoteMapper = sqlSession.getMapper(EntryVoteMapper.class);
        entryVoteMapper.insertVote(entryVote);
    }

    public void deleteVote(int user_no, int entry_no) {
        EntryVoteMapper entryVoteMapper = sqlSession.getMapper(EntryVoteMapper.class);
        entryVoteMapper.deleteVote(entry_no, user_no);
    }

    public List<EntryVote> getEntryVoteByEntryNo(int entry_no) {
        EntryVoteMapper entryVoteMapper = sqlSession.getMapper(EntryVoteMapper.class);
        return entryVoteMapper.getEntryVoteByEntryNo(entry_no);
    }

    public List<EntryVote> getEntryVoteByUserNo(int user_no) {
        EntryVoteMapper entryVoteMapper = sqlSession.getMapper(EntryVoteMapper.class);
        return entryVoteMapper.getEntryVoteByUserNo(user_no);
    }

    public EntryVote getEntryVote(int user_no, int entry_no) {
        EntryVoteMapper entryVoteMapper = sqlSession.getMapper(EntryVoteMapper.class);
        return entryVoteMapper.getEntryVote(user_no, entry_no);
    }

}
