package com.restapi.Restfull.API.Server.utility;

import java.io.File;
import java.io.IOException;

import com.coremedia.iso.IsoFile;
import com.restapi.Restfull.API.Server.services.PortfolioService;
import com.restapi.Restfull.API.Server.services.UserService;
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
            videoThread[i] = new VideoThread(source, threadSize, i, plusSize, "E:/vodAppServer/target/Restfull-API-Server-0.0.1-SNAPSHOT/WEB-INF/api");
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
