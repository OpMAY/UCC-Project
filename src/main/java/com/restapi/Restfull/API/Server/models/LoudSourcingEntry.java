package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class LoudSourcingEntry {
    private int entry_no;
    private int loudsourcing_no;
    private int artist_no;
    private String file;
    private String content;
    private String reg_date;
    private String revise_date;
    private int visit;
    private boolean agree;
    private String thumbnail;
    private int comment_number;
    private int vote_number;
    private String artist_name;
    private String artist_profile_img;
    private int fan_number;
    private String video_length;
    private int user_no;

}
