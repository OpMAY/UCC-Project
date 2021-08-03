package com.restapi.Restfull.API.Server.controller.admin;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.interfaces.controller.ControllerInitialize;
import com.restapi.Restfull.API.Server.models.Admin;
import com.restapi.Restfull.API.Server.models.CommentHandleRequest;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.services.AdminService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@Log4j2
@Controller
public class AdminController implements ControllerInitialize {
    private ModelAndView modelAndView;

    @Autowired
    private AdminService adminService;

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
    class AdminLogin{
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
        } else{
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
    public ModelAndView CommentPage(HttpSession session, @RequestParam("user_no") String user_no){
        init("GET Comments");
        return adminService.getComments(user_no);
    }

    @GetMapping("/admin/user_detail.do")
    public ModelAndView UserDetailPage(HttpSession session, @RequestParam("user_no") String user_no){
        init("GET UserDetail");
        return adminService.getUserDetail(user_no);
    }

    @PostMapping("/admin/comment/delete.do")
    @ResponseBody
    public int DeleteComment(@RequestBody String body){
        init("GET DeleteComment");
        CommentHandleRequest commentHandleRequest = new Gson().fromJson(body, CommentHandleRequest.class);
        return adminService.deleteComment(commentHandleRequest.getComment_no(), commentHandleRequest.getType());
    }

    @PostMapping("/admin/comment/private.do")
    @ResponseBody
    public int PrivateComment(@RequestBody String body){
        init("GET PrivateComment");
        CommentHandleRequest commentHandleRequest = new Gson().fromJson(body, CommentHandleRequest.class);
        return adminService.setCommentPrivate(commentHandleRequest.getComment_no(), commentHandleRequest.getType());
    }

    @GetMapping("/admin/artist_detail.do")
    public ModelAndView ArtistDetailPage(@RequestParam("artist_no") String artist_no){
        init("GET ArtistDetail");
        return adminService.getArtistDetail(artist_no);
    }

    @GetMapping("/admin/portfolio.do")
    public ModelAndView PortfolioListPage(@RequestParam("artist_no") String artist_no, @RequestParam("type") String type){
        init("GET Portfolio List");
        return adminService.getPortfolioList(artist_no, type);
    }

    @GetMapping("/admin/board.do")
    public ModelAndView BoardListPage(@RequestParam("artist_no") String artist_no){
        init("GET Board List");
        return adminService.getBoardList(artist_no);
    }

    @GetMapping("/admin/portfolio_detail.do")
    public ModelAndView PortfolioDetailPage(@RequestParam("portfolio_no") String portfolio_no){
        init("GET Portfolio Detail");
        return adminService.getPortfolioDetail(portfolio_no);
    }

    @Getter
    @Setter
    @Data
    class PortfolioHandleRequest{
        private int portfolio_no;
    }

    @PostMapping("/admin/portfolio_delete.do")
    @ResponseBody
    public int DeletePortfolio(@RequestBody String body){
        init("POST DeletePortfolio");
        PortfolioHandleRequest portfolioHandleRequest = new Gson().fromJson(body, PortfolioHandleRequest.class);
        return adminService.deletePortfolio(portfolioHandleRequest.getPortfolio_no());
    }

    @GetMapping("/admin/board_detail.do")
    public ModelAndView BoardDetailPage(@RequestParam("board_no") String board_no){
        init("GET Board Detail");
        return adminService.getBoardDetail(board_no);
    }

    @Getter
    @Setter
    @Data
    class BoardHandleRequest{
        private int board_no;
    }

    @PostMapping("/admin/board_delete.do")
    @ResponseBody
    public int DeleteBoard(@RequestBody String body){
        init("POST DeleteBoard");
        BoardHandleRequest boardHandleRequest = new Gson().fromJson(body, BoardHandleRequest.class);
        return adminService.deleteBoard(boardHandleRequest.getBoard_no());
    }

    @GetMapping("/admin/loudsourcing_recruitment.do")
    public ModelAndView LoudSourcingRecruitmentPage(){
        init("GET LoudSourcingRecruitmentPage");
        return adminService.getLoudSourcingRecruitmentPage();
    }

    @GetMapping("/admin/loudsourcing_process.do")
    public ModelAndView LoudSourcingProcessPage(){
        init("GET LoudSourcingProcessPage");
        return adminService.getLoudSourcingProcessPage();
    }

    @GetMapping("/admin/loudsourcing_judge.do")
    public ModelAndView LoudSourcingJudgePage() throws ParseException {
        init("GET LoudSourcingJudgePage");
        return adminService.getLoudSourcingJudgePage();
    }

    @GetMapping("/admin/loudsourcing_end.do")
    public ModelAndView LoudSourcingEndPage(){
        init("GET LoudSourcingEndPage");
        return adminService.getLoudSourcingEndPage();
    }

    @GetMapping("/admin/recruitment_apply_list.do")
    public ModelAndView RecruitmentApplyListPage(@RequestParam("loudsourcing_no") String loudsourcing_no){
        init("GET RecruitmentApplyListPage");
        return adminService.getRecruitmentApplyListPage(loudsourcing_no);
    }

    @Getter
    @Setter
    @Data
    class LoudSourcingNotificationRequest{
        private int loudsourcing_no;
    }

    @PostMapping("/admin/recruit_alarm.do")
    @ResponseBody
    public int RecruitAlarmSend(@RequestBody String body){
        init("POST RecruitAlarmSend");
        LoudSourcingNotificationRequest loudSourcingNotificationRequest = new Gson().fromJson(body, LoudSourcingNotificationRequest.class);
        return adminService.recruitAlarmSend(loudSourcingNotificationRequest.getLoudsourcing_no());
    }

}

