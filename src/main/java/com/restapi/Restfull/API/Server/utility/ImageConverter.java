package com.restapi.Restfull.API.Server.utility;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Log4j2
public class ImageConverter {

    @Value("${uploadPath}")
    private String upload_path;

    public File convert64to32(File file) {
        try {
            BufferedImage oldRGBA = ImageIO.read(file);
            final int width = 1200;
            final int height = 800;
            BufferedImage newRGB = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            newRGB.createGraphics().drawImage(oldRGBA, 0, 0, width, height, null);
            File convertedFile = new File(upload_path, "temp_" + file.getName());
            if(ImageIO.write(newRGB, "PNG", convertedFile)){
                log.info("Image File Converted Successfully");
                log.info("Converted Image File Name : " + convertedFile.getName());
                return convertedFile;
            } else {
                log.info("Image File Convert Failed");
                log.info("Error File Name : " + file.getName());
                log.info("Error File Path : " + file.getAbsolutePath());
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }
}
