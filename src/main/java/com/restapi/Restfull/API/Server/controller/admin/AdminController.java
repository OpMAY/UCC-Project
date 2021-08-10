package com.restapi.Restfull.API.Server.controller.admin;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.interfaces.controller.ControllerInitialize;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.services.AdminService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.utility.FileConverter;
import com.restapi.Restfull.API.Server.utility.URLConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.*;

@Log4j2
@Controller
public class AdminController implements ControllerInitialize {
    private ModelAndView modelAndView;

    @Autowired
    private AdminService adminService;

    @Value("${uploadPath}")
    private String upload_path;

    @Value("${cdnPath}")
    private String cdn_path;

    @Override
    public void init(String method) {
        log.info(method);
    }

    @RequestMapping(value = "/admin/login.do", method = RequestMethod.GET)
    public ModelAndView Login() {
        modelAndView = new ModelAndView("/pages/auth/login");
        init("GET Login");
        return modelAndView;
    }

    @Data
    @Getter
    @Setter
    class AdminLogin {
        private String id;
        private String password;
    }

    @PostMapping("/admin/login")
    @ResponseBody
    public int adminLogin(@RequestBody String body,
                          HttpSession session) {
        AdminLogin admin = new Gson().fromJson(body, AdminLogin.class);
        String id = admin.getId();
        String password = admin.getPassword();
        log.info("id: " + id);
        log.info("password: " + password);

        if (session.getAttribute("adminLogin") != null)
            session.removeAttribute("adminLogin");

        if (adminService.loginAdmin(id, password) != null) {
            session.setAttribute("adminLogin", adminService.loginAdmin(id, password));
            init("POST Login");
            return 1;
        } else {
            return 0;
        }
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        init("GET Logout");
        session.removeAttribute("adminLogin");
        return "redirect:/admin/login.do";
    }

    @GetMapping("/admin/users.do")
    public ModelAndView UserPage(HttpSession session) {
        init("GET User");
        return adminService.getUser();
    }

    @GetMapping("/admin/artists.do")
    public ModelAndView ArtistPage(HttpSession session) {
        init("GET Artist");
        return adminService.getArtist();
    }

    @GetMapping("/admin/comments.do")
    public ModelAndView CommentPage(HttpSession session, @RequestParam("user_no") String user_no) {
        init("GET Comments");
        return adminService.getComments(user_no);
    }

    @GetMapping("/admin/user_detail.do")
    public ModelAndView UserDetailPage(HttpSession session, @RequestParam("user_no") String user_no) {
        init("GET UserDetail");
        return adminService.getUserDetail(user_no);
    }

    @PostMapping("/admin/comment/delete.do")
    @ResponseBody
    public int DeleteComment(@RequestBody String body) {
        init("GET DeleteComment");
        CommentHandleRequest commentHandleRequest = new Gson().fromJson(body, CommentHandleRequest.class);
        return adminService.deleteComment(commentHandleRequest.getComment_no(), commentHandleRequest.getType());
    }

    @PostMapping("/admin/comment/private.do")
    @ResponseBody
    public int PrivateComment(@RequestBody String body) {
        init("GET PrivateComment");
        CommentHandleRequest commentHandleRequest = new Gson().fromJson(body, CommentHandleRequest.class);
        return adminService.setCommentPrivate(commentHandleRequest.getComment_no(), commentHandleRequest.getType());
    }

    @GetMapping("/admin/artist_detail.do")
    public ModelAndView ArtistDetailPage(@RequestParam("artist_no") String artist_no) {
        init("GET ArtistDetail");
        return adminService.getArtistDetail(artist_no);
    }

    @GetMapping("/admin/portfolio.do")
    public ModelAndView PortfolioListPage(@RequestParam("artist_no") String artist_no, @RequestParam("type") String type) {
        init("GET Portfolio List");
        return adminService.getPortfolioList(artist_no, type);
    }

    @GetMapping("/admin/board.do")
    public ModelAndView BoardListPage(@RequestParam("artist_no") String artist_no) {
        init("GET Board List");
        return adminService.getBoardList(artist_no);
    }

    @GetMapping("/admin/portfolio_detail.do")
    public ModelAndView PortfolioDetailPage(@RequestParam("portfolio_no") String portfolio_no) {
        init("GET Portfolio Detail");
        return adminService.getPortfolioDetail(portfolio_no);
    }

    @Getter
    @Setter
    @Data
    class PortfolioHandleRequest {
        private int portfolio_no;
    }

    @PostMapping("/admin/portfolio_delete.do")
    @ResponseBody
    public int DeletePortfolio(@RequestBody String body) {
        init("POST DeletePortfolio");
        PortfolioHandleRequest portfolioHandleRequest = new Gson().fromJson(body, PortfolioHandleRequest.class);
        return adminService.deletePortfolio(portfolioHandleRequest.getPortfolio_no());
    }

    @GetMapping("/admin/board_detail.do")
    public ModelAndView BoardDetailPage(@RequestParam("board_no") String board_no) {
        init("GET Board Detail");
        return adminService.getBoardDetail(board_no);
    }

    @Getter
    @Setter
    @Data
    class BoardHandleRequest {
        private int board_no;
    }

    @PostMapping("/admin/board_delete.do")
    @ResponseBody
    public int DeleteBoard(@RequestBody String body) {
        init("POST DeleteBoard");
        BoardHandleRequest boardHandleRequest = new Gson().fromJson(body, BoardHandleRequest.class);
        return adminService.deleteBoard(boardHandleRequest.getBoard_no());
    }

    @GetMapping("/admin/loudsourcing_recruitment.do")
    public ModelAndView LoudSourcingRecruitmentPage() {
        init("GET LoudSourcingRecruitmentPage");
        return adminService.getLoudSourcingRecruitmentPage();
    }

    @GetMapping("/admin/loudsourcing_process.do")
    public ModelAndView LoudSourcingProcessPage() {
        init("GET LoudSourcingProcessPage");
        return adminService.getLoudSourcingProcessPage();
    }

    @GetMapping("/admin/loudsourcing_judge.do")
    public ModelAndView LoudSourcingJudgePage() throws ParseException {
        init("GET LoudSourcingJudgePage");
        return adminService.getLoudSourcingJudgePage();
    }

    @GetMapping("/admin/loudsourcing_end.do")
    public ModelAndView LoudSourcingEndPage() {
        init("GET LoudSourcingEndPage");
        return adminService.getLoudSourcingEndPage();
    }

    @GetMapping("/admin/recruitment_apply_list.do")
    public ModelAndView RecruitmentApplyListPage(@RequestParam("loudsourcing_no") String loudsourcing_no) {
        init("GET RecruitmentApplyListPage");
        return adminService.getRecruitmentApplyListPage(loudsourcing_no);
    }

    @Getter
    @Setter
    @Data
    class LoudSourcingNotificationRequest {
        private int loudsourcing_no;
    }

    @PostMapping("/admin/recruit_alarm.do")
    @ResponseBody
    public int RecruitAlarmSend(@RequestBody String body) {
        init("POST RecruitAlarmSend");
        LoudSourcingNotificationRequest loudSourcingNotificationRequest = new Gson().fromJson(body, LoudSourcingNotificationRequest.class);
        return adminService.recruitAlarmSend(loudSourcingNotificationRequest.getLoudsourcing_no());
    }

    @GetMapping("/admin/loudsourcing_detail.do")
    public ModelAndView GetLoudSourcingDetail(@RequestParam("loudsourcing_no") String loudsourcing_no) {
        init("GET GetLoudsourcingDetail");
        return adminService.getLoudSourcingDetailPage(loudsourcing_no, "Detail");
    }

    @GetMapping("/admin/loudsourcing_upload.do")
    public ModelAndView GetLoudSourcingUploadPage() {
        init("GET GetLoudSourcingEditPage");
        return adminService.getLoudSourcingUploadPage();
    }

    @GetMapping("/admin/loudsourcing_edit.do")
    public ModelAndView GetLoudSourcingEditPage(@RequestParam("loudsourcing_no") String loudsourcing_no) {
        init("GET GetLoudSourcingEditPage");
        return adminService.getLoudSourcingDetailPage(loudsourcing_no, "Edit");
    }

    @PostMapping("/admin/loudsourcing_edit_post.do")
    @ResponseBody
    public int EditLoudSourcing(@RequestParam("loudsourcing") String body,
                                @RequestParam(value = "files", required = false) MultipartFile[] files,
                                @RequestParam(value = "img", required = false) MultipartFile img,
                                @RequestParam(value = "original_files", required = false) String original_file) {
        try {
            init("POST EditLoudSourcing");
            log.info("Body : " + body);
            Gson gson = new Gson();
            LoudSourcing loudSourcing = gson.fromJson(body, LoudSourcing.class);
            String loudsourcing_info = loudSourcing.getLoudsourcing_no() + "/";
            log.info(loudSourcing);
            log.info("Files : " + Arrays.toString(files));
            log.info("Original files : " + original_file);
            Upload[] original_fileList = gson.fromJson(original_file, Upload[].class);

            URLConverter urlConverter = new URLConverter();
            List<Upload> fileList = new ArrayList<>();
            for (Upload upload : original_fileList) {
                log.info("originalFile name : " + upload.getName());
                log.info("originalFile url : " + upload.getUrl());
                if (upload.getUrl() != null)
                    fileList.add(upload);
            }
            if (files != null && files.length > 0) {
                Map<String, MultipartFile> multipartFileMap = new HashMap<>();
                for (int i = 0; i < files.length; i++) {
                    if (!files[i].isEmpty())
                        multipartFileMap.put("files-" + i, files[i]);
                }
                /** Multiple File Upload Logic*/
                for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                    MultipartFile multipartFile = entry.getValue();
                    log.info("originalName:" + multipartFile.getOriginalFilename());
                    log.info("size:" + multipartFile.getSize());
                    log.info("ContentType:" + multipartFile.getContentType());

                    String decoded_file_name = multipartFile.getOriginalFilename();

                    if(!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                        decoded_file_name = Normalizer.normalize(multipartFile.getOriginalFilename(), Normalizer.Form.NFC);
                        log.info(decoded_file_name);
                    }

                    String fileName = uploadFile(decoded_file_name, multipartFile, loudsourcing_info);
                    fileList.add(new Upload(fileName, urlConverter.convertSpecialLetter(cdn_path + "files/loudsourcing/" + loudsourcing_info + fileName)));
                }
            }
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if(!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }

                String fileName = uploadFile(decoded_file_name, img, loudsourcing_info);
                loudSourcing.setImg(urlConverter.convertSpecialLetter(cdn_path + "files/loudsourcing/" + loudsourcing_info + fileName));
            }
            List<String> file_msgList = new ArrayList<>();
            log.info(1);
            for (Upload upload : fileList) {
                FileJson fileJson = new FileJson();
                String name = upload.getName();
                String url = upload.getUrl();
                fileJson.setName(name);
                fileJson.setUrl(url);
                String jsonString = gson.toJson(fileJson);
                file_msgList.add(jsonString);
            }
            loudSourcing.setFiles(file_msgList.toString());
            return adminService.updateLoudsourcing(loudSourcing);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @PostMapping("/admin/loudsourcing_make_post.do")
    @ResponseBody
    public int UploadLoudSourcing(@RequestParam("loudsourcing") String body,
                                @RequestParam(value = "files", required = false) MultipartFile[] files,
                                @RequestParam(value = "img", required = false) MultipartFile img) {
        try {
            init("POST UploadLoudSourcing");
            log.info("Body : " + body);
            Gson gson = new Gson();
            LoudSourcing loudSourcing = gson.fromJson(body, LoudSourcing.class);
            String loudsourcing_info = "primary/";
            log.info(loudSourcing);
            log.info("Files : " + Arrays.toString(files));

            URLConverter urlConverter = new URLConverter();
            List<Upload> fileList = new ArrayList<>();
            if (files != null && files.length > 0) {
                Map<String, MultipartFile> multipartFileMap = new HashMap<>();
                for (int i = 0; i < files.length; i++) {
                    if (!files[i].isEmpty())
                        multipartFileMap.put("files-" + i, files[i]);
                }
                /** Multiple File Upload Logic*/
                for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                    MultipartFile multipartFile = entry.getValue();
                    log.info("originalName:" + multipartFile.getOriginalFilename());
                    log.info("size:" + multipartFile.getSize());
                    log.info("ContentType:" + multipartFile.getContentType());

                    String decoded_file_name = multipartFile.getOriginalFilename();

                    if(!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                        decoded_file_name = Normalizer.normalize(multipartFile.getOriginalFilename(), Normalizer.Form.NFC);
                        log.info(decoded_file_name);
                    }

                    String fileName = uploadFile(decoded_file_name, multipartFile, loudsourcing_info);
                    fileList.add(new Upload(fileName, urlConverter.convertSpecialLetter(cdn_path + "files/loudsourcing/" + loudsourcing_info + fileName)));
                }
            }
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if(!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }

                String fileName = uploadFile(decoded_file_name, img, loudsourcing_info);
                loudSourcing.setImg(urlConverter.convertSpecialLetter(cdn_path + "files/loudsourcing/" + loudsourcing_info + fileName));
            }
            List<String> file_msgList = new ArrayList<>();
            log.info(1);
            for (Upload upload : fileList) {
                FileJson fileJson = new FileJson();
                String name = upload.getName();
                String url = upload.getUrl();
                fileJson.setName(name);
                fileJson.setUrl(url);
                String jsonString = gson.toJson(fileJson);
                file_msgList.add(jsonString);
            }
            loudSourcing.setFiles(file_msgList.toString());
            return adminService.uploadLoudSourcing(loudSourcing);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Getter
    @Setter
    @Data
    class LoudSourcingDeleteRequest{
        private int loudsourcing_no;
    }

    @PostMapping("/admin/delete_loudsourcing.do")
    @ResponseBody
    public int DeleteLoudSourcing(@RequestBody String body){
        LoudSourcingDeleteRequest loudSourcingDeleteRequest = new Gson().fromJson(body, LoudSourcingDeleteRequest.class);
        return adminService.deleteLoudSourcing(loudSourcingDeleteRequest.getLoudsourcing_no());
    }

    @GetMapping("/admin/loudsourcing_advertiser.do")
    public ModelAndView GetLoudSourcingAdvertiser(@RequestParam("loudsourcing_no") String param){
        init("GET LoudSourcingAdvertiser");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getLoudSourcingAdvertiserInfo(loudsourcing_no);
    }

    @GetMapping("/admin/advertiser_edit.do")
    public ModelAndView GetLoudSourcingAdvertiserForEdit(@RequestParam("loudsourcing_no") String param){
        init("GET GetLoudSourcingAdvertiserForEdit");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getLoudSourcingAdvertiserForEdit(loudsourcing_no);
    }

    @PostMapping("/admin/advertiser_edit_post.do")
    @ResponseBody
    public int LoudSourcingAdvertiserEdit(@RequestBody String body){
        init("POST LoudSourcingAdvertiserEdit");
        log.info("Body : " + body);
        AdvertiserEditRequest request = new Gson().fromJson(body, AdvertiserEditRequest.class);
        return adminService.advertiserEdit(request);
    }

    @GetMapping("/admin/process_apply_list.do")
    public ModelAndView GetLoudSourcingProcessArtistList(@RequestParam("loudsourcing_no") String param){
        init("GET GetLoudSourcingProcessArtistList");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getLoudSourcingProcessArtistList(loudsourcing_no);
    }

    private String uploadFile(String originalName, MultipartFile mfile, String loudsourcing_info) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        FileConverter fileConverter = new FileConverter();
        File file = fileConverter.convert(mfile);
        CDNService cdnService = new CDNService();
        cdnService.upload("api/files/loudsourcing/" + loudsourcing_info + savedName, file);
        Files.deleteIfExists(file.toPath());
        return savedName;
    }

}

