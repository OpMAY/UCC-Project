package com.restapi.Restfull.API.Server.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AppleVerifyResponse {
    private String environment;
    private boolean is_retryable;
    private String latest_receipt;
    private ArrayList<LatestReceiptInfo> latest_receipt_info;
    private ArrayList<PendingRenewalInfo> pending_renewal_info;
    private Receipt receipt;
    private int status;
    private String status_explain;
}
