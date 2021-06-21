package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
public class BannerAd {
    private int banner_ad_no;
    private String img;
    private Date reg_date;
    private Date revise_date;
    private String status;
}
