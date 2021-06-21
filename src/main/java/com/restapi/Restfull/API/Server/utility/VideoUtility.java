package com.restapi.Restfull.API.Server.utility;

import java.io.File;
import java.io.IOException;

import org.jcodec.api.JCodecException;

public class VideoUtility {
    /**
     * 썸네일을 추출하는 메소드
     *
     * @param source mp4 file.
     * @return
     * @throws IOException
     * @throws JCodecException
     * @throws InterruptedException
     */
    public void getThumbnail(File source) throws Exception {
        double plusSize = 0.5;
        int threadSize = 8;

        VideoThread[] videoThread = new VideoThread[threadSize];

        for (int i = 0; i < videoThread.length; i++) {
            videoThread[i] = new VideoThread(source, threadSize, i, plusSize, "C:/Users/zlzld/OneDrive/Desktop/projects/server_test/Restfull-API-Server/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api");
            videoThread[i].start();
        }

        boolean runFlag = true;
        while (runFlag) {
            Thread.sleep(1000);

            runFlag = false;
            for (int i = 0; i < threadSize; i++) {
                if (videoThread[i].isAlive())
                    runFlag = true;
            }
        }
    }
}
