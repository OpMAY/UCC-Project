package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.PortfolioLikeMapper;
import com.restapi.Restfull.API.Server.models.PortfolioLike;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PortfolioLikeDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertLike(PortfolioLike portfolioLike) {
        PortfolioLikeMapper portfolioLikeMapper = sqlSession.getMapper(PortfolioLikeMapper.class);
        portfolioLikeMapper.insertLike(portfolioLike);
    }

    public void deleteLike(int portfolio_no, int user_no) {
        PortfolioLikeMapper portfolioLikeMapper = sqlSession.getMapper(PortfolioLikeMapper.class);
        portfolioLikeMapper.deleteLike(portfolio_no, user_no);
    }

    public List<PortfolioLike> getPortfolioLikeByPortfolioNo(int portfolio_no) {
        PortfolioLikeMapper portfolioLikeMapper = sqlSession.getMapper(PortfolioLikeMapper.class);
        return portfolioLikeMapper.getPortfolioLikeByPortfolioNo(portfolio_no);
    }

    public List<PortfolioLike> getPortfolioLikeByUserNo(int user_no) {
        PortfolioLikeMapper portfolioLikeMapper = sqlSession.getMapper(PortfolioLikeMapper.class);
        return portfolioLikeMapper.getPortfolioLikeByUserNo(user_no);
    }

    public PortfolioLike getPortfolioLike(int portfolio_no, int user_no) {
        PortfolioLikeMapper portfolioLikeMapper = sqlSession.getMapper(PortfolioLikeMapper.class);
        return portfolioLikeMapper.getPortfolioLike(portfolio_no, user_no);
    }

}
