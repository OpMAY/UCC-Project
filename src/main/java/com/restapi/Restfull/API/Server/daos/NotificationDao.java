package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.NotificationMapper;
import com.restapi.Restfull.API.Server.models.Notification;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<Notification> getNotification(int user_no) {
        NotificationMapper notificationMapper = sqlSession.getMapper(NotificationMapper.class);
        return notificationMapper.getNotification(user_no);
    }

    public List<Notification> getNotificationRefresh(int user_no, Notification notification){
        NotificationMapper notificationMapper = sqlSession.getMapper(NotificationMapper.class);
        return notificationMapper.getNotificationRefresh(user_no, notification.getNotification_no(), notification.getReg_date());
    }

    public void insertNotification(Notification notification){
        NotificationMapper notificationMapper = sqlSession.getMapper(NotificationMapper.class);
        notificationMapper.insertNotification(notification);
    }

    public Notification getNotificationByNotificationNo(int notification_no) {
        NotificationMapper notificationMapper = sqlSession.getMapper(NotificationMapper.class);
        return notificationMapper.getNotificationByNotificationNo(notification_no);
    }
}
