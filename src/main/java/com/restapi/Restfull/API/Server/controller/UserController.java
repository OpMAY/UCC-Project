package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Auth;
import com.restapi.Restfull.API.Server.models.User;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.UserService;
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
public class UserController {
    @Autowired
    UserService userService;

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

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity Login(@ModelAttribute User user){
        String name = user.getName();
        String email = user.getEmail();
        String sns = user.getSns();
        try {
            Message message = new Message();
            User user1 = userService.loginUser(email, sns);
            if(user1 != null){ // 회원 정보 있으면 바로 로그인
                userMessageMake(message, user1);
            }else{ // 없으면 회원 정보 등록 후 로그인
                Date now = Time.LongTimeStampCurrent();
                User newUser = new User();
                newUser.setName(name);
                newUser.setEmail(email);
                newUser.setSns(sns);
                newUser.setReg_date(now);
                newUser.set_artist(false);
                //기본 이미지 링크 설정
                newUser.setProfile_img("basic Img");
                // 유저 별 fcm 토큰 설정
                newUser.setToken("");
                userService.registerUser(newUser);
                User user2 = userService.loginUser(email, sns);
                userMessageMake(message, user2);
            }
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOGIN_SUCCESS, message.getHashMap("Login()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }
    // TODO 로그인 및 회원가입 - 토큰 정보 기입 로직 작성 필요, 기본 프로필 이미지 경로 설정 필요 2021-06-21

    @RequestMapping(value = "/api/withdraw/{user_no}", method = RequestMethod.POST)
    public ResponseEntity WithdrawUser(@PathVariable("user_no") int user_no){
        if(userService.selectUserByUserNo(user_no) != null) {
            userService.deleteUser(user_no);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.WITHDRAW_SUCCESS), HttpStatus.OK);
        }else{
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    private void userMessageMake(Message message, User user1) {
        message.put("no", user1.getUser_no());
        message.put("name", user1.getName());
        message.put("email", user1.getEmail());
        message.put("sns", user1.getSns());
        message.put("reg_date", user1.getReg_date());
        message.put("is_artist", user1.is_artist());
        message.put("profile_img", user1.getProfile_img());
        message.put("token", user1.getToken());
    }
}
