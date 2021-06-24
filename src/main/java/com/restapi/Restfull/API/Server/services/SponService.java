package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.BoardCommentDao;
import com.restapi.Restfull.API.Server.daos.BoardDao;
import com.restapi.Restfull.API.Server.daos.SponDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.interfaces.mappers.SponMapper;
import com.restapi.Restfull.API.Server.models.Spon;
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

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class SponService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private SponDao sponDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private BoardCommentDao boardCommentDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity insertSpon(Spon spon) {
        try {
            sponDao.setSession(sqlSession);
            Message message = new Message();
            Date now = Time.LongTimeStampCurrent();
            // Spon Data Set
            spon.setSpon_date(now);
            spon.setStatus(SponStatus.NOT_CONFIRMED);
            if (spon.getBoard_no() != 0) {
                spon.setType(SponType.BOARD_SPON);
                // DB Set
                sponDao.insertSpon(spon);
                // TODO BoardDao, BoardCommentDao SET

                // Message Set
                message.put("Spon", spon);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_SPON_SUCCESS, message.getHashMap("ArtistSpon()")), HttpStatus.OK);
            } else {
                spon.setType(SponType.Artist_SPON);

                // DB Set
                sponDao.insertSpon(spon);

                // Message Set
                message.put("Spon", spon);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_SPON_SUCCESS, message.getHashMap("BoardSpon()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Spon> getSponList() {
        sponDao.setSession(sqlSession);
        return sponDao.getSponList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Spon> getSponListByArtistNo(int artist_no) {
        sponDao.setSession(sqlSession);
        return sponDao.getSponListByArtistNo(artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Spon> getSponListByUserNo(int user_no) {
        sponDao.setSession(sqlSession);
        return sponDao.getSponListByUserNo(user_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Spon> getSponListByBoardNo(int board_no) {
        sponDao.setSession(sqlSession);
        return sponDao.getSponListByBoardNo(board_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Spon getSponAfterSpon(int user_no, int artist_no, Date spon_date) {
        sponDao.setSession(sqlSession);
        return sponDao.getSponAfterSpon(user_no, artist_no, spon_date);
    }
}
