package com.restapi.Restfull.API.Server.controller.advisor;

import com.restapi.Restfull.API.Server.exceptions.AdminException;
import com.restapi.Restfull.API.Server.exceptions.AuthException;
import com.restapi.Restfull.API.Server.exceptions.BadRequestException;
import com.restapi.Restfull.API.Server.exceptions.NotFoundException;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Log4j2
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity handle(AuthException e) {
        log.info("Auth Exception Handler");
        return new ResponseEntity(DefaultRes.res(StatusCode.UNAUTHORIZED, ResMessage.UNAUTHORIZED, e.getLocalizedMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(AdminException.class)
    public ModelAndView handle(AdminException e){
        log.info("Admin Exception Handler");
        e.printStackTrace();
        return new ModelAndView("500");
    }

    @ExceptionHandler(BadRequestException.class)
    public ModelAndView handle(BadRequestException e){
        log.info("BadRequest Exception Handler");
        e.printStackTrace();
        return new ModelAndView("400");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handle(MissingServletRequestParameterException e){
        log.info("MissingServletRequestParameter Exception Handler");
        e.printStackTrace();
        return new ModelAndView("400");
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handle(NotFoundException e){
        log.info("NotFound Exception Handler");
        e.printStackTrace();
        return new ModelAndView("400");
    }

}
