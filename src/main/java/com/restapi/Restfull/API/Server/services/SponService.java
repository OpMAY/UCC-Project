package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.interfaces.mappers.SponMapper;
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
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private BoardCommentDao boardCommentDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity insertSpon(Spon spon) {
        try {
            sponDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);

            int artist_no = spon.getArtist_no();

            if(artist_no == 0){
                artist_no = boardDao.getBoardByBoardNo(spon.getBoard_no()).getArtist_no();
            }

            if(artistDao.getArtistByArtistNo(artist_no).getUser_no() == spon.getUser_no()){
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.CANNOT_SPON_YOURSELF), HttpStatus.OK);
            }
            Message message = new Message();
            Date now = Time.LongTimeStampCurrent();
            // Spon Data Set
            spon.setSpon_date(now);
            spon.setStatus(SponStatus.NOT_CONFIRMED);
            if (spon.getBoard_no() != 0) {
                spon.setType(SponType.BOARD_SPON);
                // DB Set
                sponDao.insertSpon(spon);
                // BoardDao, BoardCommentDao SET
                User user = userDao.selectUserByUserNo(spon.getUser_no());
                Artist artist = artistDao.getArtistByUserNo(user.getUser_no());
                BoardComment boardSponComment = new BoardComment();
                boardSponComment.setBoard_no(spon.getBoard_no());
                boardSponComment.setUser_no(spon.getUser_no());
                if(artist != null){
                    boardSponComment.setCommenter_name(artist.getArtist_name());
                    boardSponComment.setProfile_img(artist.getArtist_profile_img());
                }else {
                    boardSponComment.setCommenter_name(user.getName());
                    boardSponComment.setProfile_img(user.getProfile_img());
                }
                boardSponComment.setType(BoardCommentType.SPON_COMMENT);
                boardSponComment.setContent(Integer.toString(spon.getPrice()));
                boardSponComment.setComment_private(false);
                boardCommentDao.insertComment(boardSponComment);

                Board board = boardDao.getBoardByBoardNo(spon.getBoard_no());
                board.setBoardCommentList(boardCommentDao.getCommentListByBoardNo(spon.getBoard_no()));
                // Message Set
                message.put("Spon", spon);
                message.put("Board", board);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_SPON_SUCCESS, message.getHashMap("ArtistSpon()")), HttpStatus.OK);
            } else {
                spon.setType(SponType.Artist_SPON);

                // DB Set
                sponDao.insertSpon(spon);

                // Message Set
                message.put("Spon", spon);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_SPON_SUCCESS, message.getHashMap("BoardSpon()")), HttpStatus.OK);
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
