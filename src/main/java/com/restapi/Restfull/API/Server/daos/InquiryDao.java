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

    public List<Inquiry> getInquiryListByUserNoRefresh(int user_no, int inquiry_no, String reg_date) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        return inquiryMapper.getInquiryListByUserNoRefresh(user_no, inquiry_no, reg_date);
    }

    public List<Inquiry> getInquiryListByAnswerStatus(boolean is_answered) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        return inquiryMapper.getInquiryListByAnswerStatus(is_answered);
    }

    public List<Inquiry> getInquiryListByUserNo(int user_no) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        return inquiryMapper.getInquiryListByUserNo(user_no);
    }

    public Inquiry getInquiryByInquiryNo(int inquiry_no) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        return inquiryMapper.getInquiryByInquiryNo(inquiry_no);
    }

    public List<Inquiry> getInquiryForCDN() {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        return inquiryMapper.getInquiryForCDN();
    }

    public List<Inquiry> getInquiryListByAnswerStatusAndType(boolean answer_state, String type) {
        InquiryMapper inquiryMapper = sqlSession.getMapper(InquiryMapper.class);
        if (answer_state) {
            return inquiryMapper.getInquiryAnsweredListByType(type);
        } else {
            return inquiryMapper.getInquiryNotAnsweredListByType(type);
        }
    }
}
