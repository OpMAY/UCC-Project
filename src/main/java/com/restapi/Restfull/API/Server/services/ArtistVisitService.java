package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistVisitDao;
import com.restapi.Restfull.API.Server.models.ArtistVisit;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class ArtistVisitService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private ArtistVisitDao artistVisitDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertVisit(ArtistVisit artistVisit) {
        artistVisitDao.setSession(sqlSession);
        artistVisitDao.insertVisit(artistVisit);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ArtistVisit> getArtistVisitByArtistNo(int artist_no, String visit_date) {
        artistVisitDao.setSession(sqlSession);
        return artistVisitDao.getArtistVisitByArtistNo(artist_no, visit_date);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArtistVisit getArtistVisit(int artist_no, int user_no, String visit_date) {
        artistVisitDao.setSession(sqlSession);
        return artistVisitDao.getArtistVisit(artist_no, user_no, visit_date);
    }
}
