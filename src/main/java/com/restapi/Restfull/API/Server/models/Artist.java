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
public class Artist {
    private int artist_no;
    private int user_no;
    private String artist_name;
    private String bank_name;
    private String bank_account;
    private String bank_owner;
    private String email;
    private String artist_phone;
    private String fan_main_img;
    private String artist_profile_img;
    private Date reg_date;
    private int fan_number;
    private int fan_visit_today;
    private String fan_explain;
    private boolean artist_private;
    private String hashtag;
}
