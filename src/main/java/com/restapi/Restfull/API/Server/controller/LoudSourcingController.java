package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.EntryComment;
import com.restapi.Restfull.API.Server.models.LoudSourcingApply;
import com.restapi.Restfull.API.Server.models.LoudSourcingEntry;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.BannerAdService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.LoudSourcingService;
import com.restapi.Restfull.API.Server.utility.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.UUID;

@Log4j2
@RestController
public class LoudSourcingController {

    @Value("${uploadPath}")
    private String upload_path;

    @Value("${cdnPath}")
    private String cdn_path;

    @Autowired
    private LoudSourcingService loudSourcingService;

    @Autowired
    private BannerAdService bannerAdService;

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

    @Getter
    @Setter
    @Data
    private class DetailRequest {
        private int loudsourcing_no;
        private int user_no;
    }

    @RequestMapping(value = "/api/loudsourcing/{sort}/last_index/{last_index}", method = RequestMethod.GET) // CHECK
    public ResponseEntity GetLoudSourcingList(@PathVariable("sort") String sort, @PathVariable("last_index") int last_index) {
        /**
         * 1. LoudSourcingList
         * **/
        return loudSourcingService.getLoudSourcingList(sort, last_index);
    }

    @RequestMapping(value = "/api/banner", method = RequestMethod.GET) // CHECK
    public ResponseEntity GetBanner() {
        return bannerAdService.getBanners();
    }

    @RequestMapping(value = "/api/loudsourcing/detail", method = RequestMethod.POST) // CHECK
    public ResponseEntity GetLoudSourcingDetail(@RequestBody String body) {
        DetailRequest detailRequest = new Gson().fromJson(body, DetailRequest.class);
        return loudSourcingService.getLoudSourcingDetail(detailRequest.getUser_no(), detailRequest.getLoudsourcing_no());
    }

    @RequestMapping(value = "/api/loudsourcing/entry/upload", method = RequestMethod.POST) // CHECK
    public ResponseEntity UploadLoudSourcingEntry(@RequestParam("entry") String body,
                                                  @RequestParam("vod") MultipartFile vod,
                                                  @RequestParam("thumbnail") MultipartFile thumbnail
    ) {
        try {
            LoudSourcingEntry loudSourcingEntry = new Gson().fromJson(body, LoudSourcingEntry.class);
            Message message = new Message();

            String entry_info = loudSourcingEntry.getLoudsourcing_no() + "/" + loudSourcingEntry.getArtist_no() + "/";

            if (vod == null || vod.isEmpty() || thumbnail == null || thumbnail.isEmpty()) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadLoudSourcingEntry()")), HttpStatus.OK);
            } else if (!Format.CheckVODFile(vod.getOriginalFilename()) || !Format.CheckIMGFile(thumbnail.getOriginalFilename())) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("UploadLoudSourcingEntry()")), HttpStatus.OK);
            } else {
                /** File Upload Log Logic*/
                log.info("originalName:" + vod.getOriginalFilename());
                log.info("size:" + vod.getSize());
                log.info("ContentType:" + vod.getContentType());

                log.info("originalName:" + thumbnail.getOriginalFilename());
                log.info("size:" + thumbnail.getSize());
                log.info("ContentType:" + thumbnail.getContentType());

                String vod_decoded_file_name = vod.getOriginalFilename();
                // CHECK UTF-8 ENCODING
                if(EncodeChecker.encodeCheck(vod_decoded_file_name)){
                    vod_decoded_file_name = URLDecoder.decode(vod_decoded_file_name, "UTF-8");
                    log.info("VOD File Name URL ENCODED - Decoded File : " + vod_decoded_file_name);
                }

                // CHECK NFD ENCODING - For IOS Korean
                if(!Normalizer.isNormalized(vod_decoded_file_name, Normalizer.Form.NFC)) {
                    vod_decoded_file_name = Normalizer.normalize(vod_decoded_file_name, Normalizer.Form.NFC);
                    log.info("VOD File is NFD Encoded - Decoded File : " + vod_decoded_file_name);
                }

                String thumbnail_decoded_file_name = thumbnail.getOriginalFilename();
                // CHECK UTF-8 ENCODING
                if(EncodeChecker.encodeCheck(thumbnail_decoded_file_name)){
                    thumbnail_decoded_file_name = URLDecoder.decode(thumbnail_decoded_file_name, "UTF-8");
                    log.info("Thumbnail File Name URL ENCODED - Decoded File : " + thumbnail_decoded_file_name);
                }

                // CHECK NFD ENCODING - For IOS Korean
                if(!Normalizer.isNormalized(thumbnail_decoded_file_name, Normalizer.Form.NFC)) {
                    thumbnail_decoded_file_name = Normalizer.normalize(thumbnail_decoded_file_name, Normalizer.Form.NFC);
                    log.info("Thumbnail File is NFD Encoded - Decoded File : " + thumbnail_decoded_file_name);
                }

                /** VOD THUMBNAIL LOGIC **/
                VideoUtility videoUtility = new VideoUtility();
                UUID uid = UUID.randomUUID();
                String temp_fileName = uid.toString().substring(0, 8) + "test" + vod_decoded_file_name.substring(vod_decoded_file_name.lastIndexOf(".")).toLowerCase();
                FileConverter fileConverter = new FileConverter();
                File file = fileConverter.convert(vod, temp_fileName);
                String video_length = videoUtility.getDuration(file);

                URLConverter urlConverter = new URLConverter();

                /** File Upload Logic */
                String file_name = uploadFile(vod_decoded_file_name, vod, entry_info, temp_fileName);
                String thumbnail_name = uploadFile(thumbnail_decoded_file_name, thumbnail, entry_info, null);

                loudSourcingEntry.setFile(urlConverter.convertSpecialLetter(cdn_path + "videos/loudsourcing/" + entry_info + file_name));
                loudSourcingEntry.setThumbnail(urlConverter.convertSpecialLetter(cdn_path + "images/loudsourcing/" + entry_info + thumbnail_name));
                loudSourcingEntry.setVideo_length(video_length);
            }
            return loudSourcingService.uploadEntry(loudSourcingEntry);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @RequestMapping(value = "/api/loudsourcing/entry/list/{sort}/last_index/{last_index}", method = RequestMethod.POST)
    public ResponseEntity GetEntryList(@RequestBody String body, @PathVariable("sort") String sort, @PathVariable("last_index") int last_index) {
        DetailRequest detailRequest = new Gson().fromJson(body, DetailRequest.class);
        log.info(body);
        log.info(sort);
        log.info(last_index);
        return loudSourcingService.getEntryList(detailRequest.getUser_no(), detailRequest.getLoudsourcing_no(), sort, last_index);
    }

    @RequestMapping(value = "/api/loudsourcing/apply", method = RequestMethod.POST) //CHECK
    public ResponseEntity ApplyLoudSourcing(@RequestBody String body) {
        LoudSourcingApply loudSourcingApply = new Gson().fromJson(body, LoudSourcingApply.class);
        return loudSourcingService.applyLoudSourcing(loudSourcingApply);
    }

    @RequestMapping(value = "/api/loudsourcing/{sort}/last_index/{last_index}/search", method = RequestMethod.GET)
    // CHECK
    public ResponseEntity SearchLoudSourcing(@RequestParam("query") String query, @PathVariable("sort") String sort, @PathVariable("last_index") int last_index) {
        return loudSourcingService.searchLoudSourcing(sort, query, last_index);
    }

    @Getter
    @Setter
    @Data
    class EntryRequest {
        private int user_no;
        private int entry_no;
    }

    @RequestMapping(value = "/api/loudsourcing/entry", method = RequestMethod.POST)
    public ResponseEntity GetEntry(@RequestBody String body) {
        EntryRequest entryRequest = new Gson().fromJson(body, EntryRequest.class);
        return loudSourcingService.getEntry(entryRequest.getUser_no(), entryRequest.getEntry_no());
    }

    @RequestMapping(value = "/api/loudsourcing/entry/comments/{last_index}", method = RequestMethod.POST)
    public ResponseEntity GetEntryComment(@RequestBody String body, @PathVariable("last_index") int last_index) {
        EntryRequest entryRequest = new Gson().fromJson(body, EntryRequest.class);
        return loudSourcingService.getEntryComment(entryRequest.getEntry_no(), last_index);
    }

    @RequestMapping(value = "/api/loudsourcing/entry/vote", method = RequestMethod.POST)
    public ResponseEntity VoteEntry(@RequestBody String body) {
        EntryRequest entryRequest = new Gson().fromJson(body, EntryRequest.class);
        return loudSourcingService.voteEntry(entryRequest.getUser_no(), entryRequest.getEntry_no());
    }

    @RequestMapping(value = "/api/loudsourcing/entry/comment", method = RequestMethod.POST)
    public ResponseEntity InsertComment(@RequestBody String body) {
        EntryComment entryComment = new Gson().fromJson(body, EntryComment.class);
        return loudSourcingService.insertComment(entryComment);
    }

    @Getter
    @Setter
    @Data
    class CommentDeleteRequest {
        private int entry_no;
        private int comment_no;
    }

    @RequestMapping(value = "/api/loudsourcing/entry/comment/delete", method = RequestMethod.POST)
    public ResponseEntity DeleteComment(@RequestBody String body) {
        log.info(body);
        CommentDeleteRequest commentDeleteRequest = new Gson().fromJson(body, CommentDeleteRequest.class);
        return loudSourcingService.deleteComment(commentDeleteRequest.getEntry_no(), commentDeleteRequest.getComment_no());
    }

    @RequestMapping(value = "/api/loudsourcing/entry/delete/{entry_no}", method = RequestMethod.POST) // CHECK
    public ResponseEntity DeleteEntry(@PathVariable("entry_no") int entry_no) {
        return loudSourcingService.deleteEntry(entry_no);
    }


    private String uploadFile(String originalName, MultipartFile mfile, String entry_info, String vod_tmp) throws IOException {
        UUID uid = UUID.randomUUID();
        String mOriginalName = originalName;
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        CDNService cdnService = new CDNService();
        if (Format.CheckIMGFile(originalName)) {
            FileConverter fileConverter = new FileConverter();
            File file = fileConverter.convert(mfile, uid.toString().substring(0, 8) + "test" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase());
            cdnService.upload("api/images/loudsourcing/" + entry_info + savedName, file);
            Files.deleteIfExists(file.toPath());
        } else if (Format.CheckVODFile(originalName)) {
            File file = new File(upload_path, vod_tmp);
            cdnService.upload("api/videos/loudsourcing/" + entry_info + savedName, file);
            Files.deleteIfExists(file.toPath());
        } else {
            throw new BusinessException(new Exception());
        }
        return savedName;
    }
}
