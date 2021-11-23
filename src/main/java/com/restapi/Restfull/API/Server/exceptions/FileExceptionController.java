package com.restapi.Restfull.API.Server.exceptions;

import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
@Log4j2
public class FileExceptionController {
    @Value("${ucc.fileupload.limit}")
    private long sizeLimit;

    @ExceptionHandler(MultipartException.class)
    protected ResponseEntity handleException(MultipartException e) {
        e.printStackTrace();
        log.info(e.getMessage());
        return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity handleException(MaxUploadSizeExceededException e) throws JSONException {
        Message message = new Message();
        message.put("size_limit", sizeLimit);
        e.printStackTrace();
        log.info(e.getMessage());
        return new ResponseEntity(DefaultRes.res(StatusCode.FILE_MAX_SIZE_EXCEED, ResMessage.FILE_MAX_SIZE_EXCEED, message.getHashMap()), HttpStatus.OK);
    }
}
