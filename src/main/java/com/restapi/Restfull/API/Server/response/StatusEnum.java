package com.restapi.Restfull.API.Server.response;

public enum StatusEnum {
    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    UNSUPPORTED_MEDIA_TYPE(415,"UNSUPPORTED_MEDIA_TYPE");

    int status_code;
    String code;

    StatusEnum(int status_code, String code) {
        this.status_code = status_code;
        this.code = code;
    }
}
