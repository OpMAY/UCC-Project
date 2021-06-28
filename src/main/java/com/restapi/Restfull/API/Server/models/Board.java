package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@ToString
public class Board {
    private int board_no;
    private int artist_no;
    private String title;
    private String content; // 스마트 에디터 형태
    private Date reg_date;
    private Date revise_date;
    private int visit_number;
    private boolean board_private;
    private String thumbnail;
    private int comment_number;
    private int like_number;
    private String artist_name;
    private String artist_profile_img;
    private List<BoardComment> boardCommentList;
    private List<BoardLike> boardLikeList;
}
