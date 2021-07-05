package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Notice;

import java.util.List;

public interface NoticeMapper {
    List<Notice> getNotice(int start_index, int end_index);

    void insertNotice(Notice notice);

    void updateNotice(Notice notice);

    void deleteNotice(int notice_no);
}
