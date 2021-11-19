package com.restapi.Restfull.API.Server.utility;

import com.coremedia.iso.IsoFile;
import org.jcodec.api.JCodecException;

import java.io.File;
import java.io.IOException;


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
            videoThread[i] = new VideoThread(source, threadSize, i, plusSize, "/www/weart-page_com/www/api/temp");
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

    public String getDuration(File video_file) throws IOException {
        IsoFile isoFile = new IsoFile(video_file.getCanonicalPath());
        long lengthInSeconds =
                isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                        isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
        int minute = (int) (lengthInSeconds / 60);
        int second = (int) (lengthInSeconds % 60);
        StringBuilder duration = new StringBuilder();
        duration.append(minute);
        duration.append(":");
        if (second < 10) {
            duration.append("0");
        }
        duration.append(second);
        isoFile.close();
        return duration.toString();
    }

}
