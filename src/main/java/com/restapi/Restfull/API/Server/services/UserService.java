package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistDao;
import com.restapi.Restfull.API.Server.daos.UserDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class UserService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;


    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity loginUser(User user) {
        try {
            File basic_profile_img = new File("E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api/profile_img_basic.png");
            Message message = new Message();
            Message file_message = new Message();
            userDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            /** 회원 없을 때 회원가입 **/
            if (userDao.loginUser(user) == null) {
                /** SET User **/
                user.set_artist(false);
                user.setProfile_img(basic_profile_img.getAbsolutePath());
                user.set_user_private(false);
                userDao.registerUser(user);
                message.put("User", user);
            }else { /** 회원 있으면 로그인 **/
                User login_user = userDao.loginUser(user);
                message.put("User", login_user);
                /** 아티스트의 경우 아티스트 정보 반환 **/
                int user_no = login_user.getUser_no();

                if(artistDao.getArtistByUserNo(user_no) != null){
                    Artist artist = artistDao.getArtistByUserNo(user_no);
                    message.put("Artist", artist);
                }
            }
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

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity checkUserPrivate(int user_no){
        try {
            Message message = new Message();
            userDao.setSession(sqlSession);
            boolean userCheck = userDao.selectUserByUserNo(user_no).is_user_private();
            log.info(userCheck);
            message.put("user_no", user_no);
            message.put("is_user_private", userCheck);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.CHECK_USER_PRIVATE_SUCCESS, message.getHashMap("CheckUserPrivate()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
