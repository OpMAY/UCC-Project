package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.UserDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Auth;
import com.restapi.Restfull.API.Server.models.User;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Log4j2
@Service
public class UserService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityService securityService;


    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity loginUser(User user) {
        try {
            String name = "okiwi";
            String key = "AKIAJLBYKVWCC3IPIINQ";
            File basic_profile_img = new File("E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api/profile_img_basic.png");
            Message message = new Message();
            userDao.setSession(sqlSession);
            /** 회원 없을 때 회원가입 **/
            if (userDao.loginUser(user) == null) {
                /** SET TOKEN **/
                user.setToken(securityService.createToken(new Auth(name, key)));
                user.setReg_date(Time.LongTimeStampCurrent());
                user.set_artist(false);
                user.setProfile_img(basic_profile_img.getPath());
                userDao.registerUser(user);
            }
            /** GET LOGIN INFO **/
            User user1 = userDao.loginUser(user);

            /** TOKEN VALIDATION CHECK **/
            if (!securityService.validateToken(user1.getToken())) {
                user1.setToken(securityService.createToken(new Auth(name, key)));
            }
            message.put("User", user1);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.LOGIN_SUCCESS, message.getHashMap("Login()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity deleteUser(int user_no) {
        userDao.setSession(sqlSession);
        if (userDao.selectUserByUserNo(user_no) != null) {
            userDao.deleteUser(user_no);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.WITHDRAW_SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.NO_USER_DETECTED), HttpStatus.OK);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity selectUserByUserNo(int user_no) {
        try {
            Message message = new Message();
            userDao.setSession(sqlSession);
            User user = userDao.selectUserByUserNo(user_no);
            message.put("user_no", user.getUser_no());
            message.put("name", user.getName());
            if (user.getEmail() != null)
                message.put("email", user.getEmail());
            else
                message.put("email", "");
            message.put("profile_img", user.getProfile_img());
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.USER_INFO_ACCESS, message.getHashMap("ChangeArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
