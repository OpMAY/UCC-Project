package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.ArtistVisitMapper;
import com.restapi.Restfull.API.Server.models.ArtistVisit;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ArtistVisitDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertVisit(ArtistVisit artistVisit) {
        ArtistVisitMapper artistVisitMapper = sqlSession.getMapper(ArtistVisitMapper.class);
        artistVisitMapper.insertVisit(artistVisit);
    }

    public List<ArtistVisit> getArtistVisitByArtistNo(int artist_no, Date visit_date) {
        ArtistVisitMapper artistVisitMapper = sqlSession.getMapper(ArtistVisitMapper.class);
        return artistVisitMapper.getArtistVisitByArtistNo(artist_no, visit_date);
    }

    public ArtistVisit getArtistVisit(int artist_no, int user_no, Date visit_date) {
        ArtistVisitMapper artistVisitMapper = sqlSession.getMapper(ArtistVisitMapper.class);
        return artistVisitMapper.getArtistVisit(artist_no, user_no, visit_date);
    }
}
