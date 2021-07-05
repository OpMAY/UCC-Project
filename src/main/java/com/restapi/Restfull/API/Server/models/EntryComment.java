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
public class EntryComment {
    private int entry_comment_no;
    private int user_no;
    private int entry_no;
    private int loudsourcing_no;
    private String reg_date;
    private boolean comment_private;
    private String content;
    private String commenter_name;
    private String profile_img;

    private boolean is_fankoked;
}
