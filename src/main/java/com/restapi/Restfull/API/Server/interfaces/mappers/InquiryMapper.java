package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Inquiry;

import java.util.List;

public interface InquiryMapper {
    void insertInquiry(Inquiry inquiry);

    void deleteInquiry(int inquiry_no);

    void answerInquiry(Inquiry inquiry);

    List<Inquiry> getInquiryListByUserNo(int user_no, int start_index, int end_index);

    List<Inquiry> getInquiryListByAnswerStatus(boolean is_answered);
}