package com.restapi.Restfull.API.Server.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuthException extends RuntimeException {

    public AuthException(Exception e) {
        super(e.getMessage());
    }
}
