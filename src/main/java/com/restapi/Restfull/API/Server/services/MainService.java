package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistDao;
import com.restapi.Restfull.API.Server.daos.BoardDao;
import com.restapi.Restfull.API.Server.daos.PortfolioDao;
import com.restapi.Restfull.API.Server.daos.UserDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.Board;
import com.restapi.Restfull.API.Server.models.Portfolio;
import com.restapi.Restfull.API.Server.models.Subscribe;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MainService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetMain(){
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);

            /**
             * 1. New Artist
             * 2. Popular Artist
             * 3. Random Portfolio List
             * 4. Recent Board List
             * **/
            // New Artist List - total 10
            List<Artist> newArtistList = artistDao.getNewArtistList();

            // Popular Artist - total 10
            List<Artist> popularArtistList = artistDao.getArtistListByPopular();

            // Random Portfolio List
            List<Portfolio> randomPortfolioList = portfolioDao.getPortfolioListByRandom();

            // Recent Board List
            List<Board> recentBoardList = boardDao.getRecentBoardList();

            message.put("New Artists", newArtistList);
            message.put("Popular Artists", popularArtistList);
            message.put("Random Portfolios", randomPortfolioList);
            message.put("Recent Boards", recentBoardList);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.MAIN_PAGE_LOADED, message.getHashMap("GetMain()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

}
