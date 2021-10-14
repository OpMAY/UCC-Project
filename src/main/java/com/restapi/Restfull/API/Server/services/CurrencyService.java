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
            if(exchangeRateDao.getExchangeRate(Time.SetCurrencyUpdateTime(now)) == null) {
                HttpResponse res = getCurrencyRequest(now);
                log.info(res);
                String resBody = IOUtils.toString(res.getEntity().getContent(), StandardCharsets.UTF_8);
                log.info(resBody);
                if (resBody.replace("[", "").replace("]", "").equals("")) {
                    // 휴일 및 공휴일엔 영업 X, 그 전날 데이터 그대로 반영
                    resBody = exchangeRateDao.getExchangeRate(Time.DateMinusOneDay(now));
                }
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setJson(resBody);
                exchangeRate.setReg_date(Time.SetCurrencyUpdateTime(now));
                exchangeRateDao.insertExchangeRate(exchangeRate);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
//        List<CurrencyApiResponse> currencyList = new Gson().fromJson(resBody, new TypeToken<ArrayList<CurrencyApiResponse>>(){}.getType());
    }

    public String getCurrencyRate(String spon_date, String currency){
        List<CurrencyApiResponse> currencyList = getCurrencyRate(spon_date);
        String result = "";
        for(CurrencyApiResponse response : currencyList){
            if(currency.equals(response.getCur_unit())){
                if(currency.equals("JPY(100)") || currency.equals("IDR(100)")){
                    result = result + (Double.parseDouble(response.getDeal_bas_r().replaceAll("[^0-9.]", "")) / 100);
                } else {
                    result = result + response.getDeal_bas_r();
                }
                break;
            }
        }
        return result;
    }


    public long calculateCurrency(String money, String currency, String spon_date) throws Exception {
        List<CurrencyApiResponse> currencyList = getCurrencyRate(spon_date);
        String clearMoneyValue = money.replaceAll("[^0-9.]", "");
        double price = Double.parseDouble(clearMoneyValue);
        long after = 0;
        for(CurrencyApiResponse response : currencyList){
            if(response.getCur_unit().equals(currency)){
                double calcRate = Double.parseDouble(response.getDeal_bas_r().replaceAll("[^0-9.]", ""));
                double result;
                if(currency.equals("JPY(100)") || currency.equals("IDR(100)")){
                    result = (calcRate * price) / 100;
                } else {
                    result = calcRate * price;
                }
                after = Math.round(result);
                break;
            }
        }
        return after;
    }

    private List<CurrencyApiResponse> getCurrencyRate(String spon_date){
        exchangeRateDao.setSession(sqlSession);
        String json = exchangeRateDao.getExchangeRate(spon_date);
//        String jsonString = "[{\"ttb\": \"319.94\", \"tts\": \"326.41\", \"bkpr\": \"323\", \"cur_nm\": \"아랍에미리트 디르함\", \"result\": 1, \"cur_unit\": \"AED\", \"kftc_bkpr\": \"323\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"323.18\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"323.18\"}, {\"ttb\": \"856.74\", \"tts\": \"874.05\", \"bkpr\": \"865\", \"cur_nm\": \"호주 달러\", \"result\": 1, \"cur_unit\": \"AUD\", \"kftc_bkpr\": \"865\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"865.4\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"865.4\"}, {\"ttb\": \"3,117.32\", \"tts\": \"3,180.29\", \"bkpr\": \"3,148\", \"cur_nm\": \"바레인 디나르\", \"result\": 1, \"cur_unit\": \"BHD\", \"kftc_bkpr\": \"3,148\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"3,148.81\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"3,148.81\"}, {\"ttb\": \"865.95\", \"tts\": \"883.44\", \"bkpr\": \"874\", \"cur_nm\": \"브루나이 달러\", \"result\": 1, \"cur_unit\": \"BND\", \"kftc_bkpr\": \"874\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"874.7\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"874.7\"}, {\"ttb\": \"934.09\", \"tts\": \"952.96\", \"bkpr\": \"943\", \"cur_nm\": \"캐나다 달러\", \"result\": 1, \"cur_unit\": \"CAD\", \"kftc_bkpr\": \"943\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"943.53\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"943.53\"}, {\"ttb\": \"1,266.07\", \"tts\": \"1,291.64\", \"bkpr\": \"1,278\", \"cur_nm\": \"스위스 프랑\", \"result\": 1, \"cur_unit\": \"CHF\", \"kftc_bkpr\": \"1,278\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"1,278.86\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"1,278.86\"}, {\"ttb\": \"182.15\", \"tts\": \"185.82\", \"bkpr\": \"183\", \"cur_nm\": \"위안화\", \"result\": 1, \"cur_unit\": \"CNH\", \"kftc_bkpr\": \"183\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"183.99\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"183.99\"}, {\"ttb\": \"183.24\", \"tts\": \"186.95\", \"bkpr\": \"185\", \"cur_nm\": \"덴마아크 크로네\", \"result\": 1, \"cur_unit\": \"DKK\", \"kftc_bkpr\": \"185\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"185.1\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"185.1\"}, {\"ttb\": \"1,362.97\", \"tts\": \"1,390.5\", \"bkpr\": \"1,376\", \"cur_nm\": \"유로\", \"result\": 1, \"cur_unit\": \"EUR\", \"kftc_bkpr\": \"1,376\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"1,376.74\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"1,376.74\"}, {\"ttb\": \"1,601.72\", \"tts\": \"1,634.07\", \"bkpr\": \"1,617\", \"cur_nm\": \"영국 파운드\", \"result\": 1, \"cur_unit\": \"GBP\", \"kftc_bkpr\": \"1,617\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"1,617.9\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"1,617.9\"}, {\"ttb\": \"150.95\", \"tts\": \"154\", \"bkpr\": \"152\", \"cur_nm\": \"홍콩 달러\", \"result\": 1, \"cur_unit\": \"HKD\", \"kftc_bkpr\": \"152\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"152.48\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"152.48\"}, {\"ttb\": \"8.24\", \"tts\": \"8.41\", \"bkpr\": \"8\", \"cur_nm\": \"인도네시아 루피아\", \"result\": 1, \"cur_unit\": \"IDR(100)\", \"kftc_bkpr\": \"8\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"8.33\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"8.33\"}, {\"ttb\": \"1,054.16\", \"tts\": \"1,075.45\", \"bkpr\": \"1,064\", \"cur_nm\": \"일본 옌\", \"result\": 1, \"cur_unit\": \"JPY(100)\", \"kftc_bkpr\": \"1,064\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"1,064.81\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"1,064.81\"}, {\"ttb\": \"0\", \"tts\": \"0\", \"bkpr\": \"1\", \"cur_nm\": \"한국 원\", \"result\": 1, \"cur_unit\": \"KRW\", \"kftc_bkpr\": \"1\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"1\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"1\"}, {\"ttb\": \"3,896.64\", \"tts\": \"3,975.37\", \"bkpr\": \"3,936\", \"cur_nm\": \"쿠웨이트 디나르\", \"result\": 1, \"cur_unit\": \"KWD\", \"kftc_bkpr\": \"3,936\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"3,936.01\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"3,936.01\"}, {\"ttb\": \"281.23\", \"tts\": \"286.92\", \"bkpr\": \"284\", \"cur_nm\": \"말레이지아 링기트\", \"result\": 1, \"cur_unit\": \"MYR\", \"kftc_bkpr\": \"284\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"284.08\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"284.08\"}, {\"ttb\": \"137.8\", \"tts\": \"140.59\", \"bkpr\": \"139\", \"cur_nm\": \"노르웨이 크로네\", \"result\": 1, \"cur_unit\": \"NOK\", \"kftc_bkpr\": \"139\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"139.2\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"139.2\"}, {\"ttb\": \"818.07\", \"tts\": \"834.6\", \"bkpr\": \"826\", \"cur_nm\": \"뉴질랜드 달러\", \"result\": 1, \"cur_unit\": \"NZD\", \"kftc_bkpr\": \"826\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"826.34\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"826.34\"}, {\"ttb\": \"313.36\", \"tts\": \"319.69\", \"bkpr\": \"316\", \"cur_nm\": \"사우디 리얄\", \"result\": 1, \"cur_unit\": \"SAR\", \"kftc_bkpr\": \"316\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"316.53\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"316.53\"}, {\"ttb\": \"134.47\", \"tts\": \"137.18\", \"bkpr\": \"135\", \"cur_nm\": \"스웨덴 크로나\", \"result\": 1, \"cur_unit\": \"SEK\", \"kftc_bkpr\": \"135\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"135.83\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"135.83\"}, {\"ttb\": \"865.95\", \"tts\": \"883.44\", \"bkpr\": \"874\", \"cur_nm\": \"싱가포르 달러\", \"result\": 1, \"cur_unit\": \"SGD\", \"kftc_bkpr\": \"874\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"874.7\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"874.7\"}, {\"ttb\": \"34.72\", \"tts\": \"35.43\", \"bkpr\": \"35\", \"cur_nm\": \"태국 바트\", \"result\": 1, \"cur_unit\": \"THB\", \"kftc_bkpr\": \"35\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"35.08\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"35.08\"}, {\"ttb\": \"1,175.22\", \"tts\": \"1,198.97\", \"bkpr\": \"1,187\", \"cur_nm\": \"미국 달러\", \"result\": 1, \"cur_unit\": \"USD\", \"kftc_bkpr\": \"1,187\", \"yy_efee_r\": \"0\", \"deal_bas_r\": \"1,187.1\", \"ten_dd_efee_r\": \"0\", \"kftc_deal_bas_r\": \"1,187.1\"}]";
        return new Gson().fromJson(json, new TypeToken<ArrayList<CurrencyApiResponse>>(){}.getType());
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
