package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.models.Upload;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.utility.Format;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * API Test
 */

/**
 * http://localhost:8080/swagger-ui.html
 */

@Log4j2
@CrossOrigin
@RestController
public class Uploader {

    @Value("${uploadPath}")
    private String upload_path;

    @Value("${cdnPath}")
    private String cdn_path;

    @PostMapping(value = "/api/upload/file", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity singleUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("string") String string) {
        log.info(string);
        try {
            if (multipartFile.isEmpty()) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY), HttpStatus.OK);
            } else if (!Format.CheckFileType(multipartFile.getOriginalFilename())) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
            } else {
                /** File Upload Log Logic*/
                log.info("originalName:" + multipartFile.getOriginalFilename());
                log.info("size:" + multipartFile.getSize());
                log.info("ContentType:" + multipartFile.getContentType());

                /** File Upload Logic */
                String file_name = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes());

                log.info(string);

                /** Response Json Logic*/
                Message message = new Message();
                message.put("upload", new Upload("file_name", cdn_path + file_name));

                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.FILE_UPLOAD_SUCCESS, message.getHashMap("Upload()")), HttpStatus.OK);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/api/upload/files", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity multipleUpload(@RequestParam("files") MultipartFile[] files) {
        try {
            log.info(files.length);
            /**Test Parsing Logic*/
            Map<String, MultipartFile> multipartFileMap = new HashMap<>();
            for (int i = 0; i < files.length; i++) {
                multipartFileMap.put("files-" + i, files[i]);
            }

            /** Multiple File Upload Logic*/
            ArrayList<Upload> uploads = new ArrayList<>();
            for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                MultipartFile multipartFile = entry.getValue();
                if (multipartFile.isEmpty()) {
                    Message message = new Message();
                    message.put("order", entry.getKey());
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("Upload()")), HttpStatus.OK);
                } else if (!Format.CheckFileType(multipartFile.getOriginalFilename())) {
                    Message message = new Message();
                    message.put("order", entry.getKey());
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED, message.getHashMap("Upload()")), HttpStatus.OK);
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

            /** Response Json Logic*/
            Message message = new Message();
            message.put("files", uploads);
            message.put("images", uploads);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.MULTIPLE_FILE_UPLOAD_SUCCESS, message.getHashMap("Upload()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*업로드된 파일을 저장하는 함수*/
    private String uploadFile(String originalName, byte[] fileDate) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        File target = new File(upload_path, savedName);
        //org.springframework.util 패키지의 FileCopyUtils는 파일 데이터를 파일로 처리하거나, 복사하는 등의 기능이 있다.
        FileCopyUtils.copy(fileDate, target);
        CDNService cdnService = new CDNService();
        cdnService.upload("api/test/" + savedName, target);
        return savedName;
    }
}
