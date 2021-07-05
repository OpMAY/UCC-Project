package com.restapi.Restfull.API.Server.controller;


import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Inquiry;
import com.restapi.Restfull.API.Server.models.Upload;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.InquiryService;
import com.restapi.Restfull.API.Server.utility.Format;
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

    @RequestMapping(value = "/api/inquiry/{user_no}/start_index/{start_index}", method = RequestMethod.GET) // CHECK
    public ResponseEntity GetInquiryList(@PathVariable("user_no") int user_no, @PathVariable("start_index") int start_index){
        return inquiryService.getInquiryList(user_no, start_index);
    }

    @RequestMapping(value = "/api/inquiry/upload", method = RequestMethod.POST) // CHECK
    public ResponseEntity UploadInquiry(@RequestPart("inquiry") Inquiry inquiry,
                                        @RequestPart(value = "files", required = false) MultipartFile[] files){

        try{
            Message message = new Message();
            ArrayList<Upload> uploads = new ArrayList<>();


            if(files.length > 0 && !files[0].isEmpty()) {
                Map<String, MultipartFile> multipartFileMap = new HashMap<>();
                for (int i = 0; i < files.length; i++) {
                    multipartFileMap.put("files-" + i, files[i]);
                }
                /** Multiple File Upload Logic*/
                for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                    MultipartFile multipartFile = entry.getValue();
                    if (multipartFile.isEmpty()) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadInquiry()")), HttpStatus.OK);
                    } else if (!Format.CheckFile(multipartFile.getOriginalFilename())) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("UploadInquiry()")), HttpStatus.OK);
                    } else {
                        /** File Upload Log Logic*/
                        log.info("originalName:" + multipartFile.getOriginalFilename());
                        log.info("size:" + multipartFile.getSize());
                        log.info("ContentType:" + multipartFile.getContentType());

                        /** File Upload Logic */
                        String file_name = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes());
                        uploads.add(new Upload(file_name, cdn_path + file_name));
                    }
                }
            }
            return inquiryService.uploadInquiry(inquiry, uploads);
        }catch (IOException | JSONException e){
            throw new BusinessException(e);
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
