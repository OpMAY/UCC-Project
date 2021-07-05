package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
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

import java.sql.Date;
import java.util.ArrayList;
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

    @Autowired
    private SubscribeDao subscribeDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetPortfolio(int user_no, int portfolio_no, int start_index) {
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
            artistDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);


            if(start_index > -1){
                List<PortfolioComment> commentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolio_no, start_index);
                List<PortfolioComment> resCommentList = new ArrayList<>();
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                for(PortfolioComment portfolioComment : commentList){
                    int commentWriter = portfolioComment.getUser_no();
                    if(subscribeDao.getSubscribeInfoByUserNoANDArtistNo(commentWriter, portfolio.getArtist_no()) != null)
                        portfolioComment.set_fankoked(true);
                    else
                        portfolioComment.set_fankoked(false);

                    resCommentList.add(portfolioComment);
                }

                message.put("portfolio_comment", resCommentList);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_COMMENT_SUCCESS, message.getHashMap("GetPortfolioComment()")), HttpStatus.OK);
            }else {
                // GET DATA FROM DB
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                portfolio.setVisit_number(portfolio.getVisit_number() + 1); // 조회수 증가
                boolean portfolioLike = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;

                Artist artist = artistDao.getArtistByArtistNo(portfolio.getArtist_no());

                if(portfolio.getType().equals(PortfolioType.FILE)){
                    String jsonString = portfolio.getFile();
                    Gson gson = new Gson();
                    FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                    ArrayList<Upload> uploads = new ArrayList<>();
                    for (FileJson json : fileJson) {
                        uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
                    }
                    message.put("files", uploads);
                }

                // RESPONSE MESSAGE SET

                portfolio.setUser_no(artist.getUser_no());
                message.put("portfolio", portfolio);
                message.put("portfolio_like", portfolioLike);
                portfolioDao.updatePortfolioByVisit(portfolio);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetPortfolio()")), HttpStatus.OK);
            }
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
    public ResponseEntity getPortfolioListByTypeVOD(String type, String sort, int start_index) {
        try {
            portfolioDao.setSession(sqlSession);
            Message message = new Message();
            List<Portfolio> vodList = portfolioDao.getPortfolioListByTypeVODSort(type, sort, start_index);

            message.put("vod", vodList);
            message.put("sort", sort);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.VOD_LIST_CALL_SUCCESS, message.getHashMap("GetVODList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Portfolio getPortfolioByPortfolioNo(int portfolio_no) {
        portfolioDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
        portfolio.setUser_no(artistDao.getArtistByArtistNo(portfolio.getArtist_no()).getUser_no());
        return portfolio;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertPortfolio(Portfolio portfolio) {
        portfolioDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        Artist artist = artistDao.getArtistByArtistNo(portfolio.getArtist_no());
        portfolio.setFan_number(artist.getFan_number());
        portfolioDao.insertPortfolio(portfolio);
        artist.setRecent_act_date(Time.TimeFormatHMS());
        artistDao.updateArtist(artist);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updatePortfolio(Portfolio portfolio) {
        try {
            portfolioDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();
            // Update Method
            String d = Time.TimeFormatHMS();
            portfolio.setRevise_date(d);
            portfolioDao.updatePortfolio(portfolio);

            Portfolio portfolio1 = portfolioDao.getPortfolioByPortfolioNo(portfolio.getPortfolio_no());


            Artist artist = artistDao.getArtistByArtistNo(portfolio1.getArtist_no());
            artist.setRecent_act_date(Time.TimeFormatHMS());
            artistDao.updateArtist(artist);

            portfolio1.setUser_no(artist.getUser_no());



            message.put("portfolio", portfolio1);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.EDIT_PORTFOLIO_SUCCESS, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deletePortfolio(int portfolio_no) {
        try {
            portfolioDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);

            Artist artist = artistDao.getArtistByArtistNo(portfolioDao.getPortfolioByPortfolioNo(portfolio_no).getArtist_no());
            artist.setRecent_act_date(Time.TimeFormatHMS());
            artistDao.updateArtist(artist);
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
            subscribeDao.setSession(sqlSession);
            Message message = new Message();
            if (method.equals("UPDATE")) {
                // DB SET
                portfolioComment.setComment_private(false);
                User user = userdao.selectUserByUserNo(portfolioComment.getUser_no());
                Artist artist = artistDao.getArtistByUserNo(portfolioComment.getUser_no());
                if (artist != null) {
                    portfolioComment.setCommenter_name(artist.getArtist_name());
                    portfolioComment.setProfile_img(artist.getArtist_profile_img());
                } else {
                    portfolioComment.setCommenter_name(user.getName());
                    portfolioComment.setProfile_img(user.getProfile_img());
                }
                String d = Time.TimeFormatHMS();
                portfolioComment.setReg_date(d);
                portfolioCommentDao.insertComment(portfolioComment);

                // Portfolio SET
                portfolioDao.updatePortfolioByComment(portfolioComment.getPortfolio_no(), 1);

                // Response Message SET
                List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolioComment.getPortfolio_no(), 0);
                List<PortfolioComment> resCommentList = new ArrayList<>();
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolioComment.getPortfolio_no());
                for(PortfolioComment portfolioComment1 : portfolioCommentList){
                    int commentWriter = portfolioComment1.getUser_no();
                    if(subscribeDao.getSubscribeInfoByUserNoANDArtistNo(commentWriter, portfolio.getArtist_no()) != null)
                        portfolioComment1.set_fankoked(true);
                    else
                        portfolioComment1.set_fankoked(false);

                    resCommentList.add(portfolioComment1);
                }
                message.put("comment_number", portfolio.getComment_number());
                message.put("portfolio_comment", resCommentList);

                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.PORTFOLIO_COMMENT_INSERT_SUCCESS, message.getHashMap("InsertPortfolioComment()")), HttpStatus.OK);
            } else {
                // DB SET
                portfolioCommentDao.deleteComment(portfolioComment.getComment_no());

                // Portfolio SET

                portfolioDao.updatePortfolioByComment(portfolioComment.getPortfolio_no(), -1);

                List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByPortfolioNo(portfolioComment.getPortfolio_no(), 0);
                List<PortfolioComment> resCommentList = new ArrayList<>();
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolioComment.getPortfolio_no());
                for(PortfolioComment portfolioComment1 : portfolioCommentList){
                    int commentWriter = portfolioComment1.getUser_no();
                    if(subscribeDao.getSubscribeInfoByUserNoANDArtistNo(commentWriter, portfolio.getArtist_no()) != null)
                        portfolioComment1.set_fankoked(true);
                    else
                        portfolioComment1.set_fankoked(false);

                    resCommentList.add(portfolioComment1);
                }
                message.put("comment_number", portfolio.getComment_number());
                message.put("portfolio_comment", resCommentList);

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
            artistDao.setSession(sqlSession);
            Message message = new Message();

            if (portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null) {
                // 좋아요 한 게시물일 때 -> 좋아요 취소
                portfolioLikeDao.deleteLike(portfolio_no, user_no);

                // Portfolio SET
                portfolioDao.updatePortfolioByLike(portfolio_no, -1);

                // Response Message SET
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                boolean portfolioLike = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;
                portfolio.setUser_no(artistDao.getArtistByArtistNo(portfolio.getArtist_no()).getUser_no());

                message.put("like_number", portfolio.getLike_number());
                message.put("portfolio_like", portfolioLike);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_LIKE_PORTFOLIO_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            } else {
                // Portfolio Like SET - 좋아요 하지 않은 게시물일 때 -> 좋아요
                PortfolioLike portfolioLike = new PortfolioLike();
                portfolioLike.setPortfolio_no(portfolio_no);
                portfolioLike.setUser_no(user_no);
                String d = Time.TimeFormatHMS();
                portfolioLike.setReg_date(d);
                portfolioLikeDao.insertLike(portfolioLike);

                // Portfolio SET
                portfolioDao.updatePortfolioByLike(portfolio_no, 1);

                // Response Message SET
                Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
                boolean portfolioLikeBool = portfolioLikeDao.getPortfolioLike(portfolio_no, user_no) != null;
                portfolio.setUser_no(artistDao.getArtistByArtistNo(portfolio.getArtist_no()).getUser_no());
                message.put("like_number", portfolio.getLike_number());
                message.put("portfolio_like", portfolioLikeBool);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LIKE_PORTFOLIO_SUCCESS, message.getHashMap("PressPortfolioLike()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }

    }

}
