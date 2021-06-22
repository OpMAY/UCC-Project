package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Spon;
import com.restapi.Restfull.API.Server.models.User;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.services.BoardService;
import com.restapi.Restfull.API.Server.services.SponService;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Date;

@Log4j2
@RestController
public class SponController {
    @Autowired
    private SponService sponService;

    @Autowired
    private BoardService boardService;

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

    @RequestMapping(value = "/api/spon", method = RequestMethod.POST)
    public ResponseEntity ArtistSpon(@ModelAttribute Spon spon){
        try {
            Message message = new Message();
            Date now = Time.LongTimeStampCurrent();
            // Spon Data Set
            spon.setSpon_date(now);
            spon.setStatus(SponStatus.NOT_CONFIRMED);
            spon.setType(SponType.Artist_SPON);
            // DB Set
            sponService.insertSpon(spon);
            // Message Set
            message.put("Spon", spon);

            // TODO 부트페이 REST API로 결제변조 여부 확인 로직 -> 서버 연결 후 작성
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_SPON_SUCCESS, message.getHashMap("ArtistSpon()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/board/spon", method = RequestMethod.POST)
    public ResponseEntity BoardSpon(@ModelAttribute Spon spon){
        try {
            Message message = new Message();
            Date now = Time.LongTimeStampCurrent();
            int board_no = spon.getBoard_no();
            // Spon Data Set
            spon.setSpon_date(now);
            spon.setStatus(SponStatus.NOT_CONFIRMED);
            spon.setType(SponType.BOARD_SPON);
            // DB Set
            sponService.insertSpon(spon);
            // TODO Board Comment Set -> 게시판 로직 작성 후 이어서 작성 2021-06-22
            // Message Set
            message.put("Spon", spon);

            // TODO 부트페이 REST API로 결제변조 여부 확인 로직 -> 서버 연결 후 작성
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.BOARD_SPON_SUCCESS, message.getHashMap("BoardSpon()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }
}
