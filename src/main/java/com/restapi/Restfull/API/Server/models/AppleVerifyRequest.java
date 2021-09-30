package com.restapi.Restfull.API.Server.models;

import lombok.Data;

@Data
public class AppleVerifyRequest {
    private byte receipt_data;
    private String password;
    private boolean exclude_old_transactions;
}
