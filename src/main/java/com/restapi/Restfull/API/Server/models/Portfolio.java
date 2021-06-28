package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@ToString
public class Portfolio {
    private int portfolio_no;
    private int artist_no;
    private String title;
    private String type;
    private String file;
    private String content;
    private Date reg_date;
    private Date revise_date;
    private int visit_number;
    private boolean agree;
    private String thumbnail;
    private int comment_number;
    private int like_number;
    private String artist_name;
    private String artist_profile_img;
    private List<PortfolioComment> portfolioCommentList;
}
