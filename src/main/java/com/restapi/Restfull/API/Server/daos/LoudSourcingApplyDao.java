package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.LoudSourcingApplyMapper;
import com.restapi.Restfull.API.Server.models.LoudSourcingApply;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoudSourcingApplyDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public LoudSourcingApply getLoudSourcingApplyByArtistNoAndLoudSourcingNo(int artist_no, int loudsourcing_no) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        return loudSourcingApplyMapper.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artist_no, loudsourcing_no);
    }

    public List<LoudSourcingApply> getLoudSourcingApplyListByArtistNo(int artist_no) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        return loudSourcingApplyMapper.getLoudSourcingApplyListByArtistNo(artist_no);
    }

    public List<LoudSourcingApply> getLoudSourcingApplyListByLoudSourcingNo(int loudsourcing_no) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        return loudSourcingApplyMapper.getLoudSourcingApplyListByLoudSourcingNo(loudsourcing_no);
    }

    public void insertLoudSourcingApply(LoudSourcingApply loudSourcingApply) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        loudSourcingApplyMapper.insertLoudSourcingApply(loudSourcingApply);
    }

    public void deleteLoudSourcingApply(int artist_no, int loudsourcing_no) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        loudSourcingApplyMapper.deleteLoudSourcingApply(artist_no, loudsourcing_no);
    }

    public void updateApply(LoudSourcingApply loudSourcingApply) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        loudSourcingApplyMapper.updateApply(loudSourcingApply);
    }

    public List<LoudSourcingApply> getEntryNum(int loudsourcing_no) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        return loudSourcingApplyMapper.getEntryNum(loudsourcing_no);
    }

    public void updateApplyForJudge(LoudSourcingApply apply) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        loudSourcingApplyMapper.updateApplyForJudge(apply);
    }

    public void updateApplyForEnd(LoudSourcingApply apply){
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        loudSourcingApplyMapper.updateApplyForEnd(apply);
    }

    public List<LoudSourcingApply> getLoudSourcingApplyListByLoudSourcingNoPreSelected(int loudsourcing_no) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        return loudSourcingApplyMapper.getLoudSourcingApplyListByLoudSourcingNoPreSelected(loudsourcing_no);
    }

    public List<LoudSourcingApply> getLoudSourcingApplyListByLoudSourcingNoUnPreSelected(int loudsourcing_no){
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        return loudSourcingApplyMapper.getLoudSourcingApplyListByLoudSourcingNoUnPreSelected(loudsourcing_no);
    }

    public List<LoudSourcingApply> getLoudSourcingApplyListByLoudSourcingNoSelected(int loudsourcing_no) {
        LoudSourcingApplyMapper loudSourcingApplyMapper = sqlSession.getMapper(LoudSourcingApplyMapper.class);
        return loudSourcingApplyMapper.getLoudSourcingApplyListByLoudSourcingNoSelected(loudsourcing_no);
    }
}
