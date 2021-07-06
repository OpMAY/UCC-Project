package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;

@Data
@Getter
@Setter
@ToString
public class Notice {
    private int notice_no;
    private int admin_no;
    private String title;
    private String content;
    private String reg_date;
    private String revise_date;
    private String img;
    private ArrayList<String> imgList;
}
