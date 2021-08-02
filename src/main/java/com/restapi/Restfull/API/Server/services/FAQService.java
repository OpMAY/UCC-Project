package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.FAQDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.FAQ;
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
public class FAQService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private FAQDao faqDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getFAQ(int last_index) {
        try {
            Message message = new Message();
            faqDao.setSession(sqlSession);
            if(last_index == 0){
                List<FAQ> faqList = faqDao.getFAQ();
                for (FAQ faq : faqList) {
                    String img = faq.getImg();
                    log.info(img);
                    if (img != null) {
                        ArrayList<String> imgList = new ArrayList<>(Arrays.asList(img.split(", ")));
                        faq.setImgList(imgList);
                        log.info(imgList);
                    }
                }
                message.put("faq", faqList);
                if(faqList.size() > 0)
                    message.put("last_index", faqList.get(faqList.size() - 1).getFaq_no());
            } else {
                FAQ faq1 = faqDao.getFAQByFAQNo(last_index);
                if(faq1 == null){
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                }
                List<FAQ> faqList = faqDao.getFAQRefresh(faq1);
                for (FAQ faq : faqList) {
                    String img = faq.getImg();
                    log.info(img);
                    if (img != null) {
                        ArrayList<String> imgList = new ArrayList<>(Arrays.asList(img.split(", ")));
                        faq.setImgList(imgList);
                        log.info(imgList);
                    }
                }
                message.put("faq", faqList);
                if(faqList.size() > 0)
                    message.put("last_index", faqList.get(faqList.size() - 1).getFaq_no());
            }


            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_FAQ_LIST, message.getHashMap("GetFAQ()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
