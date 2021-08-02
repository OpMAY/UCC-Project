package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Portfolio;

import java.util.List;

public interface PortfolioMapper {
    List<Portfolio> getPortfolioListByArtistNo(int artist_no);

    Portfolio getPortfolioByPortfolioNo(int portfolio_no);

    List<Portfolio> getPortfolioListByTypeVODSortRecentRefresh(String type, String reg_date, int portfolio_no);

    List<Portfolio> getPortfolioListByTypeVODSortTitleRefresh(String type, String title, int portfolio_no);

    List<Portfolio> getPortfolioListByTypeVODSortFanNumberRefresh(String type, int fan_number, int portfolio_no);

    List<Portfolio> getPortfolioListByTypeVODSortRecent(String type);

    List<Portfolio> getPortfolioListByTypeVODSortTitle(String type);

    List<Portfolio> getPortfolioListByTypeVODSortFanNumber(String type);

    void insertPortfolio(Portfolio portfolio);

    void updatePortfolio(Portfolio portfolio);

    void deletePortfolio(int portfolio_no);

    void updatePortfolioByComment(Portfolio portfolio);

    void updatePortfolioByLike(Portfolio portfolio);

    void updatePortfolioByVisit(Portfolio portfolio);

    void updatePortfolioByFankok(Portfolio portfolio);

    List<Portfolio> getPortfolioListByRandom(String type);

    List<Portfolio> SearchPortfolioLimit(String query);

    List<Portfolio> getPortfolioListByArtistNoLimit(int artist_no);

    void insertFiles(Portfolio portfolio);

    List<Portfolio> getPortfolioByTypeAdmin(int artist_no, String type);

    List<Portfolio> getPortfolioListSortRecentRefresh(int artist_no, String revise_date, int portfolio_no);

    List<Portfolio> getPortfolioListSortWordRefresh(int artist_no, String title, int portfolio_no);

    List<Portfolio> getPortfolioListSortFanNumberRefresh(int artist_no, int fan_number, int portfolio_no);

    List<Portfolio> getPortfolioListSortRecent(int artist_no);

    List<Portfolio> getPortfolioListSortWord(int artist_no);

    List<Portfolio> getPortfolioListSortFanNumber(int artist_no);
}
