package com.restapi.Restfull.API.Server.exceptions;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class BusinessException extends RuntimeException {

    public BusinessException(Exception e) {
        super(e.getMessage());
    }
}
