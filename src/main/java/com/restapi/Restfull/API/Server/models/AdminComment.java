package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class AdminComment {
    private int comment_no;
    private int user_no;
    private String type;
    private String writer_name;
    private String content;
    private String reg_date;
    private boolean is_private;
}
