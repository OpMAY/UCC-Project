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
public class Portfolio {
    private int portfolio_no;
    private int artist_no;
    private String title;
    private String type;
    private String content;
    private Date reg_date;
    private Date revise_date;
    private int visit_number;
    private boolean portfolio_private;
    private boolean agree;
    private String thumbnail;
    private int comment_number;
    private int like_number;
}
