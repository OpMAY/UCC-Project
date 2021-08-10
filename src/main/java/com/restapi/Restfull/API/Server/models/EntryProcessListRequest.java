package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EntryProcessListRequest {
    private int artist_no;
    private int loudsourcing_no;
    private int entry_no;
    private String artist_name;
    private String phone;
    private String email;
    private String upload_date;
    private int vote_number;
}
