package com.restapi.Restfull.API.Server.controller.admin;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.interfaces.controller.ControllerInitialize;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.services.AdminService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.services.LoudSourcingService;
import com.restapi.Restfull.API.Server.utility.FileConverter;
import com.restapi.Restfull.API.Server.utility.Time;
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

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private LoudSourcingService loudSourcingService;

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

    @PostMapping("/admin/login_post.do")
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

    @GetMapping("/admin/logout.do")
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

    @GetMapping("/admin/penalty.do")
    public ModelAndView GetPenalty(@RequestParam("user_no") String param){
        init("GET GetPenalty");
        int user_no = Integer.parseInt(param);
        return adminService.getPenalty(user_no);
    }

    @PostMapping("/admin/penalty_user.do")
    @ResponseBody
    public int PenaltyUser(@RequestBody String body){
        init("POST PenaltyUser");
        Penalty penalty = new Gson().fromJson(body, Penalty.class);
        return adminService.penaltyUser(penalty);
    }

    @GetMapping("/admin/comments.do")
    public ModelAndView CommentPage(HttpSession session, @RequestParam("user_no") String user_no, @RequestParam("beforeType") String type) {
        init("GET Comments");
        session.setAttribute("beforeType", type);
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

    @GetMapping("/admin/artist_loudsourcing.do")
    public ModelAndView GetArtistLoudSourcingList(@RequestParam("artist_no") String param) {
        init("GET GetArtistLoudSourcingList");
        int artist_no = Integer.parseInt(param);
        return adminService.getArtistLoudSourcingList(artist_no);
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


    @PostMapping("/admin/recruit_alarm.do")
    @ResponseBody
    public int RecruitAlarmSend(@RequestBody String body) {
        init("POST RecruitAlarmSend");
        LoudSourcingRequest loudSourcingNotificationRequest = new Gson().fromJson(body, LoudSourcingRequest.class);
        return adminService.sendProcessStartMessageToAll(loudSourcingNotificationRequest.getLoudsourcing_no());
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

                    if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                        decoded_file_name = Normalizer.normalize(multipartFile.getOriginalFilename(), Normalizer.Form.NFC);
                        log.info(decoded_file_name);
                    }

                    String fileName = uploadFile(decoded_file_name, multipartFile, loudsourcing_info, "loudsourcing");
                    fileList.add(new Upload(fileName, urlConverter.convertSpecialLetter(cdn_path + "files/loudsourcing/" + loudsourcing_info + fileName)));
                }
            }
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }

                String fileName = uploadFile(decoded_file_name, img, loudsourcing_info, "loudsourcing");
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

                    if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                        decoded_file_name = Normalizer.normalize(multipartFile.getOriginalFilename(), Normalizer.Form.NFC);
                        log.info(decoded_file_name);
                    }

                    String fileName = uploadFile(decoded_file_name, multipartFile, loudsourcing_info, "loudsourcing");
                    fileList.add(new Upload(fileName, urlConverter.convertSpecialLetter(cdn_path + "files/loudsourcing/" + loudsourcing_info + fileName)));
                }
            }
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }

                String fileName = uploadFile(decoded_file_name, img, loudsourcing_info, "loudsourcing");
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
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @Getter
    @Setter
    @Data
    class LoudSourcingRequest {
        private int loudsourcing_no;
    }

    @PostMapping("/admin/delete_loudsourcing.do")
    @ResponseBody
    public int DeleteLoudSourcing(@RequestBody String body) {
        LoudSourcingRequest loudSourcingDeleteRequest = new Gson().fromJson(body, LoudSourcingRequest.class);
        return adminService.deleteLoudSourcing(loudSourcingDeleteRequest.getLoudsourcing_no());
    }

    @GetMapping("/admin/loudsourcing_advertiser.do")
    public ModelAndView GetLoudSourcingAdvertiser(@RequestParam("loudsourcing_no") String param) {
        init("GET LoudSourcingAdvertiser");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getLoudSourcingAdvertiserInfo(loudsourcing_no);
    }

    @GetMapping("/admin/advertiser_edit.do")
    public ModelAndView GetLoudSourcingAdvertiserForEdit(@RequestParam("loudsourcing_no") String param) {
        init("GET GetLoudSourcingAdvertiserForEdit");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getLoudSourcingAdvertiserForEdit(loudsourcing_no);
    }

    @PostMapping("/admin/advertiser_edit_post.do")
    @ResponseBody
    public int LoudSourcingAdvertiserEdit(@RequestBody String body) {
        init("POST LoudSourcingAdvertiserEdit");
        log.info("Body : " + body);
        AdvertiserEditRequest request = new Gson().fromJson(body, AdvertiserEditRequest.class);
        return adminService.advertiserEdit(request);
    }

    @GetMapping("/admin/process_apply_list.do")
    public ModelAndView GetLoudSourcingProcessArtistList(@RequestParam("loudsourcing_no") String param) {
        init("GET GetLoudSourcingProcessArtistList");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getLoudSourcingProcessArtistList(loudsourcing_no);
    }

    @GetMapping("/admin/entry_detail.do")
    public ModelAndView GetEntryDetail(@RequestParam("loudsourcing_no") String lparam, @RequestParam("artist_no") String aparam) {
        init("GET GetEntryDetail");
        int loudsourcing_no = Integer.parseInt(lparam);
        int artist_no = Integer.parseInt(aparam);
        return adminService.getEntryDetail(loudsourcing_no, artist_no);
    }

    @PostMapping("/admin/send_all_process_start_message.do")
    @ResponseBody
    public int SendAllProcessStartMessage(@RequestBody String body) {
        init("POST SendProcessStartMessage");
        LoudSourcingRequest request = new Gson().fromJson(body, LoudSourcingRequest.class);
        return adminService.sendProcessStartMessageToAll(request.getLoudsourcing_no());
    }

    @Getter
    @Setter
    @Data
    class LoudSourcingArtistRequest {
        private int loudsourcing_no;
        private int artist_no;
    }

    @PostMapping("/admin/send_process_start_message.do")
    @ResponseBody
    public int SendProcessStartMessage(@RequestBody String body) {
        init("POST SendProcessStartMessage");
        LoudSourcingArtistRequest request = new Gson().fromJson(body, LoudSourcingArtistRequest.class);
        return adminService.sendProcessStartMessage(request.getLoudsourcing_no(), request.getArtist_no());
    }

    @GetMapping("/admin/unknown_entry_list.do")
    public ModelAndView GetUnknownEntryList(@RequestParam("loudsourcing_no") String param) {
        init("GET GetUnknownEntryList");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getUnknownEntryList(loudsourcing_no);
    }

    @GetMapping("/admin/unknown_entry_detail.do")
    public ModelAndView GetUnknownEntryDetail(@RequestParam("loudsourcing_no") String lParam, @RequestParam("entry_no") String eParam) {
        init("GET GetUnknownEntryDetail");
        int loudsourcing_no = Integer.parseInt(lParam);
        int entry_no = Integer.parseInt(eParam);
        return adminService.getUnknownEntryDetail(loudsourcing_no, entry_no);
    }

    @Getter
    @Setter
    @Data
    class EntryDeleteRequest {
        private int entry_no;
    }

    @PostMapping("/admin/entry_delete.do")
    @ResponseBody
    public int DeleteUnknownEntry(@RequestBody String body) {
        init("POST DeleteUnknownEntry");
        EntryDeleteRequest request = new Gson().fromJson(body, EntryDeleteRequest.class);
        return adminService.deleteUnknownEntry(request.getEntry_no());
    }

    @PostMapping("/admin/apply_delete.do")
    @ResponseBody
    public int DeleteApply(@RequestBody String body) {
        init("POST DeleteApply");
        LoudSourcingArtistRequest request = new Gson().fromJson(body, LoudSourcingArtistRequest.class);
        return adminService.deleteApply(request.getArtist_no(), request.getLoudsourcing_no());
    }

    @GetMapping("/admin/selected_entry.do")
    public ModelAndView GetSelectedEntryList(@RequestParam("loudsourcing_no") String param) {
        init("GET GetSelectedEntryList");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getSelectedEntryList(loudsourcing_no);
    }

    @GetMapping("/admin/unselected_entry.do")
    public ModelAndView GetUnSelectedEntryList(@RequestParam("loudsourcing_no") String param) {
        init("GET GetUnSelectedEntryList");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getUnSelectedEntryList(loudsourcing_no);
    }

    @PostMapping("/admin/send_selected_message.do")
    @ResponseBody
    public int SendSelectedMessage(@RequestBody String body) {
        init("POST SendSelectedMessage");
        LoudSourcingArtistRequest request = new Gson().fromJson(body, LoudSourcingArtistRequest.class);
        return adminService.sendSelectedMessage(request.getArtist_no(), request.getLoudsourcing_no());
    }

    @PostMapping("/admin/send_unselected_message.do")
    @ResponseBody
    public int SendUnSelectedMessage(@RequestBody String body) {
        init("POST SendUnSelectedMessage");
        LoudSourcingArtistRequest request = new Gson().fromJson(body, LoudSourcingArtistRequest.class);
        return adminService.sendUnSelectedMessage(request.getArtist_no(), request.getLoudsourcing_no());
    }

    @PostMapping("/admin/send_all_selected_message.do")
    @ResponseBody
    public int SendAllSelectedMessage(@RequestBody String body) {
        init("POST SendAllSelectedMessage");
        LoudSourcingRequest request = new Gson().fromJson(body, LoudSourcingRequest.class);
        return adminService.sendAllSelectedMessage(request.getLoudsourcing_no());
    }

    @PostMapping("/admin/send_all_unselected_message.do")
    @ResponseBody
    public int SendAllUnSelectedMessage(@RequestBody String body) {
        init("POST SendAllSelectedMessage");
        LoudSourcingRequest request = new Gson().fromJson(body, LoudSourcingRequest.class);
        return adminService.sendAllUnSelectedMessage(request.getLoudsourcing_no());
    }

    @PostMapping("/admin/change_select.do")
    @ResponseBody
    public int ChangeSelect(@RequestBody String body) {
        init("POST ChangeSelect");
        LoudSourcingArtistRequest request = new Gson().fromJson(body, LoudSourcingArtistRequest.class);
        return adminService.changeSelect(request.getArtist_no(), request.getLoudsourcing_no());
    }

    @GetMapping("/admin/final_selected.do")
    public ModelAndView GetFinalSelectedList(@RequestParam("loudsourcing_no") String param) {
        init("GET GetFinalSelectedList");
        int loudsourcing_no = Integer.parseInt(param);
        return adminService.getFinalSelectedList(loudsourcing_no);
    }

    @GetMapping("/admin/notices.do")
    public ModelAndView GetNoticeList() {
        init("GET GetNoticeList");
        return adminService.getNoticeList();
    }

    @Getter
    @Setter
    @Data
    class NoticeRequest {
        private int notice_no;
    }

    @PostMapping("/admin/notice_delete.do")
    @ResponseBody
    public int DeleteNotice(@RequestBody String body) {
        init("POST DeleteNotice");
        NoticeRequest request = new Gson().fromJson(body, NoticeRequest.class);
        return adminService.deleteNotice(request.getNotice_no());
    }

    @GetMapping("/admin/notice_detail.do")
    public ModelAndView GetNoticeDetail(@RequestParam("notice_no") String param) {
        init("GET GetNoticeDetail");
        int notice_no = Integer.parseInt(param);
        return adminService.getNoticeDetail(notice_no);
    }

    @GetMapping("/admin/notice_make.do")
    public ModelAndView GetNoticeMake() {
        init("GET GetNoticeMake");
        return adminService.getNoticeMake();
    }


    @PostMapping("/admin/notice_make_post.do")
    @ResponseBody
    public NoticeMakeResponse MakeNotice(@RequestParam("notice") String body,
                                         @RequestParam(value = "img", required = false) MultipartFile img) {
        try {
            init("POST MakeNotice");
            log.info(body);
            Notice notice = new Gson().fromJson(body, Notice.class);
            URLConverter urlConverter = new URLConverter();
            String time = Time.TimeFormatNoSpecialCharacter();
            String notice_info = time + "/";
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }

                String fileName = uploadFile(decoded_file_name, img, notice_info, "notice");
                notice.setImg(urlConverter.convertSpecialLetter(cdn_path + "images/notice/" + notice_info + fileName));
            }
            return adminService.makeNotice(notice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @PostMapping("/admin/notice_edit_post.do")
    @ResponseBody
    public int EditNotice(@RequestParam("notice") String body,
                          @RequestParam(value = "img", required = false) MultipartFile img) {
        try {
            init("POST MakeNotice");
            log.info(body);
            Notice notice = new Gson().fromJson(body, Notice.class);
            URLConverter urlConverter = new URLConverter();
            String time = Time.TimeFormatNoSpecialCharacter();
            String notice_info = time + "/";
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }

                String fileName = uploadFile(decoded_file_name, img, notice_info, "notice");
                notice.setImg(urlConverter.convertSpecialLetter(cdn_path + "images/notice/" + notice_info + fileName));
            }
            return adminService.editNotice(notice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @GetMapping("/admin/faqs.do")
    public ModelAndView GetFAQList() {
        init("GET GetFAQList");
        return adminService.getFAQList();
    }

    @GetMapping("/admin/faq_detail.do")
    public ModelAndView GetFAQDetail(@RequestParam("faq_no") String param) {
        init("GET GetFAQDetail");
        int faq_no = Integer.parseInt(param);
        return adminService.getFAQDetail(faq_no);
    }

    @GetMapping("/admin/faq_make.do")
    public ModelAndView GetFAQMake(){
        init("GET GetFAQMake");
        return adminService.getFAQMake();
    }

    @Getter
    @Setter
    @Data
    class FAQRequest {
        private int faq_no;
    }

    @PostMapping("/admin/faq_delete.do")
    @ResponseBody
    public int DeleteFAQ(@RequestBody String body) {
        init("POST DeleteFAQ");
        FAQRequest request = new Gson().fromJson(body, FAQRequest.class);
        return adminService.deleteFAQ(request.getFaq_no());
    }

    @PostMapping("/admin/faq_edit_post.do")
    @ResponseBody
    public int EditFAQ(@RequestParam("faq") String body,
                       @RequestParam(value = "img", required = false) MultipartFile img) {
        try {
            init("POST EditFAQ");
            FAQ faq = new Gson().fromJson(body, FAQ.class);
            URLConverter urlConverter = new URLConverter();
            String time = Time.TimeFormatNoSpecialCharacter();
            String faq_info = time + "/";
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }

                String fileName = uploadFile(decoded_file_name, img, faq_info, "faq");
                faq.setImg(urlConverter.convertSpecialLetter(cdn_path + "images/faq/" + faq_info + fileName));
            }
            return adminService.editFAQ(faq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @PostMapping("/admin/faq_make_post.do")
    @ResponseBody
    public int MakeFAQ(@RequestParam("faq") String body,
                       @RequestParam(value = "img", required = false) MultipartFile img) {
        try {
            init("POST MakeFAQ");
            FAQ faq = new Gson().fromJson(body, FAQ.class);
            URLConverter urlConverter = new URLConverter();
            String time = Time.TimeFormatNoSpecialCharacter();
            String faq_info = time + "/";
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }

                String fileName = uploadFile(decoded_file_name, img, faq_info, "faq");
                faq.setImg(urlConverter.convertSpecialLetter(cdn_path + "images/faq/" + faq_info + fileName));
            }
            return adminService.makeFAQ(faq);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @GetMapping("/admin/banners.do")
    public ModelAndView GetBannerList(){
        init("GET GetBannerList");
        return adminService.getBannerList();
    }

    @GetMapping("/admin/banner_make.do")
    public ModelAndView GetBannerMake(){
        init("GET GetBannerMake");
        return adminService.getBannerMake();
    }

    @GetMapping("/admin/banner_detail.do")
    public ModelAndView GetBannerDetail(@RequestParam("banner_no") String param){
        init("GET GetBannerDetail");
        int banner_ad_no = Integer.parseInt(param);
        return adminService.getBannerDetail(banner_ad_no);
    }

    @Getter
    @Setter
    @Data
    class BannerRequest{
        private int banner_ad_no;
    }

    @PostMapping("/admin/banner_delete.do")
    @ResponseBody
    public int DeleteBanner(@RequestBody String body){
        init("POST DeleteBanner");
        BannerRequest request = new Gson().fromJson(body, BannerRequest.class);
        return adminService.deleteBanner(request.getBanner_ad_no());
    }

    @PostMapping("/admin/banner_active.do")
    @ResponseBody
    public int ActiveBanner(@RequestBody String body){
        init("POST ActiveBanner");
        BannerRequest request = new Gson().fromJson(body, BannerRequest.class);
        return adminService.activeBanner(request.getBanner_ad_no());
    }

    @PostMapping("/admin/banner_disable.do")
    @ResponseBody
    public int DisableBanner(@RequestBody String body){
        init("POST DisableBanner");
        BannerRequest request = new Gson().fromJson(body, BannerRequest.class);
        return adminService.disableBanner(request.getBanner_ad_no());
    }

    @PostMapping("/admin/banner_make_post.do")
    @ResponseBody
    public int MakeBanner(@RequestParam("img") MultipartFile img){
        try {
            init("POST MakeBanner");
            URLConverter urlConverter = new URLConverter();
            String time = Time.TimeFormatNoSpecialCharacter();
            String banner_info = time + "/";
            BannerAd bannerAd = new BannerAd();
            String now = Time.TimeFormatHMS();
            bannerAd.setReg_date(now);
            bannerAd.setRevise_date(now);
            bannerAd.setStatus(false);
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }
                String fileName = uploadFile(decoded_file_name, img, banner_info, "banner");
                bannerAd.setImg(urlConverter.convertSpecialLetter(cdn_path + "images/banner/" + banner_info + fileName));
            } else {
                throw new BusinessException(new Exception("No Image. Invalid Access"));
            }
            return adminService.makeBanner(bannerAd);
            } catch (Exception e){
                e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @PostMapping("/admin/banner_edit_post.do")
    @ResponseBody
    public int EditBanner(@RequestParam("banner") String body, @RequestParam("img") MultipartFile img){
        try {
            init("POST EditBanner");
            URLConverter urlConverter = new URLConverter();
            String time = Time.TimeFormatNoSpecialCharacter();
            String banner_info = time + "/";
            BannerAd bannerAd = new Gson().fromJson(body, BannerAd.class);
            if (img != null && !img.isEmpty()) {
                log.info("originalName:" + img.getOriginalFilename());
                log.info("size:" + img.getSize());
                log.info("ContentType:" + img.getContentType());

                String decoded_file_name = img.getOriginalFilename();

                if (!Normalizer.isNormalized(decoded_file_name, Normalizer.Form.NFC)) {
                    decoded_file_name = Normalizer.normalize(img.getOriginalFilename(), Normalizer.Form.NFC);
                    log.info(decoded_file_name);
                }
                String fileName = uploadFile(decoded_file_name, img, banner_info, "banner");
                bannerAd.setImg(urlConverter.convertSpecialLetter(cdn_path + "images/banner/" + banner_info + fileName));
            } else {
                throw new BusinessException(new Exception("No Image. Invalid Access"));
            }
            return adminService.editBanner(bannerAd);
        } catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    @GetMapping("/admin/inquiry_loudsourcing.do")
    public ModelAndView GetInquiryLoudsourcingList(){
        init("GET GetInquiryLoudsourcingList");
        return adminService.getInquiryList("loudsourcing");
    }

    @GetMapping("/admin/inquiry_report.do")
    public ModelAndView GetInquiryReportList(){
        init("GET GetInquiryReportList");
        return adminService.getInquiryList("report");
    }

    @GetMapping("/admin/inquiry_normal.do")
    public ModelAndView GetInquiryNormalList(){
        init("GET GetInquiryNormalList");
        return adminService.getInquiryList("normal");
    }

    @GetMapping("/admin/inquiry_detail.do")
    public ModelAndView GetInquiryDetail(@RequestParam("inquiry_no") String param){
        init("GET GetInquiryDetail");
        int inquiry_no = Integer.parseInt(param);
        return adminService.getInquiryDetail(inquiry_no);
    }

    @Getter
    @Setter
    @Data
    class InquiryRequest{
        private int inquiry_no;
    }

    @PostMapping("/admin/inquiry_delete.do")
    @ResponseBody
    public int DeleteInquiry(@RequestBody String body){
        init("POST DeleteInquiry");
        InquiryRequest request = new Gson().fromJson(body, InquiryRequest.class);
        return adminService.deleteInquiry(request.getInquiry_no());
    }

    @PostMapping("/admin/inquiry_answer.do")
    @ResponseBody
    public int AnswerInquiry(@RequestBody String body){
        init("POST AnswerInquiry");
        Inquiry inquiry = new Gson().fromJson(body, Inquiry.class);
        return adminService.answerInquiry(inquiry);
    }

    @GetMapping("/admin/spon.do")
    public ModelAndView GetSponList(){
        init("GET GetSponList");
        return adminService.getSponList();
    }

    @GetMapping("/admin/spon_detail.do")
    public ModelAndView GetSponDetail(@RequestParam("spon_no") String param){
        init("GET GetSponDetail");
        int spon_no = Integer.parseInt(param);
        return adminService.getSponDetail(spon_no);
    }

    @Getter
    @Setter
    @Data
    class SponRequest{
        private int spon_no;
    }

    @PostMapping("/admin/spon_apply.do")
    @ResponseBody
    public int ApplySpon(@RequestBody String body){
        init("POST ApplySpon");
        SponRequest request = new Gson().fromJson(body, SponRequest.class);
        return adminService.applySpon(request.getSpon_no());
    }

    @PostMapping("/admin/spon_send.do")
    @ResponseBody
    public int SendSpon(@RequestBody String body){
        init("POST SendSpon");
        SponRequest request = new Gson().fromJson(body, SponRequest.class);
        return adminService.sendSpon(request.getSpon_no());
    }

    @PostMapping("/admin/spon_delete.do")
    @ResponseBody
    public int DeleteSpon(@RequestBody String body){
        init("POST DeleteSpon");
        SponRequest request = new Gson().fromJson(body, SponRequest.class);
        return adminService.deleteSpon(request.getSpon_no());
    }

    @GetMapping("/admin/hashtag.do")
    public ModelAndView HashTagEdit(){
        init("GET HashTagEdit");
        return adminService.getHashTagEdit();
    }

    @PostMapping("/admin/hashtag_edit.do")
    @ResponseBody
    public int EditHashTagPost(@RequestBody String body){
        init("POST EditHashTagPost");
        return adminService.editHashTag(body);
    }

    @Data
    class ArtistRequest{
        private int artist_no;
    }

    @Data
    class UserRequest{
        private int user_no;
    }

    @PostMapping("/admin/artist_change_name.do")
    @ResponseBody
    public int ChangeArtistName(@RequestBody String body){
        init("POST ChangeArtistName");
        ArtistRequest request = new Gson().fromJson(body, ArtistRequest.class);
        return adminService.changeArtistName(request.getArtist_no());
    }

    @PostMapping("/admin/artist_change_profile.do")
    @ResponseBody
    public int ChangeArtistProfileImg(@RequestBody String body){
        init("POST ChangeArtistName");
        ArtistRequest request = new Gson().fromJson(body, ArtistRequest.class);
        return adminService.changeArtistProfileImg(request.getArtist_no());
    }

    @PostMapping("/admin/artist_change_main.do")
    @ResponseBody
    public int ChangeArtistMainImg(@RequestBody String body){
        init("POST ChangeArtistName");
        ArtistRequest request = new Gson().fromJson(body, ArtistRequest.class);
        return adminService.changeArtistMainImg(request.getArtist_no());
    }

    @PostMapping("/admin/user_change_name.do")
    @ResponseBody
    public int ChangeUserName(@RequestBody String body){
        init("POST ChangeArtistName");
        UserRequest request = new Gson().fromJson(body, UserRequest.class);
        return adminService.changeUserName(request.getUser_no());
    }

    @PostMapping("/admin/user_change_profile.do")
    @ResponseBody
    public int ChangeUserProfileImg(@RequestBody String body){
        init("POST ChangeArtistName");
        UserRequest request = new Gson().fromJson(body, UserRequest.class);
        return adminService.changeUserProfileImg(request.getUser_no());
    }

    @PostMapping("/admin/artist_reset_explain.do")
    @ResponseBody
    public int ResetArtistExplain(@RequestBody String body){
        init("POST ResetArtistExplain");
        ArtistRequest request = new Gson().fromJson(body, ArtistRequest.class);
        return adminService.resetArtistExplain(request.getArtist_no());
    }

    @PostMapping("/admin/artist_reset_hashtag.do")
    @ResponseBody
    public int ResetArtistHashTag(@RequestBody String body){
        init("POST ResetArtistHashTag");
        ArtistRequest request = new Gson().fromJson(body, ArtistRequest.class);
        return adminService.resetArtistHashTag(request.getArtist_no());
    }

    @PostMapping("/admin/user_reset_penalty.do")
    @ResponseBody
    public int ResetUserPenalty(@RequestBody String body){
        init("POST ResetArtistPenalty");
        UserRequest request = new Gson().fromJson(body, UserRequest.class);
        return adminService.resetUserPenalty(request.getUser_no());
    }

    @PostMapping("/admin/loudsourcing/set/judge.do")
    @ResponseBody
    public int ManualSetLoudSourcingToJudge(){
        try {
            init("POST ManualSetLoudSourcingToJudge");
            loudSourcingService.setLoudSourcingProcessToJudge();
            return 1;
        } catch (Exception e){
            return 0;
        }
    }

    @PostMapping("/admin/loudsourcing/set/end.do")
    @ResponseBody
    public int ManualSetLoudSourcingToEnd(){
        try {
            init("POST ManualSetLoudSourcingToEnd");
            loudSourcingService.setLoudSourcingJudgeToEnd();
            return 1;
        } catch (Exception e){
            return 0;
        }
    }

    private String uploadFile(String originalName, MultipartFile mfile, String content_info, String type) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        FileConverter fileConverter = new FileConverter();
        File file = fileConverter.convert(mfile, uid.toString().substring(0, 8) + "test" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase());
        CDNService cdnService = new CDNService();
        if (type.equals("notice")) {
            cdnService.upload("api/images/notice/" + content_info + savedName, file);
        } else if (type.equals("loudsourcing")) {
            cdnService.upload("api/files/loudsourcing/" + content_info + savedName, file);
        } else if (type.equals("faq")) {
            cdnService.upload("api/images/faq/" + content_info + savedName, file);
        } else if (type.equals("banner")){
            cdnService.upload("api/images/banner/" + content_info + savedName, file);
        }
        Files.deleteIfExists(file.toPath());
        return savedName;
    }

}

