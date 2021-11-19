package com.restapi.Restfull.API.Server.services;
/*
 * Created By zlzld in CP
 * Date : 7월 수 11 44
 * Description : Bootpay REST API 와 관련된 서비스
 */

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.models.Cancel;
import com.restapi.Restfull.API.Server.models.SubscribeBilling;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BootpayService {
    private BootpayApi api;
    private String rest_application_id = "61108e6b7b5ba40023529add";
    private String private_key = "Ft1F56rFrFLxIsisP5N1nCrvhB2XN6/6UqJe/y9l+Ys=";

    /**
     * goGetToken();
     * goVerfity();
     * goCancel();
     * goSubscribeBilling();
     */

    public String goGetToken() throws Exception {
        api = new BootpayApi(rest_application_id, private_key);  // application_id, private key
        return api.getAccessToken();
    }

    /**
     * @param receipt_id : 영수증 id
     * @param token      : Bootpay 토큰
     * @return : void
     * Description : 영수증 아이디 결제 검증 함수
     * Date : 2021-07-07
     * Version : 1
     */
    public String goVerify(String receipt_id, String token) throws Exception {
        log.info(token);
        api = new BootpayApi(rest_application_id, private_key);  // application_id, private key
        api.setToken(token);
        HttpResponse res = api.verify(receipt_id);
        String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
        log.info("영수증 아이디 결제 검증 함수 : " + str);
        return str;
    }

    /**
     * @param receipt_id : 영수증 id
     * @return : void
     * Description : 결제 취소 함수
     * Date : 2021-07-07
     * Version : 1
     */
    public boolean goCancel(String receipt_id) throws Exception {
        Cancel cancel = new Cancel();
        cancel.receipt_id = receipt_id;
        cancel.name = "관리자 김우식";
        cancel.reason = "테스트에 의한 취소";
        HttpResponse res = api.cancel(cancel);
        String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
        log.info(str);
        return true;
    }

    public void goSubscribeBilling() {
        SubscribeBilling subscribeBilling = new SubscribeBilling();
        subscribeBilling.billing_key = "5b025b33e13f33310ce560fb";
        subscribeBilling.item_name = "정기결제 테스트 아이템";
        subscribeBilling.price = 3000;
        subscribeBilling.order_id = "" + (System.currentTimeMillis() / 1000); // 고객사에서 관리하는 주문번호로, 고유값으로 생성 후 부트페이에 전달해주셔야합니다

        try {
            HttpResponse res = api.subscribe_billing(subscribeBilling);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
            System.out.println(new Gson().toJson(subscribeBilling));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
