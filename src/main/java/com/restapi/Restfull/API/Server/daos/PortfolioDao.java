package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.PortfolioMapper;
import com.restapi.Restfull.API.Server.models.Portfolio;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PortfolioDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<Portfolio> getPortfolioListByArtistNo(int artist_no) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        return portfolioMapper.getPortfolioListByArtistNo(artist_no);
    }

    public List<Portfolio> getPortfolioListByTypeVOD(String type) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        return portfolioMapper.getPortfolioListByTypeVOD(type);
    }

    public Portfolio getPortfolioByPortfolioNo(int portfolio_no) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        return portfolioMapper.getPortfolioByPortfolioNo(portfolio_no);
    }

    public void insertPortfolio(Portfolio portfolio) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        portfolioMapper.insertPortfolio(portfolio);
    }

    public void updatePortfolio(Portfolio portfolio) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        portfolioMapper.updatePortfolio(portfolio);
    }

    public void deletePortfolio(int portfolio_no) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        portfolioMapper.deletePortfolio(portfolio_no);
    }

    public void updatePortfolioByComment(int portfolio_no, int number) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        portfolioMapper.updatePortfolioByComment(portfolio_no, number);
    }

    public void updatePortfolioByLike(int portfolio_no, int number) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        portfolioMapper.updatePortfolioByLike(portfolio_no, number);
    }
}
