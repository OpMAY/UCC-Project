package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Portfolio;

import java.util.List;

public interface PortfolioMapper {
    List<Portfolio> getPortfolioListByArtistNo(int artist_no);

    Portfolio getPortfolioByPortfolioNo(int portfolio_no);

    List<Portfolio> getPortfolioListByTypeVODSortRecent(String type, int start_index, int end_index);

    List<Portfolio> getPortfolioListByTypeVODSortTitle(String type, int start_index, int end_index);

    List<Portfolio> getPortfolioListByTypeVODSortFanNumber(String type, int start_index, int end_index);

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

    List<Portfolio> getPortfolioListSortRecent(int artist_no, int start_index);

    List<Portfolio> getPortfolioListSortWord(int artist_no, int start_index);
}
