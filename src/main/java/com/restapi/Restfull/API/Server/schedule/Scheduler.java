package com.restapi.Restfull.API.Server.schedule;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.File;

@Log4j2
public class Scheduler {

    @Value("${uploadPath}")
    private String upload_path;

    @Scheduled(fixedDelay = 86400000)
    public void deleteFiles(){
        File deleteFolder = new File(upload_path);
        if (deleteFolder.exists()) {
            File[] deleteForderList = deleteFolder.listFiles();
            if (deleteForderList.length != 0) {
                for (File f : deleteForderList) {
                    log.info(f.getName() + " Delete");
                    f.delete();
                }
            }
        }
    }
}
