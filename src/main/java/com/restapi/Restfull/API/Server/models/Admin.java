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
public class Admin {
    private int admin_no;
    private String id;
    private String pw;
    private String img;
    private Date reg_date;
}
