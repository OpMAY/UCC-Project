package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.AppleVerifyRequest;
import com.restapi.Restfull.API.Server.models.AppleVerifyResponse;
import com.restapi.Restfull.API.Server.models.Auth;
import com.restapi.Restfull.API.Server.models.GPResponseModel;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.SecurityService;
import com.restapi.Restfull.API.Server.services.CurrencyService;
import com.restapi.Restfull.API.Server.utility.ASVerification;
import com.restapi.Restfull.API.Server.utility.GPVerification;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;


@Log4j2
@RestController
public class HomeController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private CurrencyService currencyService;

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

    @RequestMapping(value = "/api/auth", method = RequestMethod.GET)
    public ResponseEntity Auth() {
        String name = "okiwi";
        String key = "test";
        try {
            String api_access_key = securityService.createToken(new Auth(name, key));
            Message message = new Message();
            message.put("access_token", api_access_key);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("Auth()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/auth/valid", method = RequestMethod.POST)
    public ResponseEntity ValidateToken(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION).substring(headers.getFirst(HttpHeaders.AUTHORIZATION).lastIndexOf("bearer ") + 7);
        if (securityService.validateToken(token)) {
            log.info("success");
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity(DefaultRes.res(StatusCode.UNAUTHORIZED, ResMessage.TEST_FAILED), HttpStatus.OK);
        }
    }

    @Data
    class Test {
        private String name;
    }

    @Data
    class PurchaseVerification {
        private int user_no;
        private int artist_no;
        private String packageName;
        private String productId;
        private String purchaseToken;
        private String receipt_data;
        private String password;
        private String platform;
    }

    @RequestMapping(value = "/api/test", method = RequestMethod.POST)
    public ResponseEntity TestMethod(@RequestBody String body) {
        try {
            Message message = new Message();
//            String time = Time.TimeFormatDay();
//            currencyService.saveCurrencyInfo(time);
            /** Google PlayStore, Apple AppStore Purchase Verification Logic **/
            PurchaseVerification verification = new Gson().fromJson(body, PurchaseVerification.class);
            if (verification.getPlatform().equals("Android")) {
                GPResponseModel gpResponse = GPVerification.getInstance().verify(verification.getProductId(), verification.getPurchaseToken());
                log.info(gpResponse);
                message.put("google_verify", gpResponse);
            } else if (verification.getPlatform().equals("IOS")) {
//                String receipt = Base64.getEncoder().encodeToString(verification.getReceipt_data().getBytes(StandardCharsets.UTF_8));
                AppleVerifyRequest request = new AppleVerifyRequest(verification.getReceipt_data(), verification.getPassword(), true);
                AppleVerifyResponse asResponse = ASVerification.getInstance().verify(request);
                message.put("apple_verify", asResponse);
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("TestEncode()")), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }
}
