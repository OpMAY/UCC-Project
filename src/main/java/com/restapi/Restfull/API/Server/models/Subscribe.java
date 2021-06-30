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
public class Subscribe {
    private int subscribe_no;
    private int user_no;
    private int artist_no;
    private String subscribe_date;
}
