package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Notification;

import java.util.List;

public interface NotificationMapper {
    List<Notification> getNotificationRefresh(int user_no, int notification_no, String reg_date);

    void insertNotification(Notification notification);

    void deleteNotification(int notification_no);

    List<Notification> getNotification(int user_no);

    Notification getNotificationByNotificationNo(int notification_no);
}
