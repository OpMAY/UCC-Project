package com.restapi.Restfull.API.Server.utility;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Log4j2
@Service
public class GooglePlayStoreVerification {

    public void verify() throws GeneralSecurityException, IOException {
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        String DEVELOP_EMAIL = "kevin8622@playstore-purchase-verify.iam.gserviceaccount.com";
        String PRIVATE_KEY_ID = "111590174174827635203";
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(DEVELOP_EMAIL)
                .setServiceAccountPrivateKeyId(PRIVATE_KEY_ID)
                .setServiceAccountScopes(Collections.singleton("https://www.googleapis.com/auth/androidpublisher"))
                .build();

        String packageName = "";
        String productId = "";
        String purchaseToken = "";

        AndroidPublisher publisher = new AndroidPublisher.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(packageName)
                .build();

        AndroidPublisher.Purchases.Products.Get get = publisher.purchases().products().get(packageName, productId, purchaseToken);
        ProductPurchase productPurchase = get.execute();
        log.info("Purchase Data : " + productPurchase.toPrettyString());

        // 인앱 상품의 소비 상태. 0 아직 소비 안됨(Yet to be consumed) / 1 소비됨(Consumed)
        boolean consumptionState = productPurchase.getConsumptionState() == 1;

        // 개발자가 지정한 임의 문자열 정보
        String developerPayload = productPurchase.getDeveloperPayload();

        // 구매 상태. 0 구매완료 / 1 취소됨
        boolean purchaseState = productPurchase.getPurchaseState() == 0;

        // 상품이 구매된 시각. 타임스탬프 형태
        Long purchaseTimeMillis = productPurchase.getPurchaseTimeMillis();
        String purchaseTimeString = Time.TimeMillsToDateString(purchaseTimeMillis, "yyyy-MM-dd HH:mm:ss.SSS");
    }
}
