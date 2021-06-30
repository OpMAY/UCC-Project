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
public class LoudSourcing {
    private int loudsourcing_no;
    private String name;
    private String status;
    private String host;
    private String type;
    private String reward;
    private String warning;
    private String reg_date;
    private String revise_date;
    private String files;
    private String img;
    private String content;
    private String start_date;
    private String recruitment_end_date;
    private String process_start_date;
    private String process_end_date;
    private String end_date;
}
