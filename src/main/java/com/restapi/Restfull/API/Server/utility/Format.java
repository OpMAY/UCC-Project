package com.restapi.Restfull.API.Server.utility;

public class Format {
    public static boolean CheckFileType(String str) {
        str = str.substring(str.lastIndexOf(".")).toLowerCase();
        /** Image Extention*/
        if (str.contains("png") || str.contains("jpg") || str.contains("jpeg") || str.contains("svg") || str.contains("gif")) {
            return true;
            /** Video Extention*/
        } else if (str.contains("mp4") || str.contains("avi") || str.contains("m4a") || str.contains("asx") || str.contains("mpeg") || str.contains("mpg")) {
            return true;
            /** Document Extention*/
        } else if (str.contains("docx") || str.contains("docs") || str.contains("hwp") || str.contains("pdf") || str.contains("txt")) {
            return true;
        }
        return false;
    }
}