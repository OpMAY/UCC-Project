package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistDao;
import com.restapi.Restfull.API.Server.daos.RequestChangeDao;
import com.restapi.Restfull.API.Server.daos.UserDao;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.RequestChange;
import com.restapi.Restfull.API.Server.models.User;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class RequestChangeService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private RequestChangeDao requestChangeDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<RequestChange> getAllRequests() {
        requestChangeDao.setSession(sqlSession);
        return requestChangeDao.getAllRequests();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RequestChange getRequestByUserNo(int user_no) {
        requestChangeDao.setSession(sqlSession);
        return requestChangeDao.getRequestByUserNo(user_no);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequest(RequestChange rc) {
        /** Set Session **/
        requestChangeDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        userDao.setSession(sqlSession);

        /** User Set**/
        User user = userDao.selectUserByUserNo(rc.getUser_no());
        user.set_artist(true);
        userDao.updateUser(user);

        /** Artist Set **/
        Artist artist = new Artist();
        artist.setUser_no(rc.getUser_no());
        artist.setArtist_name(rc.getArtist_name());
        artist.setBank_name(rc.getBank_name());
        artist.setBank_account(rc.getBank_account());
        artist.setBank_owner(rc.getBank_owner());
        artist.setEmail(rc.getArtist_email());
        artist.setArtist_phone(rc.getArtist_phone());
        artist.setMain_img(rc.getMain_img());
        String d = Time.TimeFormatHMS();
        artist.setReg_date(d);
        artist.setRecent_act_date(d);
        artist.setExplain(rc.getExplain());
        artist.setArtist_private(false);
        artist.setHashtag(rc.getHashtag());
        artist.setArtist_profile_img(rc.getArtist_profile_img());
        artist.setLoudsourcing_push(true);

        /** DB INSERT **/
        rc.setReg_date(d);
        requestChangeDao.insertRequest(rc);
        artistDao.insertArtist(artist);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean artistNameCheck(String artist_name) {
        requestChangeDao.setSession(sqlSession);
        return requestChangeDao.artistNameCheck(artist_name);
    }
}
