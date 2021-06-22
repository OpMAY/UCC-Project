package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.SubscribeDao;
import com.restapi.Restfull.API.Server.models.Subscribe;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class SubscribeService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private SubscribeDao subscribeDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertSubscribe(Subscribe subscribe){
        subscribeDao.setSession(sqlSession);
        subscribeDao.insertSubscribe(subscribe);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSubscribe(int user_no, int artist_no){
        subscribeDao.setSession(sqlSession);
        subscribeDao.deleteSubscribe(user_no, artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Subscribe getSubscribeInfoByUserNoANDArtistNo(int user_no, int artist_no){
        subscribeDao.setSession(sqlSession);
        return subscribeDao.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Subscribe> getSubscribeListByArtistNo(int artist_no){
        subscribeDao.setSession(sqlSession);
        return subscribeDao.getSubscribeListByArtistNo(artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Subscribe> getSubscribeListByUserNo(int user_no){
        subscribeDao.setSession(sqlSession);
        return subscribeDao.getSubscribeListByUserNo(user_no);
    }
}
