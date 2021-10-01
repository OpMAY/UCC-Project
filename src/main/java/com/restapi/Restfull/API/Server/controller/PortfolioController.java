package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.services.ArtistService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.PortfolioService;
import com.restapi.Restfull.API.Server.utility.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.Normalizer;
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
    public ResponseEntity GetPortfolio(@RequestBody String body) {
        PortfolioRequest portfolioRequest = new Gson().fromJson(body, PortfolioRequest.class);
        return portfolioService.GetPortfolio(portfolioRequest.getUser_no(), portfolioRequest.getPortfolio_no(), -1);
    }

    @RequestMapping(value = "/api/portfolio/comments/{last_index}", method = RequestMethod.POST) //CHECK
    public ResponseEntity GetPortfolioComment(@RequestBody String body, @PathVariable("last_index") int last_index) {
        PortfolioRequest portfolioRequest = new Gson().fromJson(body, PortfolioRequest.class);
        return portfolioService.GetPortfolio(portfolioRequest.getUser_no(), portfolioRequest.getPortfolio_no(), last_index);
    }

    @RequestMapping(value = "/api/portfolio/upload", method = RequestMethod.POST) //CHECK
    public ResponseEntity UploadPortfolio(@RequestParam(value = "portfolio") String body,
                                          @RequestParam(value = "files", required = false) MultipartFile[] portfolio_files,
                                          @RequestParam(value = "vod", required = false) MultipartFile vod_file,
                                          @RequestParam(value = "images", required = false) MultipartFile[] img_files,
                                          @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail) {
        try {
            /**
             * TODO 썸네일 앱에서 받아서 CDN에 올린 후 경로 DB에 저장
             * @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail
             * if(platform = android){
             *      savedName = savedName + "png";
             * }
             * **/
            Portfolio portfolio = new Gson().fromJson(body, Portfolio.class);
            Message message = new Message();
            ArrayList<Upload> uploads = new ArrayList<>();

            String d = Time.TimeFormatHMS();
            URLConverter urlConverter = new URLConverter();
            StringBuilder portfolio_info = new StringBuilder();
            portfolio_info.append(portfolio.getArtist_no());
            portfolio_info.append("/");

            switch (portfolio.getType()) {
                case PortfolioType.VOD: /** VOD PORTFOLIO **/
                    if (vod_file == null || vod_file.isEmpty() || thumbnail == null || thumbnail.isEmpty()) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                    } else if (!Format.CheckVODFile(vod_file.getOriginalFilename()) || !Format.CheckIMGFile(thumbnail.getOriginalFilename())) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                    } else {
                        /** File Upload Log Logic*/
                        log.info("originalName:" + vod_file.getOriginalFilename());
                        log.info("size:" + vod_file.getSize());
                        log.info("ContentType:" + vod_file.getContentType());

                        log.info("originalName:" + thumbnail.getOriginalFilename());
                        log.info("size:" + thumbnail.getSize());
                        log.info("ContentType:" + thumbnail.getContentType());

                        /** IOS EUC-KR Name Converter **/
                        String vod_decoded_file_name = vod_file.getOriginalFilename();

                        // CHECK UTF-8 ENCODING
                        if(EncodeChecker.encodeCheck(vod_decoded_file_name)){
                            vod_decoded_file_name = URLDecoder.decode(vod_decoded_file_name, "UTF-8");
                        }

                        // CHECK NFD ENCODING - For IOS Korean
                        if(!Normalizer.isNormalized(vod_decoded_file_name, Normalizer.Form.NFC)) {
                            vod_decoded_file_name = Normalizer.normalize(vod_decoded_file_name, Normalizer.Form.NFC);
                        }

                        String thumbnail_decoded_file_name = thumbnail.getOriginalFilename();

                        // CHECK UTF-8 ENCODING
                        if(EncodeChecker.encodeCheck(thumbnail_decoded_file_name)){
                            thumbnail_decoded_file_name = URLDecoder.decode(thumbnail_decoded_file_name, "UTF-8");
                        }

                        // CHECK NFD ENCODING - For IOS Korean
                        if(!Normalizer.isNormalized(thumbnail_decoded_file_name, Normalizer.Form.NFC)) {
                            thumbnail_decoded_file_name = Normalizer.normalize(thumbnail_decoded_file_name, Normalizer.Form.NFC);
                        }

                        /** VOD THUMBNAIL LOGIC - NOT WORKING IN LARGE FILES - JAVA HEAP SPACE**/
                        VideoUtility videoUtility = new VideoUtility();
                        UUID uid = UUID.randomUUID();
                        String temp_fileName = uid.toString().substring(0, 8) + "test" + vod_decoded_file_name.substring(vod_decoded_file_name.lastIndexOf(".")).toLowerCase();
                        FileConverter fileConverter = new FileConverter();
                        File file = fileConverter.convert(vod_file, temp_fileName);


                        /** VOD FILE DURATION LOGIC **/
                        String duration = videoUtility.getDuration(file);
                        log.info("Video Time : " + duration);

                        /** File Upload Logic */
                        String file_name = uploadFile(vod_decoded_file_name, vod_file, portfolio_info.toString(), temp_fileName);
                        String thumbnail_name = uploadFile(thumbnail_decoded_file_name, thumbnail, portfolio_info.toString(), null);
                        uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "videos/portfolio/" + portfolio_info.toString() + file_name)));
                        uploads.add(new Upload(thumbnail_name, urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + thumbnail_name)));
                        portfolio.setFile(urlConverter.convertSpecialLetter(cdn_path + "videos/portfolio/" + portfolio_info.toString() + file_name));
                        portfolio.setThumbnail(urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + thumbnail_name));
                        portfolio.setVideo_length(duration);
                    }
                    break;
                case PortfolioType.IMAGE: { /** IMAGE PORTFOLIO **/
                    /**Test Parsing Logic*/

                    if (portfolio.getFile().isEmpty()) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
                    } else {
                        if (img_files.length <= 0)
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

                                /** IOS EUC-KR Name Converter **/
                                String decoded_file_name = multipartFile.getOriginalFilename();

                                // CHECK UTF-8 ENCODING
                                if(EncodeChecker.encodeCheck(decoded_file_name)){
                                    decoded_file_name = URLDecoder.decode(decoded_file_name, "UTF-8");
                                }

                                // CHECK NFD ENCODING - For IOS Korean
                                if(!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                                    decoded_file_name = Normalizer.normalize(decoded_file_name, Normalizer.Form.NFC);
                                }

                                /** File Upload Logic */
                                String file_name = uploadFile(decoded_file_name, multipartFile, portfolio_info.toString(), null);
                                uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + file_name)));

                                portfolio.setFile(portfolio.getFile().replace(multipartFile.getOriginalFilename(), urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + file_name)));
                            }
                        }
                    }
                    break;
                }
                case PortfolioType.FILE: { /** FILE PORTFOLIO **/
                    /**Test Parsing Logic*/
                    if (portfolio_files.length <= 0)
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

                            /** IOS EUC-KR Name Converter **/
                            String decoded_file_name = multipartFile.getOriginalFilename();

                            // CHECK UTF-8 ENCODING
                            if(EncodeChecker.encodeCheck(decoded_file_name)){
                                decoded_file_name = URLDecoder.decode(decoded_file_name, "UTF-8");
                            }

                            // CHECK NFD ENCODING - For IOS Korean
                            if(!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                                decoded_file_name = Normalizer.normalize(decoded_file_name, Normalizer.Form.NFC);
                            }

                            /** File Upload Logic */
                            String file_name = uploadFile(decoded_file_name, multipartFile, portfolio_info.toString(), null);
                            if (Format.CheckIMGFile(multipartFile.getOriginalFilename())) {
                                uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + file_name)));
                            } else {
                                uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "files/portfolio/" + portfolio_info.toString() + file_name)));
                            }
                        }
                    }
                    break;
                }
                default:
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.PORTFOLIO_TYPE_ERROR, message.getHashMap("UploadPortfolio()")), HttpStatus.OK);
            }
            // CHECK FILE SAVE COMPLETE
            if (portfolio.getType().equals(PortfolioType.IMAGE)) {
                portfolio.setFile(portfolio.getFile().replace("[", "").replace("]", "").replace("\"", ""));
                ArrayList<String> urls = new ArrayList<>(Arrays.asList(portfolio.getFile().split(",")));
                ArrayList<String> final_url = new ArrayList<>(urls);
                portfolio.setThumbnail(final_url.get(0));
                portfolio.setFile(final_url.toString());
                message.put("files", uploads);
            } else if (portfolio.getType().equals(PortfolioType.FILE)) {
                List<String> file_msgList = new ArrayList<>();
                for (Upload upload : uploads) {
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
    public ResponseEntity EditPortfolio(@RequestParam(value = "portfolio") String body,
                                        @RequestParam(value = "files", required = false) MultipartFile[] portfolio_files,
                                        @RequestParam(value = "vod", required = false) MultipartFile vod_file,
                                        @RequestParam(value = "images", required = false) MultipartFile[] img_files,
                                        @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail) {
        try {
            Portfolio portfolio = new Gson().fromJson(body, Portfolio.class);
            Message message = new Message();
            ArrayList<Upload> uploads = new ArrayList<>();
            Portfolio original_portfolio = portfolioService.getPortfolioByPortfolioNo(portfolio.getPortfolio_no());
            StringBuilder portfolio_info = new StringBuilder();
            portfolio_info.append(original_portfolio.getArtist_no());
            portfolio_info.append("/");
            URLConverter urlConverter = new URLConverter();

            switch (portfolio.getType()) {
                case PortfolioType.VOD: /** VOD PORTFOLIO **/
                    if (portfolio.getFile().equals("") && portfolio.getThumbnail().equals("")) {
                        if (vod_file == null || vod_file.isEmpty() || thumbnail == null || thumbnail.isEmpty()) {
                            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
                        } else if (!Format.CheckVODFile(vod_file.getOriginalFilename()) || !Format.CheckIMGFile(thumbnail.getOriginalFilename())) {
                            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
                        } else {
                            /** File Upload Log Logic*/
                            log.info("originalName:" + vod_file.getOriginalFilename());
                            log.info("size:" + vod_file.getSize());
                            log.info("ContentType:" + vod_file.getContentType());

                            /** IOS EUC-KR Name Converter **/
                            String vod_decoded_file_name = vod_file.getOriginalFilename();

                            // CHECK UTF-8 ENCODING
                            if(EncodeChecker.encodeCheck(vod_decoded_file_name)){
                                vod_decoded_file_name = URLDecoder.decode(vod_decoded_file_name, "UTF-8");
                            }

                            // CHECK NFD ENCODING - For IOS Korean
                            if(!Normalizer.isNormalized(vod_decoded_file_name, Normalizer.Form.NFC)) {
                                vod_decoded_file_name = Normalizer.normalize(vod_decoded_file_name, Normalizer.Form.NFC);
                            }

                            String thumbnail_decoded_file_name = thumbnail.getOriginalFilename();

                            // CHECK UTF-8 ENCODING
                            if(EncodeChecker.encodeCheck(thumbnail_decoded_file_name)){
                                thumbnail_decoded_file_name = URLDecoder.decode(thumbnail_decoded_file_name, "UTF-8");
                            }

                            // CHECK NFD ENCODING - For IOS Korean
                            if(!Normalizer.isNormalized(thumbnail_decoded_file_name, Normalizer.Form.NFC)) {
                                thumbnail_decoded_file_name = Normalizer.normalize(thumbnail_decoded_file_name, Normalizer.Form.NFC);
                            }

                            /** VOD THUMBNAIL LOGIC - NOT WORKING IN LARGE FILES - JAVA HEAP SPACE**/
                            VideoUtility videoUtility = new VideoUtility();
                            UUID uid = UUID.randomUUID();
                            String temp_fileName = uid.toString().substring(0, 8) + "test" + vod_decoded_file_name.substring(vod_decoded_file_name.lastIndexOf(".")).toLowerCase();
                            FileConverter fileConverter = new FileConverter();
                            File file = fileConverter.convert(vod_file, temp_fileName);

                            /** VOD FILE DURATION LOGIC **/
                            String duration = videoUtility.getDuration(file);
                            log.info("Video Time : " + duration);

                            /** File Upload Logic */
                            String file_name = uploadFile(vod_decoded_file_name, vod_file, portfolio_info.toString(), temp_fileName);
                            String thumbnail_name = uploadFile(thumbnail_decoded_file_name, thumbnail, portfolio_info.toString(), null);
                            uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "videos/portfolio/" + portfolio_info.toString() + file_name)));
                            uploads.add(new Upload(thumbnail_name, urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + thumbnail_name)));
                            portfolio.setFile(urlConverter.convertSpecialLetter(cdn_path + "videos/portfolio/" + portfolio_info.toString() + file_name));
                            portfolio.setThumbnail(urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + thumbnail_name));
                            portfolio.setVideo_length(duration);

                            message.put("files", uploads);
                        }
                    }
                    break;
                case PortfolioType.IMAGE: { /** IMAGE PORTFOLIO **/
                    if (!portfolio.getFile().equals(original_portfolio.getFile())) {
                        /**Test Parsing Logic*/
                        if (img_files == null || img_files.length <= 0) {
                            break;
                        } else {
                            Map<String, MultipartFile> multipartFileMap = new HashMap<>();
                            for (int i = 0; i < img_files.length; i++) {
                                multipartFileMap.put("files-" + i, img_files[i]);
                            }
                            /** Multiple File Upload Logic*/
                            for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                                MultipartFile multipartFile = entry.getValue();
                                if (multipartFile.isEmpty()) {
                                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
                                } else if (!Format.CheckIMGFile(multipartFile.getOriginalFilename())) {
                                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
                                } else {
                                    /** File Upload Log Logic*/
                                    log.info("originalName:" + multipartFile.getOriginalFilename());
                                    log.info("size:" + multipartFile.getSize());
                                    log.info("ContentType:" + multipartFile.getContentType());

                                    /** IOS EUC-KR Name Converter **/
                                    String decoded_file_name = multipartFile.getOriginalFilename();

                                    // CHECK UTF-8 ENCODING
                                    if(EncodeChecker.encodeCheck(decoded_file_name)){
                                        decoded_file_name = URLDecoder.decode(decoded_file_name, "UTF-8");
                                    }

                                    // CHECK NFD ENCODING - For IOS Korean
                                    if(!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                                        decoded_file_name = Normalizer.normalize(decoded_file_name, Normalizer.Form.NFC);
                                    }

                                    /** File Upload Logic */
                                    String file_name = uploadFile(decoded_file_name, multipartFile, portfolio_info.toString(), null);
                                    uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + file_name)));

                                    portfolio.setFile(portfolio.getFile().replace(multipartFile.getOriginalFilename(), urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + file_name)));
                                }
                            }
                        }
                    }
                }
                break;
                case PortfolioType.FILE: { /** FILE PORTFOLIO **/
                    /**Test Parsing Logic*/
                    if (!(portfolio_files == null || portfolio_files.length <= 0)) {
                        Map<String, MultipartFile> multipartFileMap = new HashMap<>();
                        for (int i = 0; i < portfolio_files.length; i++) {
                            multipartFileMap.put("files-" + i, portfolio_files[i]);
                        }
                        /** Multiple File Upload Logic*/
                        for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                            MultipartFile multipartFile = entry.getValue();
                            if (multipartFile.isEmpty()) {
                                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_IS_EMPTY, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
                            } else if (!Format.CheckFile(multipartFile.getOriginalFilename())) {
                                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_MISMATCH, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
                            } else {
                                /** File Upload Log Logic*/
                                log.info("originalName:" + multipartFile.getOriginalFilename());
                                log.info("size:" + multipartFile.getSize());
                                log.info("ContentType:" + multipartFile.getContentType());

                                /** IOS EUC-KR Name Converter **/
                                String decoded_file_name = multipartFile.getOriginalFilename();

                                // CHECK UTF-8 ENCODING
                                if(EncodeChecker.encodeCheck(decoded_file_name)){
                                    decoded_file_name = URLDecoder.decode(decoded_file_name, "UTF-8");
                                }

                                // CHECK NFD ENCODING - For IOS Korean
                                if(!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                                    decoded_file_name = Normalizer.normalize(decoded_file_name, Normalizer.Form.NFC);
                                }

                                /** File Upload Logic */
                                String file_name = uploadFile(decoded_file_name, multipartFile, portfolio_info.toString(), null);
                                if (Format.CheckIMGFile(multipartFile.getOriginalFilename())) {
                                    uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "images/portfolio/" + portfolio_info.toString() + file_name)));
                                } else {
                                    uploads.add(new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "files/portfolio/" + portfolio_info.toString() + file_name)));
                                }
                            }
                        }
                    }
                    break;
                }
                default:
                    log.info("type Error");
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.PORTFOLIO_TYPE_ERROR, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
            }
            // CHECK FILE SAVE COMPLETE
            if (portfolio.getType().equals(PortfolioType.IMAGE)) {
                portfolio.setFile(portfolio.getFile().replace("[", "").replace("]", "").replace("\"", ""));
                ArrayList<String> urls = new ArrayList<>(Arrays.asList(portfolio.getFile().split(",")));
                ArrayList<String> final_url = new ArrayList<>();
                for (String string : urls) {
                    if (!string.contains("vodappserver")) {
                        log.info("String change Failed");
                        return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.PORTFOLIO_TYPE_ERROR, message.getHashMap("EditPortfolio()")), HttpStatus.OK);
                    } else {
                        final_url.add(string);
                    }
                }
                portfolio.setThumbnail(final_url.get(0));
                portfolio.setFile(final_url.toString());
                message.put("files", uploads);
            } else if (portfolio.getType().equals(PortfolioType.FILE)) {
                List<String> file_msgList = new ArrayList<>();
                FileJson[] fileJsons = new Gson().fromJson(portfolio.getFile(), FileJson[].class);
                if (fileJsons != null) {
                    for (FileJson fileJson : fileJsons) {
                        String jsonString = new Gson().toJson(fileJson);
                        file_msgList.add(jsonString);
                    }
                }
                for (Upload upload : uploads) {
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
            return portfolioService.updatePortfolio(portfolio);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @RequestMapping(value = "/api/portfolio/delete/{portfolio_no}", method = RequestMethod.POST) //CHECK
    public ResponseEntity DeletePortfolio(@PathVariable("portfolio_no") int portfolio_no) {
        return portfolioService.deletePortfolio(portfolio_no);
    }

    @RequestMapping(value = "/api/portfolio/like", method = RequestMethod.POST) //CHECK
    public ResponseEntity PressPortfolioLike(@RequestBody String body) {
        PortfolioRequest portfolioRequest = new Gson().fromJson(body, PortfolioRequest.class);
        int user_no = portfolioRequest.getUser_no();
        int portfolio_no = portfolioRequest.getPortfolio_no();
        return portfolioService.updatePortfolioByLike(portfolio_no, user_no);
    }

    @RequestMapping(value = "/api/portfolio/comment", method = RequestMethod.POST) //CHECK
    public ResponseEntity InsertPortfolioComment(@RequestBody String body) {
        PortfolioComment portfolioComment = new Gson().fromJson(body, PortfolioComment.class);
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
    public ResponseEntity DeletePortfolioComment(@RequestBody String body) {
        CommentDeleteRequest commentDeleteRequest = new Gson().fromJson(body, CommentDeleteRequest.class);
        PortfolioComment portfolioComment = new PortfolioComment();
        portfolioComment.setComment_no(commentDeleteRequest.getComment_no());
        portfolioComment.setPortfolio_no(commentDeleteRequest.getPortfolio_no());
        return portfolioService.updatePortfolioByComment(portfolioComment, "DELETE");
    }

    @Getter
    @Setter
    @Data
    class PortfolioEditRequest {
        private int portfolio_no;
        private int artist_no;
    }

    @RequestMapping(value = "/api/portfolio/edit_source", method = RequestMethod.POST) // CHECK
    public ResponseEntity GetPortfolioForEdit(@RequestBody String body) {
        PortfolioEditRequest portfolioEditRequest = new Gson().fromJson(body, PortfolioEditRequest.class);
        return portfolioService.getPortfolioForEdit(portfolioEditRequest.getPortfolio_no(), portfolioEditRequest.getArtist_no());
    }

    @RequestMapping(value = "/api/portfolio/vod_list/{sort}/{last_index}", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetVODList(@PathVariable("sort") String sort, @PathVariable("last_index") int last_index) {
        return portfolioService.getPortfolioListByTypeVOD(PortfolioType.VOD, sort, last_index);
    }

    @RequestMapping(value = "/api/artist/portfolioList/{sort}/{last_index}", method = RequestMethod.POST)
    public ResponseEntity GetPortfolioList(@RequestBody String body, @PathVariable("sort") String sort, @PathVariable("last_index") int last_index) {
        PortfolioEditRequest portfolioRequest = new Gson().fromJson(body, PortfolioEditRequest.class);
        return portfolioService.getPortfolioList(portfolioRequest.getArtist_no(), sort, last_index);
    }


    private String uploadFile(String originalName, MultipartFile mfile, String portfolio_info, String vod_tmp) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        CDNService cdnService = new CDNService();
        String temp_fileName = uid.toString().substring(0, 8) + "test" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase();
        if (Format.CheckIMGFile(originalName)) {
            FileConverter fileConverter = new FileConverter();
            File file = fileConverter.convert(mfile, temp_fileName);
            cdnService.upload("api/images/portfolio/" + portfolio_info + savedName, file);
            Files.deleteIfExists(file.toPath());
        } else if (Format.CheckFile(originalName)) {
            FileConverter fileConverter = new FileConverter();
            File file = fileConverter.convert(mfile, temp_fileName);
            cdnService.upload("api/files/portfolio/" + portfolio_info + savedName, file);
            Files.deleteIfExists(file.toPath());
        } else if (Format.CheckVODFile(originalName)) {
            File file = new File(upload_path, vod_tmp);
            cdnService.upload("api/videos/portfolio/" + portfolio_info + savedName, file);
            Files.deleteIfExists(file.toPath());
        } else {
            throw new BusinessException(new Exception("File Format is not valid"));
        }

        return savedName;
    }
}
