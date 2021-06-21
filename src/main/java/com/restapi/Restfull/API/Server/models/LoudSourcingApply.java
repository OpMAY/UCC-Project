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
public class LoudSourcingApply {
    private int apply_no;
    private int loudsourcing_no;
    private int artist_no;
    private String status;
    private Date reg_date;
}
