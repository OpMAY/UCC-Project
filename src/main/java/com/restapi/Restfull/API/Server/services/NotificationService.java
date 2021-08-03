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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class NotificationService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private NotificationDao notificationDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getNotification(int user_no, int last_index) {
        try {
            notificationDao.setSession(sqlSession);
            Message message = new Message();

            if(last_index == 0){
                List<Notification> notificationList = notificationDao.getNotification(user_no);
                message.put("notification", notificationList);
                if(notificationList.size() > 0){
                    message.put("last_index", notificationList.get(notificationList.size() - 1).getNotification_no());
                }
            } else {
                Notification notification = notificationDao.getNotificationByNotificationNo(last_index);
                if(notification == null){
                    return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                }
                List<Notification> notificationList = notificationDao.getNotificationRefresh(user_no, notification);
                message.put("notification", notificationList);
                if(notificationList.size() > 0){
                    message.put("last_index", notificationList.get(notificationList.size() - 1).getNotification_no());
                }
            }

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_NOTIFICATION_LIST, message.getHashMap("GetUserFankok()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

}
