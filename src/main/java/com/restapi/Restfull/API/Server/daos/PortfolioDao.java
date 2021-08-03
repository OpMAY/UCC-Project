package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.interfaces.mappers.PortfolioMapper;
import com.restapi.Restfull.API.Server.models.Portfolio;
import com.restapi.Restfull.API.Server.response.DataListSortType;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
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

    public List<Portfolio> getPortfolioListByArtistNoLimit(int artist_no) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        return portfolioMapper.getPortfolioListByArtistNoLimit(artist_no);
    }

    public List<Portfolio> getPortfolioListByTypeVODSortRefresh(String type, String sort, Portfolio portfolio) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return portfolioMapper.getPortfolioListByTypeVODSortRecentRefresh(type, portfolio.getReg_date(), portfolio.getPortfolio_no());
        else if (sort.equals(DataListSortType.SORT_BY_WORD))
            return portfolioMapper.getPortfolioListByTypeVODSortTitleRefresh(type, portfolio.getTitle(), portfolio.getPortfolio_no());
        else
            return portfolioMapper.getPortfolioListByTypeVODSortFanNumberRefresh(type, portfolio.getFan_number(), portfolio.getPortfolio_no());
    }

    public List<Portfolio> getPortfolioListByTypeVODSort(String type, String sort) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return portfolioMapper.getPortfolioListByTypeVODSortRecent(type);
        else if (sort.equals(DataListSortType.SORT_BY_WORD))
            return portfolioMapper.getPortfolioListByTypeVODSortTitle(type);
        else
            return portfolioMapper.getPortfolioListByTypeVODSortFanNumber(type);
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
        Portfolio portfolio = portfolioMapper.getPortfolioByPortfolioNo(portfolio_no);
        portfolio.setComment_number(number);
        portfolioMapper.updatePortfolioByComment(portfolio);
    }

    public void updatePortfolioByLike(int portfolio_no, int number) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        Portfolio portfolio = portfolioMapper.getPortfolioByPortfolioNo(portfolio_no);
        portfolio.setLike_number(number);
        portfolioMapper.updatePortfolioByLike(portfolio);
    }

    public void updatePortfolioByVisit(Portfolio portfolio) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        portfolioMapper.updatePortfolioByVisit(portfolio);
    }

    public List<Portfolio> getPortfolioListByRandom() {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        return portfolioMapper.getPortfolioListByRandom("vod");
    }

    public List<Portfolio> SearchPortfolioLimit(String query) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        String sqlSearch = "%" + query + "%";
        return portfolioMapper.SearchPortfolioLimit(sqlSearch);
    }

    public void updatePortfolioByFankok(Portfolio portfolio) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        portfolioMapper.updatePortfolioByFankok(portfolio);
    }

    public void insertFiles(Portfolio portfolio) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        portfolioMapper.insertFiles(portfolio);
    }

    public List<Portfolio> getPortfolioByTypeAdmin(int artist_no, String type){
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        return portfolioMapper.getPortfolioByTypeAdmin(artist_no, type);
    }

    public List<Portfolio> getPortfolioListSortRefresh(int artist_no, String sort, Portfolio portfolio){
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        if(sort.equals(DataListSortType.SORT_BY_RECENT)){
            return portfolioMapper.getPortfolioListSortRecentRefresh(artist_no, portfolio.getRevise_date(), portfolio.getPortfolio_no());
        } else if (sort.equals(DataListSortType.SORT_BY_WORD)){
            return portfolioMapper.getPortfolioListSortWordRefresh(artist_no, portfolio.getTitle(), portfolio.getPortfolio_no());
        } else if (sort.equals(DataListSortType.SORT_BY_FANKOK)) {
            return portfolioMapper.getPortfolioListSortFanNumberRefresh(artist_no, portfolio.getFan_number(), portfolio.getPortfolio_no());
        } else {
            log.info("Wrong Sort Type");
            throw new BusinessException(new Exception());
        }
    }

    public List<Portfolio> getPortfolioListSort(int artist_no, String sort) {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        if(sort.equals(DataListSortType.SORT_BY_RECENT)){
            return portfolioMapper.getPortfolioListSortRecent(artist_no);
        } else if (sort.equals(DataListSortType.SORT_BY_WORD)){
            return portfolioMapper.getPortfolioListSortWord(artist_no);
        } else if (sort.equals(DataListSortType.SORT_BY_FANKOK)){
            return portfolioMapper.getPortfolioListSortFanNumber(artist_no);
        } else {
            log.info("Wrong Sort Type");
            throw new BusinessException(new Exception());
        }
    }

    public List<Portfolio> getPortfolioForCDN() {
        PortfolioMapper portfolioMapper = sqlSession.getMapper(PortfolioMapper.class);
        return portfolioMapper.getPortfolioForCDN();
    }
}
