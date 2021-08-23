package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AlarmMessage {
    private int message_no;
    private String type;
    private String content;
    private String reg_date;
    private String revise_date;
}
