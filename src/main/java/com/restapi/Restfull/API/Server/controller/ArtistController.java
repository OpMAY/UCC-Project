package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.*;
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
    private ArtistService artistService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private ArtistVisitService artistVisitService;

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
        return artistService.ArtistMain(artistRequest.getUser_no(), artistRequest.getArtist_no());
    }

    @RequestMapping(value = "/api/fankok", method = RequestMethod.POST)
    public ResponseEntity Subscribe(@ModelAttribute ArtistRequest artistRequest) {
        return subscribeService.Fankok(artistRequest.getUser_no(), artistRequest.getArtist_no());
    }

    @RequestMapping(value = "/api/artists", method = RequestMethod.GET)
    public ResponseEntity GetArtistList(){
        return artistService.getAllArtists();
    }

    @RequestMapping(value = "/api/artists/search", method = RequestMethod.GET)
    public ResponseEntity SearchArtist(@RequestParam("query") String query){
        return artistService.SearchArtist(query);
    }
}
