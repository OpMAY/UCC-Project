package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.restapi.Restfull.API.Server.daos.ExchangeRateDao;
import com.restapi.Restfull.API.Server.models.CurrencyApiResponse;
import com.restapi.Restfull.API.Server.models.ExchangeRate;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class CurrencyService {
    @Autowired
    private ExchangeRateDao exchangeRateDao;

    @Autowired
    private SqlSession sqlSession;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCurrencyInfo(String now) {
        try {
            exchangeRateDao.setSession(sqlSession);
            HttpResponse res = getCurrencyRequest(now);
            String resBody = IOUtils.toString(res.getEntity().getContent(), StandardCharsets.UTF_8);
            log.info(resBody);
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setJson(resBody);
            exchangeRate.setReg_date(Time.TimeFormatHMS());
            exchangeRateDao.insertExchangeRate(exchangeRate);
        } catch (Exception e){
            e.printStackTrace();
        }
//        List<CurrencyApiResponse> currencyList = new Gson().fromJson(resBody, new TypeToken<ArrayList<CurrencyApiResponse>>(){}.getType());
    }

    @Transactional(readOnly = true)
    public String getCurrencyCalc(String currency){
        exchangeRateDao.setSession(sqlSession);
        String json = exchangeRateDao.getExchangeRate();
        List<CurrencyApiResponse> currencyList = new Gson().fromJson(json, new TypeToken<ArrayList<CurrencyApiResponse>>(){}.getType());
        for(CurrencyApiResponse response : currencyList){
            if(response.getCur_unit().equals(currency)) {
                return response.getCur_unit();
            }
        }
        return "";
    }

    private HttpGet getGet(String url) {
        HttpGet get = new HttpGet(url);
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Accept-Charset", "utf-8");
        return get;
    }

    private HttpResponse getCurrencyRequest(String now) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        String AUTH_KEY = "NZ0c7lI4EgWyJNgkbcfLoOkzPkqjudlq";
        String SEARCH_TYPE = "AP01";
        String REQUEST_URL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON";
        HttpGet get = getGet(REQUEST_URL + "?authkey=" + AUTH_KEY + "&searchdate=" + now + "&data=" + SEARCH_TYPE);
        return client.execute(get);
    }
}
