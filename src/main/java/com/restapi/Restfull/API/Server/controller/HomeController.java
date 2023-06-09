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
import com.restapi.Restfull.API.Server.services.CurrencyService;
import com.restapi.Restfull.API.Server.services.SecurityService;
import com.restapi.Restfull.API.Server.utility.ASVerification;
import com.restapi.Restfull.API.Server.utility.FileConverter;
import com.restapi.Restfull.API.Server.utility.GPVerification;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                AppleVerifyRequest request = new AppleVerifyRequest(verification.getReceipt_data(), verification.getPassword(), true, false);
                AppleVerifyResponse asResponse = ASVerification.getInstance().verify(request);
                message.put("apple_verify", asResponse);
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("TestEncode()")), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @RequestMapping(value = "/api/currency", method = RequestMethod.GET)
    public ResponseEntity UpdateCurrency(@RequestParam("date") String date) {
        try {
            currencyService.saveCurrencyInfo(date);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @RequestMapping(value = "/api/filetest", method = RequestMethod.POST)
    public ResponseEntity FileUploadTest(@RequestParam("file") MultipartFile[] files) {
        try {
            FileConverter fileConverter = new FileConverter();
            List<File> fileList = new ArrayList<>();
            Map<String, MultipartFile> multipartFileMap = new HashMap<>();
            for (int i = 0; i < files.length; i++) {
                multipartFileMap.put("files-" + i, files[i]);
            }
            for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                MultipartFile multipartFile = entry.getValue();
                log.info("originalName:" + multipartFile.getOriginalFilename());
                log.info("size:" + multipartFile.getSize());
                log.info("ContentType:" + multipartFile.getContentType());
                File file = fileConverter.convert(multipartFile, "temp" + Time.TimeForFile() + multipartFile.getOriginalFilename());
                log.info(file.getAbsolutePath());
                fileList.add(file);
            }
            for (File file : fileList) {
                Files.deleteIfExists(file.toPath());
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @Value("${ucc.fileupload.limit}")
    private long limitSize;

    @RequestMapping(value = "/api/check/filesize", method = RequestMethod.GET)
    public ResponseEntity CheckFileSize(@RequestParam("size") long fileSize) {
        if (fileSize > limitSize)
            throw new MaxUploadSizeExceededException(limitSize);
        else
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS), HttpStatus.OK);
    }
}
