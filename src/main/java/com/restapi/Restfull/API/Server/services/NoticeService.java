package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.NoticeDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Notice;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
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
import java.util.List;

@Log4j2
@Service
public class NoticeService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private NoticeDao noticeDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getNotice(int start_index) {
        try {
            noticeDao.setSession(sqlSession);
            Message message = new Message();
            List<Notice> noticeList = noticeDao.getNotice(start_index);
            for (Notice notice : noticeList) {
                String img = notice.getImg();
                log.info(img);
                if (img != null) {
                    ArrayList<String> imgList = new ArrayList<>(Arrays.asList(img.split(", ")));
                    notice.setImgList(imgList);
                    log.info(imgList);
                }
            }

            message.put("notice", noticeList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_NOTICE_LIST, message.getHashMap("GetNotice()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
