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
public class BoardComment {
    private int comment_no;
    private int user_no;
    private int board_no;
    private String content;
    private Date reg_date;
    private Date revise_date;
    private boolean comment_private;
    private String type;
    private String commenter_name;
    private String profile_img;
}
