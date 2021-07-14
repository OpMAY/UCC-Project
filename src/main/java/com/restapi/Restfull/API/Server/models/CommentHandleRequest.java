package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class CommentHandleRequest {
    private int comment_no;
    private String type;
}
