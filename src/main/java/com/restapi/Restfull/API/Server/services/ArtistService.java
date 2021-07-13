package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.ArtistVisit;
import com.restapi.Restfull.API.Server.models.Board;
import com.restapi.Restfull.API.Server.models.Portfolio;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Log4j2
@Service
public class ArtistService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private ArtistVisitDao artistVisitDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getNewArtists() {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            List<Artist> newArtistList = artistDao.getNewArtistList();
            List<Artist> resArtistList = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                if (newArtistList.size() <= i)
                    break;
                resArtistList.add(newArtistList.get(i));
            }
            message.put("new_artists", resArtistList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_LIST_LOADED, message.getHashMap("GetArtistList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getAllArtists(int start_index, String sort) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);

            List<Artist> resArtistList = artistDao.getAllArtistRefresh(start_index, sort);

            int artist_size = artistDao.getAllArtists().size();

            message.put("artists", resArtistList);
            message.put("sort", sort);
            message.put("artist_size", artist_size);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_LIST_LOADED, message.getHashMap("GetArtistList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public Artist getArtistByUserNo(int user_no) {
        artistDao.setSession(sqlSession);
        return artistDao.getArtistByUserNo(user_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Artist getArtistByArtistNo(int artist_no) {
        artistDao.setSession(sqlSession);
        return artistDao.getArtistByArtistNo(artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertArtist(Artist artist) {
        artistDao.setSession(sqlSession);
        artistDao.insertArtist(artist);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateArtist(Artist artist) {
        artistDao.setSession(sqlSession);
        artistDao.updateArtist(artist);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity ArtistMain(int user_no, int artist_no, int start_index) {
        try {
            Message message = new Message();
            /** required Data
             * 1. Artist - Done
             * 2. PortfolioList - Done
             * 3. Board - Done
             * 4. Subscribe - Done
             * **/
            String now = Time.TimeFormatDay();
            artistDao.setSession(sqlSession);
            artistVisitDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);

            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            if(artist.getHashtag() != null){
                ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                artist.setHashtag_list(hashtagList);
                log.info(hashtagList);
            }
            List<Portfolio> portfolioList = portfolioDao.getPortfolioListByArtistNoLimit(artist_no);
            List<Portfolio> resPortfolioList = new ArrayList<>();
            for(Portfolio portfolio : portfolioList){
                portfolio.setUser_no(artist.getUser_no());
                resPortfolioList.add(portfolio);
            }
            boolean subscribe = subscribeDao.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no) != null;

            if (start_index == -1) {
                if (artistVisitDao.getArtistVisit(artist_no, user_no, now) == null) {
                    // 당일 방문하지 않았을 경우 - 방문자 정보 추가 후 금일 방문자 수 수정
                    if (artist.getUser_no() != user_no) { // 본인 페이지 입장은 방문자 수 변동 X
                        // 방문 정보 SET
                        ArtistVisit artistVisit = new ArtistVisit();
                        artistVisit.setArtist_no(artist_no);
                        artistVisit.setUser_no(user_no);
                        String d = Time.TimeFormatDay();
                        artistVisit.setVisit_date(d);
                        artistVisitDao.insertVisit(artistVisit);

                        // 방문 정보로 아티스트의 방문 숫자 변동
                        List<ArtistVisit> artistVisitList = artistVisitDao.getArtistVisitByArtistNo(artist_no, now);
                        artist.setVisit_today(artistVisitList.size());
                        artistDao.updateArtist(artist);
                    }
                }
                message.put("artist", artist);
                message.put("subscribe", subscribe);
                message.put("portfolios", resPortfolioList);
            } else {
                List<Board> boardList = boardDao.getBoardListByArtistNoForRefresh(artist_no, start_index, start_index + 10);

                message.put("boards", boardList);

            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_INFO_CALL_SUCCESS, message.getHashMap("GetArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }
    //TODO 아티스트 목록 정렬 방식에 따라 서버에서 그에 맞게 데이터를 뿌려줄지, 앱단에서 처리할지? -> 기획의 의도에 맞게 화면 별 기준에 맞춰 서버처리 or 어플 단 처리

    public ResponseEntity SearchArtist(String search) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            List<Artist> artistList = artistDao.SearchArtist(search);
            message.put("artists", artistList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SEARCH_ARTIST_RESULT_LOADED, message.getHashMap("SearchArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
