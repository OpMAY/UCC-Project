package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.FAQ;

import java.util.List;

public interface FAQMapper {
    List<FAQ> getFAQ();

    void insertFAQ(FAQ faq);

    void deleteFAQ(int faq_no);

    void updateFAQ(FAQ faq);

    List<FAQ> getFAQRefresh(String reg_date, int faq_no);

    FAQ getFAQByFAQNo(int faq_no);
}
