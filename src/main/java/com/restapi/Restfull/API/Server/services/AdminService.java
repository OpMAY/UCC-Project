package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.PortfolioType;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class AdminService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private InquiryDao inquiryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoudSourcingDao loudSourcingDao;

    @Autowired
    private LoudSourcingApplyDao loudSourcingApplyDao;

    @Autowired
    private LoudSourcingEntryDao loudSourcingEntryDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private SponDao sponDao;

    @Autowired
    private BoardCommentDao boardCommentDao;

    @Autowired
    private PortfolioCommentDao portfolioCommentDao;

    @Autowired
    private EntryCommentDao entryCommentDao;

    private ModelAndView modelAndView;

    @Transactional(propagation = Propagation.REQUIRED)
    public Admin loginAdmin(String id, String password) {
        adminDao.setSession(sqlSession);
        return adminDao.loginAdmin(id, password);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getAdminMain() {
        adminDao.setSession(sqlSession);
        inquiryDao.setSession(sqlSession);
        userDao.setSession(sqlSession);
        loudSourcingDao.setSession(sqlSession);
        modelAndView = new ModelAndView("index");
        List<Inquiry> notAnsweredInquiryList = inquiryDao.getInquiryListByAnswerStatus(false);
        for (Inquiry inquiry : notAnsweredInquiryList) {
            User user = userDao.selectUserByUserNo(inquiry.getUser_no());
            inquiry.setUser_name(user.getName());
        }

        List<User> kakaoUserList = userDao.selectUserBySNS("KAKAO");
        List<User> naverUserList = userDao.selectUserBySNS("NAVER");
        List<User> googleUserList = userDao.selectUserBySNS("GOOGLE");
        List<User> appleUserList = userDao.selectUserBySNS("APPLE");

        List<LoudSourcing> loudSourcingList = loudSourcingDao.getRecentLSAdminMain();

        modelAndView.addObject("InquiryList", notAnsweredInquiryList);
        modelAndView.addObject("KakaoUser", kakaoUserList.size());
        modelAndView.addObject("NaverUser", naverUserList.size());
        modelAndView.addObject("GoogleUser", googleUserList.size());
        modelAndView.addObject("AppleUser", appleUserList.size());
        modelAndView.addObject("LoudsourcingList", loudSourcingList);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getUser() {
        userDao.setSession(sqlSession);
        modelAndView = new ModelAndView("user");

        List<User> userList = userDao.getAllUserList();

        modelAndView.addObject("UserList", userList);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getArtist() {
        userDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        modelAndView = new ModelAndView("artist");
        List<Artist> artistList = artistDao.getAllArtists();

        modelAndView.addObject("artistList", artistList);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getComments(String query) {
        boardCommentDao.setSession(sqlSession);
        portfolioCommentDao.setSession(sqlSession);
        entryCommentDao.setSession(sqlSession);

        int user_no = Integer.parseInt(query);

        modelAndView = new ModelAndView("comments");

        List<AdminComment> adminCommentList = new ArrayList<>();
        List<BoardComment> boardCommentList = boardCommentDao.getCommentListByUserNo(user_no);
        List<PortfolioComment> portfolioCommentList = portfolioCommentDao.getCommentListByUserNo(user_no);
        List<EntryComment> entryCommentList = entryCommentDao.getCommentListByUserNo(user_no);

        for (BoardComment boardComment : boardCommentList) {
            AdminComment adminComment = new AdminComment();
            adminComment.setUser_no(boardComment.getUser_no());
            adminComment.setComment_no(boardComment.getComment_no());
            adminComment.set_private(boardComment.isComment_private());
            adminComment.setType("게시글");
            adminComment.setContent(boardComment.getContent());
            adminComment.setWriter_name(boardComment.getCommenter_name());
            adminComment.setReg_date(Time.MsToSecond(boardComment.getReg_date()));
            adminCommentList.add(adminComment);
        }
        for (PortfolioComment portfolioComment : portfolioCommentList) {
            AdminComment adminComment = new AdminComment();
            adminComment.setComment_no(portfolioComment.getComment_no());
            adminComment.setUser_no(portfolioComment.getUser_no());
            adminComment.set_private(portfolioComment.isComment_private());
            adminComment.setType("포트폴리오");
            adminComment.setContent(portfolioComment.getContent());
            adminComment.setWriter_name(portfolioComment.getCommenter_name());
            adminComment.setReg_date(portfolioComment.getReg_date());
            adminCommentList.add(adminComment);
        }
        for (EntryComment entryComment : entryCommentList) {
            AdminComment adminComment = new AdminComment();
            adminComment.setComment_no(entryComment.getEntry_comment_no());
            adminComment.setUser_no(entryComment.getUser_no());
            adminComment.set_private(entryComment.isComment_private());
            adminComment.setType("크라우드");
            adminComment.setContent(entryComment.getContent());
            adminComment.setWriter_name(entryComment.getCommenter_name());
            adminComment.setReg_date(entryComment.getReg_date());
            adminCommentList.add(adminComment);
        }

        adminCommentList.sort((o1, o2) -> {
            String ds1 = o1.getReg_date();
            String ds2 = o2.getReg_date();
            Date d1 = new Date();
            Date d2 = new Date();
            try {
                d1 = Time.StringToDateTimeFormat(ds1);
                d2 = Time.StringToDateTimeFormat(ds2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (d1.after(d2))
                return -1;
            if (d1.before(d2))
                return 1;
            else
                return 0;
        });

        modelAndView.addObject("commentList", adminCommentList);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteComment(int comment_no, String type) {
        boardCommentDao.setSession(sqlSession);
        portfolioCommentDao.setSession(sqlSession);
        entryCommentDao.setSession(sqlSession);


        int result = 0;
        switch (type) {
            case "게시글":
                boardCommentDao.deleteComment(comment_no);
                result = 1;
                break;
            case "포트폴리오":
                portfolioCommentDao.deleteComment(comment_no);
                result = 1;
                break;
            case "크라우드":
                entryCommentDao.deleteComment(comment_no);
                result = 1;
                break;
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int setCommentPrivate(int comment_no, String type) {
        boardCommentDao.setSession(sqlSession);
        portfolioCommentDao.setSession(sqlSession);
        entryCommentDao.setSession(sqlSession);

        int result = 0;

        switch (type) {
            case "게시글":
                BoardComment boardComment = boardCommentDao.getCommentByCommentNo(comment_no);
                if (!boardComment.isComment_private()) {
                    boardComment.setComment_private(true);
                    result = 1;
                } else {
                    boardComment.setComment_private(false);
                    result = 2;
                }
                boardCommentDao.updateComment(boardComment);
                break;
            case "포트폴리오":
                PortfolioComment portfolioComment = portfolioCommentDao.getCommentByCommentNo(comment_no);
                if (!portfolioComment.isComment_private()) {
                    portfolioComment.setComment_private(true);
                    result = 1;
                } else {
                    portfolioComment.setComment_private(false);
                    result = 2;
                }
                portfolioCommentDao.updateComment(portfolioComment);
                break;
            case "크라우드":
                EntryComment entryComment = entryCommentDao.getEntryCommentByCommentNo(comment_no);
                if (!entryComment.isComment_private()) {
                    entryComment.setComment_private(true);
                    result = 1;
                } else {
                    entryComment.setComment_private(false);
                    result = 2;
                }
                entryCommentDao.updateComment(entryComment);
                break;
        }

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getUserDetail(String query) {
        userDao.setSession(sqlSession);
        modelAndView = new ModelAndView("user_detail");
        int user_no = Integer.parseInt(query);
        User user = userDao.selectUserByUserNo(user_no);

        modelAndView.addObject("User", user);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getArtistDetail(String query) {
        userDao.setSession(sqlSession);
        artistDao.setSession(sqlSession);
        modelAndView = new ModelAndView("artist_detail");

        int artist_no = Integer.parseInt(query);
        Artist artist = artistDao.getArtistByArtistNo(artist_no);
        User user = userDao.selectUserByUserNo(artist.getUser_no());

        if (artist.getHashtag() != null) {
            ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(artist.getHashtag().split(", ")));
            ArrayList<String> modified_HashtagList = new ArrayList<>();
            for (String str : hashtagList) {
                modified_HashtagList.add("#" + str);
            }
            artist.setHashtag_list(modified_HashtagList);
            log.info(hashtagList);
        }

        modelAndView.addObject("User", user);
        modelAndView.addObject("Artist", artist);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getPortfolioList(String query, String type) {
        artistDao.setSession(sqlSession);
        portfolioDao.setSession(sqlSession);
        modelAndView = new ModelAndView("portfolio_list");

        int artist_no = Integer.parseInt(query);

        List<Portfolio> res = new ArrayList<>();

        switch (type) {
            case "all":
                List<Portfolio> portfolioList = portfolioDao.getPortfolioListByArtistNo(artist_no);
                for (Portfolio portfolio : portfolioList) {
                    portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                    portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                    res.add(portfolio);
                }
                break;
            case "vod":
                List<Portfolio> vodPortfolioList = portfolioDao.getPortfolioByTypeAdmin(artist_no, "vod");
                for (Portfolio portfolio : vodPortfolioList) {
                    portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                    portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                    res.add(portfolio);
                }
                break;
            case "image":
                List<Portfolio> imgPortfolioList = portfolioDao.getPortfolioByTypeAdmin(artist_no, "image");
                for (Portfolio portfolio : imgPortfolioList) {
                    portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                    portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                    res.add(portfolio);
                }
                break;
            case "text":
                List<Portfolio> textPortfolioList = portfolioDao.getPortfolioByTypeAdmin(artist_no, "text");
                for (Portfolio portfolio : textPortfolioList) {
                    portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                    portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                    res.add(portfolio);
                }
                break;
            case "file":
                List<Portfolio> filePortfolioList = portfolioDao.getPortfolioByTypeAdmin(artist_no, "file");
                for (Portfolio portfolio : filePortfolioList) {
                    portfolio.setReg_date(Time.MsToSecond(portfolio.getReg_date()));
                    portfolio.setRevise_date(Time.MsToSecond(portfolio.getRevise_date()));
                    res.add(portfolio);
                }
                break;
            default:
                throw new BusinessException(new Exception("Wrong Portfolio Type"));
        }

        modelAndView.addObject("portfolioList", res);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getBoardList(String query) {
        boardDao.setSession(sqlSession);
        sponDao.setSession(sqlSession);
        modelAndView = new ModelAndView("board_list");
        int artist_no = Integer.parseInt(query);

        List<Board> boardList = boardDao.getBoardListByArtistNo(artist_no);
        for (Board board : boardList) {
            board.setReg_date(Time.MsToSecond(board.getReg_date()));
            board.setRevise_date(Time.MsToSecond(board.getRevise_date()));

            if (sponDao.getSponListByBoardNo(board.getBoard_no()) != null && sponDao.getSponListByBoardNo(board.getBoard_no()).size() > 0) {
                List<Spon> sponList = sponDao.getSponListByBoardNo(board.getBoard_no());
                for (Spon spon : sponList) {
                    board.setSpon_amount(board.getSpon_amount() + spon.getPrice());
                }
            }
        }

        modelAndView.addObject("boardList", boardList);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getPortfolioDetail(String query) {
        artistDao.setSession(sqlSession);
        portfolioDao.setSession(sqlSession);
        modelAndView = new ModelAndView("portfolio_detail");

        int portfolio_no = Integer.parseInt(query);
        Portfolio portfolio = portfolioDao.getPortfolioByPortfolioNo(portfolio_no);
        if(portfolio.getType().equals(PortfolioType.FILE)){
            String jsonString = portfolio.getFile();
            Gson gson = new Gson();
            FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
            ArrayList<Upload> uploads = new ArrayList<>();
            for (FileJson json : fileJson) {
                uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
            }
            portfolio.setFile_list(uploads);
        } else if (portfolio.getType().equals(PortfolioType.IMAGE)) {
            if (portfolio.getFile() != null) {
                String images = portfolio.getFile().replace("[", "");
                images = images.replace("]", "");
                ArrayList<String> filelist = new ArrayList<>(Arrays.asList(images.split(", ")));
                portfolio.setImage_list(filelist);
                log.info(filelist);
            }
        }


        modelAndView.addObject("portfolio", portfolio);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deletePortfolio(int portfolio_no) {
        try {
            portfolioDao.setSession(sqlSession);
            portfolioDao.deletePortfolio(portfolio_no);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getBoardDetail(String query) {
        boardDao.setSession(sqlSession);
        sponDao.setSession(sqlSession);
        modelAndView = new ModelAndView("board_detail");
        int board_no = Integer.parseInt(query);

        Board board = boardDao.getBoardByBoardNo(board_no);
        List<Spon> sponList = sponDao.getSponListByBoardNo(board_no);
        for(Spon spon : sponList){
            board.setSpon_amount(board.getSpon_amount() + spon.getPrice());
        }

        modelAndView.addObject("board", board);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteBoard(int board_no) {
        try{
            log.info(board_no);
            boardDao.setSession(sqlSession);
            boardDao.deleteBoard(board_no);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingRecruitmentPage() {
        loudSourcingDao.setSession(sqlSession);
        loudSourcingApplyDao.setSession(sqlSession);
        modelAndView = new ModelAndView("loudsourcing_recruitment_list");

        List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusAdmin("recruitment");

        for(LoudSourcing loudSourcing : loudSourcingList){
            int applied_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no()).size();
            loudSourcing.setApplied_artist_num(applied_num);
        }

        modelAndView.addObject("loudsourcingList", loudSourcingList);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingProcessPage() {
        loudSourcingDao.setSession(sqlSession);
        loudSourcingApplyDao.setSession(sqlSession);

        modelAndView = new ModelAndView("loudsourcing_process_list");

        List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusAdmin("process");

        for(LoudSourcing loudSourcing : loudSourcingList){
            int applied_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no()).size();
            loudSourcing.setApplied_artist_num(applied_num);
        }

        modelAndView.addObject("loudsourcingList", loudSourcingList);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingJudgePage() throws ParseException {
        loudSourcingDao.setSession(sqlSession);
        loudSourcingApplyDao.setSession(sqlSession);

        modelAndView = new ModelAndView("loudsourcing_judge_list");

        List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusAdmin("judge");

        for(LoudSourcing loudSourcing : loudSourcingList){
            List<LoudSourcingApply> loudSourcingApplyList = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no());
            for(LoudSourcingApply loudSourcingApply : loudSourcingApplyList){
                if(loudSourcingApply.is_pre_selected()){
                    loudSourcing.setSelected_artist_num(loudSourcing.getSelected_artist_num() + 1);
                }
            }
            loudSourcing.setJudge_date(Time.DatePlusOneDay(loudSourcing.getProcess_end_date()));
        }

        modelAndView.addObject("loudsourcingList", loudSourcingList);

        return modelAndView;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ModelAndView getLoudSourcingEndPage() {
        loudSourcingDao.setSession(sqlSession);
        loudSourcingApplyDao.setSession(sqlSession);

        modelAndView = new ModelAndView("loudsourcing_end_list");

        List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudSourcingListByStatusAdmin("end");

        for(LoudSourcing loudSourcing : loudSourcingList){
            int applied_num = loudSourcingApplyDao.getLoudSourcingApplyListByLoudSourcingNo(loudSourcing.getLoudsourcing_no()).size();
            loudSourcing.setApplied_artist_num(applied_num);
        }

        modelAndView.addObject("loudsourcingList", loudSourcingList);

        return modelAndView;
    }
}
