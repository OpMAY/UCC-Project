package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Portfolio;

import java.util.List;

public interface PortfolioMapper {
    List<Portfolio> getPortfolioListByArtistNo(int artist_no);

    Portfolio getPortfolioByPortfolioNo(int portfolio_no);

    List<Portfolio> getPortfolioListByTypeVOD(String type);

    void insertPortfolio(Portfolio portfolio);

    void updatePortfolio(Portfolio portfolio);

    void deletePortfolio(int portfolio_no);

    void updatePortfolioByComment(int portfolio_no, int number);

    void updatePortfolioByLike(int portfolio_no, int number);
}
