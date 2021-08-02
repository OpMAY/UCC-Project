package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.utility.FirebaseMessagingSnippets;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class LoudSourcingService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private LoudSourcingDao loudSourcingDao;

    @Autowired
    private LoudSourcingEntryDao loudSourcingEntryDao;

    @Autowired
    private LoudSourcingApplyDao loudSourcingApplyDao;

    @Autowired
    private EntryVoteDao entryVoteDao;

    @Autowired
    private EntryCommentDao entryCommentDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    private PenaltyDao penaltyDao;

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getLoudSourcingList(String sort, int last_index) {
        try {
            Message message = new Message();
            loudSourcingDao.setSession(sqlSession);
            if (last_index == 0) {
                List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatus(sort);
                for (LoudSourcing loudSourcing : loudSourcingList) {
                    if (loudSourcing.getHashtag() != null) {
                        ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(loudSourcing.getHashtag().split(", ")));
                        loudSourcing.setHashtag_list(hashtagList);
                        log.info(hashtagList);
                    }
                }
                message.put("loudsourcing", loudSourcingList);
                if(loudSourcingList.size() > 0)
                    message.put("last_index", loudSourcingList.get(loudSourcingList.size() - 1).getLoudsourcing_no());
            } else {
                LoudSourcing loudSourcing1 = loudSourcingDao.getLoudSourcingByLoudsourcingNo(last_index);
                if(loudSourcing1 == null){
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                }
                List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusRefresh(sort, loudSourcing1);
                for (LoudSourcing loudSourcing : loudSourcingList) {
                    if (loudSourcing.getHashtag() != null) {
                        ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(loudSourcing.getHashtag().split(", ")));
                        loudSourcing.setHashtag_list(hashtagList);
                        log.info(hashtagList);
                    }
                }
                message.put("loudsourcing", loudSourcingList);
                if(loudSourcingList.size() > 0)
                    message.put("last_index", loudSourcingList.get(loudSourcingList.size() - 1).getLoudsourcing_no());
            }
            message.put("sort", sort);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_LOUDSOURCING_LIST, message.getHashMap("GetLoudSourcingList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getLoudSourcingDetail(int user_no, int loudsourcing_no) {
        try {
            Message message = new Message();
            loudSourcingApplyDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);

            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no) == null) {
                log.info(loudsourcing_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }


            if (artistDao.getArtistByUserNo(user_no) != null) {
                if (loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artistDao.getArtistByUserNo(user_no).getArtist_no(), loudsourcing_no) != null) {
                    message.put("apply", true);
                    if (loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artistDao.getArtistByUserNo(user_no).getArtist_no(), loudsourcing_no).isEntry()) {
                        message.put("has_entry", true);
                    } else {
                        message.put("has_entry", false);
                    }
                } else {
                    message.put("apply", false);
                }
            } else {
                message.put("apply", false);
            }

            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);

            if (loudSourcing.getFiles() != null) {
                String jsonString = loudSourcing.getFiles();
                Gson gson = new Gson();
                FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                ArrayList<Upload> uploads = new ArrayList<>();
                for (FileJson json : fileJson) {
                    uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
                }
                message.put("file_list", uploads);
            }

            int applied_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudsourcing_no).size();
            int entry_num = loudSourcingApplyDao.getEntryNum(loudsourcing_no).size();
            message.put("applied_num", applied_num);
            message.put("entry_num", entry_num);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_LOUDSOURCING_DETAIL, message.getHashMap("GetLoudSourcingList()")), HttpStatus.OK);
        } catch (JSONException e) {

            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity uploadEntry(LoudSourcingEntry loudSourcingEntry) {
        try {
            Message message = new Message();
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);
            penaltyDao.setSession(sqlSession);

            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudSourcingEntry.getLoudsourcing_no()) == null) {
                log.info(loudSourcingEntry);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            List<Penalty> penaltyList = penaltyDao.getPenaltyListByArtistNo(loudSourcingEntry.getArtist_no());
            if (penaltyList != null && !penaltyList.isEmpty()) {
                Penalty penalty = penaltyList.get(0);
                String now = Time.TimeFormatHMS();
                Date nowDate = Time.StringToDateFormat(now);
                if (nowDate.before(Time.StringToDateFormat(penalty.getPenalty_end_date())) && nowDate.after(Time.StringToDateFormat(penalty.getPenalty_start_date()))) {
                    message.put("penalty", penalty);
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAN_ARTIST, ResMessage.BANNED_ARTIST, message.getHashMap("UploadLoudSourcingEntry()")), HttpStatus.OK);
                }
            }

            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudSourcingEntry.getLoudsourcing_no());

            LoudSourcingApply loudSourcingApply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(loudSourcingEntry.getArtist_no(), loudSourcingEntry.getLoudsourcing_no());
            Artist artist = artistDao.getArtistByArtistNo(loudSourcingEntry.getArtist_no());
            // Upload Entry
            if (!loudSourcingApply.isEntry()) {
                loudSourcingEntry.setArtist_name(artist.getArtist_name());
                loudSourcingEntry.setArtist_profile_img(artist.getArtist_profile_img());

                String d = Time.TimeFormatHMS();
                loudSourcingEntry.setReg_date(d);
                loudSourcingEntry.setRevise_date(d);
                loudSourcingEntry.setFan_number(artist.getFan_number());
                loudSourcingEntryDao.insertEntry(loudSourcingEntry);


                // Update LoudSourcingApply Entry Status
                loudSourcingApply.setEntry(true);
                loudSourcingApplyDao.updateApply(loudSourcingApply);
                // Update artist Recent Act Date

                artist.setRecent_act_date(Time.TimeFormatHMS());
                artistDao.updateArtist(artist);

                // 해당 아티스트를 팬콕한 유저들에게 등록 알림
                FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();
                List<Subscribe> subscribeList = subscribeDao.getSubscribeListByArtistNo(artist.getArtist_no());
                List<User> userList = new ArrayList<>();
                if (subscribeList != null && !subscribeList.isEmpty()) {
                    for (Subscribe subscribe : subscribeList) {
                        User user = userDao.selectUserByUserNo(subscribe.getUser_no());
                        userList.add(user);
                    }

                    if (!userList.isEmpty()) {
                        for (User user : userList) {
                            //FCM MESSAGE SEND
                            if (user.getFcm_token() != null && user.isFankok_push()) {
                                NotificationNext notificationNext = new NotificationNext(NotificationType.CONTENT_UPLOADED, NotificationType.CONTENT_TYPE_ENTRY, NotificationType.CONTENT_CATEGORY_VOD, loudSourcingEntry.getEntry_no(), null, loudSourcingEntry.getArtist_no());
                                firebaseMessagingSnippets.push(user.getFcm_token(), NotificationType.CONTENT_UPLOADED_FCM, "회원님이 팬콕한 " + artist.getArtist_name() + "님이" + loudSourcing.getName() + " 새로운 출품작을 업로드 하였습니다. 많은 응원 부탁드립니다.", new Gson().toJson(notificationNext));
                            } else {
                                if (user.getFcm_token() == null)
                                    log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                                else if (user.isFankok_push())
                                    log.info("this user's push alarm off");
                            }
                            //NOTIFICATION SET
                            Notification notification = new Notification();
                            notification.setUser_no(user.getUser_no());
                            notification.setType(NotificationType.CONTENT_UPLOADED);
                            notification.setContent("회원님이 팬콕한 " + artist.getArtist_name() + "님이" + loudSourcing.getName() + " 새로운 출품작을 업로드 하였습니다. 많은 응원 부탁드립니다.");
                            notification.setReg_date(Time.TimeFormatHMS());
                            NotificationNext notificationNext = new NotificationNext(NotificationType.CONTENT_UPLOADED, NotificationType.CONTENT_TYPE_ENTRY, NotificationType.CONTENT_CATEGORY_VOD, loudSourcingEntry.getEntry_no(), null, loudSourcingEntry.getArtist_no());
                            notification.setNext(new Gson().toJson(notificationNext));
                            notificationDao.insertNotification(notification);
                        }
                    }
                }

                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOUDSOURCING_ENTRY_UPLOADED, message.getHashMap("UploadLoudSourcingEntry()")), HttpStatus.OK);
            } else {
                return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
            }
        } catch (JSONException | ParseException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getEntryList(int user_no, int loudsourcing_no, String sort, int last_index) {
        try {
            artistDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            Message message = new Message();

            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no) == null) {
                log.info(loudsourcing_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            if (artistDao.getArtistByUserNo(user_no) != null && loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artistDao.getArtistByUserNo(user_no).getArtist_no(), loudsourcing_no) != null) {
                LoudSourcingEntry myEntry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artistDao.getArtistByUserNo(user_no).getArtist_no(), loudsourcing_no);
                if (myEntry != null) {
                    myEntry.setUser_no(artistDao.getArtistByArtistNo(myEntry.getArtist_no()).getUser_no());
                    myEntry.setFan_number(artistDao.getArtistByArtistNo(myEntry.getArtist_no()).getFan_number());
                    message.put("my_entry", myEntry);
                }
            }

            if(last_index == 0){
                List<LoudSourcingEntry> loudSourcingEntryList = loudSourcingEntryDao.getEntryListByLoudSourcingNo(loudsourcing_no, sort);

                log.info(loudSourcingEntryList.size());
                if (loudSourcingEntryList.size() > 0) {
                    for (LoudSourcingEntry loudSourcingEntry : loudSourcingEntryList) {
                        log.info(loudSourcingEntry);
                        if (loudSourcingEntry.getArtist_no() != 0) {
                            Artist artist = artistDao.getArtistByArtistNo(loudSourcingEntry.getArtist_no());
                            log.info(artist);
                            loudSourcingEntry.setFan_number(artist.getFan_number());
                            loudSourcingEntry.setUser_no(artist.getUser_no());
                        }
                    }
                }

                int entry_num = loudSourcingEntryDao.getEntryListNumByLoudsourcingNo(loudsourcing_no);

                message.put("entry_num", entry_num);
                message.put("entry_list", loudSourcingEntryList);
                if(loudSourcingEntryList.size() > 0)
                    message.put("last_index", loudSourcingEntryList.get(loudSourcingEntryList.size() - 1).getEntry_no());
            } else {
                LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(last_index);
                if(entry == null){
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                }
                List<LoudSourcingEntry> loudSourcingEntryList = loudSourcingEntryDao.getEntryListByLoudSourcingNoRefresh(loudsourcing_no, sort, entry);

                log.info(loudSourcingEntryList.size());
                if (loudSourcingEntryList.size() > 0) {
                    for (LoudSourcingEntry loudSourcingEntry : loudSourcingEntryList) {
                        log.info(loudSourcingEntry);
                        if (loudSourcingEntry.getArtist_no() != 0) {
                            Artist artist = artistDao.getArtistByArtistNo(loudSourcingEntry.getArtist_no());
                            log.info(artist);
                            loudSourcingEntry.setFan_number(artist.getFan_number());
                            loudSourcingEntry.setUser_no(artist.getUser_no());
                        }
                    }
                }

                int entry_num = loudSourcingEntryDao.getEntryListNumByLoudsourcingNo(loudsourcing_no);

                message.put("entry_num", entry_num);
                message.put("entry_list", loudSourcingEntryList);
                if(loudSourcingEntryList.size() > 0)
                    message.put("last_index", loudSourcingEntryList.get(loudSourcingEntryList.size() - 1).getEntry_no());
            }
            message.put("sort", sort);


            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_ENTRY_LIST, message.getHashMap("GetEntryList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity applyLoudSourcing(LoudSourcingApply loudSourcingApply) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            Message message = new Message();

            if (loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudSourcingApply.getLoudsourcing_no()) == null) {
                log.info(loudSourcingApply);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            if (loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(loudSourcingApply.getArtist_no(), loudSourcingApply.getLoudsourcing_no()) != null) {
                loudSourcingApplyDao.deleteLoudSourcingApply(loudSourcingApply.getArtist_no(), loudSourcingApply.getLoudsourcing_no());
                message.put("status", "cancel");
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOUDSOURCING_CANCEL_SUCCESS, message.getHashMap("CancelLoudSourcing()")), HttpStatus.OK);
            } else {
                loudSourcingApply.setEntry(false);
                loudSourcingApply.setReg_date(Time.TimeFormatHMS());
                loudSourcingApplyDao.insertLoudSourcingApply(loudSourcingApply);

                message.put("status", "apply");
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOUDSOURCING_APPLICATION_SUCCESS, message.getHashMap("ApplyLoudSourcing()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity searchLoudSourcing(String sort, String query, int last_index) {
        try {
            loudSourcingDao.setSession(sqlSession);
            Message message = new Message();
            if(last_index == 0){
                List<LoudSourcing> searchedLoudSourcingList = loudSourcingDao.searchLoudSourcing(sort, query);
                message.put("result", searchedLoudSourcingList);
                if(searchedLoudSourcingList.size() > 0)
                    message.put("last_index", searchedLoudSourcingList.get(searchedLoudSourcingList.size() - 1).getLoudsourcing_no());
            } else {
                LoudSourcing loudsourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(last_index);
                if(loudsourcing == null){
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                }
                List<LoudSourcing> searchedLoudSourcingList = loudSourcingDao.searchLoudSourcingRefresh(sort, query, loudsourcing);
                message.put("result", searchedLoudSourcingList);
                if(searchedLoudSourcingList.size() > 0)
                    message.put("last_index", searchedLoudSourcingList.get(searchedLoudSourcingList.size() - 1).getLoudsourcing_no());
            }
            message.put("query", query);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SEARCH_SUCCESS, message.getHashMap("SearchLoudSourcing()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getEntry(int user_no, int entry_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            entryVoteDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();

            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);
            if (entry == null || loudSourcingDao.getLoudSourcingByLoudsourcingNo(entry.getLoudsourcing_no()) == null) {
                log.info(entry_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }
            Artist artist = artistDao.getArtistByArtistNo(entry.getArtist_no());
            entry.setVisit(entry.getVisit() + 1);
            loudSourcingEntryDao.updateEntryByVisit(entry);
            if (entryVoteDao.getEntryVote(user_no, entry_no) != null) {
                message.put("vote", true);
            } else {
                message.put("vote", false);
            }
            if (artist != null) {
                entry.setUser_no(artist.getUser_no());
            }
            message.put("entry", entry);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_ENTRY_SUCCESS, message.getHashMap("GetEntry()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getEntryComment(int entry_no, int last_index) {
        try {
            entryCommentDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            Message message = new Message();
            LoudSourcingEntry loudSourcingEntry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);

            if (loudSourcingEntry == null || loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudSourcingEntry.getLoudsourcing_no()) == null) {
                log.info(entry_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }
            int artist_no = loudSourcingEntry.getArtist_no();

            if(last_index == 0){
                List<EntryComment> entryCommentList = entryCommentDao.getCommentListByEntryNo(entry_no);
                List<EntryComment> resEntryComments = new ArrayList<>();

                if (artist_no != 0) {
                    for (EntryComment entryComment : entryCommentList) {
                        if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(entryComment.getUser_no(), artist_no) != null)
                            entryComment.set_fankoked(true);
                        else
                            entryComment.set_fankoked(false);
                        resEntryComments.add(entryComment);
                    }
                    message.put("comments", resEntryComments);
                } else {
                    message.put("comments", entryCommentList);
                }
                message.put("comment_number", loudSourcingEntry.getComment_number());
            } else {
                EntryComment entryComment1 = entryCommentDao.getEntryCommentByCommentNo(last_index);
                if(entryComment1 == null)
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                List<EntryComment> entryCommentList = entryCommentDao.getCommentListByEntryNoRefresh(entry_no, entryComment1);
                List<EntryComment> resEntryComments = new ArrayList<>();

                if (artist_no != 0) {
                    for (EntryComment entryComment : entryCommentList) {
                        if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(entryComment.getUser_no(), artist_no) != null)
                            entryComment.set_fankoked(true);
                        else
                            entryComment.set_fankoked(false);
                        resEntryComments.add(entryComment);
                    }
                    message.put("comments", resEntryComments);
                } else {
                    message.put("comments", entryCommentList);
                }
                message.put("comment_number", loudSourcingEntry.getComment_number());
            }


            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_ENTRY_COMMENTS_SUCCESS, message.getHashMap("GetEntryComment()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 1000))
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity voteEntry(int user_no, int entry_no) {
        try {
            entryVoteDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            Message message = new Message();
            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);

            if (entry == null || loudSourcingDao.getLoudSourcingByLoudsourcingNo(entry.getLoudsourcing_no()) == null) {
                log.info(entry_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            if (entryVoteDao.getEntryVote(user_no, entry_no) != null) {
                // 투표 이미 한 상태일 시
                entryVoteDao.deleteVote(user_no, entry_no);
                entry.setVote_number(entryVoteDao.getEntryVoteByEntryNo(entry_no).size());
                loudSourcingEntryDao.updateEntryByVote(entry);
                message.put("vote_number", entry.getVote_number());
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNVOTE_SUCCESS, message.getHashMap("VoteEntry()")), HttpStatus.OK);
            } else {
                // 투표 안한 상태일 시
                EntryVote entryVote = new EntryVote();
                entryVote.setLoudsourcing_no(entry.getLoudsourcing_no());
                entryVote.setEntry_no(entry_no);
                entryVote.setUser_no(user_no);
                entryVote.setReg_date(Time.TimeFormatHMS());
                entryVoteDao.insertVote(entryVote);
                entry.setVote_number(entryVoteDao.getEntryVoteByEntryNo(entry_no).size());
                loudSourcingEntryDao.updateEntryByVote(entry);
                message.put("vote_number", entry.getVote_number());
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.VOTE_SUCCESS, message.getHashMap("VoteEntry()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 1000))
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity insertComment(EntryComment entryComment) {
        try {
            entryCommentDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            notificationDao.setSession(sqlSession);
            Message message = new Message();
            LoudSourcingEntry thisEntry = loudSourcingEntryDao.getEntryByEntryNo(entryComment.getEntry_no());

            if (thisEntry == null || loudSourcingDao.getLoudSourcingByLoudsourcingNo(thisEntry.getLoudsourcing_no()) == null) {
                log.info(entryComment.getEntry_no());
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            // Comment SET
            entryComment.setLoudsourcing_no(loudSourcingEntryDao.getEntryByEntryNo(entryComment.getEntry_no()).getLoudsourcing_no());
            Artist entryArtist = artistDao.getArtistByArtistNo(loudSourcingEntryDao.getEntryByEntryNo(entryComment.getEntry_no()).getArtist_no());
            entryComment.setReg_date(Time.TimeFormatHMS());
            if (artistDao.getArtistByUserNo(entryComment.getUser_no()) != null) {
                Artist artist = artistDao.getArtistByUserNo(entryComment.getUser_no());
                entryComment.setProfile_img(artist.getArtist_profile_img());
                entryComment.setCommenter_name(artist.getArtist_name());
            } else {
                User user = userDao.selectUserByUserNo(entryComment.getUser_no());
                entryComment.setProfile_img(user.getProfile_img());
                entryComment.setCommenter_name(user.getName());
            }
            entryComment.setComment_private(false);
            entryCommentDao.insertComment(entryComment);

            // UPDATE ENTRY INFO

            thisEntry.setComment_number(entryCommentDao.getCommentNumberByEntryNo(entryComment.getEntry_no()).size());
            loudSourcingEntryDao.updateEntryByComment(thisEntry);

            // MAKE RESPONSE MESSAGE
            List<EntryComment> entryCommentList = entryCommentDao.getCommentListByEntryNo(entryComment.getEntry_no());
            List<EntryComment> resCommentList = new ArrayList<>();
            if (thisEntry.getArtist_no() != 0) {
                for (EntryComment comment : entryCommentList) {
                    if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(comment.getUser_no(), entryArtist.getArtist_no()) != null)
                        comment.set_fankoked(true);
                    else
                        comment.set_fankoked(false);
                    resCommentList.add(comment);
                }
                message.put("comments", resCommentList);
            } else {
                message.put("comments", entryCommentList);
            }
            message.put("comment_number", thisEntry.getComment_number());


            //FCM MESSAGE SEND
            FirebaseMessagingSnippets firebaseMessagingSnippets = new FirebaseMessagingSnippets();

            String comment_short;
            if (entryComment.getContent().length() < 20) {
                comment_short = entryComment.getContent();
            } else {
                comment_short = entryComment.getContent().substring(0, 20);
            }

            Artist entry_artist = artistDao.getArtistByArtistNo(thisEntry.getArtist_no());
            User entry_artist_user = userDao.selectUserByUserNo(entry_artist.getUser_no());

            if (entryComment.getUser_no() == entry_artist_user.getUser_no()) {
                entry_artist.setRecent_act_date(Time.TimeFormatHMS());
                artistDao.updateArtist(entry_artist);
                List<EntryComment> entryCommentList1 = entryCommentDao.getCommentNumberByEntryNo(entryComment.getEntry_no());
                List<User> userList = new ArrayList<>();
                if (entryCommentList1 != null && !entryCommentList1.isEmpty()) {
                    for (EntryComment comment : entryCommentList1) {
                        if (comment.getUser_no() != entry_artist_user.getUser_no()) {
                            User user1 = userDao.selectUserByUserNo(comment.getUser_no());
                            userList.add(user1);
                        }
                    }

                    if (!userList.isEmpty()) {
                        for (User comment_user : userList) {
                            //FCM MESSAGE SEND
                            if (comment_user.getFcm_token() != null && comment_user.isComment_push()) {
                                NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_ARTIST, NotificationType.CONTENT_TYPE_ENTRY, NotificationType.CONTENT_CATEGORY_VOD, entryComment.getEntry_no(), null, entry_artist.getArtist_no());
                                firebaseMessagingSnippets.push(comment_user.getFcm_token(), NotificationType.COMMENT_ARTIST_FCM, "댓글을 작성한 출품작에 작성한 아티스트가 댓글을 남겼습니다. '" + comment_short + "...'", new Gson().toJson(notificationNext));
                            } else {
                                if (comment_user.getFcm_token() == null)
                                    log.info("FCM TOKEN ERROR, CANNOT SEND FCM MESSAGE");
                                else if (comment_user.isFankok_push())
                                    log.info("this user's push alarm off");
                            }
                            //NOTIFICATION SET
                            Notification notification = new Notification();
                            notification.setUser_no(comment_user.getUser_no());
                            notification.setType(NotificationType.COMMENT_ARTIST);
                            notification.setContent("댓글을 작성한 출품작에 작성한 아티스트가 댓글을 남겼습니다. '" + comment_short + "...'");
                            notification.setReg_date(Time.TimeFormatHMS());
                            NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_ARTIST, NotificationType.CONTENT_TYPE_ENTRY, NotificationType.CONTENT_CATEGORY_VOD, entryComment.getEntry_no(), null, entry_artist.getArtist_no());
                            notification.setNext(new Gson().toJson(notificationNext));
                            notificationDao.insertNotification(notification);
                        }
                    }
                }
            } else {
                NotificationNext notificationNext = new NotificationNext(NotificationType.COMMENT_OTHERS, NotificationType.CONTENT_TYPE_ENTRY, NotificationType.CONTENT_CATEGORY_VOD, entryComment.getEntry_no(), null, entry_artist.getArtist_no());
                if (entry_artist_user.getFcm_token() != null && entry_artist_user.isComment_push())
                    firebaseMessagingSnippets.push(entry_artist_user.getFcm_token(), NotificationType.COMMENT_OTHERS_FCM, "출품작에 새로운 댓글이 등록되었습니다. '" + comment_short + "...'", new Gson().toJson(notificationNext));
                Notification notification = new Notification();
                notification.setUser_no(entry_artist_user.getUser_no());
                notification.setReg_date(Time.TimeFormatHMS());
                notification.setContent("출품작에 새로운 댓글이 등록되었습니다. '" + comment_short + "...'");
                notification.setType(NotificationType.COMMENT_OTHERS);
                notification.setNext(new Gson().toJson(notificationNext));
                notificationDao.insertNotification(notification);
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ENTRY_COMMENT_INSERT_SUCCESS, message.getHashMap("InsertComment()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deleteComment(int entry_no, int comment_no) {
        try {
            loudSourcingEntryDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            Message message = new Message();

            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);

            if (entry == null || loudSourcingDao.getLoudSourcingByLoudsourcingNo(entry.getLoudsourcing_no()) == null) {
                log.info(entry_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            // DELETE COMMENT
            entryCommentDao.deleteComment(comment_no);

            // ENTRY INFO UPDATE

            entry.setComment_number(entryCommentDao.getCommentNumberByEntryNo(entry_no).size());
            loudSourcingEntryDao.updateEntryByComment(entry);

            Artist entryArtist = artistDao.getArtistByArtistNo(loudSourcingEntryDao.getEntryByEntryNo(entry_no).getArtist_no());

            // RESPONSE MESSAGE SET
            List<EntryComment> entryCommentList = entryCommentDao.getCommentListByEntryNo(entry_no);
            List<EntryComment> resCommentList = new ArrayList<>();
            if (entry.getArtist_no() != 0) {
                for (EntryComment comment : entryCommentList) {
                    if (subscribeDao.getSubscribeInfoByUserNoANDArtistNo(comment.getUser_no(), entryArtist.getArtist_no()) != null)
                        comment.set_fankoked(true);
                    else
                        comment.set_fankoked(false);
                    resCommentList.add(comment);
                }
                message.put("comments", resCommentList);
            } else {
                message.put("comments", entryCommentList);
            }
            message.put("comment_number", entry.getComment_number());

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ENTRY_COMMENT_DELETE_SUCCESS, message.getHashMap("DeleteComment()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deleteEntry(int entry_no) {
        try {
            loudSourcingDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();
            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);

            if (entry == null || loudSourcingDao.getLoudSourcingByLoudsourcingNo(entry.getLoudsourcing_no()) == null) {
                log.info(entry_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.DELETE_CONTENTS, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
            }

            Artist artist = artistDao.getArtistByArtistNo(entry.getArtist_no());
            LoudSourcingApply apply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artist.getArtist_no(), entry.getLoudsourcing_no());
            loudSourcingEntryDao.deleteEntry(entry_no);

            apply.setEntry(false);
            loudSourcingApplyDao.updateApply(apply);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.DELETE_ENTRY_SUCCESS, message.getHashMap("DeleteEntry()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getMyLoudsourcingList(int user_no, int start_index) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);

            if (artistDao.getArtistByUserNo(user_no) != null) {
                Artist artist = artistDao.getArtistByUserNo(user_no);
                List<LoudSourcingApply> myApplyList = loudSourcingApplyDao.getLoudSourcingApplyListByArtistNo(artist.getArtist_no());
                List<MyLoudSourcingResponse> myLoudsourcingList = new ArrayList<>();
                for (LoudSourcingApply loudSourcingApply : myApplyList) {
                    int loudsourcing_no = loudSourcingApply.getLoudsourcing_no();
                    String apply_date = loudSourcingApply.getReg_date();
                    LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
                    MyLoudSourcingResponse myLoudSourcingResponse = new MyLoudSourcingResponse();
                    myLoudSourcingResponse.setLoudSourcing(loudSourcing);
                    myLoudSourcingResponse.setApply_date(apply_date);
                    myLoudsourcingList.add(myLoudSourcingResponse);
                }

                myLoudsourcingList.sort((o1, o2) -> {
                    String ds1 = o1.getApply_date();
                    String ds2 = o2.getApply_date();
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

                List<MyLoudSourcingResponse> indexList = new ArrayList<>();
                for (int i = start_index; i < start_index + 10; i++) {
                    if (myLoudsourcingList.size() <= i)
                        break;
                    indexList.add(myLoudsourcingList.get(i));
                }
                message.put("loudsourcing_list", indexList);
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_MY_LOUDSOURCING_LIST, message.getHashMap("GetMyLoudsourcingList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Getter
    @Setter
    @Data
    class MyLoudSourcingResponse {
        private LoudSourcing loudSourcing;
        private String apply_date;
    }
}
