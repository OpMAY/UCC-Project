package com.restapi.Restfull.API.Server.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UnifiedReceipt {
    private String environment;
    private String latest_receipt;
    private ArrayList<LatestReceiptInfo> latest_receipt_info;
    private ArrayList<PendingRenewalInfo> pending_renewal_info;
    private Integer status; // 0 : Valid
}
