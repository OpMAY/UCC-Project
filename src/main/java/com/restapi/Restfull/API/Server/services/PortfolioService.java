package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class PortfolioService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private PortfolioCommentDao portfolioCommentDao;

    @Autowired
    private PortfolioLikeDao portfolioLikeDao;

    @Autowired
    private UserDao userdao;

    @Autowired
    private ArtistDao artistDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetPortfolio(int user_no, int portfolio_no) {
        try {
            Message message = new Message();
            /** required Data
             * 1. Portfolio - DONE
             * 2. Portfolio Comment - DONE
             * 3. Portfolio Like - DONE
             * **/
            // Setting SQL SESSION
            portfolioDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            portfolioLikeDao.setSession(sqlSession);

            // GET DATA FROM DB
            Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
            portfolio.setVisit_number(portfolio.getVisit_number() + 1); // 조회수 증가
            List<PortfolioComment> commentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolio_no);
            boolean portfolioLike = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;

            /*if(portfolio.getType().equals(PortfolioType.FILE)){

            }*/

            // RESPONSE MESSAGE SET
            portfolio.setPortfolioCommentList(commentList);
            message.put("Portfolio", portfolio);
            message.put("PortfolioLike", portfolioLike);
            portfolioDao.updatePortfolioByVisit(portfolio);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetPortfolio()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Portfolio> getPortfolioListByArtistNo(int artist_no) {
        portfolioDao.setSession(sqlSession);
        return portfolioDao.getPortfolioListByArtistNo(artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getPortfolioListByTypeVOD(String type) {
        try {
            portfolioDao.setSession(sqlSession);
            Message message = new Message();

            message.put("VODList", portfolioDao.getPortfolioListByTypeVOD(type));

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.VOD_LIST_CALL_SUCCESS, message.getHashMap("GetVODList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Portfolio getPortfolioByPortfolioNo(int portfolio_no) {
        portfolioDao.setSession(sqlSession);
        return portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertPortfolio(Portfolio portfolio) {
        portfolioDao.setSession(sqlSession);
        portfolioDao.insertPortfolio(portfolio);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updatePortfolio(Portfolio portfolio) {
        try {
            portfolioDao.setSession(sqlSession);
            Message message = new Message();
            // Update Method
            portfolioDao.updatePortfolio(portfolio);

            Portfolio portfolio1 = portfolioDao.getPortfolioByPortfolioNo(portfolio.getPortfolio_no());

            message.put("Portfolio", portfolio1);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.EDIT_PORTFOLIO_SUCCESS, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deletePortfolio(int portfolio_no) {
        try {
            portfolioDao.setSession(sqlSession);
            Message message = new Message();
            portfolioDao.deletePortfolio(portfolio_no);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.DELETE_PORTFOLIO_SUCCESS, message.getHashMap("DeletePortfolio()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }

    }

    /**
     * COMMENT, LIKE 추가 시 숫자 증가, DELETE 시 number = -1, 추가 시 number = 1
     **/
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updatePortfolioByComment(PortfolioComment portfolioComment, String method) {
        try {
            portfolioCommentDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            userdao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();
            if (method.equals("UPDATE")) {
                // DB SET
                portfolioComment.setComment_private(false);
                User user = userdao.selectUserByUserNo(portfolioComment.getUser_no());
                Artist artist = artistDao.getArtistByUserNo(portfolioComment.getUser_no());
                if(artist != null){
                    portfolioComment.setCommenter_name(artist.getArtist_name());
                    portfolioComment.setProfile_img(artist.getArtist_profile_img());
                }else{
                    portfolioComment.setCommenter_name(user.getName());
                    portfolioComment.setProfile_img(user.getProfile_img());
                }
                portfolioCommentDao.insertComment(portfolioComment);

                // Portfolio SET
                portfolioDao.updatePortfolioByComment(portfolioComment.getPortfolio_no(), 1);

                // Response Message SET
                List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolioComment.getPortfolio_no());
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolioComment.getPortfolio_no());
                portfolio.setPortfolioCommentList(portfolioCommentList);
                message.put("Portfolio", portfolio);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.PORTFOLIO_COMMENT_INSERT_SUCCESS, message.getHashMap("InsertPortfolioComment()")), HttpStatus.OK);
            } else {
                // DB SET
                portfolioCommentDao.deleteComment(portfolioComment.getComment_no());

                // Portfolio SET

                portfolioDao.updatePortfolioByComment(portfolioComment.getPortfolio_no(), -1);

                // Response Message SET
                List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolioComment.getPortfolio_no());
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolioComment.getPortfolio_no());
                portfolio.setPortfolioCommentList(portfolioCommentList);
                message.put("Portfolio", portfolio);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.PORTFOLIO_COMMENT_DELETE_SUCCESS, message.getHashMap("DeletePortfolioComment()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updatePortfolioByLike(int portfolio_no, int user_no) {
        try {
            portfolioDao.setSession(sqlSession);
            portfolioLikeDao.setSession(sqlSession);
            Message message = new Message();

            if (portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null) {
                // 좋아요 한 게시물일 때 -> 좋아요 취소
                portfolioLikeDao.deleteLike(portfolio_no, user_no);

                // Portfolio SET
                portfolioDao.updatePortfolioByLike(portfolio_no, -1);

                // Response Message SET
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                boolean portfolioLike = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;
                message.put("Portfolio", portfolio);
                message.put("PortfolioLike", portfolioLike);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_LIKE_PORTFOLIO_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            } else {
                // Portfolio Like SET - 좋아요 하지 않은 게시물일 때 -> 좋아요
                PortfolioLike portfolioLike = new PortfolioLike();
                portfolioLike.setPortfolio_no(portfolio_no);
                portfolioLike.setUser_no(user_no);
                portfolioLikeDao.insertLike(portfolioLike);

                // Portfolio SET
                portfolioDao.updatePortfolioByLike(portfolio_no, 1);

                // Response Message SET
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                boolean portfolioLikeBool = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;
                message.put("Portfolio", portfolio);
                message.put("PortfolioLike", portfolioLikeBool);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LIKE_PORTFOLIO_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }

    }

}
