package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.AppleNotificationResponse;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.MainService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Log4j2
@RestController
public class MainController {
    @Autowired
    private MainService mainService;


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

    @Data
    class BanArtist{
        private List<Integer> artistList;
    }

    @RequestMapping(value = "/api/main", method = RequestMethod.POST)
    public ResponseEntity GetMain(@RequestBody String body) {
        BanArtist artist = new Gson().fromJson(body, BanArtist.class);
        return mainService.GetMain(artist.getArtistList());
    }

    @RequestMapping(value = "/api/cdn/delete", method = RequestMethod.GET)
    public ResponseEntity DeleteCDNManual(){
        try {
            Message message = new Message();
            mainService.deleteCDNFiles();
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.MANUALLY_DELETE_CDN, message.getHashMap("DeleteCDNManual()")), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @RequestMapping(value = "/api/apple/notification", method = RequestMethod.POST)
    public ResponseEntity AppleServerToServerNotification(@RequestBody String body){
        try{
            AppleNotificationResponse response = new Gson().fromJson(body, AppleNotificationResponse.class);
            log.info(response);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.APPLE_NOTIFICATION_SUCCESS), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.TEST_FAILED), HttpStatus.OK);
        }
    }

}
