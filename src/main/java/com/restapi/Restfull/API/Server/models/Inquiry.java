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
public class Inquiry {
    private int inquiry_no;
    private int user_no;
    private int reported_user_no;
    private String title;
    private String content;
    private String type;
    private String phone;
    private String email;
    private boolean is_answered;
    private String answer_content;
    private Date reg_date;
    private Date answer_date;
    private String file;
    private String report_content_type;
    private int reported_content_type;
}
