package com.restapi.Restfull.API.Server.models;

import lombok.Data;

@Data
public class AndroidVerificationRequest {
    private String product_id;
    private String purchase_token;
}

