package com.restapi.Restfull.API.Server.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLConverter {
    public String convertSpecialLetter(String url) throws UnsupportedEncodingException {
        if(url != null) {
            String file_url = url.substring(url.lastIndexOf("/") + 1);
            String encoded_file_url = URLEncoder.encode(file_url, "UTF-8");

            url = url.substring(0, url.lastIndexOf("/") + 1) + encoded_file_url;
        }
        return url;
    }

    public String DecodeFileName(String url) throws UnsupportedEncodingException {
        if(url != null) {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            while (EncodeChecker.encodeCheck(fileName)) {
                fileName = URLDecoder.decode(fileName, "UTF-8");
            }
            url = url.substring(0, url.lastIndexOf("/") + 1) + fileName;
        }
        return url;
    }

}
