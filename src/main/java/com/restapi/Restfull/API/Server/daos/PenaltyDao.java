package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.PenaltyMapper;
import com.restapi.Restfull.API.Server.models.Penalty;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PenaltyDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertPenalty(Penalty penalty){
        PenaltyMapper penaltyMapper = sqlSession.getMapper(PenaltyMapper.class);
        penaltyMapper.insertPenalty(penalty);
    }

    public List<Penalty> getPenaltyListByUserNo(int user_no){
        PenaltyMapper penaltyMapper = sqlSession.getMapper(PenaltyMapper.class);
        return penaltyMapper.getPenaltyListByUserNo(user_no);
    }

    public List<Penalty> getPenaltyListByArtistNo(int artist_no){
        PenaltyMapper penaltyMapper = sqlSession.getMapper(PenaltyMapper.class);
        return penaltyMapper.getPenaltyListByArtistNo(artist_no);
    }

    public List<Penalty> getPenaltyList(){
        PenaltyMapper penaltyMapper = sqlSession.getMapper(PenaltyMapper.class);
        return penaltyMapper.getPenaltyList();
    }
}
