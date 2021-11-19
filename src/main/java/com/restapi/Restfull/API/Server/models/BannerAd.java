package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class BannerAd {
    private int banner_ad_no;
    private String img;
    private String reg_date;
    private String revise_date;
    private boolean status;
}
