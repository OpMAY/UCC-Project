package com.restapi.Restfull.API.Server.models;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
public class User {
    private int user_no;
    private String name;
    private String email;
    private String sns;
    private Date reg_date;
    private boolean is_artist;
    private String profile_img;
    private boolean is_user_private;
}