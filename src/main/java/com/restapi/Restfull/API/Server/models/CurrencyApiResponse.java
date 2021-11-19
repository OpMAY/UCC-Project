package com.restapi.Restfull.API.Server.models;

import lombok.Data;

@Data
public class CurrencyApiResponse {
    private int result;
    private String cur_unit;
    private String deal_bas_r;
}
