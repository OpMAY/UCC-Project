package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.LoudSourcingEntryMapper;
import com.restapi.Restfull.API.Server.models.LoudSourcingEntry;
import com.restapi.Restfull.API.Server.response.DataListSortType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoudSourcingEntryDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertEntry(LoudSourcingEntry loudSourcingEntry) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        loudSourcingEntryMapper.insertEntry(loudSourcingEntry);
    }

    public List<LoudSourcingEntry> getEntryListByLoudSourcingNoRefresh(int loudsourcing_no, String sort, LoudSourcingEntry entry) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return loudSourcingEntryMapper.getEntryListByLoudSourcingNoSortRecentRefresh(loudsourcing_no, entry.getEntry_no(), entry.getReg_date());
        else
            return loudSourcingEntryMapper.getEntryListByLoudSourcingNoSortFankokRefresh(loudsourcing_no, entry.getEntry_no(), entry.getFan_number());
    }

    public List<LoudSourcingEntry> getEntryListByLoudSourcingNo(int loudsourcing_no, String sort) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return loudSourcingEntryMapper.getEntryListByLoudSourcingNoSortRecent(loudsourcing_no);
        else
            return loudSourcingEntryMapper.getEntryListByLoudSourcingNoSortFankok(loudsourcing_no);
    }

    public List<LoudSourcingEntry> getEntryListByArtistNo(int artist_no) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        return loudSourcingEntryMapper.getEntryListByArtistNo(artist_no);
    }

    public LoudSourcingEntry getEntryByEntryNo(int entry_no) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        return loudSourcingEntryMapper.getEntryByEntryNo(entry_no);
    }

    public void updateEntry(LoudSourcingEntry loudSourcingEntry) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        loudSourcingEntryMapper.updateEntry(loudSourcingEntry);
    }

    public void updateEntryByComment(LoudSourcingEntry loudSourcingEntry) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        loudSourcingEntryMapper.updateEntryByComment(loudSourcingEntry);
    }

    public void updateEntryByVote(LoudSourcingEntry loudSourcingEntry) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        loudSourcingEntryMapper.updateEntryByVote(loudSourcingEntry);
    }

    public void updateEntryByVisit(LoudSourcingEntry loudSourcingEntry) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        loudSourcingEntryMapper.updateEntryByVisit(loudSourcingEntry);
    }

    public void updateEntryByFankok(LoudSourcingEntry loudSourcingEntry) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        loudSourcingEntryMapper.updateEntryByFankok(loudSourcingEntry);
    }

    public void deleteEntry(int entry_no) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        loudSourcingEntryMapper.deleteEntry(entry_no);
    }

    public LoudSourcingEntry getEntryByArtistNOAndLoudSourcingNo(int artist_no, int loudsourcing_no) {
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        return loudSourcingEntryMapper.getEntryByArtistNOAndLoudSourcingNo(artist_no, loudsourcing_no);
    }

    public int getEntryListNumByLoudsourcingNo(int loudsourcing_no){
        LoudSourcingEntryMapper loudSourcingEntryMapper = sqlSession.getMapper(LoudSourcingEntryMapper.class);
        return loudSourcingEntryMapper.getEntryListNum(loudsourcing_no).size();
    }


}
