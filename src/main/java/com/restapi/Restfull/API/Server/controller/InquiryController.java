package com.restapi.Restfull.API.Server.controller;


import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Inquiry;
import com.restapi.Restfull.API.Server.models.Upload;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.InquiryService;
import com.restapi.Restfull.API.Server.utility.FileConverter;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
@RestController
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

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

    @RequestMapping(value = "/api/inquiry/{user_no}/start_index/{last_index}", method = RequestMethod.GET) // CHECK
    public ResponseEntity GetInquiryList(@PathVariable("user_no") int user_no, @PathVariable("last_index") int last_index) {
        return inquiryService.getInquiryList(user_no, last_index);
    }

    @RequestMapping(value = "/api/inquiry/upload", method = RequestMethod.POST) // CHECK
    public ResponseEntity UploadInquiry(@RequestParam("inquiry") String body,
                                        @RequestParam(value = "files", required = false) MultipartFile[] files) {

        try {
            Inquiry inquiry = new Gson().fromJson(body, Inquiry.class);
            Message message = new Message();
            ArrayList<Upload> uploads = new ArrayList<>();
            String d = Time.TimeFormatHMS();
            String inquiry_info = inquiry.getUser_no() + "/" + d + "/";

            log.info(body);

            if(files != null) {
                if (files.length > 0 && !files[0].isEmpty()) {
                    Map<String, MultipartFile> multipartFileMap = new HashMap<>();
                    for (int i = 0; i < files.length; i++) {
                        multipartFileMap.put("files-" + i, files[i]);
                    }
                    /** Multiple File Upload Logic*/
                    for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                        MultipartFile multipartFile = entry.getValue();
                        if (multipartFile.isEmpty()) {
                            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadInquiry()")), HttpStatus.OK);
                        } else {
                            /** File Upload Log Logic*/
                            log.info("originalName:" + multipartFile.getOriginalFilename());
                            log.info("size:" + multipartFile.getSize());
                            log.info("ContentType:" + multipartFile.getContentType());

                            /** File Upload Logic */
                            String file_name = uploadFile(multipartFile.getOriginalFilename(), multipartFile, inquiry_info);
                            /**
                             * 1. Garbage Collector 시행
                             * 2. MultipartFile -> File 바꾸기
                             * **/
                            uploads.add(new Upload(file_name, cdn_path + "files/inquiry/" + inquiry_info + file_name));
                        }
                    }
                }
            }
            inquiry.setReg_date(d);
            return inquiryService.uploadInquiry(inquiry, uploads);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    private String uploadFile(String originalName, MultipartFile mfile, String inquiry_info) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        FileConverter fileConverter = new FileConverter();
        File file = fileConverter.convert(mfile);
        CDNService cdnService = new CDNService();
        cdnService.upload("api/files/inquiry/" + inquiry_info + savedName, file);
        Files.deleteIfExists(file.toPath());
        return savedName;
    }
}
