package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.NoticeMapper;
import com.restapi.Restfull.API.Server.models.Notice;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDao {
    private SqlSession sqlSession;

    @Autowired
    private SqlSession ROSqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<Notice> getNoticeRefresh(Notice notice) {
        NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
        return noticeMapper.getNoticeRefresh(notice.getRevise_date(), notice.getNotice_no());
    }

    public void insertNotice(Notice notice) {
        NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
        noticeMapper.insertNotice(notice);
    }

    public void updateNotice(Notice notice) {
        NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
        noticeMapper.updateNotice(notice);
    }

    public void deleteNotice(int notice_no) {
        NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
        noticeMapper.deleteNotice(notice_no);
    }

    public List<Notice> getNotice() {
        NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
        return noticeMapper.getNotice();
    }

    public Notice getNoticeByNoticeNo(int notice_no) {
        NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
        return noticeMapper.getNoticeByNoticeNo(notice_no);
    }

    public List<Notice> getNoticeForCDN() {
        NoticeMapper noticeMapper = ROSqlSession.getMapper(NoticeMapper.class);
        return noticeMapper.getNoticeForCDN();
    }
}
