package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class ArtistVisit {
    private int artist_visit_no;
    private int user_no;
    private int artist_no;
    private String visit_date;
}
