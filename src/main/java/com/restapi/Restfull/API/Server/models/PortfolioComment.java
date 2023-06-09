package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class PortfolioComment {
    private int comment_no;
    private int user_no;
    private int portfolio_no;
    private String content;
    private String reg_date;
    private boolean comment_private;
    private String profile_img;
    private String commenter_name;

    private boolean is_fankoked;
}
