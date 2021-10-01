package com.restapi.Restfull.API.Server.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AdminException extends RuntimeException {

    public AdminException(Exception e) {
        super(e.getMessage());
    }
}
