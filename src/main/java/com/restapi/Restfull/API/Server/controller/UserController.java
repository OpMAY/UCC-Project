package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.Upload;
import com.restapi.Restfull.API.Server.models.User;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.UserService;
import com.restapi.Restfull.API.Server.utility.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
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

    @RequestMapping(value = "/api/login", method = RequestMethod.POST) // CHECK
    public ResponseEntity Login(@RequestBody String body) {
        User user = new Gson().fromJson(body, User.class);
        /** 회원가입 + 로그인 **/
        return userService.loginUser(user);
    }

    @RequestMapping(value = "/api/withdraw/{user_no}", method = RequestMethod.POST) // CHECK
    public ResponseEntity WithdrawUser(@PathVariable("user_no") int user_no) {
        return userService.deleteUser(user_no);
    }


    @RequestMapping(value = "/api/user/valid/{user_no}", method = RequestMethod.GET) // CHECK
    public ResponseEntity CheckUserPrivate(@PathVariable("user_no") int user_no) {
        return userService.checkUserPrivate(user_no);
    }

    @RequestMapping(value = "/api/user/mypage/{user_no}", method = RequestMethod.GET) // CHECK
    public ResponseEntity GetUserInfo(@PathVariable("user_no") int user_no) {
        return userService.GetUserSpecificInfo(user_no);
    }

    @RequestMapping(value = "/api/user/mypage", method = RequestMethod.POST) //CHECK
    public ResponseEntity UpdateUserInfo(@RequestParam("user") String body,
                                         @RequestParam(value = "artist", required = false) String artistBody,
                                         @RequestParam(value = "profile_img", required = false) MultipartFile profile_img,
                                         @RequestParam(value = "main_img", required = false) MultipartFile fan_main_img) {
        try {
            User user = new Gson().fromJson(body, User.class);
            Artist artist = new Gson().fromJson(artistBody, Artist.class);
            ArrayList<Upload> uploads = new ArrayList<>();
            URLConverter urlConverter = new URLConverter();
            if(profile_img != null) {
                if (!profile_img.isEmpty()) {
                    if (!Format.CheckIMGFile(profile_img.getOriginalFilename())) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                    }
                    /** File Upload Log Logic*/
                    log.info("originalName:" + profile_img.getOriginalFilename());
                    log.info("size:" + profile_img.getSize());
                    log.info("ContentType:" + profile_img.getContentType());

                    /** IOS EUC-KR Name Converter **/
                    String profile_img_decoded_file_name = profile_img.getOriginalFilename();

                    // CHECK UTF-8 ENCODING
                    if(EncodeChecker.encodeCheck(profile_img_decoded_file_name)){
                        profile_img_decoded_file_name = URLDecoder.decode(profile_img_decoded_file_name, "UTF-8");
                    }

                    // CHECK NFD ENCODING - For IOS Korean
                    if(!Normalizer.isNormalized(profile_img_decoded_file_name, Normalizer.Form.NFC)) {
                        profile_img_decoded_file_name = Normalizer.normalize(profile_img_decoded_file_name, Normalizer.Form.NFC);
                    }

                    /** File Upload Logic */

                    if (artist != null) {
                        String file_name = uploadFile(profile_img_decoded_file_name, profile_img, "api/images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/", false);
                        String blur_file_name = uploadFile(profile_img_decoded_file_name, profile_img, "api/images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/", true);
                        artist.setArtist_profile_img(urlConverter.convertSpecialLetter(cdn_path + "images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/" + file_name));
                        artist.setProfile_blur_img(urlConverter.convertSpecialLetter(cdn_path + "images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/" + blur_file_name));
                        uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/" + file_name)));
                    } else {
                        String file_name = uploadFile(profile_img_decoded_file_name, profile_img, "api/images/user/" + user.getUser_no() + "/", false);
                        user.setProfile_img(urlConverter.convertSpecialLetter(cdn_path + "images/user/" + user.getUser_no() + "/" + file_name));
                        uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "images/user/" + user.getUser_no() + "/" + file_name)));
                    }
                }
            }
            if(fan_main_img != null) {
                if (!fan_main_img.isEmpty() && artist != null) {
                    if (!Format.CheckIMGFile(fan_main_img.getOriginalFilename())) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                    }
                    /** File Upload Log Logic*/
                    log.info("originalName:" + fan_main_img.getOriginalFilename());
                    log.info("size:" + fan_main_img.getSize());
                    log.info("ContentType:" + fan_main_img.getContentType());

                    /** IOS EUC-KR Name Converter **/
                    String main_img_decoded_file_name = fan_main_img.getOriginalFilename();

                    // CHECK UTF-8 ENCODING
                    if(EncodeChecker.encodeCheck(main_img_decoded_file_name)){
                        main_img_decoded_file_name = URLDecoder.decode(main_img_decoded_file_name, "UTF-8");
                    }

                    // CHECK NFD ENCODING - For IOS Korean
                    if(!Normalizer.isNormalized(main_img_decoded_file_name, Normalizer.Form.NFC)) {
                        main_img_decoded_file_name = Normalizer.normalize(main_img_decoded_file_name, Normalizer.Form.NFC);
                    }

                    /** File Upload Logic */
                    String file_name = uploadFile(main_img_decoded_file_name, fan_main_img, "api/images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/", false);
                    String blur_file_name = uploadFile(main_img_decoded_file_name, fan_main_img, "api/images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/", true);
                    artist.setMain_img(urlConverter.convertSpecialLetter(cdn_path + "images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/" + file_name));
                    artist.setMain_blur_img(urlConverter.convertSpecialLetter(cdn_path + "images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/" + blur_file_name));
                    uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "images/user/" + user.getUser_no() + "/artist/" + artist.getArtist_no() + "/" + file_name)));
                }
            }
            return userService.UpdateUserInfo(artist, user, uploads);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/user/set_push/all/{user_no}", method = RequestMethod.POST)
    public ResponseEntity UpdateUserAllPushSet(@PathVariable("user_no") int user_no){
        return userService.updateUserPush(user_no, "all");
    }

    @RequestMapping(value = "/api/user/set_push/comment/{user_no}", method = RequestMethod.POST)
    public ResponseEntity UpdateUserCommentPushSet(@PathVariable("user_no") int user_no){
        return userService.updateUserPush(user_no, "comment");
    }

    @RequestMapping(value = "/api/user/set_push/fankok/{user_no}", method = RequestMethod.POST)
    public ResponseEntity UpdateUserFankokPushSet(@PathVariable("user_no") int user_no){
        return userService.updateUserPush(user_no, "fankok");
    }

    @RequestMapping(value = "/api/user/update/fcm", method = RequestMethod.POST)
    public ResponseEntity UpdateUserFcmToken(@RequestBody String body){
        return userService.updateUserFcmToken(body);
    }

    private String uploadFile(String originalName, MultipartFile mfile, String file_path, boolean isBlur) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName;
        FileConverter fileConverter = new FileConverter();
        File file = fileConverter.convert(mfile, uid.toString().substring(0, 8) + "test" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase());
        ImageConverter imageConverter = new ImageConverter();
        CDNService cdnService = new CDNService();
        if(isBlur){
            savedName = uid.toString().substring(0, 8) + "_blurred_" + originalName;
            File imgFile = imageConverter.blurImage(file);
            cdnService.upload(file_path + savedName, imgFile);
            Files.deleteIfExists(imgFile.toPath());
        } else {
            savedName = uid.toString().substring(0, 8) + "_" + originalName;
            cdnService.upload(file_path + savedName, file);
        }
        Files.deleteIfExists(file.toPath());
        return savedName;
    }

}
