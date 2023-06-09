package com.restapi.Restfull.API.Server.response;

public class StatusCode {
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int NO_CONTENT = 204;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int ARTIST_NAME_ALREADY_IN_USE = 1400;
    /**
     * 부트페이 error code
     **/
    public static final int NOT_EXTENDED = 510;
    public static final int DB_ERROR = 600;

    public static final int BAN_ARTIST = 1234;
    public static final int DELETED_USER = 2222;
    public static final int RETRY_RELOAD = 3330;
    public static final int DELETE_CONTENTS = 3333;
    public static final int FILE_MAX_SIZE_EXCEED = 4444;
}