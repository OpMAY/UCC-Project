package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistDao;
import com.restapi.Restfull.API.Server.daos.BoardDao;
import com.restapi.Restfull.API.Server.daos.PortfolioDao;
import com.restapi.Restfull.API.Server.daos.SubscribeDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class SubscribeService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private BoardDao boardDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity Fankok(int user_no, int artist_no) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);

            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no) != null) {
                // 팬콕 했을 경우 -> 팬콕 취소
                subscribeDao.deleteSubscribe(user_no, artist_no);

                // 아티스트의 팬 수 변동
                artist.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                artistDao.updateArtist(artist);

                message.put("Artist", artistDao.getArtistByArtistNo(artist_no));
                message.put("Subscribe", false);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_SUBSCRIBE_SUCCESS, message.getHashMap("Subscribe()")), HttpStatus.OK);
            } else if (artistDao.getArtistByArtistNo(artist_no).getUser_no() == user_no) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.CANNOT_SUBSCRIBE_YOURSELF), HttpStatus.OK);
            } else {
                // 팬콕하지 않았을 경우 -> 팬콕
                Subscribe subscribe = new Subscribe();
                Date now = Time.LongTimeStampCurrent();
                // Set Subscribe Info
                subscribe.setUser_no(user_no);
                subscribe.setArtist_no(artist_no);
                subscribe.setSubscribe_date(now);
                // DB SET
                subscribeDao.insertSubscribe(subscribe);

                // 아티스트의 팬 수 변동
                artist.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                artistDao.updateArtist(artist);
                message.put("Artist", artist);
                message.put("Subscribe", subscribe);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SUBSCRIBE_SUCCESS, message.getHashMap("Subscribe()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Subscribe getSubscribeInfoByUserNoANDArtistNo(int user_no, int artist_no) {
        subscribeDao.setSession(sqlSession);
        return subscribeDao.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Subscribe> getSubscribeListByArtistNo(int artist_no) {
        subscribeDao.setSession(sqlSession);
        return subscribeDao.getSubscribeListByArtistNo(artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getSubscribeListByUserNo(int user_no, int start_index) {
        try {
            subscribeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);

            Message message = new Message();

            List<Subscribe> subscribeList = subscribeDao.getSubscribeListByUserNo(user_no);
            List<Artist> artistList = new ArrayList<>();
            List<Fankok> fankokList = new ArrayList<>();
            for (Subscribe subscribe : subscribeList) {
                /** Artist Info **/
                int artist_no = subscribe.getArtist_no();
                Artist artist = artistDao.getArtistByArtistNo(artist_no);
                artistList.add(artist);
                /** Portfolio Info **/
                List<Portfolio> individualPortfolioList = portfolioDao.getPortfolioListByArtistNo(artist_no);
                for (Portfolio portfolio : individualPortfolioList) {
                    Fankok fankok = new Fankok();
                    fankok.setPortfolio(portfolio);
                    fankok.setReg_date(portfolio.getReg_date());
                    fankok.setType("Portfolio");
                    fankokList.add(fankok);
                }
                /** Board Info **/
                List<Board> individualBoardList = boardDao.getBoardListByArtistNo(artist_no);
                for (Board board : individualBoardList) {
                    Fankok fankok = new Fankok();
                    fankok.setBoard(board);
                    fankok.setReg_date(board.getReg_date());
                    fankok.setType("Board");
                    fankokList.add(fankok);
                }
            }
            /** Array Sort **/
            fankokList.sort((o1, o2) -> {
                Date d1 = o1.getReg_date();
                Date d2 = o2.getReg_date();
                if(d1.after(d2))
                    return -1;
                if(d1.before(d2))
                    return 1;
                else
                    return 0;

            });
            artistList.sort((o1, o2) -> {
                int f1 = o1.getFan_number();
                int f2 = o2.getFan_number();
                if(f1 > f2)
                    return -1;
                else if(f1 < f2)
                    return 1;
                else
                    return 0;
            });

            List<Artist> resArtist = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                if(artistList.size() <= i)
                    break;
                resArtist.add(artistList.get(i));
            }

            /** Response Message Set **/
            List<Fankok> indexList = new ArrayList<>();
            for(int i = start_index; i < start_index + 10; i++){
                if(fankokList.size() <= i)
                    break;
                indexList.add(fankokList.get(i));
            }
            message.put("Contents", indexList);
            if(start_index == 0) {
                message.put("Artists", resArtist);
            }

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_USER_FANKOK_LIST, message.getHashMap("GetUserFankok()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getSubscribeArtistList(int user_no) {
        try{
            subscribeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);

            Message message = new Message();

            List<Subscribe> subscribeList = subscribeDao.getSubscribeListByUserNo(user_no);
            List<Artist> artistList = new ArrayList<>();
            for(Subscribe subscribe : subscribeList){
                int artist_no = subscribe.getArtist_no();
                Artist artist = artistDao.getArtistByArtistNo(artist_no);
                artistList.add(artist);
            }
            message.put("Artists" , artistList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_USER_FANKOK_ARTIST_LIST, message.getHashMap("GetUserFankokArtist()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }
}
