package com.restapi.Restfull.API.Server.models;

import lombok.Data;

@Data
public class AppleNotificationResponse {
    private String auto_renew_adam_id;
    private String auto_renew_product_id;
    // auto_renew_status;
    // 0 : 구독 취소, 1 : 구독 연장
    private String auto_renew_status;
    private String auto_renew_status_change_date;
    private String auto_renew_status_change_date_ms;
    private String auto_renew_status_change_date_pst;
    private String bid;
    private String bvrs;
    private String environment;
    // expiration_intent
    // 1 : 구매자 직접 구독 취소
    // 2 : 유효하지 않은 영수증
    // 3 : 가격 상승에 고객이 동의하지 않음
    // 4 : renewal 결제 시각에 상품이 유효하지 않았음
    // 5 : Unknown Error
    private Integer expiration_intent;
    private String notification_type;
    // original_transaction_id
    // CONSUMPTION_REQUEST 의 경우에만 오는 데이터
    private long original_transaction_id;
    private String password; // shared secret
    private UnifiedReceipt unified_receipt;
}
