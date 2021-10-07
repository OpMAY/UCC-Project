package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.AndroidVerificationRequest;
import com.restapi.Restfull.API.Server.models.BootpayVerificationData;
import com.restapi.Restfull.API.Server.models.PurchaseData;
import com.restapi.Restfull.API.Server.models.Spon;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.BoardService;
import com.restapi.Restfull.API.Server.services.BootpayService;
import com.restapi.Restfull.API.Server.services.SponService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Log4j2
@RestController
public class SponController {
    @Autowired
    private SponService sponService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BootpayService bootpayService;

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

    @RequestMapping(value = "/api/spon", method = RequestMethod.POST) //CHECK
    public ResponseEntity ArtistSpon(@RequestBody String body) {
        try {
            Spon spon = new Gson().fromJson(body, Spon.class);
            log.info("Before : " + spon);
            return sponService.insertSpon(spon);
        } catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @RequestMapping(value = "/api/spon/validate/android", method = RequestMethod.POST)
    public ResponseEntity AndroidPurchaseValidate(@RequestBody String body){
        try{
            AndroidVerificationRequest request = new Gson().fromJson(body, AndroidVerificationRequest.class);
            return sponService.androidPurchaseValidate(request);
        } catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

}
