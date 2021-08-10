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

import java.text.ParseException;
import java.util.*;

@Log4j2
@Service
public class SubscribeService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private LoudSourcingEntryDao loudSourcingEntryDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity Fankok(int user_no, int artist_no, String sort) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);

            Artist artist = artistDao.getArtistByArtistNo(artist_no);
            if(artist == null){
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETED_USER, ResMessage.NO_ARTIST_DETECTED), HttpStatus.OK);
            }
            if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no) != null) {
                // 팬콕 했을 경우 -> 팬콕 취소
                subscribeDao.deleteSubscribe(user_no, artist_no);

                // 아티스트의 팬 수 변동
                artist.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                artistDao.updateArtist(artist);

                // 게시글, 포트폴리오의 fan_number 갱신
                List<Portfolio> portfolioList = portfolioDao.getPortfolioListByArtistNo(artist_no);
                for (Portfolio portfolio : portfolioList) {
                    portfolio.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                    portfolioDao.updatePortfolioByFankok(portfolio);
                }
                List<Board> boardList = boardDao.getBoardListByArtistNo(artist_no);
                for (Board board : boardList) {
                    board.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                    boardDao.updateBoardByFankok(board);
                }

                List<LoudSourcingEntry> loudSourcingEntryList = loudSourcingEntryDao.getEntryListByArtistNo(artist_no);
                for (LoudSourcingEntry loudSourcingEntry : loudSourcingEntryList) {
                    loudSourcingEntry.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                    loudSourcingEntryDao.updateEntryByFankok(loudSourcingEntry);
                }

                List<Subscribe> subscribeList = subscribeDao.getSubscribeListByUserNo(user_no);
                List<Artist> artistList = new ArrayList<>();
                for (Subscribe subscribe : subscribeList) {
                    int artist_number = subscribe.getArtist_no();
                    Artist artist1 = artistDao.getArtistByArtistNo(artist_number);
                    artistList.add(artist1);
                }

                /** Arrange List by Sort **/
                if (!sort.equals("")) {
                    switch (sort) {
                        case DataListSortType.SORT_BY_RECENT:
                            artistList.sort((o1, o2) -> {
                                String ds1 = o1.getRecent_act_date();
                                String ds2 = o2.getRecent_act_date();
                                Date d1 = new Date();
                                Date d2 = new Date();
                                try {
                                    d1 = Time.StringToDateTimeFormat(ds1);
                                    d2 = Time.StringToDateTimeFormat(ds2);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (d1.after(d2))
                                    return -1;
                                else if (d1.before(d2))
                                    return 1;
                                else
                                    return 0;
                            });
                            break;
                        case DataListSortType.SORT_BY_FANKOK:
                            artistList.sort((o1, o2) -> {
                                int f1 = o1.getFan_number();
                                int f2 = o2.getFan_number();
                                return Integer.compare(f2, f1);
                            });
                            break;
                        case DataListSortType.SORT_BY_WORD:
                            artistList.sort((o1, o2) -> {
                                String a1 = o1.getArtist_name();
                                String a2 = o2.getArtist_name();
                                return a2.compareTo(a1);
                            });
                            break;
                    }
                    /** Refactor List by Start Index **/
                    List<Artist> responseArtistList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        if (artistList.size() <= i)
                            break;
                        responseArtistList.add(artistList.get(i));
                    }
                    message.put("artists", responseArtistList);
                    message.put("sort", sort);
                } else {
                    message.put("artist", artistDao.getArtistByArtistNo(artist_no));
                    message.put("subscribe", false);
                }
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_SUBSCRIBE_SUCCESS, message.getHashMap("Subscribe()")), HttpStatus.OK);
            } else if (artistDao.getArtistByArtistNo(artist_no).getUser_no() == user_no) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.CANNOT_SUBSCRIBE_YOURSELF), HttpStatus.OK);
            } else {
                // 팬콕하지 않았을 경우 -> 팬콕
                Subscribe subscribe = new Subscribe();
                String date = Time.TimeFormatHMS();
                // Set Subscribe Info
                subscribe.setUser_no(user_no);
                subscribe.setArtist_no(artist_no);
                subscribe.setSubscribe_date(date);
                // DB SET
                subscribeDao.insertSubscribe(subscribe);

                List<Portfolio> portfolioList = portfolioDao.getPortfolioListByArtistNo(artist_no);
                for (Portfolio portfolio : portfolioList) {
                    portfolio.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                    portfolioDao.updatePortfolioByFankok(portfolio);
                }
                List<Board> boardList = boardDao.getBoardListByArtistNo(artist_no);
                for (Board board : boardList) {
                    board.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                    boardDao.updateBoardByFankok(board);
                }

                List<LoudSourcingEntry> loudSourcingEntryList = loudSourcingEntryDao.getEntryListByArtistNo(artist_no);
                for (LoudSourcingEntry loudSourcingEntry : loudSourcingEntryList) {
                    loudSourcingEntry.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                    loudSourcingEntryDao.updateEntryByFankok(loudSourcingEntry);
                }

                // 아티스트의 팬 수 변동
                artist.setFan_number(subscribeDao.getSubscribeListByArtistNo(artist_no).size());
                artistDao.updateArtist(artist);
                message.put("artist", artist);
                message.put("subscribe", subscribe);
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
    public ResponseEntity getSubscribeListByUserNo(int user_no) {
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
                if (artist.getHashtag() != null) {
                    ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                    artist.setHashtag_list(hashtagList);
                    log.info(hashtagList);
                }
                artistList.add(artist);
                /** Portfolio Info **/
                List<Portfolio> individualPortfolioList = portfolioDao.getPortfolioListByArtistNo(artist_no);
                for (Portfolio portfolio : individualPortfolioList) {
                    Fankok fankok = new Fankok();
                    portfolio.setUser_no(artistDao.getArtistByArtistNo(portfolio.getArtist_no()).getUser_no());
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
                    fankok.setPortfolio(portfolio);
                    fankok.setReg_date(portfolio.getReg_date());
                    fankok.setType("Portfolio");
                    fankokList.add(fankok);
                }
                /** Board Info **/
                List<Board> individualBoardList = boardDao.getBoardListByArtistNo(artist_no);
                for (Board board : individualBoardList) {
                    Fankok fankok = new Fankok();
                    board.setUser_no(artistDao.getArtistByArtistNo(board.getArtist_no()).getUser_no());
                    fankok.setBoard(board);
                    fankok.setReg_date(board.getReg_date());
                    fankok.setType("Board");
                    fankokList.add(fankok);
                }
            }
            /** Array Sort **/
            fankokList.sort((o1, o2) -> {
                String ds1 = o1.getReg_date();
                String ds2 = o2.getReg_date();
                Date d1 = new Date();
                Date d2 = new Date();
                try {
                    d1 = Time.StringToDateTimeFormat(ds1);
                    d2 = Time.StringToDateTimeFormat(ds2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (d1.after(d2))
                    return -1;
                if (d1.before(d2))
                    return 1;
                else
                    return 0;

            });
            artistList.sort((o1, o2) -> {
                int f1 = o1.getFan_number();
                int f2 = o2.getFan_number();
                return Integer.compare(f2, f1);
            });

            Collections.shuffle(artistList);

            List<Artist> resArtist = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                if (artistList.size() <= i)
                    break;
                resArtist.add(artistList.get(i));
            }

            /** Response Message Set **/
            List<Fankok> indexList = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                if (fankokList.size() <= i)
                    break;
                indexList.add(fankokList.get(i));
            }
            message.put("contents", indexList);
            message.put("artists", resArtist);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_USER_FANKOK_LIST, message.getHashMap("GetUserFankok()")), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e);
        } finally {
            sqlSession.clearCache();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getSubscribeArtistList(int user_no, int last_index, String sort) {
        try {
            subscribeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);

            Message message = new Message();

            List<Subscribe> subscribeList = subscribeDao.getSubscribeListByUserNo(user_no);
            List<Integer> artistList = new ArrayList<>();
            for (Subscribe subscribe : subscribeList) {
                int artist_no = subscribe.getArtist_no();
                artistList.add(artist_no);
            }
            ArrayList<Integer> refreshArtistList = new ArrayList<>();
            int ref_index = 0;
            if(last_index != 0) {
                for (int j = 0; j < artistList.size(); j++) {
                    if (artistList.get(j) == last_index) {
                        ref_index = j;
                        break;
                    } else
                        j++;
                }
            }
            for(int i = ref_index; i < ref_index + 10; i ++){
                if(artistList.size() <= i)
                    break;
                refreshArtistList.add(artistList.get(i));
            }
            if(refreshArtistList.size() > 0) {
                List<Artist> artists = new ArrayList<>();
                switch (sort) {
                    case DataListSortType.SORT_BY_RECENT:
                        artists = artistDao.getSubscribedArtistListSortRecent(refreshArtistList);
                        break;
                    case DataListSortType.SORT_BY_WORD:
                        artists = artistDao.getSubscribedArtistListSortName(refreshArtistList);
                        break;
                    case DataListSortType.SORT_BY_FANKOK:
                        artists = artistDao.getSubscribedArtistListSortFankok(refreshArtistList);
                        break;
                    default:
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.NOT_RIGHT_SORT), HttpStatus.OK);
                }
                for(Artist artist : artists) {
                    if (artist.getHashtag() != null) {
                        ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                        artist.setHashtag_list(hashtagList);
                        log.info(hashtagList);
                    }
                }
                if(artists.size() > 0)
                    message.put("last_index", artists.get(artists.size() - 1).getArtist_no());
                message.put("artists", artists);
            } else {
                message.put("last_index", 0);
                message.put("artists", refreshArtistList);
            }

            message.put("sort", sort);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_USER_FANKOK_ARTIST_LIST, message.getHashMap("GetUserFankokArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getSubscribedArtists(int user_no) {
        try {
            subscribeDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();

            List<Subscribe> subscribeList = subscribeDao.getSubscribeListByUserNo(user_no);
            List<Integer> subscribedArtistNo = new ArrayList<>();
            for (Subscribe subscribe : subscribeList) {
                int artist_no = subscribe.getArtist_no();
                subscribedArtistNo.add(artist_no);
            }
            if (user_no != 0) {
                User login_user = userDao.selectUserByUserNo(user_no);
                // FCM TOKEN UPDATE

                if (login_user != null) {
                    // USER SET MESSAGE
                    login_user.set_register(false);
                    message.put("user", login_user);
                    /** 아티스트의 경우 아티스트 정보 반환 **/

                    if (artistDao.getArtistByUserNo(user_no) != null) {
                        Artist artist = artistDao.getArtistByUserNo(user_no);
                        if (artist.getHashtag() != null) {
                            ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                            artist.setHashtag_list(hashtagList);
                            log.info(hashtagList);
                        }
                        message.put("artist", artist);
                    }
                    message.put("artist_no_list", subscribedArtistNo);
                }
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_USER_FANKOK_ARTIST_LIST, message.getHashMap("GetSubscribedArtists()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
