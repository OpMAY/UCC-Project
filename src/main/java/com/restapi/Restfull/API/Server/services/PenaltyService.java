package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class PenaltyService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private PenaltyDao penaltyDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private LoudSourcingDao loudSourcingDao;

    @Autowired
    private LoudSourcingApplyDao loudSourcingApplyDao;

    @Autowired
    private LoudSourcingEntryDao loudSourcingEntryDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getPenaltyInfo(int user_no) {
        try {
            penaltyDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();

            if (!userDao.selectUserByUserNo(user_no).is_user_private() && !artistDao.getArtistByUserNo(user_no).isArtist_private()) {
                message.put("is_penalty", false);
            } else {
                List<Penalty> penaltyList = penaltyDao.getPenaltyListByUserNo(user_no);
                message.put("is_penalty", true);
                message.put("penalty", penaltyList.get(0));
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_NOTIFICATION_LIST, message.getHashMap("GetPenaltyInfo()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setUserPrivate() throws ParseException {
        penaltyDao.setSession(sqlSession);
        userDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        boardDao.setSession(sqlSession);

        List<Penalty> penaltyList = penaltyDao.getPenaltyList();
        List<Penalty> latestPenaltyList = new ArrayList<>();
        List<Artist> artistList = new ArrayList<>();
        if(penaltyList != null && penaltyList.size() > 0) {
            for (Penalty penalty : penaltyList) {
                Artist artist = new Artist();
                artist.setUser_no(penalty.getUser_no());
                artist.setArtist_no(penalty.getArtist_no());
                if (!artistList.contains(artist)) {
                    artistList.add(artist);
                    latestPenaltyList.add(penalty);
                }
            }
            if(latestPenaltyList.size() > 0) {
                for (Penalty penalty : latestPenaltyList) {
                    Date now = new Date();
                    if (artistList.size() > 0) {
                        for (Artist artist : artistList) {
                            if (penalty.getUser_no() == artist.getUser_no()) {
                                if (now.before(Time.StringToDateFormat(penalty.getPenalty_start_date())) || now.after(Time.StringToDateFormat(penalty.getPenalty_end_date()))) {
                                    if (artist.getArtist_no() > 0) {
                                        Artist artist1 = artistDao.getArtistByArtistNo(artist.getArtist_no());
                                        artist1.setArtist_private(false);
                                        artistDao.updateArtist(artist1);
                                        List<Board> boardList = boardDao.getBoardListByArtistNo(artist1.getArtist_no());
                                        if (boardList != null && boardList.size() > 0) {
                                            for (Board board : boardList) {
                                                board.setBoard_private(false);
                                                boardDao.updateBoardByPenalty(board);
                                            }
                                        }
                                    }
                                    User user = userDao.selectUserByUserNo(artist.getUser_no());
                                    user.set_user_private(false);
                                    userDao.updateUser(user);
                                } else if (now.after(Time.StringToDateFormat(penalty.getPenalty_start_date())) && now.before(Time.StringToDateFormat(penalty.getPenalty_end_date()))) {
                                    if (artist.getArtist_no() > 0) {
                                        Artist artist1 = artistDao.getArtistByArtistNo(artist.getArtist_no());
                                        if (!artist1.isArtist_private()) {
                                            artist1.setArtist_private(true);
                                            artistDao.updateArtist(artist1);
                                            List<Board> boardList = boardDao.getBoardListByArtistNo(artist1.getArtist_no());
                                            if (boardList != null && boardList.size() > 0) {
                                                for (Board board : boardList) {
                                                    board.setBoard_private(true);
                                                    boardDao.updateBoardByPenalty(board);
                                                }
                                            }
                                            List<LoudSourcingApply> applyList = loudSourcingApplyDao.getLoudSourcingApplyListByArtistNo(artist.getArtist_no());
                                            List<LoudSourcing> loudSourcingList = new ArrayList<>();
                                            for(LoudSourcingApply apply : applyList){
                                                LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(apply.getLoudsourcing_no());
                                                loudSourcingList.add(loudSourcing);
                                            }

                                            for(LoudSourcing loudSourcing : loudSourcingList){
                                                int loudsourcing_no = loudSourcing.getLoudsourcing_no();
                                                int artist_no = artist.getArtist_no();
                                                switch (loudSourcing.getStatus()) {
                                                    case "recruitment":
                                                        loudSourcingApplyDao.deleteLoudSourcingApply(artist_no, loudsourcing_no);
                                                        break;
                                                    case "process":
                                                        loudSourcingApplyDao.deleteLoudSourcingApply(artist_no, loudsourcing_no);
                                                        LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artist_no, loudsourcing_no);
                                                        if (entry != null) {
                                                            loudSourcingEntryDao.deleteEntry(entry.getEntry_no());
                                                        }
                                                        break;
                                                    case "judge":
                                                        LoudSourcingApply apply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artist_no, loudsourcing_no);
                                                        apply.set_pre_selected(false);
                                                        loudSourcingApplyDao.updateApplyForJudge(apply);
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                    User user = userDao.selectUserByUserNo(artist.getUser_no());
                                    if (!user.is_user_private()) {
                                        user.set_user_private(true);
                                        userDao.updateUser(user);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
