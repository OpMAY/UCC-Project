package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Notification;

import java.util.List;

public interface NotificationMapper {
    List<Notification> getNotification(int user_no, int start_index, int end_index);

    void insertNotification(Notification notification);

    void deleteNotification(int notification_no);
}
