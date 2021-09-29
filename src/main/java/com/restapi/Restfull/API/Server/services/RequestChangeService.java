package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.RequestChange;
import com.restapi.Restfull.API.Server.models.User;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.utility.*;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.Normalizer;
import java.util.*;

@Log4j2
@Service
public class RequestChangeService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private RequestChangeDao requestChangeDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BoardCommentDao boardCommentDao;

    @Autowired
    private PortfolioCommentDao portfolioCommentDao;

    @Autowired
    private EntryCommentDao entryCommentDao;

    @Value("${cdnPath}")
    private String cdn_path;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<RequestChange> getAllRequests() {
        requestChangeDao.setSession(sqlSession);
        return requestChangeDao.getAllRequests();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RequestChange getRequestByUserNo(int user_no) {
        requestChangeDao.setSession(sqlSession);
        return requestChangeDao.getRequestByUserNo(user_no);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity insertRequest(RequestChange rc, MultipartFile profile_img, MultipartFile main_img) {
        try {
            /** Set Session **/
            requestChangeDao.setSession(sqlSession);
            artistDao.setSession(sqlSession);
            userDao.setSession(sqlSession);
            boardCommentDao.setSession(sqlSession);
            portfolioCommentDao.setSession(sqlSession);
            entryCommentDao.setSession(sqlSession);
            URLConverter urlConverter = new URLConverter();
            Message message = new Message();
            Message file1_message = new Message();
            Message file2_message = new Message();
            List<Map<String, Object>> messages = new ArrayList<>();
            if (requestChangeDao.getRequestByUserNo(rc.getUser_no()) != null) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.ALREADY_REGISTERED_ARTIST), HttpStatus.OK);
            } else if (requestChangeDao.artistNameCheck(rc.getArtist_name())) {
                return new ResponseEntity(DefaultRes.res(StatusCode.ARTIST_NAME_ALREADY_IN_USE, ResMessage.ARTIST_NAME_IN_USE), HttpStatus.OK);
            } else {

                /** Artist Set **/
                Artist artist = new Artist();
                artist.setUser_no(rc.getUser_no());
                artist.setArtist_name(rc.getArtist_name());
                artist.setBank_name(rc.getBank_name());
                artist.setBank_account(rc.getBank_account());
                artist.setBank_owner(rc.getBank_owner());
                artist.setEmail(rc.getArtist_email());
                artist.setArtist_phone(rc.getArtist_phone());
                String d = Time.TimeFormatHMS();
                artist.setReg_date(d);
                artist.setRecent_act_date(d);
                artist.setExplain(rc.getExplain());
                artist.setArtist_private(false);
                artist.setHashtag(rc.getHashtag());
                artist.setLoudsourcing_push(true);
                artistDao.insertArtist(artist);

                String artist_info = "user/" + artist.getUser_no() + "/artist/" + artist.getArtist_no() + "/";

                /** Profile Img Set **/
                if (profile_img != null && !profile_img.isEmpty()) {
                    if (!Format.CheckFileType(profile_img.getOriginalFilename())) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                    } else {
                        log.info("originalName:" + profile_img.getOriginalFilename());
                        log.info("size:" + profile_img.getSize());
                        log.info("ContentType:" + profile_img.getContentType());

                        /** IOS EUC-KR Name Converter **/
                        String profile_img_decoded_file_name = profile_img.getOriginalFilename();

                        // CHECK UTF-8 ENCODING
                        if (EncodeChecker.encodeCheck(profile_img_decoded_file_name)) {
                            profile_img_decoded_file_name = URLDecoder.decode(profile_img_decoded_file_name, "UTF-8");
                            log.info("File Name URL Encoded - Decoded File : " + profile_img_decoded_file_name);
                        }

                        // CHECK NFD ENCODING - For IOS Korean
                        if (!Normalizer.isNormalized(profile_img_decoded_file_name, Normalizer.Form.NFC)) {
                            profile_img_decoded_file_name = Normalizer.normalize(profile_img_decoded_file_name, Normalizer.Form.NFC);
                            log.info("(IOS Kor File) File is NFD Encoded - Decoded File : " + profile_img_decoded_file_name);
                        }

                        String profile_img_file_name = uploadFile(profile_img_decoded_file_name, profile_img, artist_info, false);
                        String profile_blur_img_file_name = uploadFile(profile_img_decoded_file_name, profile_img, artist_info, true);
                        artist.setArtist_profile_img(urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + profile_img_file_name));
                        artist.setProfile_blur_img(urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + profile_blur_img_file_name));
                        file2_message.put("name", profile_img_file_name);
                        file2_message.put("url", urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + profile_img_file_name));
                        messages.add(file2_message.getMap());
                    }
                } else {
                    artist.setArtist_profile_img("default");
                    artist.setProfile_blur_img("default");
                }

                /** Main Img Set **/
                if(main_img != null && !main_img.isEmpty()){
                    if (!Format.CheckFileType(main_img.getOriginalFilename())) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                    } else {
                        log.info("originalName:" + main_img.getOriginalFilename());
                        log.info("size:" + main_img.getSize());
                        log.info("ContentType:" + main_img.getContentType());

                        String fan_main_img_decoded_file_name = main_img.getOriginalFilename();

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
                        String fan_main_img_file_name = uploadFile(fan_main_img_decoded_file_name, main_img, artist_info, false);
                        String main_blur_img_file_name = uploadFile(fan_main_img_decoded_file_name, main_img, artist_info, true);
                        artist.setMain_img(urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + fan_main_img_file_name));
                        artist.setMain_blur_img(urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + main_blur_img_file_name));
                        file1_message.put("name", fan_main_img_file_name);
                        file1_message.put("url", urlConverter.convertSpecialLetter(cdn_path + "images/" + artist_info + fan_main_img_file_name));
                        messages.add(file1_message.getMap());
                    }
                } else {
                    artist.setMain_img("default");
                    artist.setMain_blur_img("default");
                }
                /** Update Artist Images**/
                artistDao.updateArtist(artist);

                /** User Set**/
                User user = userDao.selectUserByUserNo(rc.getUser_no());
                user.set_artist(true);

                /** DB INSERT **/
                rc.setReg_date(d);
                requestChangeDao.insertRequest(rc);
                userDao.updateUser(user);
                boardCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());
                portfolioCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());
                entryCommentDao.updateAllCommentUserInfo(user.getUser_no(), artist.getArtist_name(), artist.getArtist_profile_img());

                if (artist.getHashtag() != null) {
                    ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
                    artist.setHashtag_list(hashtagList);
                    log.info(hashtagList);
                }

                /** Response JSON SETTING **/
                message.put("artist", artist);

                /** File JSON Setting **/
                if(!messages.isEmpty()) {
                    message.put("files", messages);
                }
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.ARTIST_REGISTER_SUCCESS, message.getHashMap("ChangeArtistRequest()")), HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private String uploadFile(String originalName, MultipartFile mFile, String artist_info, boolean isBlur) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName;
        FileConverter fileConverter = new FileConverter();
        File file = fileConverter.convert(mFile, uid.toString().substring(0, 8) + "test" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase());
        ImageConverter imageConverter = new ImageConverter();
        CDNService cdnService = new CDNService();
        if(isBlur){
            savedName = uid.toString().substring(0, 8) + "_blurred_" + originalName;
            File imgFile = imageConverter.blurImage(file);
            cdnService.upload("api/images/" + artist_info + savedName, imgFile);
            Files.deleteIfExists(imgFile.toPath());
        } else {
            savedName = uid.toString().substring(0, 8) + "_" + originalName;
            cdnService.upload("api/images/" + artist_info + savedName, file);
            Files.deleteIfExists(file.toPath());
        }
        return savedName;
    }
}
