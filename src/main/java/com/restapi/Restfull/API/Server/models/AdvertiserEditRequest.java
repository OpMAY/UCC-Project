package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AdvertiserEditRequest {
    private int loudsourcing_no;
    private String advertiser_name;
    private String advertiser_phone;
    private String advertiser_email;
    private String advertiser_bank_name;
    private String advertiser_bank_account;
    private String advertiser_bank_owner;
}
