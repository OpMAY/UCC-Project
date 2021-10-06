package com.restapi.Restfull.API.Server.utility;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.google.api.services.plus.PlusScopes;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.restapi.Restfull.API.Server.models.GPResponseModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Log4j2
@Service
public class GPVerification {

    private static GPVerification GPVerification;

    private GPVerification(){}

    public static GPVerification getInstance(){
        if(GPVerification == null){
            GPVerification = new GPVerification();
        }
        return GPVerification;
    }

    private final String PACKAGE_NAME = "com.weart.ucc";

    public GPResponseModel verify(String productId, String purchaseToken) throws GeneralSecurityException, IOException {
        getInstance();
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        File jsonFile = new File("E:\\vodAppServer\\src\\main\\webapp\\resources\\weart-ucc-abaada2307f7.json");
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(jsonFile))
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/androidpublisher"));


        AndroidPublisher publisher = new AndroidPublisher.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(PACKAGE_NAME)
                .build();

        AndroidPublisher.Purchases.Products.Get get = publisher.purchases().products().get(PACKAGE_NAME, productId, purchaseToken);
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
        return new GPResponseModel(consumptionState, developerPayload, purchaseState, purchaseTimeMillis, purchaseTimeString);
    }
}
