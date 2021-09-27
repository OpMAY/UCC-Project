package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.daos.*;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.utility.URLConverter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@Service
public class MainService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private LoudSourcingEntryDao loudSourcingEntryDao;

    @Autowired
    private LoudSourcingDao loudSourcingDao;

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private RequestChangeDao requestChangeDao;

    @Autowired
    private FAQDao faqDao;

    @Autowired
    private BannerAdDao bannerAdDao;

    @Autowired
    private InquiryDao inquiryDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity GetMain() {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);

            /**
             * 1. New Artist
             * 2. Popular Artist
             * 3. Random Portfolio List
             * 4. Recent Board List
             * **/
            // New Artist List - total 15
            List<Artist> newArtistList = artistDao.getNewArtistList();


            // Popular Artist - total 15
            List<Artist> popularArtistList = artistDao.getArtistListByPopular();
            for (Artist artist : popularArtistList) {
                String hashtag = artist.getHashtag();
                log.info(hashtag);
                if (hashtag != null) {
                    ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(hashtag.split(", ")));
                    artist.setHashtag_list(hashtagList);
                    log.info(hashtagList);
                }
            }

            // Random Portfolio List
            List<Portfolio> randomPortfolioList = portfolioDao.getPortfolioListByRandom();

            // Recent Board List
            List<Board> recentBoardList = boardDao.getRecentBoardList();

            message.put("new_artists", newArtistList);
            message.put("popular_artists", popularArtistList);
            message.put("random_portfolios", randomPortfolioList);
            message.put("recent_boards", recentBoardList);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.MAIN_PAGE_LOADED, message.getHashMap("GetMain()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * 주석 생성 날짜 - 2021-08-3, 화, 10:30
     * 코드 설명 : DB 에서 삭제된 파일 링크들 AWS 내에서 정리하기
     * 특이 사항 : 스케쥴링
     * 파일 업로드 여부 : X
     **/
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public void deleteCDNFiles() {
        try {
            /** SQL SESSION SET **/
            List<Artist> artistList = artistDao.getAllArtistForCDN();
            List<BannerAd> bannerAdList = bannerAdDao.getBannerForCDN();
            List<Board> boardList = boardDao.getBoardForCDN();
            List<FAQ> faqList = faqDao.getFAQForCDN();
            List<Inquiry> inquiryList = inquiryDao.getInquiryForCDN();
            List<LoudSourcing> loudSourcingList = loudSourcingDao.getLoudsourcingForCDN();
            List<LoudSourcingEntry> loudSourcingEntryList = loudSourcingEntryDao.getEntryForCDN();
            List<Notice> noticeList = noticeDao.getNoticeForCDN();
            List<Portfolio> portfolioList = portfolioDao.getPortfolioForCDN();
            List<User> userList = userDao.getUserForCDN();

            /** Setting OBJECTS **/
            CDNService cdnService = new CDNService();
            Gson gson = new Gson();
            URLConverter urlConverter = new URLConverter();
            List<String> allFileList = cdnService.finds();

            /** Prevent Delete Folder **/
            for(Iterator<String> iterator = allFileList.iterator(); iterator.hasNext();){
                String url = iterator.next();
                if(url != null && url.substring(url.lastIndexOf("/") + 1).equals("")){
                    log.info("Excepting URL : " + url);
                    iterator.remove();
                }
            }
            allFileList.remove("api/images/default/fan_main_img_basic.png");
            allFileList.remove("api/images/default/profile_img_basic.png");

            /**------------------------------------------------------------------   DB FILE LINK COLLECT  ------------------------------------------------------------------ **/
            log.info("CDN FILE ARRANGE START");
            /** Artist **/
            log.info("Removing Unnecessary files : Artist");

            for (Artist artist : artistList) {
                String profile_img_link = artist.getArtist_profile_img();
                String main_img_link = artist.getMain_img();
                if (profile_img_link != null) {
                    allFileList.remove(urlConverter.DecodeFileName(getPath(profile_img_link)));
                }
                if (main_img_link != null)
                    allFileList.remove(urlConverter.DecodeFileName(getPath(main_img_link)));
            }
            artistList.clear();

            /** Banner **/
            log.info("Removing Unnecessary files : Banner");
            for (BannerAd bannerAd : bannerAdList) {
                String img_link = bannerAd.getImg();
                if (img_link != null)
                    allFileList.remove(urlConverter.DecodeFileName(getPath(img_link)));
            }
            bannerAdList.clear();

            /** Board **/
            log.info("Removing Unnecessary files : Board");

            for (Board board : boardList) {
                String thumbnail_link = board.getThumbnail();
                if (thumbnail_link != null)
                    allFileList.remove(urlConverter.DecodeFileName(getPath(thumbnail_link)));
                List<String> html_img_links = getBoardFiles(board.getContent());
                if (html_img_links != null) {
                    for (String url : html_img_links) {
                        if (url != null)
                            allFileList.remove(urlConverter.DecodeFileName(getPath(url)));
                    }
                    html_img_links.clear();
                }
            }
            boardList.clear();

            /** FAQ **/
            log.info("Removing Unnecessary files : FAQ");
            for (FAQ faq : faqList) {
                if (faq.getImg() != null) {
                    ArrayList<String> imgList = new ArrayList<>(Arrays.asList(faq.getImg().split(", ")));
                    faq.setImgList(imgList);
                    for (String url : imgList) {
                        allFileList.remove(urlConverter.DecodeFileName(getPath(url)));
                    }
                    imgList.clear();
                }
            }
            faqList.clear();

            /** Inquiry **/
            log.info("Removing Unnecessary files : Inquiry");
            for (Inquiry inquiry : inquiryList) {
                if (inquiry.getFile() != null && !inquiry.getFile().isEmpty()) {
                    String jsonString = inquiry.getFile();
                    FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                    if(fileJson != null && fileJson.length > 0) {
                        for (FileJson fileJson1 : fileJson) {
                            String fileUrl = fileJson1.getUrl();
                            if (fileUrl != null)
                                allFileList.remove(urlConverter.DecodeFileName(getPath(fileUrl)));
                        }
                    }
                }
            }
            inquiryList.clear();

            /** LoudSourcing **/
            log.info("Removing Unnecessary files : LoudSourcing");

            for (LoudSourcing loudSourcing : loudSourcingList) {
                String img_link = loudSourcing.getImg();
                if (img_link != null)
                    allFileList.remove(urlConverter.DecodeFileName(getPath(img_link)));
                String jsonString = loudSourcing.getFiles();
                FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                if(fileJson != null && fileJson.length > 0) {
                    for (FileJson fileJson1 : fileJson) {
                        String fileUrl = fileJson1.getUrl();
                        if (fileUrl != null)
                            allFileList.remove(urlConverter.DecodeFileName(getPath(fileUrl)));
                    }
                }
            }
            loudSourcingList.clear();

            /** Entry **/
            log.info("Removing Unnecessary files : Entry");

            for (LoudSourcingEntry loudSourcingEntry : loudSourcingEntryList) {
                String vod_link = loudSourcingEntry.getFile();
                String thumbnail_link = loudSourcingEntry.getThumbnail();
                if (vod_link != null)
                    allFileList.remove(urlConverter.DecodeFileName(getPath(vod_link)));
                if (thumbnail_link != null)
                    allFileList.remove(urlConverter.DecodeFileName(getPath(thumbnail_link)));
            }
            loudSourcingEntryList.clear();

            /** Notice **/
            log.info("Removing Unnecessary files : Notice");

            for (Notice notice : noticeList) {
                String img = notice.getImg();
                if (img != null) {
                    ArrayList<String> imgList = new ArrayList<>(Arrays.asList(img.split(", ")));
                    for (String url : imgList) {
                        if (url != null)
                            allFileList.remove(urlConverter.DecodeFileName(getPath(url)));
                    }
                    imgList.clear();
                }
            }
            noticeList.clear();

            /** Portfolio **/
            log.info("Removing Unnecessary files : Portfolio");

            for (Portfolio portfolio : portfolioList) {
                String file = portfolio.getFile();
                String thumbnail_link = portfolio.getThumbnail();
                switch (portfolio.getType()) {
                    case PortfolioType.VOD:
                        if (file != null)
                            allFileList.remove(urlConverter.DecodeFileName(getPath(file)));
                        if (thumbnail_link != null)
                            allFileList.remove(urlConverter.DecodeFileName(getPath(thumbnail_link)));
                        break;
                    case PortfolioType.FILE:
                        FileJson[] fileJson = gson.fromJson(file, FileJson[].class);
                        if(fileJson != null && fileJson.length > 0) {
                            for (FileJson fileJson1 : fileJson) {
                                String url = fileJson1.getUrl();
                                if (url != null) {
                                    allFileList.remove(urlConverter.DecodeFileName(getPath(url)));
                                }
                            }
                        }
                        break;
                    case PortfolioType.IMAGE:
                        if (thumbnail_link != null)
                            allFileList.remove(urlConverter.DecodeFileName(getPath(thumbnail_link)));
                        if (file != null) {
                            ArrayList<String> img_links = new ArrayList<>(Arrays.asList(file.split(", ")));
                            for (String url : img_links) {
                                if (url != null)
                                    allFileList.remove(urlConverter.DecodeFileName(getPath(url)));
                            }
                            img_links.clear();
                        }
                        break;
                }
            }
            portfolioList.clear();

            /** User **/
            log.info("Removing Unnecessary files : User");

            for (User user : userList) {
                String profile_img = user.getProfile_img();
                if (profile_img != null)
                    allFileList.remove(urlConverter.DecodeFileName(getPath(profile_img)));
            }
            userList.clear();


            /** DELETE FILES IN CDN **/
            log.info("Deleting File in CDN...");
            log.info("Deleting " + allFileList.size() + " files..");
            if(!allFileList.isEmpty()) {
                for(String url : allFileList){
                    log.info("Deleting file : " + url);
                    if(url != null)
                        cdnService.delete(url);
                }
            }
            log.info(allFileList.size() + " files Deleted Successfully");
            allFileList.clear();

        } catch (Exception e) {
            log.info("Error in Deleting CDN FILES : " + e);
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }



    private List<String> getBoardFiles(String content) {
        try {
            List<String> url = new ArrayList<>();
            Pattern p = Pattern.compile("src=\"(.*?)\"");
            Matcher m = p.matcher(content);
            while (m.find()) {
                log.info("Board Image Url : " + m.group(1));
                url.add(m.group(1));
            }
            if (url.size() > 0)
                return url;
            else
                return null;
        } catch (Exception e) {
            log.info("Error on Getting String on Board");
            e.printStackTrace();
            return null;
        }
    }

    private String getPath(String url){
        url = url.substring(url.indexOf("com/") + 4);
        return url;
    }

}
