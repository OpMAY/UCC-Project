package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.ArtistService;
import com.restapi.Restfull.API.Server.services.BoardService;
import com.restapi.Restfull.API.Server.services.PortfolioService;
import com.restapi.Restfull.API.Server.services.SubscribeService;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @Autowired
    BoardService boardService;

    @Autowired
    PortfolioService portfolioService;

    @Autowired
    SubscribeService subscribeService;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity BusinessException(Exception e) {
        log.info("Business Exception Handler");
        e.printStackTrace();
        return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity SqlException(Exception e) {
        e.printStackTrace();
        log.info("SQL Exception Handler");
        return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity NullPointerException(Exception e) {
        e.printStackTrace();
        log.info("NullPointer Exception Handler");
        return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()), HttpStatus.OK);
    }

    @Setter
    @Getter
    @Data
    class ArtistRequest {
        private int user_no;
        private int artist_no;
    }

    @RequestMapping(value = "/api/artist", method = RequestMethod.POST)
    public ResponseEntity GetArtist(@ModelAttribute ArtistRequest artistRequest) {
        try {
            Message message = new Message();
            /** required Data
             * 1. Artist - Done
             * 2. PortfolioList - Done
             * 3. Board - Done
             * 4. Subscribe - Done
             * **/
            Artist artist = artistService.getArtistByArtistNo(artistRequest.getArtist_no());
            List<Portfolio> portfolioList = portfolioService.getPortfolioListByArtistNo(artist.getArtist_no());
            List<Board> boardList = boardService.getBoardListByArtistNo(artist.getArtist_no());
            boolean subscribe = subscribeService.getSubscribeInfoByUserNoANDArtistNo(artistRequest.user_no, artistRequest.artist_no) != null;
            message.put("Artist", artist);
            message.put("Portfolios", portfolioList);
            message.put("Boards", boardList);
            message.put("subscribe", subscribe);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_INFO_CALL_SUCCESS, message.getHashMap("GetArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/fankok", method = RequestMethod.POST)
    public ResponseEntity Subscribe(@ModelAttribute ArtistRequest artistRequest) {
        try {
            Message message = new Message();
            int user_no = artistRequest.user_no;
            int artist_no = artistRequest.artist_no;
            if (subscribeService.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no) != null) {
                // 팬콕 했을 경우 -> 팬콕 취소
                subscribeService.deleteSubscribe(user_no, artist_no);
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UNDO_SUBSCRIBE_SUCCESS, message.getHashMap("Subscribe()")), HttpStatus.OK);
            } else if(artistService.getArtistByArtistNo(artist_no).getUser_no() == user_no){
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.CANNOT_SUBSCRIBE_YOURSELF), HttpStatus.OK);
            } else {
                // 팬콕하지 않았을 경우 -> 팬콕
                Subscribe subscribe = new Subscribe();
                Date now =  Time.LongTimeStampCurrent();
                // Set Subscribe Info
                subscribe.setUser_no(user_no);
                subscribe.setArtist_no(artist_no);
                subscribe.setSubscribe_date(now);
                // DB SET
                subscribeService.insertSubscribe(subscribe);
                message.put("Subscribe", subscribeService.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no));
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SUBSCRIBE_SUCCESS, message.getHashMap("Subscribe()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

}
