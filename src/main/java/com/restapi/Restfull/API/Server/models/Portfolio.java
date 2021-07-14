package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Data
@Getter
@Setter
@ToString
public class Portfolio {
    private int portfolio_no;
    private int artist_no;
    private int user_no;
    private String title;
    private String type;
    private String file;
    private String content;
    private String reg_date;
    private String revise_date;
    private int visit_number;
    private String thumbnail;
    private int comment_number;
    private int like_number;
    private String artist_name;
    private String artist_profile_img;
    private int fan_number;
    private String video_length;

    private ArrayList<String> image_list;
    private ArrayList<Upload> file_list;
}
