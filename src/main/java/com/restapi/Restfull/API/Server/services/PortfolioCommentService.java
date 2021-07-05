package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.PortfolioCommentDao;
import com.restapi.Restfull.API.Server.interfaces.mappers.PortfolioCommentMapper;
import com.restapi.Restfull.API.Server.models.PortfolioComment;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class PortfolioCommentService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private PortfolioCommentDao portfolioCommentDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PortfolioComment> getCommentListByPortfolioNo(int portfolio_no) {
        portfolioCommentDao.setSession(sqlSession);
        return portfolioCommentDao.getCommentListByPortfolioNo(portfolio_no, 0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PortfolioComment> getCommentListByUserNo(int user_no) {
        portfolioCommentDao.setSession(sqlSession);
        return portfolioCommentDao.getCommentListByUserNo(user_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertComment(PortfolioComment portfolioComment) {
        portfolioCommentDao.setSession(sqlSession);
        portfolioCommentDao.insertComment(portfolioComment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteComment(int comment_no) {
        portfolioCommentDao.setSession(sqlSession);
        portfolioCommentDao.deleteComment(comment_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateComment(PortfolioComment portfolioComment) {
        portfolioCommentDao.setSession(sqlSession);
        portfolioCommentDao.updateComment(portfolioComment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PortfolioComment getCommentByCommentNo(int comment_no) {
        portfolioCommentDao.setSession(sqlSession);
        return portfolioCommentDao.getCommentByCommentNo(comment_no);
    }
}
