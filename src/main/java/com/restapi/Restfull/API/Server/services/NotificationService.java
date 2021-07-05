package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.NotificationDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Notification;
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

import java.util.List;

@Log4j2
@Service
public class NotificationService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private NotificationDao notificationDao;

    public ResponseEntity getNotification(int user_no, int start_index) {
        try{
            notificationDao.setSession(sqlSession);
            Message message = new Message();

            List<Notification> notificationList = notificationDao.getNotification(user_no, start_index);
            message.put("notification", notificationList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_NOTIFICATION_LIST, message.getHashMap("GetUserFankok()")), HttpStatus.OK);
        }catch (JSONException e){
            throw new BusinessException(e);
        }
    }
}
