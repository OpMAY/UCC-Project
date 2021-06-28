package com.restapi.Restfull.API.Server.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileConverter {
    public File convert(MultipartFile mfile) throws IOException {
        File file = new File(mfile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(mfile.getBytes());
        fos.close();
        return file;
    }
}
