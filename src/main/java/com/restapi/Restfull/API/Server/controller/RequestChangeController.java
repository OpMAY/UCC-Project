package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.RequestChange;
import com.restapi.Restfull.API.Server.models.User;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.ArtistService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.RequestChangeService;
import com.restapi.Restfull.API.Server.services.UserService;
import com.restapi.Restfull.API.Server.utility.Format;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

@Log4j2
@RestController
public class RequestChangeController {
    @Autowired
    RequestChangeService requestChangeService;

    @Autowired
    UserService userService;

    @Autowired
    ArtistService artistService;

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

    @RequestMapping(value = "/api/change/{user_no}", method = RequestMethod.GET)
    public ResponseEntity ChangeArtist(@PathVariable("user_no") int user_no){
        try {
            Message message = new Message();
            User user = userService.selectUserByUserNo(user_no);
            message.put("user_no", user.getUser_no());
            message.put("name", user.getName());
            if(user.getEmail() != null)
                message.put("email", user.getEmail());
            else
                message.put("email", "");
            message.put("profile_img", user.getProfile_img());
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.USER_INFO_ACCESS, message.getHashMap("ChangeArtist()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/api/change/submit", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity ChangeArtistRequest(@RequestPart(value = "request_change") RequestChange requestChange,
                                              @RequestPart(value = "profile_img", required = false) MultipartFile profile_img_file,
                                              @RequestPart(value = "fan_main_img", required = false) MultipartFile fan_main_img_file){
        try {
            /** File Check Logic **/
            if(profile_img_file.isEmpty()){
                File basic_profile_img = new File("E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api/profile_img_basic.png");
                profile_img_file = (MultipartFile) basic_profile_img;
            }
            if(fan_main_img_file.isEmpty()){
                File basic_fan_main_img = new File("E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api/fan_main_img_basic.png");
                fan_main_img_file = (MultipartFile) basic_fan_main_img;
            }
            if(!Format.CheckFileType(profile_img_file.getOriginalFilename()) || !Format.CheckFileType(fan_main_img_file.getOriginalFilename())){
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
            } else if (requestChangeService.getRequestByUserNo(requestChange.getUser_no()) != null){
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.ALREADY_REGISTERED_ARTIST), HttpStatus.OK);
            } else if(requestChangeService.artistNameCheck(requestChange.getArtist_name())){
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.ARTIST_NAME_IN_USE), HttpStatus.OK);
            } else{
                /** File Upload Log Logic
                 *  Profile Img*/
                log.info("originalName:" + profile_img_file.getOriginalFilename());
                log.info("size:" + profile_img_file.getSize());
                log.info("ContentType:" + profile_img_file.getContentType());

                /** File Upload Log Logic
                 *  Fan Main Img*/
                log.info("originalName:" + fan_main_img_file.getOriginalFilename());
                log.info("size:" + fan_main_img_file.getSize());
                log.info("ContentType:" + fan_main_img_file.getContentType());

                /** File Upload Logic */
                String profile_img_file_name = uploadFile(profile_img_file.getOriginalFilename(), profile_img_file.getBytes());
                String fan_main_img_file_name = uploadFile(fan_main_img_file.getOriginalFilename(), fan_main_img_file.getBytes());

                /** Date NOW **/
                Date now = new Date();

                /** RequestChange Set **/
                requestChange.setArtist_profile_img(profile_img_file_name);
                requestChange.setFan_main_img(fan_main_img_file_name);
                requestChange.setReg_date(now);
                requestChange.setAgree(true);
                requestChange.setStatus(true);

                /** Artist Set **/
                Artist artist = new Artist();
                artist.setUser_no(requestChange.getUser_no());
                artist.setArtist_name(requestChange.getArtist_name());
                artist.setBank_name(requestChange.getBank_name());
                artist.setBank_account(requestChange.getBank_account());
                artist.setBank_owner(requestChange.getBank_owner());
                artist.setEmail(requestChange.getArtist_email());
                artist.setArtist_phone(requestChange.getArtist_phone());
                artist.setFan_main_img(requestChange.getFan_main_img());
                artist.setReg_date(now);
                artist.setFan_explain(requestChange.getFan_explain());
                artist.setArtist_private(false);
                artist.setHashtag(requestChange.getHashtag());
                artist.setArtist_profile_img(requestChange.getArtist_profile_img());

                /** DB SET **/
                requestChangeService.insertRequest(requestChange, artist);

                /** DB Check **/
                RequestChange rc = requestChangeService.getRequestByUserNo(requestChange.getUser_no());
                Artist rArtist = artistService.getArtistByUserNo(requestChange.getUser_no());

                /** Response JSON SETTING **/
                Message message = new Message();
                Message rc_message = new Message();
                Message artist_message = new Message();

                /** RequestChange Message **/
                /*rc_message.put("change_no", rc.getChange_no());
                rc_message.put("user_no", rc.getUser_no());
                rc_message.put("artist_name", rc.getArtist_name());
                rc_message.put("bank_name", rc.getBank_name());
                rc_message.put("bank_account", rc.getBank_account());
                rc_message.put("bank_owner", rc.getBank_owner());
                rc_message.put("artist_email", rc.getArtist_email());
                rc_message.put("artist_phone", rc.getArtist_phone());
                rc_message.put("fan_main_img", rc.getFan_main_img());
                rc_message.put("reg_date", rc.getReg_date());
                rc_message.put("fan_explain", rc.getFan_explain());
                rc_message.put("agree", rc.isAgree());
                rc_message.put("status", rc.isStatus());
                //rc_message.put("hashtag", rc.getHashtag());*/

                /** Artist Message **/
                /*artist_message.put("artist_no", rArtist.getArtist_no());
                artist_message.put("user_no", rArtist.getUser_no());
                artist_message.put("artist_name", rArtist.getArtist_name());
                artist_message.put("bank_name", rArtist.getBank_name());
                artist_message.put("bank_account", rArtist.getBank_account());
                artist_message.put("bank_owner", rArtist.getBank_owner());
                artist_message.put("email", rArtist.getEmail());
                artist_message.put("artist_phone", rArtist.getArtist_phone());
                artist_message.put("fan_main_img", rArtist.getFan_main_img());
                artist_message.put("reg_date", rArtist.getReg_date());
                artist_message.put("fan_visit_today", rArtist.getFan_visit_today());
                artist_message.put("fan_number", rArtist.getFan_number());
                artist_message.put("fan_explain", rArtist.getFan_explain());
                artist_message.put("artist_private", rArtist.isArtist_private());
                //artist_message.put("hashtag", rArtist.getHashtag());
                artist_message.put("artist_profile_img", rArtist.getArtist_profile_img());*/

                /** Json Merge **/
                message.put("RequestChange", rc);
                message.put("Artist", rArtist);

                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_REGISTER_SUCCESS, message.getHashMap("ChangeArtistRequest()")), HttpStatus.OK);
            }
        } catch (IOException | JSONException e) {
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
        cdnService.upload("api/" + savedName, target);
        return savedName;
    }
}
