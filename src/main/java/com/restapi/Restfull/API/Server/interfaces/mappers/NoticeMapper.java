package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Notice;

import java.util.List;

public interface NoticeMapper {
    List<Notice> getNotice();

    List<Notice> getNoticeRefresh(String revise_date, int notice_no);

    void insertNotice(Notice notice);

    void updateNotice(Notice notice);

    void deleteNotice(int notice_no);

    Notice getNoticeByNoticeNo(int notice_no);

    List<Notice> getNoticeForCDN();
}
