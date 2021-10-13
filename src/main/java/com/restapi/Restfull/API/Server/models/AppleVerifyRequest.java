package com.restapi.Restfull.API.Server.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppleVerifyRequest {
    private String receipt_data;
    private String password;
    private boolean exclude_old_transactions;
    private boolean sandbox;
}
