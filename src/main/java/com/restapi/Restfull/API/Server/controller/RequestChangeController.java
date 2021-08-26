package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.RequestChange;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.ArtistService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.RequestChangeService;
import com.restapi.Restfull.API.Server.services.UserService;
import com.restapi.Restfull.API.Server.utility.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @RequestMapping(value = "/api/change/{user_no}", method = RequestMethod.GET) // CHECK
    public ResponseEntity ChangeArtist(@PathVariable("user_no") int user_no) {
        /** 아티스트 전환 페이지에 필요한 자동입력 데이터 조회 **/
        return userService.selectUserByUserNo(user_no);
    }

    @RequestMapping(value = "/api/change/submit", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    // CHECK
    public ResponseEntity ChangeArtistRequest(@RequestParam(value = "request_change") String body,
                                              @RequestParam(value = "profile_img", required = false) MultipartFile profile_img_file,
                                              @RequestParam(value = "main_img", required = false) MultipartFile fan_main_img_file) {
        try {
            RequestChange requestChange = new Gson().fromJson(body, RequestChange.class);
            URLConverter urlConverter = new URLConverter();
            log.info(fan_main_img_file);
            /** File Check Logic **/
            if (profile_img_file == null || profile_img_file.isEmpty()) {
                log.info("profile img is empty or null");
                File basic_profile_img = new File("/www/weart-page_com/www/WEB-INF/classes/static/image/profile_img_basic.png");
                FileItem fileItem = new DiskFileItem("profile_img_basic", Files.probeContentType(basic_profile_img.toPath()), false, basic_profile_img.getName(), (int) basic_profile_img.length(), basic_profile_img.getParentFile());
                InputStream input = new FileInputStream(basic_profile_img);
                OutputStream os = fileItem.getOutputStream();
                IOUtils.copy(input, os);
                profile_img_file = new CommonsMultipartFile(fileItem);
            }
            if (fan_main_img_file == null || fan_main_img_file.isEmpty()) {
                log.info("fan main img is empty or null");
                File basic_fan_main_img = new File("/www/weart-page_com/www/WEB-INF/classes/static/image/fan_main_img_basic.png");
                FileItem fileItem = new DiskFileItem("fan_main_img_basic", Files.probeContentType(basic_fan_main_img.toPath()), false, basic_fan_main_img.getName(), (int) basic_fan_main_img.length(), basic_fan_main_img.getParentFile());
                InputStream input = new FileInputStream(basic_fan_main_img);
                OutputStream os = fileItem.getOutputStream();
                IOUtils.copy(input, os);
                fan_main_img_file = new CommonsMultipartFile(fileItem);
            }
            /** 파일 유형 체크 -> 어플단에서 처리하겠지만 데이터 누락 대비를 위해 **/
            if (!Format.CheckFileType(profile_img_file.getOriginalFilename()) || !Format.CheckFileType(fan_main_img_file.getOriginalFilename())) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
            } /** 이미 아티스트로 존재하는 유저가 아티스트를 신청했을 때 -> 어플단에서 처리하겠지만 데이터 누락 대비를 위해 **/
            else if (requestChangeService.getRequestByUserNo(requestChange.getUser_no()) != null) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.ALREADY_REGISTERED_ARTIST), HttpStatus.OK);
            } /** 아티스트 명 중복 체크 **/
            else if (requestChangeService.artistNameCheck(requestChange.getArtist_name())) {
                return new ResponseEntity(DefaultRes.res(StatusCode.ARTIST_NAME_ALREADY_IN_USE, ResMessage.ARTIST_NAME_IN_USE), HttpStatus.OK);
            } else {
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

                /** IOS EUC-KR Name Converter **/
                String profile_img_decoded_file_name = profile_img_file.getOriginalFilename();

                // CHECK UTF-8 ENCODING
                if(EncodeChecker.encodeCheck(profile_img_decoded_file_name)){
                    profile_img_decoded_file_name = URLDecoder.decode(profile_img_decoded_file_name, "UTF-8");
                    log.info("File Name URL Encoded - Decoded File : " + profile_img_decoded_file_name);
                }

                // CHECK NFD ENCODING - For IOS Korean
                if(!Normalizer.isNormalized(profile_img_decoded_file_name, Normalizer.Form.NFC)) {
                    profile_img_decoded_file_name = Normalizer.normalize(profile_img_decoded_file_name, Normalizer.Form.NFC);
                    log.info("(IOS Kor File) File is NFD Encoded - Decoded File : " + profile_img_decoded_file_name);
                }

                String fan_main_img_decoded_file_name = fan_main_img_file.getOriginalFilename();

                // CHECK UTF-8 ENCODING
                if(EncodeChecker.encodeCheck(fan_main_img_decoded_file_name)){
                    fan_main_img_decoded_file_name = URLDecoder.decode(fan_main_img_decoded_file_name, "UTF-8");
                    log.info("File Name URL Encoded - Decoded File : " + fan_main_img_decoded_file_name);
                }

                // CHECK NFD ENCODING - For IOS Korean
                if(!Normalizer.isNormalized(fan_main_img_decoded_file_name, Normalizer.Form.NFC)) {
                    fan_main_img_decoded_file_name = Normalizer.normalize(fan_main_img_decoded_file_name, Normalizer.Form.NFC);
                    log.info("(IOS Kor File) File is NFD Encoded - Decoded File : " + fan_main_img_decoded_file_name);
                }

                /** RequestChange Set **/
                requestChange.setAgree(true);
                requestChange.setStatus(true);

                /** DB SET **/
                requestChangeService.insertRequest(requestChange);

                /** DB Check **/
                Artist artist = artistService.getArtistByUserNo(requestChange.getUser_no());
                String artist_info = "user/" + requestChange.getUser_no() + "/artist/" + artist.getArtist_no() + "/";

                /** File Upload Logic */
                String profile_img_file_name = uploadFile(profile_img_decoded_file_name, profile_img_file, artist_info);
                String fan_main_img_file_name = uploadFile(fan_main_img_decoded_file_name, fan_main_img_file, artist_info);

                /** Update Artist IMAGES **/
                artist.setArtist_profile_img(urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + profile_img_file_name));
                artist.setMain_img(urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + fan_main_img_file_name));
                artistService.updateArtist(artist);

                /** Response JSON SETTING **/
                Message message = new Message();
                message.put("artist", artist);

                /** File JSON Setting **/
                Message file1_message = new Message();
                Message file2_message = new Message();
                List<Map<String, Object>> messages = new ArrayList<>();
                file1_message.put("name", fan_main_img_file_name);
                file1_message.put("url", urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + fan_main_img_file_name));
                messages.add(file1_message.getMap());
                file2_message.put("name", profile_img_file_name);
                file2_message.put("url", urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + profile_img_file_name));
                messages.add(file2_message.getMap());
                message.put("files", messages);


                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_REGISTER_SUCCESS, message.getHashMap("ChangeArtistRequest()")), HttpStatus.OK);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String uploadFile(String originalName, MultipartFile mfile, String artist_info) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        FileConverter fileConverter = new FileConverter();
        File file = fileConverter.convert(mfile, uid.toString().substring(0, 8) + "test" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase());
        ImageConverter imageConverter = new ImageConverter();
        File imgFile = imageConverter.convert64to32(file);
        CDNService cdnService = new CDNService();
        cdnService.upload("api/images/" + artist_info + savedName, imgFile);
        Files.deleteIfExists(file.toPath());
        Files.deleteIfExists(imgFile.toPath());
        return savedName;
    }

    /** ALL CHECKED **/
}
