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
public class PortfolioLike {
    private int like_no;
    private int portfolio_no;
    private int user_no;
    private Date reg_date;
}
