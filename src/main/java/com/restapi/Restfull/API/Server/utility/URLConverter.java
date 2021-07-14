package com.restapi.Restfull.API.Server.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLConverter {
    public String convertSpecialLetter(String url) throws UnsupportedEncodingException {
        String file_url = url.substring(url.lastIndexOf("/") + 1);
        String encoded_file_url = URLEncoder.encode(file_url, "UTF-8");

        url = url.substring(0, url.lastIndexOf("/") + 1) + encoded_file_url;
        return url;
    }
}
