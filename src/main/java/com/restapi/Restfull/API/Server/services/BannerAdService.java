package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.BannerAdDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.BannerAd;
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

import java.util.List;

@Log4j2
@Service
public class BannerAdService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private BannerAdDao bannerAdDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getBanners() {
        try{
            bannerAdDao.setSession(sqlSession);
            Message message = new Message();
            List<BannerAd> bannerAdList = bannerAdDao.getBannerList();

            message.put("banners", bannerAdList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BANNER_LIST, message.getHashMap("GetEntryList()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }
}
