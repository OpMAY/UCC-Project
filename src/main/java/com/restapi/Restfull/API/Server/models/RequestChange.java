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
public class RequestChange {
    private int change_no;
    private int user_no;
    private String artist_name;
    private String bank_name;
    private String bank_account;
    private String bank_owner;
    private String artist_email;
    private String artist_phone;
    private String artist_profile_img;
    private String fan_main_img;
    private Date reg_date;
    private Date revise_date;
    private String fan_explain;
    private String hashtag;
    private boolean agree;
    private boolean status;
}
