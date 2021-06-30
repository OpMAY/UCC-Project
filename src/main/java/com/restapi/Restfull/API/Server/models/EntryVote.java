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
public class EntryVote {
    private int entry_vote_no;
    private int loudsourcing_no;
    private int entry_no;
    private int user_no;
    private String reg_date;
}
