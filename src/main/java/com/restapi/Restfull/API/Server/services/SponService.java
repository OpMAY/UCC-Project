package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.SponDao;
import com.restapi.Restfull.API.Server.interfaces.mappers.SponMapper;
import com.restapi.Restfull.API.Server.models.Spon;
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
public class SponService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private SponDao sponDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertSpon(Spon spon){
        sponDao.setSession(sqlSession);
        sponDao.insertSpon(spon);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Spon> getSponList(){
        sponDao.setSession(sqlSession);
        return sponDao.getSponList();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Spon> getSponListByArtistNo(int artist_no){
        sponDao.setSession(sqlSession);
        return sponDao.getSponListByArtistNo(artist_no);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Spon> getSponListByUserNo(int user_no){
        sponDao.setSession(sqlSession);
        return sponDao.getSponListByUserNo(user_no);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Spon> getSponListByBoardNo(int board_no){
        sponDao.setSession(sqlSession);
        return sponDao.getSponListByBoardNo(board_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Spon getSponAfterSpon(int user_no, int artist_no, Date spon_date){
        sponDao.setSession(sqlSession);
        return sponDao.getSponAfterSpon(user_no, artist_no, spon_date);
    }
}
