package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Penalty {
    private int penalty_no;
    private int user_no;
    private int artist_no;
    private String penalty_start_date;
    private String penalty_end_date;
    private int penalty_days;
    private String penalty_reason;
}
