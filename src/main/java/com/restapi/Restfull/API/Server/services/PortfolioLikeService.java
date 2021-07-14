package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.PortfolioLikeDao;
import com.restapi.Restfull.API.Server.models.PortfolioLike;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class PortfolioLikeService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private PortfolioLikeDao portfolioLikeDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertLike(PortfolioLike portfolioLike) {
        portfolioLikeDao.setSession(sqlSession);
        portfolioLikeDao.insertLike(portfolioLike);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLike(int portfolio_no, int user_no) {
        portfolioLikeDao.setSession(sqlSession);
        portfolioLikeDao.deleteLike(portfolio_no, user_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PortfolioLike> getPortfolioLikeByPortfolioNo(int portfolio_no) {
        portfolioLikeDao.setSession(sqlSession);
        return portfolioLikeDao.getPortfolioLikeByPortfolioNo(portfolio_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PortfolioLike> getPortfolioLikeByUserNo(int user_no) {
        portfolioLikeDao.setSession(sqlSession);
        return portfolioLikeDao.getPortfolioLikeByUserNo(user_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PortfolioLike getPortfolioLike(int portfolio_no, int user_no) {
        portfolioLikeDao.setSession(sqlSession);
        return portfolioLikeDao.getPortfolioLike(portfolio_no, user_no);
    }
}
