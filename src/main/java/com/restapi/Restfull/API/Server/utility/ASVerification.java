package com.restapi.Restfull.API.Server.utility;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.models.AppleVerifyRequest;
import com.restapi.Restfull.API.Server.models.AppleVerifyResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Log4j2
@Service
public class ASVerification {
    private static ASVerification ASVerification;

    private ASVerification() {
    }

    public static ASVerification getInstance() {
        if (ASVerification == null) {
            ASVerification = new ASVerification();
        }
        return ASVerification;
    }

    private final String APPLE_VERIFY_URL = "https://buy.itunes.apple.com/verifyReceipt";
    private final String APPLE_TEST_SANDBOX_URL = "https://sandbox.itunes.apple.com/verifyReceipt";
    private final String PASSWORD = "467d4edd3ef24af19d42f19b98c535a2";

    public AppleVerifyResponse verify(AppleVerifyRequest request) throws IOException {
        HttpResponse res = getAppleStoreVerification(request);
        String responseString = IOUtils.toString(res.getEntity().getContent(), StandardCharsets.UTF_8);
        AppleVerifyResponse response = new Gson().fromJson(responseString, AppleVerifyResponse.class);
        log.info("Apple Response : " + response);
//        if(response.is_retryable()){
//            verify(request);
//        }
        if (response.getStatus() == 0)
            response.setStatus_explain("SUCCESS");
        else {
            response.setStatus_explain(verifyStatusCode(response.getStatus()));
        }
        return response;
    }

    private HttpPost getPost(String url, StringEntity entity) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept-Charset", "utf-8");
        post.setEntity(entity);
        return post;
    }

    private HttpPut getPut(String url){
        HttpPut put = new HttpPut(url);
        put.setHeader("Accept", "application/json");
        put.setHeader("Content-Type", "application/json");
        put.setHeader("Accept-Charset", "utf-8");
        return put;
    }

    public HttpResponse replyToConsumptionRequest(long originalTransactionalId) throws IOException {
        String url = "https://api.storekit.itunes.apple.com/inApps/v1/transactions/consumption/" + originalTransactionalId;
        HttpPut put = getPut(url);
        HttpClient client = HttpClientBuilder.create().build();
        return client.execute(put);
    }

    private HttpResponse getAppleStoreVerification(AppleVerifyRequest request) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        String jsonData = "{\"receipt-data\" : \"" + request.getReceipt_data() + "\", \"password\" : \"" + PASSWORD + "\"}";
        HttpPost post;
        if(request.isSandbox()){
            post = getPost(APPLE_TEST_SANDBOX_URL, new StringEntity(jsonData));
        } else {
            post = getPost(APPLE_VERIFY_URL, new StringEntity(jsonData));
        }
        return client.execute(post);
    }

    private String verifyStatusCode(int statusCode){
        switch (statusCode) {
            case 21000:
                return "[Status code: " + statusCode + "] The request to the App Store was not made using the HTTP POST request method.\n 앱스토어에 올바르지 못한 요청 방식을 이용하였습니다.";
            case 21001:
                return "[Status code: " + statusCode + "] This status code is no longer sent by the App Store.";
            case 21002:
                return "[Status code: " + statusCode + "] The data in the receipt-data property was malformed or the service experienced a temporary issue. Try again.\n 영수증 데이터가 만료되었습니다.";
            case 21003:
                return "[Status code: " + statusCode + "] The receipt could not be authenticated.";
            case 21004:
                return "[Status code: " + statusCode + "] The shared secret you provided does not match the shared secret on file for your account.";
            case 21005:
                return "[Status code: " + statusCode + "] The receipt server was temporarily unable to provide the receipt. Try again.\nApple 영수증 조회 서버 오류입니다. 시간이 지난 후 다시 시도해주세요.";
            case 21006:
                return "[Status code: " + statusCode + "] This receipt is valid but the subscription has expired. When this status code is returned to your server, the receipt data is also decoded and returned as part of the response. Only returned for iOS 6-style transaction receipts for auto-renewable subscriptions.";
            case 21008:
                return "[Status code: " + statusCode + "] This receipt is from the production environment, but it was sent to the test environment for verification.";
            case 21009:
                return "[Status code: " + statusCode + "] Internal data access error. Try again later.\nApple 영수증 조회 서버 오류입니다. 시간이 지난 후 다시 시도해주세요.";
            case 21010:
                return "[Status code: " + statusCode + "] The user account cannot be found or has been deleted.\n해당 상품을 결제한 유저를 찾을 수 없거나 삭제되었습니다.";
            default:
                return "[Status code: " + statusCode + "] The receipt for the App Store is incorrect.\n해당 결제 데이터의 영수증이 올바르지 않습니다.";
        }
    }
}
