package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.FAQService;
import com.restapi.Restfull.API.Server.services.LoudSourcingService;
import com.restapi.Restfull.API.Server.services.NoticeService;
import com.restapi.Restfull.API.Server.services.NotificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Log4j2
@RestController
public class SettingController {
    @Autowired
    private FAQService faqService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private LoudSourcingService loudSourcingService;

    @Autowired
    private NotificationService notificationService;

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

    @RequestMapping(value = "/api/user/loudsourcing/{user_no}/last_index/{last_index}", method = RequestMethod.GET)
    public ResponseEntity GetMyLoudsourcingList(@PathVariable("user_no") int user_no, @PathVariable("last_index") int last_index) {
        return loudSourcingService.getMyLoudsourcingList(user_no, last_index);
    }

    @RequestMapping(value = "/api/notice/{last_index}", method = RequestMethod.GET)
    public ResponseEntity GetNotice(@PathVariable("last_index") int last_index) {
        return noticeService.getNotice(last_index);
    }

    @RequestMapping(value = "/api/faq/{last_index}", method = RequestMethod.GET)
    public ResponseEntity GetFAQ(@PathVariable("last_index") int last_index) {
        return faqService.getFAQ(last_index);
    }

    @RequestMapping(value = "/api/notification/{user_no}/last_index/{last_index}", method = RequestMethod.GET)
    public ResponseEntity GetNotification(@PathVariable("user_no") int user_no, @PathVariable("last_index") int last_index) {
        return notificationService.getNotification(user_no, last_index);
    }
}
