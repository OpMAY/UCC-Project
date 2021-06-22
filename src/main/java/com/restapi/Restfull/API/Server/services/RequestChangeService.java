package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistDao;
import com.restapi.Restfull.API.Server.daos.RequestChangeDao;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.RequestChange;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class RequestChangeService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private RequestChangeDao requestChangeDao;

    @Autowired
    private ArtistDao artistDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<RequestChange> getAllRequests(){
        requestChangeDao.setSession(sqlSession);
        return requestChangeDao.getAllRequests();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RequestChange getRequestByUserNo(int user_no){
        requestChangeDao.setSession(sqlSession);
        return requestChangeDao.getRequestByUserNo(user_no);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequest(RequestChange rc, Artist artist){
        requestChangeDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        requestChangeDao.insertRequest(rc);
        artistDao.insertArtist(artist);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean artistNameCheck(String artist_name){
        requestChangeDao.setSession(sqlSession);
        return requestChangeDao.artistNameCheck(artist_name);
    }
}
