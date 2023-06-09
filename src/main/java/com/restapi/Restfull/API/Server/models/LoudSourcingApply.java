package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class LoudSourcingApply {
    private int apply_no;
    private int loudsourcing_no;
    private int artist_no;
    private boolean entry;
    private String reg_date;
    private boolean is_pre_selected;
    private boolean is_selected;
}
