package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.FAQMapper;
import com.restapi.Restfull.API.Server.models.FAQ;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FAQDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<FAQ> getFAQ(int start_index) {
        FAQMapper faqMapper = sqlSession.getMapper(FAQMapper.class);
        return faqMapper.getFAQ(start_index, start_index + 10);
    }

    public void insertFAQ(FAQ faq){
        FAQMapper faqMapper = sqlSession.getMapper(FAQMapper.class);
        faqMapper.insertFAQ(faq);
    }

    public void deleteFAQ(int faq_no){
        FAQMapper faqMapper = sqlSession.getMapper(FAQMapper.class);
        faqMapper.deleteFAQ(faq_no);
    }

    public void updateFAQ(FAQ faq){
        FAQMapper faqMapper = sqlSession.getMapper(FAQMapper.class);
        faqMapper.updateFAQ(faq);
    }
}
