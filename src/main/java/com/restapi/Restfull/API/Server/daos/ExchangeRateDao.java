package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.ExchangeRateMapper;
import com.restapi.Restfull.API.Server.models.ExchangeRate;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ExchangeRateDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertExchangeRate(ExchangeRate exchangeRate){
        ExchangeRateMapper exchangeRateMapper = sqlSession.getMapper(ExchangeRateMapper.class);
        exchangeRateMapper.insertExchangeRate(exchangeRate);
    }

    public String getExchangeRate(String now){
        ExchangeRateMapper exchangeRateMapper = sqlSession.getMapper(ExchangeRateMapper.class);
        return exchangeRateMapper.getExchangeRate(now);
    }

}
