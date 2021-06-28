package com.restapi.Restfull.API.Server.utility;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class VideoThread extends Thread {
    private int threadNo;
    private int threadSize;
    private double plusSize;
    private String path;
    private File source;

    public VideoThread(File source, int threadSize, int threadNo, double plusSize, String path) {
        this.source = source;
        this.threadSize = threadSize;
        this.threadNo = threadNo;
        this.plusSize = plusSize;
        this.path = path;
    }

    public void run() {
        FrameGrab grab;

        try {
            grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));

            for (int m = 0; m < 1; m++) {
                if (m % threadSize == threadNo) {
                    double startSec = m * plusSize;
                    System.out.println(threadNo + " " + startSec);

                    int frameCount = 1;
                    grab.seekToSecondPrecise(startSec);

                    for (int i = 0; i < frameCount; i++) {
                        Picture picture = grab.getNativeFrame();

                        //for JDK (jcodec-javase)
                        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
                        ImageIO.write(bufferedImage, "png",
                                new File(path + "/" + source.getName() + "_thumb.png"));
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
