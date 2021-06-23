package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.PortfolioCommentMapper;
import com.restapi.Restfull.API.Server.models.PortfolioComment;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PortfolioCommentDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<PortfolioComment> getCommentListByPortfolioNo(int portfolio_no){
        PortfolioCommentMapper portfolioCommentMapper = sqlSession.getMapper(PortfolioCommentMapper.class);
        return portfolioCommentMapper.getCommentListByPortfolioNo(portfolio_no);
    }

    public List<PortfolioComment> getCommentListByUserNo(int user_no){
        PortfolioCommentMapper portfolioCommentMapper = sqlSession.getMapper(PortfolioCommentMapper.class);
        return portfolioCommentMapper.getCommentListByUserNo(user_no);
    }

    public void insertComment(PortfolioComment portfolioComment){
        PortfolioCommentMapper portfolioCommentMapper = sqlSession.getMapper(PortfolioCommentMapper.class);
        portfolioCommentMapper.insertComment(portfolioComment);
    }

    public void deleteComment(int comment_no){
        PortfolioCommentMapper portfolioCommentMapper = sqlSession.getMapper(PortfolioCommentMapper.class);
        portfolioCommentMapper.deleteComment(comment_no);
    }

    public void updateComment(PortfolioComment portfolioComment){
        PortfolioCommentMapper portfolioCommentMapper = sqlSession.getMapper(PortfolioCommentMapper.class);
        portfolioCommentMapper.updateComment(portfolioComment);
    }

    public PortfolioComment getCommentByCommentNo(int comment_no){
        PortfolioCommentMapper portfolioCommentMapper = sqlSession.getMapper(PortfolioCommentMapper.class);
        return portfolioCommentMapper.getCommentByCommentNo(comment_no);
    }

}
