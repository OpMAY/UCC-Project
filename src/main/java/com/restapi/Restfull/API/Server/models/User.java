package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class User {
    private int user_no;
    private String name;
    private String email;
    private String sns;
    private String reg_date;
    private boolean is_artist;
    private String profile_img;
    private boolean is_user_private;
    private String access_token;
    private String fcm_token;
    private boolean push;
    private boolean comment_push;
    private boolean fankok_push;

    private boolean is_register;
}
