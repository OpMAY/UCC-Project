package com.restapi.Restfull.API.Server.utility;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class EncodeChecker {
    public static boolean encodeCheck(String s) {
        try {
            String decoded = URLDecoder.decode(s, "UTF-8");
            return !s.equals(decoded);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }
}
