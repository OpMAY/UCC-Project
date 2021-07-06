package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
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
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getLoudSourcingList(String sort, int start_index) {
        try {
            Message message = new Message();
            loudSourcingDao.setSession(sqlSession);
            List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatus(sort, start_index);

            message.put("loudsourcing", loudSourcingList);
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

            if(artistDao.getArtistByUserNo(user_no) != null){
                if(loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artistDao.getArtistByUserNo(user_no).getArtist_no(), loudsourcing_no) != null){
                    message.put("apply", true);
                }else{
                    message.put("apply", false);
                }
            }else{
                message.put("apply", false);
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

            LoudSourcingApply loudSourcingApply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(loudSourcingEntry.getArtist_no(), loudSourcingEntry.getLoudsourcing_no());
            Artist artist = artistDao.getArtistByArtistNo(loudSourcingEntry.getArtist_no());
            // Upload Entry
            if(!loudSourcingApply.isEntry()) {
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

                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOUDSOURCING_ENTRY_UPLOADED, message.getHashMap("UploadLoudSourcingEntry()")), HttpStatus.OK);
            } else {
                return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
            }
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getEntryList(int user_no, int loudsourcing_no, String sort, int start_index) {
        try{
            artistDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            Message message = new Message();

            if(artistDao.getArtistByUserNo(user_no) != null && loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artistDao.getArtistByUserNo(user_no).getArtist_no(), loudsourcing_no) != null){
                    LoudSourcingEntry myEntry = loudSourcingEntryDao.getEntryByArtistNOAndLoudSourcingNo(artistDao.getArtistByUserNo(user_no).getArtist_no(), loudsourcing_no);
                    myEntry.setUser_no(artistDao.getArtistByArtistNo(myEntry.getArtist_no()).getUser_no());
                    myEntry.setFan_number(artistDao.getArtistByArtistNo(myEntry.getArtist_no()).getFan_number());
                    message.put("my_entry", myEntry);
            }

            List<LoudSourcingEntry> loudSourcingEntryList = loudSourcingEntryDao.getEntryListByLoudSourcingNo(loudsourcing_no, sort, start_index);

            List<LoudSourcingEntry> resEntryList = new ArrayList<>();
            for(LoudSourcingEntry loudSourcingEntry : loudSourcingEntryList){
                Artist artist = artistDao.getArtistByArtistNo(loudSourcingEntry.getArtist_no());
                loudSourcingEntry.setFan_number(artist.getFan_number());
                loudSourcingEntry.setUser_no(artist.getUser_no());
                resEntryList.add(loudSourcingEntry);
            }

            message.put("entry_list", resEntryList);
            message.put("sort", sort);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_ENTRY_LIST, message.getHashMap("GetEntryList()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity applyLoudSourcing(LoudSourcingApply loudSourcingApply) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            Message message = new Message();

            loudSourcingApply.setEntry(false);
            loudSourcingApply.setReg_date(Time.TimeFormatHMS());
            loudSourcingApplyDao.insertLoudSourcingApply(loudSourcingApply);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOUDSOURCING_APPLICATION_SUCCESS, message.getHashMap("ApplyLoudSourcing()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity cancelLoudSourcing(LoudSourcingApply loudSourcingApply) {
        try {
            loudSourcingApplyDao.setSession(sqlSession);
            Message message = new Message();

            if(loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(loudSourcingApply.getArtist_no(), loudSourcingApply.getLoudsourcing_no()) != null){
                loudSourcingApplyDao.deleteLoudSourcingApply(loudSourcingApply.getArtist_no(), loudSourcingApply.getLoudsourcing_no());
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOUDSOURCING_CANCEL_SUCCESS, message.getHashMap("CancelLoudSourcing()")), HttpStatus.OK);
            }else{
                return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
            }
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity searchLoudSourcing(String sort, String query, int start_index) {
        try {
            loudSourcingDao.setSession(sqlSession);
            Message message = new Message();
            List<LoudSourcing> searchedLoudSourcingList = loudSourcingDao.searchLoudSourcing(sort, query, start_index);

            message.put("result", searchedLoudSourcingList);
            message.put("query", query);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SEARCH_SUCCESS, message.getHashMap("SearchLoudSourcing()")), HttpStatus.OK);
        }catch (JSONException e){
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
            Artist artist = artistDao.getArtistByArtistNo(entry.getArtist_no());
            entry.setVisit(entry.getVisit() + 1);
            loudSourcingEntryDao.updateEntryByVisit(entry);
            if(entryVoteDao.getEntryVote(user_no, entry_no) != null){
                message.put("vote", true);
            }else{
                message.put("vote", false);
            }
            entry.setUser_no(artist.getUser_no());
            message.put("Entry", entry);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_ENTRY_SUCCESS, message.getHashMap("GetEntry()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getEntryComment(int entry_no, int start_index) {
        try {
            entryCommentDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            Message message = new Message();
            LoudSourcingEntry loudSourcingEntry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);
            int artist_no = loudSourcingEntry.getArtist_no();

            List<EntryComment> entryCommentList = entryCommentDao.getCommentListByEntryNo(entry_no, start_index);
            List<EntryComment> resEntryComments = new ArrayList<>();

            for(EntryComment entryComment : entryCommentList){
                if(subscribeDao.getSubscribeInfoByUserNoANDArtistNo(entryComment.getUser_no(), artist_no) != null)
                    entryComment.set_fankoked(true);
                else
                    entryComment.set_fankoked(false);
                resEntryComments.add(entryComment);
            }

            message.put("comments", resEntryComments);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_ENTRY_COMMENTS_SUCCESS, message.getHashMap("GetEntryComment()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity voteEntry(int user_no, int entry_no) {
        try {
            entryVoteDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            Message message = new Message();
            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);
            if(entryVoteDao.getEntryVote(user_no, entry_no) != null){
                // 투표 이미 한 상태일 시
                entryVoteDao.deleteVote(user_no, entry_no);
                entry.setVote_number(entry.getVote_number() - 1);
                loudSourcingEntryDao.updateEntryByVote(entry);
                message.put("vote_number", entry.getVote_number());
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNVOTE_SUCCESS, message.getHashMap("VoteEntry()")), HttpStatus.OK);
            }else {
                // 투표 안한 상태일 시
                EntryVote entryVote = new EntryVote();
                entryVote.setLoudsourcing_no(entry.getLoudsourcing_no());
                entryVote.setEntry_no(entry_no);
                entryVote.setUser_no(user_no);
                entryVote.setReg_date(Time.TimeFormatHMS());
                entryVoteDao.insertVote(entryVote);
                entry.setVote_number(entry.getVote_number() + 1);
                loudSourcingEntryDao.updateEntryByVote(entry);
                message.put("vote_number", entry.getVote_number());
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.VOTE_SUCCESS, message.getHashMap("VoteEntry()")), HttpStatus.OK);
            }
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity insertComment(EntryComment entryComment) {
        try {
            entryCommentDao.setSession(sqlSession);
            loudSourcingEntryDao.setSession(sqlSession);
            subscribeDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();

            // Comment SET
            entryComment.setLoudsourcing_no(loudSourcingEntryDao.getEntryByEntryNo(entryComment.getEntry_no()).getLoudsourcing_no());
            Artist entryArtist = artistDao.getArtistByArtistNo(loudSourcingEntryDao.getEntryByEntryNo(entryComment.getEntry_no()).getArtist_no());
            entryComment.setReg_date(Time.TimeFormatHMS());
            if(artistDao.getArtistByUserNo(entryComment.getUser_no()) != null){
                Artist artist = artistDao.getArtistByUserNo(entryComment.getUser_no());
                entryComment.setProfile_img(artist.getArtist_profile_img());
                entryComment.setCommenter_name(artist.getArtist_name());
            }else{
                User user = userDao.selectUserByUserNo(entryComment.getUser_no());
                entryComment.setProfile_img(user.getProfile_img());
                entryComment.setCommenter_name(user.getName());
            }
            entryComment.setComment_private(false);
            entryCommentDao.insertComment(entryComment);

            // UPDATE ENTRY INFO
            LoudSourcingEntry thisEntry = loudSourcingEntryDao.getEntryByEntryNo(entryComment.getEntry_no());
            thisEntry.setComment_number(thisEntry.getComment_number() + 1);
            loudSourcingEntryDao.updateEntryByComment(thisEntry);

            // MAKE RESPONSE MESSAGE
            List<EntryComment> entryCommentList = entryCommentDao.getCommentListByEntryNo(entryComment.getEntry_no(), 0);
            List<EntryComment> resCommentList = new ArrayList<>();
            for(EntryComment comment : entryCommentList){
                if(subscribeDao.getSubscribeInfoByUserNoANDArtistNo(comment.getUser_no(), entryArtist.getArtist_no()) != null)
                    comment.set_fankoked(true);
                else
                    comment.set_fankoked(false);
                resCommentList.add(comment);
            }
            message.put("comments", resCommentList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ENTRY_COMMENT_INSERT_SUCCESS, message.getHashMap("InsertComment()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deleteComment(int entry_no, int comment_no) {
        try {
            loudSourcingEntryDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            Message message = new Message();
            // DELETE COMMENT
            entryCommentDao.deleteComment(comment_no);

            // ENTRY INFO UPDATE
            LoudSourcingEntry entry = loudSourcingEntryDao.getEntryByEntryNo(entry_no);
            entry.setComment_number(entry.getComment_number() - 1);
            loudSourcingEntryDao.updateEntryByComment(entry);

            Artist entryArtist = artistDao.getArtistByArtistNo(loudSourcingEntryDao.getEntryByEntryNo(entry_no).getArtist_no());

            // RESPONSE MESSAGE SET
            List<EntryComment> entryCommentList = entryCommentDao.getCommentListByEntryNo(entry_no, 0);
            List<EntryComment> resCommentList = new ArrayList<>();
            for(EntryComment comment : entryCommentList){
                if(subscribeDao.getSubscribeInfoByUserNoANDArtistNo(comment.getUser_no(), entryArtist.getArtist_no()) != null)
                    comment.set_fankoked(true);
                else
                    comment.set_fankoked(false);
                resCommentList.add(comment);
            }
            message.put("comments", resCommentList);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ENTRY_COMMENT_DELETE_SUCCESS, message.getHashMap("DeleteComment()")), HttpStatus.OK);
        }catch (JSONException e){
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
            Artist artist = artistDao.getArtistByArtistNo(entry.getArtist_no());
            LoudSourcing loudSourcing = loudSourcingDao.getLoudSourcingByLoudsourcingNo(entry.getLoudsourcing_no());
            loudSourcingEntryDao.deleteEntry(entry_no);

            LoudSourcingApply apply = loudSourcingApplyDao.getLoudSourcingApplyByArtistNoAndLoudSourcingNo(artist.getArtist_no(), loudSourcing.getLoudsourcing_no());
            apply.setEntry(false);
            loudSourcingApplyDao.updateApply(apply);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.DELETE_ENTRY_SUCCESS, message.getHashMap("DeleteEntry()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getMyLoudsourcingList(int user_no, int start_index) {
        try{
            Message message = new Message();
            artistDao.setSession(sqlSession);
            loudSourcingApplyDao.setSession(sqlSession);
            loudSourcingDao.setSession(sqlSession);

            if(artistDao.getArtistByUserNo(user_no) != null){
                Artist artist = artistDao.getArtistByUserNo(user_no);
                List<LoudSourcingApply> myApplyList = loudSourcingApplyDao.getLoudSourcingApplyListByArtistNo(artist.getArtist_no());
                List<MyLoudSourcingResponse> myLoudsourcingList = new ArrayList<>();
                for(LoudSourcingApply loudSourcingApply : myApplyList){
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
                        d1 = Time.StringToDateFormat(ds1);
                        d2 = Time.StringToDateFormat(ds2);
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
                for(int i = start_index; i < start_index + 10; i++){
                    if(myLoudsourcingList.size() <= i)
                        break;
                    indexList.add(myLoudsourcingList.get(i));
                }
                message.put("loudsourcing_list", indexList);
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_MY_LOUDSOURCING_LIST, message.getHashMap("GetMyLoudsourcingList()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }

    @Getter
    @Setter
    @Data
    class MyLoudSourcingResponse{
        private LoudSourcing loudSourcing;
        private String apply_date;
    }
}
