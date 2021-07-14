package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.InquiryMapper;
import com.restapi.Restfull.API.Server.models.Inquiry;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InquiryDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertInquiry(Inquiry inquiry) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        inquiryMapper.insertInquiry(inquiry);
    }

    public void deleteInquiry(int inquiry_no) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        inquiryMapper.deleteInquiry(inquiry_no);
    }

    public void answerInquiry(Inquiry inquiry) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        inquiryMapper.answerInquiry(inquiry);
    }

    public List<Inquiry> getInquiryListByUserNo(int user_no, int start_index) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        return inquiryMapper.getInquiryListByUserNo(user_no, start_index, start_index + 10);
    }

    public List<Inquiry> getInquiryListByAnswerStatus(boolean is_answered) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        return inquiryMapper.getInquiryListByAnswerStatus(is_answered);
    }
}
