package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class BoardComment {
    private int comment_no;
    private int user_no;
    private int board_no;
    private String content;
    private String reg_date;
    private String revise_date;
    private boolean comment_private;
    private String type;
    private String commenter_name;
    private String profile_img;

    private boolean is_sponned;
}
