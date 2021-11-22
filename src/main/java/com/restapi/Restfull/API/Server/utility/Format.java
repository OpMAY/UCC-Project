package com.restapi.Restfull.API.Server.utility;

public class Format {
    public static boolean CheckFileType(String str) {
        str = str.substring(str.lastIndexOf(".")).toLowerCase();
        /** Image Extension*/
        if (str.contains("png") || str.contains("jpg") || str.contains("jpeg") || str.contains("svg") || str.contains("gif")) {
            return true;
            /** Video Extension*/
        } else if (str.contains("mp4") || str.contains("avi") || str.contains("m4a") || str.contains("asx") || str.contains("mpeg") || str.contains("mpg")) {
            return true;
            /** Document Extension*/
        } else return str.contains("docx") || str.contains("docs") || str.contains("hwp") || str.contains("pdf") || str.contains("txt");
    }

    public static boolean CheckVODFile(String str) {
        str = str.substring(str.lastIndexOf(".")).toLowerCase();
        return str.contains("mp4") || str.contains("avi") || str.contains("m4a") || str.contains("asx") || str.contains("mpeg") || str.contains("mpg");
    }

    public static boolean CheckIMGFile(String str) {
        str = str.substring(str.lastIndexOf(".")).toLowerCase();
        return str.contains("png") || str.contains("jpg") || str.contains("jpeg") || str.contains("svg");
    }

    public static boolean CheckFile(String str) {
        str = str.substring(str.lastIndexOf(".")).toLowerCase();
        /** Image Extension*/
        if (str.contains("png") || str.contains("jpg") || str.contains("jpeg") || str.contains("svg")) {
            return true;
            /** Document Extension*/
        } else return str.contains("docx") || str.contains("docs") || str.contains("hwp") || str.contains("pdf") || str.contains("txt") || str.contains("pptx") || str.contains("xlsx");
    }
}
