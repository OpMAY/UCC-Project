package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.Board;
import com.restapi.Restfull.API.Server.models.Portfolio;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
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

    @Autowired
    private LoudSourcingEntryDao loudSourcingEntryDao;

    @Autowired
    private LoudSourcingDao loudSourcingDao;

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private RequestChangeDao requestChangeDao;

    @Autowired
    private FAQDao faqDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetMain() {
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
            // New Artist List - total 15
            List<Artist> newArtistList = artistDao.getNewArtistList();


            // Popular Artist - total 15
            List<Artist> popularArtistList = artistDao.getArtistListByPopular();
            for (Artist artist : popularArtistList) {
                String hashtag = artist.getHashtag();
                log.info(hashtag);
                if (hashtag != null) {
                    ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(hashtag.split(", ")));
                    artist.setHashtag_list(hashtagList);
                    log.info(hashtagList);
                }
            }

            // Random Portfolio List
            List<Portfolio> randomPortfolioList = portfolioDao.getPortfolioListByRandom();

            // Recent Board List
            List<Board> recentBoardList = boardDao.getRecentBoardList();

            message.put("new_artists", newArtistList);
            message.put("popular_artists", popularArtistList);
            message.put("random_portfolios", randomPortfolioList);
            message.put("recent_boards", recentBoardList);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.MAIN_PAGE_LOADED, message.getHashMap("GetMain()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCDNFiles(){
        userDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        portfolioDao.setSession(sqlSession);
        boardDao.setSession(sqlSession);
        loudSourcingEntryDao.setSession(sqlSession);
        loudSourcingDao.setSession(sqlSession);
        noticeDao.setSession(sqlSession);
        requestChangeDao.setSession(sqlSession);
        faqDao.setSession(sqlSession);
    }

}
