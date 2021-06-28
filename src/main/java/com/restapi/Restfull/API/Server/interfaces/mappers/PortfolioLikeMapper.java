package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.PortfolioLike;

import java.util.List;

public interface PortfolioLikeMapper {
    void insertLike(PortfolioLike portfolioLike);

    void deleteLike(int portfolio_no, int user_no);

    List<PortfolioLike> getPortfolioLikeByPortfolioNo(int portfolio_no);

    List<PortfolioLike> getPortfolioLikeByUserNo(int user_no);

    PortfolioLike getPortfolioLike(int portfolio_no, int user_no);
}
