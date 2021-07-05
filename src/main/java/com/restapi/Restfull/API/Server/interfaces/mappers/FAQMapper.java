package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.FAQ;

import java.util.List;

public interface FAQMapper {
    List<FAQ> getFAQ(int start_index, int end_index);

    void insertFAQ(FAQ faq);

    void deleteFAQ(int faq_no);

    void updateFAQ(FAQ faq);
}
