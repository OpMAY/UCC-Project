package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Auth {
    private String name;
    private String key;

    public Auth(String name, String key) {
        this.name = name;
        this.key = key;
    }
}
