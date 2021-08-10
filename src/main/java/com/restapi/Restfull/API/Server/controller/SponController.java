package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
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
            Message message = new Message();
            Spon spon = new Gson().fromJson(body, Spon.class);
            PurchaseData purchaseData = new Gson().fromJson(spon.getPurchase_data(), PurchaseData.class);
            log.info("후원 정보 : " + spon);
            log.info("영수증 정보 : " + purchaseData);
            if(spon.getPrice() != purchaseData.getPrice()){
                log.info("Spon.getPrice() : " + spon.getPrice());
                log.info("PurchaseData.getPrice() : " + purchaseData.getPrice());
                return new ResponseEntity(DefaultRes.res(StatusCode.NOT_EXTENDED, ResMessage.PURCHASE_DATA_ERROR, message.getHashMap("ArtistSpon()")), HttpStatus.OK);
            }
            String token = bootpayService.goGetToken();
            log.info(token);
            String result = bootpayService.goVerify(spon.getReceipt_id(), token);
            BootpayVerificationData bootpayVerificationData = new Gson().fromJson(result, BootpayVerificationData.class);
            if(bootpayVerificationData.getStatus() != 200){
                log.info(bootpayVerificationData.getCode());
                log.info(bootpayVerificationData.getMessage());
                return new ResponseEntity(DefaultRes.res(StatusCode.NOT_EXTENDED, ResMessage.PURCHASE_DATA_ERROR, message.getHashMap("ArtistSpon()")), HttpStatus.OK);
            }
            // TODO 부트페이 REST API로 결제변조 여부 확인 로직 -> 서버 연결 후 작성
            return sponService.insertSpon(spon);
        } catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @RequestMapping(value = "/api/board/spon", method = RequestMethod.POST) //CHECK
    public ResponseEntity Spon(@RequestBody String body) {
        try {
            Message message = new Message();
            Spon spon = new Gson().fromJson(body, Spon.class);
            PurchaseData purchaseData = new Gson().fromJson(spon.getPurchase_data(), PurchaseData.class);
            log.info("후원 정보 : " + spon);
            log.info("영수증 정보 : " + purchaseData);
            if(spon.getPrice() != purchaseData.getPrice()){
                log.info("Spon.getPrice() : " + spon.getPrice());
                log.info("PurchaseData.getPrice() : " + purchaseData.getPrice());
                log.info("가격 정보가 맞지 않습니다.");
                return new ResponseEntity(DefaultRes.res(StatusCode.NOT_EXTENDED, ResMessage.PURCHASE_DATA_ERROR, message.getHashMap("Spon()")), HttpStatus.OK);
            }
            String token = bootpayService.goGetToken();
            log.info(token);
            String result = bootpayService.goVerify(spon.getReceipt_id(), token);
            BootpayVerificationData bootpayVerificationData = new Gson().fromJson(result, BootpayVerificationData.class);
            if(bootpayVerificationData.getStatus() != 200){
                log.info(bootpayVerificationData.getCode());
                log.info(bootpayVerificationData.getMessage());
                return new ResponseEntity(DefaultRes.res(StatusCode.NOT_EXTENDED, ResMessage.PURCHASE_DATA_ERROR, message.getHashMap("Spon()")), HttpStatus.OK);
            }
            // TODO 부트페이 REST API로 결제변조 여부 확인 로직 -> 서버 연결 후 작성
            return sponService.insertSpon(spon);
        } catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }
}
