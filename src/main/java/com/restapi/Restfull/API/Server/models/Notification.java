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
public class Notification {
    private int notification_no;
    private int user_no;
    private String title;
    private String type;
    private String content;
    private Date reg_date;
    private String after;
    private boolean read_status;
}
