package com.restapi.Restfull.API.Server.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

public class FileConverter {
    public File convert(MultipartFile file) throws IOException {
        File convFile = new File("/www/mvsolutions_co_kr/www/api/temp/", file.getOriginalFilename());
        try (InputStream is = file.getInputStream()) {
            Files.copy(is, convFile.toPath());
        }
        return convFile;
    }
}
