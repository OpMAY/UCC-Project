package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.PortfolioDao;
import com.restapi.Restfull.API.Server.models.Portfolio;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class PortfolioService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private PortfolioDao portfolioDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Portfolio> getPortfolioListByArtistNo(int artist_no){
        portfolioDao.setSession(sqlSession);
        return portfolioDao.getPortfolioListByArtistNo(artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Portfolio> getPortfolioListByTypeVOD(String type){
        portfolioDao.setSession(sqlSession);
        return portfolioDao.getPortfolioListByTypeVOD(type);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Portfolio getPortfolioByPortfolioNo(int portfolio_no){
        portfolioDao.setSession(sqlSession);
        return portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertPortfolio(Portfolio portfolio){
        portfolioDao.setSession(sqlSession);
        portfolioDao.insertPortfolio(portfolio);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePortfolio(Portfolio portfolio){
        portfolioDao.setSession(sqlSession);
        portfolioDao.updatePortfolio(portfolio);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePortfolio(int portfolio_no){
        portfolioDao.setSession(sqlSession);
        portfolioDao.deletePortfolio(portfolio_no);
    }

    /**
     * COMMENT, LIKE 추가 시 숫자 증가, DELETE 시 number = -1, 추가 시 number = 1
     * **/
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePortfolioByComment(int portfolio_no, int number){
        portfolioDao.setSession(sqlSession);
        portfolioDao.updatePortfolioByComment(portfolio_no, number);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePortfolioByLike(int portfolio_no, int number){
        portfolioDao.setSession(sqlSession);
        portfolioDao.updatePortfolioByLike(portfolio_no, number);
    }

}
