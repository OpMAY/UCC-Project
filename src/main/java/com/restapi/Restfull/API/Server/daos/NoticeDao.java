package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.NoticeMapper;
import com.restapi.Restfull.API.Server.models.Notice;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<Notice> getNotice(int start_index) {
        NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
        return noticeMapper.getNotice(start_index, start_index + 10);
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
}
