package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class BoardLike {
    private int like_no;
    private int board_no;
    private int user_no;
    private String reg_date;
}
