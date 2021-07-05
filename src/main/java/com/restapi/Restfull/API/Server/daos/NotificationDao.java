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

    public List<Notification> getNotification(int user_no, int start_index) {
        NotificationMapper notificationMapper = sqlSession.getMapper(NotificationMapper.class);
        return notificationMapper.getNotification(user_no, start_index, start_index + 10);
    }
}
