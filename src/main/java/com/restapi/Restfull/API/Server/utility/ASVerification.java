package com.restapi.Restfull.API.Server.utility;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.models.AppleVerifyRequest;
import com.restapi.Restfull.API.Server.models.AppleVerifyResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
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

    public AppleVerifyResponse verify(AppleVerifyRequest request) throws IOException {
        getInstance();
        HttpResponse res = getAppleStoreVerification(request);
        String responseString = IOUtils.toString(res.getEntity().getContent(), StandardCharsets.UTF_8);
        AppleVerifyResponse response = new Gson().fromJson(responseString, AppleVerifyResponse.class);
        if (response.getStatus() == 21007)
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

    private HttpResponse getAppleStoreVerification(AppleVerifyRequest request) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = getPost(APPLE_VERIFY_URL, new StringEntity(new Gson().toJson(request), "UTF-8"));
        return client.execute(post);
    }

    private String verifyStatusCode(int statusCode){
        switch (statusCode) {
            case 21000:
                return "[Status code: " + statusCode + "] The request to the App Store was not made using the HTTP POST request method.";
            case 21001:
                return "[Status code: " + statusCode + "] This status code is no longer sent by the App Store.";
            case 21002:
                return "[Status code: " + statusCode + "] The data in the receipt-data property was malformed or the service experienced a temporary issue. Try again.";
            case 21003:
                return "[Status code: " + statusCode + "] The receipt could not be authenticated.";
            case 21004:
                return "[Status code: " + statusCode + "] The shared secret you provided does not match the shared secret on file for your account.";
            case 21005:
                return "[Status code: " + statusCode + "] The receipt server was temporarily unable to provide the receipt. Try again.";
            case 21006:
                return "[Status code: " + statusCode + "] This receipt is valid but the subscription has expired. When this status code is returned to your server, the receipt data is also decoded and returned as part of the response. Only returned for iOS 6-style transaction receipts for auto-renewable subscriptions.";
            case 21008:
                return "[Status code: " + statusCode + "] This receipt is from the production environment, but it was sent to the test environment for verification.";
            case 21009:
                return "[Status code: " + statusCode + "] Internal data access error. Try again later.";
            case 21010:
                return "[Status code: " + statusCode + "] The user account cannot be found or has been deleted.";
            default:
                return "[Status code: " + statusCode + "] The receipt for the App Store is incorrect.";
        }
    }
}
