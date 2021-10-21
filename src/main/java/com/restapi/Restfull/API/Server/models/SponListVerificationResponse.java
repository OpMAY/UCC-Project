package com.restapi.Restfull.API.Server.models;

import lombok.Data;

@Data
public class SponListVerificationResponse {
    private int total_num;
    private int success_num;
    private int fail_num;
}
