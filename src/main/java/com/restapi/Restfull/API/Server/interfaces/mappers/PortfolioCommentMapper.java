package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.PortfolioComment;

import java.util.Collection;
import java.util.List;

public interface PortfolioCommentMapper {
    List<PortfolioComment> getCommentListByPortfolioNo(int portfolio_no, int start_index, int end_index);

    List<PortfolioComment> getCommentListByUserNo(int user_no);

    void insertComment(PortfolioComment portfolioComment);

    void deleteComment(int comment_no);

    void updateComment(PortfolioComment portfolioComment);

    PortfolioComment getCommentByCommentNo(int comment_no);

    List<PortfolioComment> getCommentNumberByPortfolioNo(int portfolio_no);
}
