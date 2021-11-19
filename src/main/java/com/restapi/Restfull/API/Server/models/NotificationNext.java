package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class NotificationNext {
    private String type;
    private String content_type;
    private String content_category;
    private int content_no;
    private int artist_no;
    private String admin_type;

    public NotificationNext(String type, String content_type, String content_category, int content_no, String admin_type, int artist_no){
        this.type = type;
        this.content_type = content_type;
        this.content_category = content_category;
        this.content_no = content_no;
        this.artist_no = artist_no;
        this.admin_type = admin_type;
    }
}
