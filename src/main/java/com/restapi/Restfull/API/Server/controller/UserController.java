package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.Auth;
import com.restapi.Restfull.API.Server.models.Upload;
import com.restapi.Restfull.API.Server.models.User;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.UserService;
import com.restapi.Restfull.API.Server.utility.Format;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Log4j2
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Value("${uploadPath}")
    private String upload_path;

    @Value("${cdnPath}")
    private String cdn_path;

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
    public ResponseEntity Login(@ModelAttribute User user) {
        /** 회원가입 + 로그인 **/
        return userService.loginUser(user);
    }

    @RequestMapping(value = "/api/withdraw/{user_no}", method = RequestMethod.POST)
    public ResponseEntity WithdrawUser(@PathVariable("user_no") int user_no) {
        return userService.deleteUser(user_no);
    }


    @RequestMapping(value = "/api/user/valid/{user_no}", method = RequestMethod.GET)
    public ResponseEntity CheckUserPrivate(@PathVariable("user_no") int user_no){
        log.info(user_no);
        return userService.checkUserPrivate(user_no);
    }

    @RequestMapping(value = "/api/user/mypage/{user_no}", method = RequestMethod.GET)
    public ResponseEntity GetUserInfo(@PathVariable("user_no") int user_no){
        return userService.GetUserSpecificInfo(user_no);
    }

    @RequestMapping(value = "/api/user/mypage", method = RequestMethod.POST)
    public ResponseEntity UpdateUserInfo(@RequestPart("User") User user,
                                         @RequestPart(value = "Artist", required = false) Artist artist,
                                         @RequestPart(value = "Profile_img", required = false)MultipartFile profile_img,
                                         @RequestPart(value = "Fan_main_img", required = false) MultipartFile fan_main_img){
        log.info("test");
        log.info(artist);
        try{
            ArrayList<Upload> uploads = new ArrayList<>();
            if(!profile_img.isEmpty()){
                if(!Format.CheckIMGFile(profile_img.getOriginalFilename())){
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                }
                /** File Upload Log Logic*/
                log.info("originalName:" + profile_img.getOriginalFilename());
                log.info("size:" + profile_img.getSize());
                log.info("ContentType:" + profile_img.getContentType());

                /** File Upload Logic */
                String file_name = uploadFile(profile_img.getOriginalFilename(), profile_img.getBytes());

                if(artist != null)
                    artist.setArtist_profile_img(cdn_path + file_name);
                else{
                    user.setProfile_img(cdn_path + file_name);
                }
                uploads.add(new Upload(file_name, cdn_path + file_name));

            }
            if(!fan_main_img.isEmpty() && artist != null){
                if(!Format.CheckIMGFile(fan_main_img.getOriginalFilename())){
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                }
                /** File Upload Log Logic*/
                log.info("originalName:" + fan_main_img.getOriginalFilename());
                log.info("size:" + fan_main_img.getSize());
                log.info("ContentType:" + fan_main_img.getContentType());

                /** File Upload Logic */
                String file_name = uploadFile(fan_main_img.getOriginalFilename(), fan_main_img.getBytes());

                artist.setFan_main_img(cdn_path + file_name);
                uploads.add(new Upload(file_name, cdn_path + file_name));
            }


            return userService.UpdateUserInfo(artist, user, uploads);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String uploadFile(String originalName, byte[] fileDate) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        File target = new File(upload_path, savedName);
        //org.springframework.util 패키지의 FileCopyUtils는 파일 데이터를 파일로 처리하거나, 복사하는 등의 기능이 있다.
        FileCopyUtils.copy(fileDate, target);
        CDNService cdnService = new CDNService();
        //cdnService.upload("api/" + savedName, target);
        return savedName;
    }

}
