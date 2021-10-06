package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Board {
    private int board_no;
    private int artist_no;
    private int user_no;
    private String title;
    private String content; // 스마트 에디터 형태
    private String reg_date;
    private String revise_date;
    private int visit_number;
    private boolean board_private;
    private String thumbnail;
    private int comment_number;
    private int like_number;
    private String artist_name;
    private String artist_profile_img;
    private int fan_number;

//    private int spon_amount;
}
