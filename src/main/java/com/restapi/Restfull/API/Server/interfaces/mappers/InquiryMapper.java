package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Inquiry;

import java.util.List;

public interface InquiryMapper {
    void insertInquiry(Inquiry inquiry);

    void deleteInquiry(int inquiry_no);

    void answerInquiry(Inquiry inquiry);

    List<Inquiry> getInquiryListByUserNoRefresh(int user_no, int inquiry_no, String reg_date);

    List<Inquiry> getInquiryListByUserNo(int user_no);

    List<Inquiry> getInquiryListByAnswerStatus(boolean is_answered);

    Inquiry getInquiryByInquiryNo(int inquiry_no);

    List<Inquiry> getInquiryForCDN();

    List<Inquiry> getInquiryAnsweredListByType(String type);

    List<Inquiry> getInquiryNotAnsweredListByType(String type);
}