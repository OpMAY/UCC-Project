package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Upload {
    private String name;
    private String url;

    public Upload(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
