package com.restapi.Restfull.API.Server.utility;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Log4j2
public class ImageConverter {
    // OPEN CV SETTING VALUE
    private int DELAY_BLUR = 100;
    private int MAX_KERNEL_LENGTH = 15;


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

    public File blurImage(File file){
        // Load The Native Library
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        System.load("E:\\vodAppServer\\src\\main\\java\\opencv_java451.dll");
        System.load("/www/weart-page_com/ROOT/resources/libopencv_java451.so");

        // Source Image by creating Matlab object
        Mat src = new Mat();

        // Destination Image by creating Matlab object
        Mat dst = new Mat();

        // Taking input image from directory
        src = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_COLOR);

        // Blur Image
        for(int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2){
            Imgproc.blur(src, dst, new Size(i, i), new Point(-1, -1));
        }

        // Make Target File & Write Blurred Image
        File blurredFile = new File(upload_path + "blurred_" + file.getName());
        Imgcodecs.imwrite(blurredFile.getName(), dst);
        return blurredFile;
    }
}
