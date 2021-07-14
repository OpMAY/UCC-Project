package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.*;
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
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @Autowired
    private SubscribeService subscribeService;

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

    /**
        주석 생성 날짜 - 2021-07-29, 목, 14:03
        코드 설명 : 아티스트 팬페이지 화면에서 게시글을 제외한 모든 정보를 받아오는 URL
        특이 사항 : 리로딩 X
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/artist", method = RequestMethod.POST) //CHECK
    public ResponseEntity GetArtistWithoutBoard(@RequestBody String body) {
        log.info(body);
        ArtistRequest artistRequest = new Gson().fromJson(body, ArtistRequest.class);
        return artistService.ArtistMain(artistRequest.getUser_no(), artistRequest.getArtist_no(), -1);
    }

    /**
        주석 생성 날짜 - 2021-07-29, 목, 14:04
        코드 설명 : 아티스트 팬페이지 화면에 사용할 게시글 리스트를 받아오는 URL
        특이 사항 : start_index로 10개 씩 리로딩
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/artist/{start_index}", method = RequestMethod.POST) // CHECK
    public ResponseEntity GetArtistBoard(@RequestBody String body, @PathVariable("start_index") int start_index) {
        ArtistRequest artistRequest = new Gson().fromJson(body, ArtistRequest.class);
        log.info(body);
        return artistService.ArtistMain(artistRequest.getUser_no(), artistRequest.getArtist_no(), start_index);
    }

    /**
        주석 생성 날짜 - 2021-07-29, 목, 14:06
        코드 설명 : 팬콕 / 팬콕 취소를 처리하는 URL
        특이 사항 : 팬콕 여부를 서버에서 판단함
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/fankok", method = RequestMethod.POST) //CHECK
    public ResponseEntity Subscribe(@RequestBody String body) {
        ArtistRequest artistRequest = new Gson().fromJson(body, ArtistRequest.class);
        log.info(body);
        log.info("fankok");
        return subscribeService.Fankok(artistRequest.getUser_no(), artistRequest.getArtist_no(), "");
    }

    /**
        주석 생성 날짜 - 2021-07-29, 목, 14:07
        코드 설명 : 최신 아티스트 목록을 받아오는 URL
        특이 사항 : 제일 최근에 생성된 아티스트 최대 15명을 불러옴
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/artists/new", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetNewArtistList() {
        return artistService.getNewArtists();
    }

    /**
        주석 생성 날짜 - 2021-07-29, 목, 14:08
        코드 설명 : 모든 아티스트 목록을 받아오는 URL
        특이 사항 : 정렬 기준, start_index
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/artists/all/{sort}/{start_index}", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetAllArtistList(@PathVariable("sort") String sort,
                                           @PathVariable("start_index") int start_index) {
        return artistService.getAllArtists(start_index, sort);
    }

    /**
        주석 생성 날짜 - 2021-07-29, 목, 14:08
        코드 설명 : 아티스트를 검색하는 URL
        특이 사항 : X
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/artists/search", method = RequestMethod.GET) //CHECK
    public ResponseEntity SearchArtist(@RequestParam("query") String query) {
        return artistService.SearchArtist(query);
    }

    /**
        주석 생성 날짜 - 2021-07-29, 목, 14:21
        코드 설명 : 아티스트가 정지 되었는지에 대한 여부 판단 및 정지 정보 받아오는 URL
        특이 사항 : X
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/artist/ban/{artist_no}", method = RequestMethod.GET)
    public ResponseEntity GetArtistBanInfo(@PathVariable("artist_no") int artist_no){
        return artistService.getArtistBanInfo(artist_no);
    }

    /**
        주석 생성 날짜 - 2021-07-30, 금, 14:43
        코드 설명 : 아티스트 크라우드 알림 설정 변경
        특이 사항 : X
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/artist/set_push/loudsourcing/{artist_no}", method = RequestMethod.POST)
    public ResponseEntity UpdateArtistPush(@PathVariable("artist_no") int artist_no){
        return artistService.updateArtistPush(artist_no);
    }
}
