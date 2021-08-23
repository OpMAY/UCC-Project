package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AdminArtistLoudSourcing {
    private int artist_no;
    private int loudsourcing_no;
    private String loudsourcing_name;
    private String host;
    private String status;
    private String start_date;
    private String end_date;
    private String reg_date;
}
