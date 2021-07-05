package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.services.*;
import com.restapi.Restfull.API.Server.utility.FileConverter;
import com.restapi.Restfull.API.Server.utility.Format;
import com.restapi.Restfull.API.Server.utility.Time;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

@Log4j2
@RestController
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private ArtistService artistService;

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

    @Setter
    @Getter
    @Data
    class PortfolioRequest {
        private int user_no;
        private int portfolio_no;
    }

    @RequestMapping(value = "/api/portfolio", method = RequestMethod.POST) //CHECK
    public ResponseEntity GetPortfolio(@ModelAttribute PortfolioRequest portfolioRequest) {
        return portfolioService.GetPortfolio(portfolioRequest.getUser_no(), portfolioRequest.getPortfolio_no(), -1);
    }

    @RequestMapping(value = "/api/portfolio/comments/{start_index}", method = RequestMethod.POST) //CHECK
    public ResponseEntity GetPortfolioComment(@ModelAttribute PortfolioRequest portfolioRequest, @PathVariable("start_index") int start_index) {
        return portfolioService.GetPortfolio(portfolioRequest.getUser_no(), portfolioRequest.getPortfolio_no(), start_index);
    }

    @RequestMapping(value = "/api/portfolio/upload", method = RequestMethod.POST) //CHECK
    public ResponseEntity UploadPortfolio(@RequestPart(value = "portfolio") Portfolio portfolio,
                                          @RequestPart(value = "files", required = false) MultipartFile[] portfolio_files,
                                          @RequestPart(value = "vod", required = false) MultipartFile vod_file,
                                          @RequestPart(value = "images", required = false) MultipartFile[] img_files) throws Exception {
        try {
            Message message = new Message();
            Message filepath_msg = new Message();

            ArrayList<Upload> uploads = new ArrayList<>();

            switch (portfolio.getType()) {
                case PortfolioType.VOD: /** VOD PORTFOLIO **/
                    if (vod_file.isEmpty()) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                    } else if (!Format.CheckVODFile(vod_file.getOriginalFilename())) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                    } else {
                        String path = "E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api";
                        /** File Upload Log Logic*/
                        log.info("originalName:" + vod_file.getOriginalFilename());
                        log.info("size:" + vod_file.getSize());
                        log.info("ContentType:" + vod_file.getContentType());

                        /** VOD THUMBNAIL LOGIC **/
                        VideoUtility videoUtility = new VideoUtility();
                        FileConverter fileConverter = new FileConverter();
                        File convFile = fileConverter.convert(vod_file);
                        videoUtility.getThumbnail(convFile);
                        File thumbnail = new File(path + "/" + convFile.getName() + "_thumb.png");

                        /** File Upload Logic */
                        String file_name = uploadFile(vod_file.getOriginalFilename(), vod_file.getBytes());
                        byte[] thumbnail_byte = Files.readAllBytes(thumbnail.toPath());
                        String thumbnail_name = uploadFile(thumbnail.getName(), thumbnail_byte);
                        uploads.add(new Upload(file_name, cdn_path + file_name));
                        uploads.add(new Upload(thumbnail_name, cdn_path + thumbnail_name));
                        portfolio.setFile(cdn_path + file_name);
                        portfolio.setThumbnail(cdn_path + thumbnail_name);
                    }
                    break;
                case PortfolioType.IMAGE: { /** IMAGE PORTFOLIO **/
                    /**Test Parsing Logic*/
                    if(img_files.length <= 0)
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                    Map<String, MultipartFile> multipartFileMap = new HashMap<>();
                    for (int i = 0; i < img_files.length; i++) {
                        multipartFileMap.put("files-" + i, img_files[i]);
                    }
                    /** Multiple File Upload Logic*/
                    for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                        MultipartFile multipartFile = entry.getValue();
                        if (multipartFile.isEmpty()) {
                            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                        } else if (!Format.CheckIMGFile(multipartFile.getOriginalFilename())) {
                            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
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
                    break;
                }
                case PortfolioType.FILE: { /** FILE PORTFOLIO **/
                    /**Test Parsing Logic*/
                    if(portfolio_files.length <= 0)
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                    Map<String, MultipartFile> multipartFileMap = new HashMap<>();
                    for (int i = 0; i < portfolio_files.length; i++) {
                        multipartFileMap.put("files-" + i, portfolio_files[i]);
                    }
                    /** Multiple File Upload Logic*/
                    for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                        MultipartFile multipartFile = entry.getValue();
                        if (multipartFile.isEmpty()) {
                            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                        } else if (!Format.CheckFile(multipartFile.getOriginalFilename())) {
                            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
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
                    break;
                }
                case PortfolioType.TEXT: /** TEXT PORTFOLIO **/
                    break;
                default:
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.PORTFOLIO_TYPE_ERROR, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
            }
            // CHECK FILE SAVE COMPLETE
            if (uploads.isEmpty() && !portfolio.getType().equals(PortfolioType.TEXT)) {
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
            } else if(portfolio.getType().equals(PortfolioType.IMAGE)) {
                portfolio.setThumbnail(uploads.get(0).getUrl());
                List<String> uploadUrl = new ArrayList<String>();
                for(Upload upload : uploads){
                    String url = upload.getUrl();
                    uploadUrl.add(url);
                }
                portfolio.setFile(uploadUrl.toString());
                message.put("files", uploads);
            } else if(portfolio.getType().equals(PortfolioType.FILE)){
                List<String> file_msgList = new ArrayList<>();
                for(Upload upload : uploads){
                    Gson gson = new Gson();
                    FileJson fileJson = new FileJson();
                    String name = upload.getName();
                    String url = upload.getUrl();
                    fileJson.setName(name);
                    fileJson.setUrl(url);
                    String jsonString = gson.toJson(fileJson);
                    file_msgList.add(jsonString);
                }
                message.put("files", uploads);
                portfolio.setFile(file_msgList.toString());
            }
            Artist artist = artistService.getArtistByArtistNo(portfolio.getArtist_no());
            portfolio.setArtist_name(artist.getArtist_name());
            portfolio.setArtist_profile_img(artist.getArtist_profile_img());
            String d = Time.TimeFormatHMS();
            portfolio.setReg_date(d);
            portfolio.setRevise_date(d);
            portfolioService.insertPortfolio(portfolio);

            Portfolio portfolio1 = portfolioService.getPortfolioByPortfolioNo(portfolio.getPortfolio_no());
            portfolio1.setUser_no(artist.getUser_no());
            message.put("portfolio", portfolio1);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UPLOAD_PORTFOLIO_SUCCESS, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/portfolio/edit", method = RequestMethod.POST) //CHECK
    public ResponseEntity EditPortfolio(@ModelAttribute Portfolio portfolio) {
        return portfolioService.updatePortfolio(portfolio);
    }

    @RequestMapping(value = "/api/portfolio/delete/{portfolio_no}", method = RequestMethod.POST) //CHECK
    public ResponseEntity DeletePortfolio(@PathVariable("portfolio_no") int portfolio_no) {
        return portfolioService.deletePortfolio(portfolio_no);
    }

    @RequestMapping(value = "/api/portfolio/like", method = RequestMethod.POST) //CHECK
    public ResponseEntity PressPortfolioLike(@ModelAttribute PortfolioRequest portfolioRequest) {
        int user_no = portfolioRequest.getUser_no();
        int portfolio_no = portfolioRequest.getPortfolio_no();
        return portfolioService.updatePortfolioByLike(portfolio_no, user_no);
    }

    @RequestMapping(value = "/api/portfolio/comment", method = RequestMethod.POST) //CHECK
    public ResponseEntity InsertPortfolioComment(@ModelAttribute PortfolioComment portfolioComment) {
        return portfolioService.updatePortfolioByComment(portfolioComment, "UPDATE");
    }

    @Getter
    @Setter
    @Data
    class CommentDeleteRequest {
        private int comment_no;
        private int portfolio_no;
    }

    @RequestMapping(value = "/api/portfolio/comment/delete", method = RequestMethod.POST) //CHECK
    public ResponseEntity DeletePortfolioComment(@ModelAttribute CommentDeleteRequest commentDeleteRequest) {
        PortfolioComment portfolioComment = new PortfolioComment();
        portfolioComment.setComment_no(commentDeleteRequest.getComment_no());
        portfolioComment.setPortfolio_no(commentDeleteRequest.getPortfolio_no());
        return portfolioService.updatePortfolioByComment(portfolioComment, "DELETE");
    }

    @RequestMapping(value = "/api/portfolio/edit_source/{portfolio_no}", method = RequestMethod.GET) // CHECK
    public ResponseEntity GetPortfolioForEdit(@PathVariable("portfolio_no") int portfolio_no) {
        try {
            Message message = new Message();
            // Portfolio SET
            Portfolio portfolio = portfolioService.getPortfolioByPortfolioNo(portfolio_no);

            // Response Message SET
            message.put("portfolio", portfolio);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_PORTFOLIO_SUCCESS, message.getHashMap("GetPortfolioForEdit()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/portfolio/VOD_List/{sort}/{start_index}", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetVODList(@PathVariable("sort") String sort, @PathVariable("start_index") int start_index) {
        return portfolioService.getPortfolioListByTypeVOD(PortfolioType.VOD, sort, start_index);
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
