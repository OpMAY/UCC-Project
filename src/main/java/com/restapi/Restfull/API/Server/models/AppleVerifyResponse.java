package com.restapi.Restfull.API.Server.models;

import lombok.Data;

@Data
public class AppleVerifyResponse {
    private String environment;
    private boolean is_retryable;
    private byte latest_receipt;
    private LatestReceiptInfo latest_receipt_info;
    private PendingRenewalInfo pending_renewal_info;
    private Receipt receipt;
    private int status;
}
