package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class BootpayVerificationData {
    private int status;
    private int code;
    private String message;
    private PurchaseData data;
}
