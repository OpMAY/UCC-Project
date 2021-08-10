package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Admin;
import com.restapi.Restfull.API.Server.models.Auth;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.SecurityService;
import com.restapi.Restfull.API.Server.services.TestService;
import com.restapi.Restfull.API.Server.services.UserService;
import com.restapi.Restfull.API.Server.utility.FirebaseMessagingSnippets;
import com.restapi.Restfull.API.Server.utility.VideoUtility;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * API Test
 */

/**
 * http://localhost:8080/swagger-ui.html
 */
@Log4j2
@RestController
public class HomeController {
    @Autowired
    private TestService testService;
    //22
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseMessagingSnippets firebaseMessagingSnippets;

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

    @Setter
    @Getter
    @Data
    class Test {
        private String name;
    }

    @RequestMapping(value = "/api/encode", method = RequestMethod.POST)
    public ResponseEntity TestEncode(@RequestBody String body){
        try {
            Message message = new Message();
            Test test = new Gson().fromJson(body, Test.class);
            String testString = "뷁궭뙗뺷";
            log.info(testString);
            System.out.println(testString);
            String testString2 = "안녕하세요.";
            log.info(testString2);
            System.out.println(testString2);
            String file_name = test.getName();
            log.info("Original : " + file_name);
            String nfd_to_nfc = Normalizer.normalize(file_name, Normalizer.Form.NFC);
            log.info("NFC : " + nfd_to_nfc);
            String trimmed = file_name.trim();
            log.info("Trimmed : " + trimmed);
            String utf = new String(file_name.getBytes("euc-kr"), StandardCharsets.UTF_8);
            log.info("euc-kr to utf8 - 1 : " + utf);
            String utf_to_euc = new String(utf.getBytes(StandardCharsets.UTF_8), "euc-kr");
            log.info("euc-kr -> utf-8 -> euc-kr : " + utf_to_euc);
            String urlDecodeNormal = URLDecoder.decode(file_name);
            log.info("URL Decode normal : " + urlDecodeNormal);
            String urlDecodeUtf = URLDecoder.decode(file_name, "utf-8");
            log.info("URL Decode utf-8 : " + urlDecodeUtf);
            String utf16Decode = new String(file_name.getBytes(), StandardCharsets.UTF_16);
            log.info("UTF 16 Decode : " +  utf16Decode);
//            String LE_utf16 = byteToStr(convertToLittleEndian(Integer.parseInt(utf16Decode)));
//            log.info("Little Endian UTF 16 : "+ LE_utf16);
            String utf16DecodeEU = new String(file_name.getBytes("euc-kr"), StandardCharsets.UTF_16);
            log.info("EUC-kr To UTF 16 : " + utf16DecodeEU);
            String utf16DecodeUTF = new String(file_name.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_16);
            log.info("UTF8 to UTF 16 : " + utf16DecodeUTF);
            String url16 = URLDecoder.decode(file_name, "utf-16");
            log.info("URL DECoder utf 16 : " + url16);
            testService.encodeTest(urlDecodeNormal);
            Test test1 = new Test();
            test1.setName(file_name);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("TestEncode()")), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    private byte[] convertToLittleEndian(int value){
        return new byte[]{
                (byte) value,
                (byte) (value >> 8),
                (byte) (value >> 16),
                (byte) (value >> 24)
        };
    }
    private String byteToStr(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x ", b));
        }

        return builder.toString();
    }

    private int bytesToInt(byte[] bytes) {
        int result = (int) bytes[3] & 0xFF;
        result |= (int) bytes[2] << 8 & 0xFF00;
        result |= (int) bytes[1] << 16 & 0xFF0000;
        result |= (int) bytes[0] << 24;

        return result;
    }

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    class Product {
        private int reference;
        private String name;
        private String category;
    }

    @RequestMapping(value = "/api/product", method = RequestMethod.POST)
    public ResponseEntity Product(@ModelAttribute Product product) {
        log.info(product);
        // 123213212
        try {
            Message message = new Message();
            message.put("product", product);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("Product")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.TEST_FAILED), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/products", method = RequestMethod.POST)
    public ResponseEntity Products(@ModelAttribute Product product) {
        log.info(product);
        try {
            Message message = new Message();
            ArrayList<Product> products = new ArrayList<>();
            products.add(product);
            products.add(product);
            products.add(product);
            products.add(product);
            message.put("products", products);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("Products")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.TEST_FAILED), HttpStatus.OK);
        }
    }

    @Getter
    @Setter
    @Data
    @ToString
    class Comment {
        private int reference;
        private int board_reference;
        private String content;
        private int user_no;
        private String user_profile;
        private String user_name;
        private ArrayList<Comment> comments;
    }

    @RequestMapping(value = "/api/comments", method = RequestMethod.GET)
    public ResponseEntity Comments() {
        ArrayList<Comment> comments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setReference(i + 1);
            comment.setBoard_reference(i + 1);
            comment.setContent("content test in comment " + i);
            comment.setUser_no(i + 1);
            comment.setUser_profile("http://www.weart-page.com/resources/images/icon/test_logo.svg");
            comment.setUser_name("kim woosik");
            ArrayList<Comment> in_comments = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Comment in_comment = new Comment();
                in_comment.setReference(j + 1);
                in_comment.setBoard_reference(j + 1);
                in_comment.setContent("content test in comment in comment " + j);
                in_comment.setUser_no(j + 1);
                in_comment.setUser_profile("http://www.weart-page.com/resources/images/icon/test_logo.svg");
                in_comment.setUser_name("kim woosik");
                in_comments.add(in_comment);
            }
            comment.setComments(in_comments);
            comments.add(comment);
        }
        log.info(comments);
        try {
            Message message = new Message();
            message.put("comments", comments);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("Comments")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.TEST_FAILED), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/video/thumbnail", method = RequestMethod.GET)
    public ResponseEntity ExportThumbnail() {
        try {
            /**
             * H.264 main profile decoder;
             * H.264 baseline profile encoder;
             * VP8 decoder (I frames only);
             * VP8 encoder (I frames only);
             * MPEG 1/2 decoder ( I/P/B frames, interlace );
             * Apple ProRes decoder/encoder;
             * JPEG decoder;
             * PNG decoder/encoder.
             * DivX/Xvid
             */
            VideoUtility videoUtility = new VideoUtility();
            File file = new File("E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api/test_android.mp4");
            long s_time = System.currentTimeMillis();
            videoUtility.getThumbnail(file);
            long e_time = System.currentTimeMillis();
            log.info((e_time - s_time) + "(ms)");
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.UNAUTHORIZED, ResMessage.TEST_FAILED), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/home", method = RequestMethod.GET)
    public ResponseEntity Home() {
        try {
            CDNService cdnService = new CDNService();
            cdnService.finds();
            /*testService.sqlRollbackTest();*/
            Message message = new Message();
            message.put("key1", "value");
            message.put("key2", 1);
            message.put("key3", 0.11f);
            message.put("key4", 0.22d);
            message.put("key5", new Auth("func", "hi"));
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("Home()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/test_firebase", method = RequestMethod.GET)
    public ResponseEntity Firebase(){
        try {
            Message message = new Message();
            String token = "femn0IVxQ8-04NUrQKk7HP:APA91bFddaHInKMoJLkArgFv7bME-L64bq0mWo9pbVygX71L0l_WqI8txCzyFBO0nB3gpHQEBSvz_7003DDol9YVSY1LcJvzFyD4QoCiNHFN-Sn-nrAzOtqldtKypfZq0B0kXqh_Q-L8";
            //firebaseMessagingSnippets.push(token, "테스트 알림 입니다.", "테스트 알림 내용");
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("Firebase()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }
}
