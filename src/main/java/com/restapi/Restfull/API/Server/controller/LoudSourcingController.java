package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.BannerAdService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.LoudSourcingService;
import com.restapi.Restfull.API.Server.utility.FileConverter;
import com.restapi.Restfull.API.Server.utility.Format;
import com.restapi.Restfull.API.Server.utility.VideoUtility;
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

import javax.xml.soap.Detail;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private class DetailRequest{
        private int loudsourcing_no;
        private int user_no;
    }
    @RequestMapping(value = "/api/loudsourcing/{sort}/start_index/{start_index}", method = RequestMethod.GET) // CHECK
    public ResponseEntity GetLoudSourcingList(@PathVariable("sort") String sort, @PathVariable("start_index") int start_index){
        /**
         * 1. LoudSourcingList
         * **/
        return loudSourcingService.getLoudSourcingList(sort, start_index);
    }

    @RequestMapping(value = "/api/banner", method = RequestMethod.GET) // CHECK
    public ResponseEntity GetBanner(){
        return bannerAdService.getBanners();
    }

    @RequestMapping(value = "/api/loudsourcing/detail", method = RequestMethod.POST) // CHECK
    public ResponseEntity GetLoudSourcingDetail(@RequestBody String body){
        DetailRequest detailRequest = new Gson().fromJson(body, DetailRequest.class);
        return loudSourcingService.getLoudSourcingDetail(detailRequest.getUser_no(), detailRequest.getLoudsourcing_no());
    }

    @RequestMapping(value = "/api/loudsourcing/entry/upload", method = RequestMethod.POST) // CHECK
    public ResponseEntity UploadLoudSourcingEntry(@RequestPart("entry") LoudSourcingEntry loudSourcingEntry,
                                                  @RequestPart("vod") MultipartFile vod
                                                  ){
        try {
            Message message = new Message();
            Message filepath_msg = new Message();

            ArrayList<Upload> uploads = new ArrayList<>();

            if (vod.isEmpty()) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadLoudSourcingEntry()")), HttpStatus.OK);
            } else if (!Format.CheckVODFile(vod.getOriginalFilename())) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("UploadLoudSourcingEntry()")), HttpStatus.OK);
            } else {
                String path = "E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api";
                /** File Upload Log Logic*/
                log.info("originalName:" + vod.getOriginalFilename());
                log.info("size:" + vod.getSize());
                log.info("ContentType:" + vod.getContentType());

                /** VOD THUMBNAIL LOGIC **/
                VideoUtility videoUtility = new VideoUtility();
                FileConverter fileConverter = new FileConverter();
                File convFile = fileConverter.convert(vod);
                videoUtility.getThumbnail(convFile);
                File thumbnail = new File(path + "/" + convFile.getName() + "_thumb.png");

                /** File Upload Logic */
                String file_name = uploadFile(vod.getOriginalFilename(), vod.getBytes());
                byte[] thumbnail_byte = Files.readAllBytes(thumbnail.toPath());
                String thumbnail_name = uploadFile(thumbnail.getName(), thumbnail_byte);

                loudSourcingEntry.setFile(cdn_path + file_name);
                loudSourcingEntry.setThumbnail(cdn_path + thumbnail_name);
            }
            return loudSourcingService.uploadEntry(loudSourcingEntry);

        } catch (Exception e){
            throw new BusinessException(e);
        }
    }

    @RequestMapping(value = "/api/loudsourcing/entry/list/{sort}/start_index/{start_index}", method = RequestMethod.POST)
    public ResponseEntity GetEntryList(@ModelAttribute DetailRequest detailRequest, @PathVariable("sort") String sort, @PathVariable("start_index") int start_index){
        return loudSourcingService.getEntryList(detailRequest.getUser_no(), detailRequest.getLoudsourcing_no(), sort, start_index);
    }

    @RequestMapping(value = "/api/loudsourcing/apply", method = RequestMethod.POST) //CHECK
    public ResponseEntity ApplyLoudSourcing(@RequestBody String body){
        LoudSourcingApply loudSourcingApply = new Gson().fromJson(body, LoudSourcingApply.class);
        return loudSourcingService.applyLoudSourcing(loudSourcingApply);
    }

    @RequestMapping(value = "/api/loudsourcing/cancel", method = RequestMethod.POST) //CHECK
    public ResponseEntity CancelLoudSourcing(@RequestBody String body){
        LoudSourcingApply loudSourcingApply = new Gson().fromJson(body, LoudSourcingApply.class);
        return loudSourcingService.cancelLoudSourcing(loudSourcingApply);
    }

    @RequestMapping(value = "/api/loudsourcing/{sort}/start_index/{start_index}/search", method = RequestMethod.GET) // CHECK
    public ResponseEntity SearchLoudSourcing(@RequestParam("query") String query, @PathVariable("sort") String sort, @PathVariable("start_index") int start_index){
        return loudSourcingService.searchLoudSourcing(sort, query, start_index);
    }

    @Getter
    @Setter
    @Data
    class EntryRequest{
        private int user_no;
        private int entry_no;
    }

    @RequestMapping(value = "/api/loudsourcing/entry", method = RequestMethod.POST)
    public ResponseEntity GetEntry(@RequestBody String body){
        EntryRequest entryRequest = new Gson().fromJson(body, EntryRequest.class);
        return loudSourcingService.getEntry(entryRequest.getUser_no(), entryRequest.getEntry_no());
    }

    @RequestMapping(value = "/api/loudsourcing/entry/comments/{start_index}", method = RequestMethod.POST)
    public ResponseEntity GetEntryComment(@ModelAttribute EntryRequest entryRequest, @PathVariable("start_index") int start_index){
        return loudSourcingService.getEntryComment(entryRequest.getEntry_no(), start_index);
    }

    @RequestMapping(value = "/api/loudsourcing/entry/vote", method = RequestMethod.POST)
    public ResponseEntity VoteEntry(@RequestBody String body){
        EntryRequest entryRequest = new Gson().fromJson(body, EntryRequest.class);
        return loudSourcingService.voteEntry(entryRequest.getUser_no(), entryRequest.getEntry_no());
    }

    @RequestMapping(value = "/api/loudsourcing/entry/comment", method = RequestMethod.POST)
    public ResponseEntity InsertComment(@RequestBody String body){
        EntryComment entryComment = new Gson().fromJson(body, EntryComment.class);
        return loudSourcingService.insertComment(entryComment);
    }

    @Getter
    @Setter
    @Data
    class CommentDeleteRequest{
        private int entry_no;
        private int comment_no;
    }

    @RequestMapping(value = "/api/loudsourcing/entry/comment/delete", method = RequestMethod.POST)
    public ResponseEntity DeleteComment(@RequestBody String body){
        CommentDeleteRequest commentDeleteRequest = new Gson().fromJson(body, CommentDeleteRequest.class);
        return loudSourcingService.deleteComment(commentDeleteRequest.getEntry_no(), commentDeleteRequest.getComment_no());
    }

    @RequestMapping(value = "/api/loudsourcing/entry/delete/{entry_no}", method = RequestMethod.POST) // CHECK
    public ResponseEntity DeleteEntry(@PathVariable("entry_no") int entry_no){
        return loudSourcingService.deleteEntry(entry_no);
    }

    private String uploadFile(String originalName, byte[] fileDate) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        File target = new File(upload_path, savedName);
        //org.springframework.util 패키지의 FileCopyUtils는 파일 데이터를 파일로 처리하거나, 복사하는 등의 기능이 있다.
        FileCopyUtils.copy(fileDate, target);
        CDNService cdnService = new CDNService();
        if(Format.CheckIMGFile(originalName)) {
            cdnService.upload("api/images/" + savedName, target);
        }else if(Format.CheckFile(originalName)){
            cdnService.upload("api/files/" + savedName, target);
        }else if(Format.CheckVODFile(originalName)){
            cdnService.upload("api/videos/" + savedName, target);
        }else{
            throw new BusinessException(new Exception());
        }
        return savedName;
    }
}
