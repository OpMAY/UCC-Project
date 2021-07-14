package com.restapi.Restfull.API.Server.controller.advisor;

import com.restapi.Restfull.API.Server.exceptions.AuthException;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Log4j2
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity handle(AuthException e) {
        log.info("Auth Exception Handler");
        e.printStackTrace();
        return new ResponseEntity(DefaultRes.res(StatusCode.UNAUTHORIZED, ResMessage.UNAUTHORIZED, e.getLocalizedMessage()), HttpStatus.OK);
    }
}
