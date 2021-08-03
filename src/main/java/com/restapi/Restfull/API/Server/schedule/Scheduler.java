package com.restapi.Restfull.API.Server.schedule;

import com.restapi.Restfull.API.Server.services.MainService;
import com.restapi.Restfull.API.Server.services.PenaltyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.ParseException;

@Log4j2
@Component
public class Scheduler {

    @Value("${uploadPath}")
    private String upload_path;

    @Autowired
    private PenaltyService penaltyService;

    @Autowired
    private MainService mainService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteFiles() {
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

    /**
        주석 생성 날짜 - 2021-08-3, 화, 15:04
        코드 설명 : 매달 1일 오전 3시마다 DB 내의 파일들 조회하여 CDN 서버의 Dummy 파일 삭제
        특이 사항 : 스케쥴링
        파일 업로드 여부 : X
    **/
    @Scheduled(cron = "0 0 3 1 * ?")
    public void deleteCDNFiles(){
        log.info("Delete CDN Files Start");
        mainService.deleteCDNFiles();
        log.info("Delete CDN Files END");
    }

    /**
        주석 생성 날짜 - 2021-08-3, 화, 15:05
        코드 설명 : 매일 자정 유저 정지 Setting
        특이 사항 : 스케쥴링
        파일 업로드 여부 : X
    **/
    @Scheduled(cron = "0 0 0 * * ?")
    public void penaltyUserSetting() throws ParseException {
        penaltyService.setUserPrivate();
    }

}
