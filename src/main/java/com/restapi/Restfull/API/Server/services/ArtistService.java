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

import java.util.*;

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

    @Autowired
    private PenaltyDao penaltyDao;

    @Autowired
    private SearchDao searchDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getNewArtists() {
        try {
            Message message = new Message();
            searchDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            List<Artist> newArtistList = artistDao.getNewArtistList();
            List<Artist> resArtistList = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                if (newArtistList.size() <= i)
                    break;
                resArtistList.add(newArtistList.get(i));
            }
            for (Artist artist : resArtistList) {
                if (artist.getHashtag() != null) {
                    ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                    artist.setHashtag_list(hashtagList);
                    log.info(hashtagList);
                }
            }
            List<Search> searchList = searchDao.getKeywords();
            List<String> keywordList = new ArrayList<>();
            Collections.shuffle(searchList);
            for (int i = 0; i < 10; i++) {
                if(searchList.size() <= i)
                    break;
                String keyword = searchList.get(i).getWord();
                keywordList.add(keyword);
            }
            message.put("keywords", keywordList);
            message.put("new_artists", resArtistList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_LIST_LOADED, message.getHashMap("GetArtistList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getAllArtists(int last_index, String sort) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            Artist artist = artistDao.getArtistByArtistNo(last_index);
            if (last_index != 0) {
                if (artist == null) {
                    log.info(last_index);
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_ARTIST_DETECTED), HttpStatus.OK);
                }

                List<Artist> resArtistList = artistDao.getAllArtistRefresh(artist.getArtist_no(), sort, artist);

                for (Artist artist1 : resArtistList) {
                    if (artist1.getHashtag() != null) {
                        ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist1.getHashtag().split(", ")));
                        artist1.setHashtag_list(hashtagList);
                        log.info(hashtagList);
                    }
                }

                int artist_size = artistDao.getAllArtists().size();

                message.put("artists", resArtistList);
                message.put("sort", sort);
                message.put("artist_size", artist_size);
                if (resArtistList.size() > 0)
                    message.put("last_index", resArtistList.get(resArtistList.size() - 1).getArtist_no());
            } else {
                List<Artist> resArtistList = artistDao.getAllArtistLimit(sort);

                for (Artist artist1 : resArtistList) {
                    if (artist1.getHashtag() != null) {
                        ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist1.getHashtag().split(", ")));
                        artist1.setHashtag_list(hashtagList);
                        log.info(hashtagList);
                    }
                }

                int artist_size = artistDao.getAllArtists().size();

                message.put("artists", resArtistList);
                message.put("sort", sort);
                message.put("artist_size", artist_size);
                if (resArtistList.size() > 0) {
                    message.put("last_index", resArtistList.get(resArtistList.size() - 1).getArtist_no());
                }
            }
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
    public ResponseEntity ArtistMain(int user_no, int artist_no, int last_index) {
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
            if (artist == null) {
                log.info("artist_no : " + artist_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETED_USER, ResMessage.NO_ARTIST_DETECTED), HttpStatus.OK);
            } else {
                if (last_index == -1) {
                    if (artist.getHashtag() != null) {
                        ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                        artist.setHashtag_list(hashtagList);
                        log.info(hashtagList);
                    }
                    List<Portfolio> portfolioList = portfolioDao.getPortfolioListByArtistNoLimit(artist_no);
                    List<Portfolio> resPortfolioList = new ArrayList<>();
                    for (Portfolio portfolio : portfolioList) {
                        portfolio.setUser_no(artist.getUser_no());
                        if (portfolio.getType().equals(PortfolioType.FILE)) {
                            String jsonString = portfolio.getFile();
                            Gson gson = new Gson();
                            FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                            ArrayList<Upload> uploads = new ArrayList<>();
                            for (FileJson json : fileJson) {
                                uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
                            }
                            portfolio.setFile_list(uploads);
                        } else if (portfolio.getType().equals(PortfolioType.IMAGE)) {
                            if (portfolio.getFile() != null) {
                                ArrayList<String> filelist = new ArrayList<>(Arrays.asList(portfolio.getFile().split(", ")));
                                portfolio.setImage_list(filelist);
                                log.info(filelist);
                            }
                        }
                        resPortfolioList.add(portfolio);
                    }
                    boolean subscribe = subscribeDao.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no) != null;


                    if (user_no != 0) {
                        if (artistVisitDao.getArtistVisit(artist_no, user_no, now) == null) {
                            // 당일 방문하지 않았을 경우 - 방문자 정보 추가 후 금일 방문자 수 수정
                            if (artist.getUser_no() != user_no) { // 본인 페이지 입장은 방문자 수 변동 X, 로그인 하지 않은 유저도 변동 X
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
                    }
                    message.put("artist", artist);
                    message.put("subscribe", subscribe);
                    message.put("portfolios", resPortfolioList);
                } else {
                    if (last_index == 0) {
                        List<Board> boardList = boardDao.getBoardListByArtistNoLimit(artist_no);
                        message.put("boards", boardList);
                        if (boardList.size() > 0) {
                            message.put("last_index", boardList.get(boardList.size() - 1).getBoard_no());
                        }
                    } else {
                        Board board = boardDao.getBoardByBoardNo(last_index);
                        List<Board> boardList = boardDao.getBoardListByArtistNoForRefresh(artist_no, last_index, board.getReg_date());
                        message.put("boards", boardList);
                        if (boardList.size() > 0) {
                            message.put("last_index", boardList.get(boardList.size() - 1).getBoard_no());
                        }
                    }

                }
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_INFO_CALL_SUCCESS, message.getHashMap("GetArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }
    //TODO 아티스트 목록 정렬 방식에 따라 서버에서 그에 맞게 데이터를 뿌려줄지, 앱단에서 처리할지? -> 기획의 의도에 맞게 화면 별 기준에 맞춰 서버처리 or 어플 단 처리

    @Transactional(propagation = Propagation.REQUIRED)
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

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getArtistBanInfo(int artist_no) {
        try {
            Message message = new Message();
            penaltyDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            boolean artistchk = artistDao.getArtistByArtistNo(artist_no).isArtist_private();

            if (artistchk) {
                List<Penalty> penaltyList = penaltyDao.getPenaltyListByArtistNo(artist_no);
                message.put("penalty", penaltyList.get(0));
            }

            message.put("artist_no", artist_no);
            message.put("is_artist_private", artistchk);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SEARCH_ARTIST_RESULT_LOADED, message.getHashMap("SearchArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity updateArtistPush(int artist_no) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            if (artist == null)
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETED_USER, ResMessage.NO_ARTIST_DETECTED), HttpStatus.OK);
            if (artist.isLoudsourcing_push()) {
                artistDao.updateArtistPush(artist_no, false);
                message.put("loudsourcing_push", false);
            } else {
                artistDao.updateArtistPush(artist_no, true);
                message.put("loudsourcing_push", true);
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SEARCH_ARTIST_RESULT_LOADED, message.getHashMap("updateArtistPush()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
