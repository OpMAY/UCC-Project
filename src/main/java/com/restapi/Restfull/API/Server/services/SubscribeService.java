package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistDao;
import com.restapi.Restfull.API.Server.daos.SubscribeDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.Subscribe;
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
    public List<Subscribe> getSubscribeListByUserNo(int user_no) {
        subscribeDao.setSession(sqlSession);
        return subscribeDao.getSubscribeListByUserNo(user_no);
    }
}
