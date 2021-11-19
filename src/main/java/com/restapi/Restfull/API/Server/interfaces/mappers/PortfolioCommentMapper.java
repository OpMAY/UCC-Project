package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.PortfolioComment;

import java.util.Collection;
import java.util.List;

public interface PortfolioCommentMapper {
    List<PortfolioComment> getCommentListByPortfolioNoRefresh(int portfolio_no, String reg_date, int comment_no);

    List<PortfolioComment> getCommentListByUserNo(int user_no);

    void insertComment(PortfolioComment portfolioComment);

    void deleteComment(int comment_no);

    void updateComment(PortfolioComment portfolioComment);

    PortfolioComment getCommentByCommentNo(int comment_no);

    List<PortfolioComment> getCommentNumberByPortfolioNo(int portfolio_no);

    List<PortfolioComment> getCommentListByPortfolioNo(int portfolio_no);

    void updateAllCommentUserInfo(int user_no, String commenter_name, String profile_img);
}
