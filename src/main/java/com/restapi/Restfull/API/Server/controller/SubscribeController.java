package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.SubscribeService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Log4j2
@RestController
public class SubscribeController {
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

    @RequestMapping(value = "/api/fankok/delete/{sort}", method = RequestMethod.POST) // CHECK
    public ResponseEntity DeleteFankok(@RequestBody String body, @PathVariable String sort) {
        ArtistRequest artistRequest = new Gson().fromJson(body, ArtistRequest.class);
        /** 구독 정보 확인 - 있으면 팬콕 취소, 없으면 BAD REQUEST ERROR**/
        if (subscribeService.getSubscribeInfoByUserNoANDArtistNo(artistRequest.getUser_no(), artistRequest.getArtist_no()) != null) {
            return subscribeService.Fankok(artistRequest.getUser_no(), artistRequest.getArtist_no(), sort);
        } else {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.NOT_SUBSCRIBED_ARTIST), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/fankok/user/{user_no}/start_index/{start_index}", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetUserFankokContents(@PathVariable("user_no") int user_no,
                                                @PathVariable("start_index") int start_index) {
        return subscribeService.getSubscribeListByUserNo(user_no, start_index);
    }

    @RequestMapping(value = "/api/fankok/user/{user_no}/artists/{sort}/{start_index}", method = RequestMethod.GET)
    // CHECK
    public ResponseEntity GetUserFankokArtist(@PathVariable("user_no") int user_no,
                                              @PathVariable("sort") String sort,
                                              @PathVariable("start_index") int start_index) {
        return subscribeService.getSubscribeArtistList(user_no, start_index, sort);
    }

    @RequestMapping(value = "/api/user/{user_no}/fankoklist", method = RequestMethod.GET)
    public ResponseEntity GetSubscribedArtists(@PathVariable("user_no") int user_no) {
        return subscribeService.getSubscribedArtists(user_no);
    }
}
