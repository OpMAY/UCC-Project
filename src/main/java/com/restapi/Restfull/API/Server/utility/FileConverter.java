package com.restapi.Restfull.API.Server.utility;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.Normalizer;

@Log4j2
public class FileConverter {
    public File convert(MultipartFile file, String temp_fileName) throws IOException {
        //File convFile = new File("E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api/", temp_fileName);
        File convFile = new File("/www/weart-page_com/www/api/temp/", temp_fileName);
        try (InputStream is = file.getInputStream()) {
            Files.copy(is, convFile.toPath());
        }
        return convFile;
    }
}
