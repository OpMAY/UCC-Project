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
public class Fankok {
    private Portfolio portfolio;
    private Board board;
    private Date reg_date;
    private String type;
}
