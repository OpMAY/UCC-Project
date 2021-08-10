package com.restapi.Restfull.API.Server.utility;

import java.text.Normalizer;

/**
    작성자 : 한지우
    주석 생성 날짜 - 2021-08-10, 화, 14:57
    코드 설명 : IOS, MAC OS 에서의 한글 파일이 Linux 에서 깨지는 현상을 해결하는 코드
    특이 사항 : IOS, MAC OS는 한글을 NFD 형식으로, Linux, Window 는 NFC 형식으로 이용함
                해당 한글을 NFD -> NFC 로 변환함
                MultipartFile.getOriginalFileName()을 인자로 넣으면 됨
**/
public class IOSKorFileDecoder {
    public String decode(String file_name){
        if(!Normalizer.isNormalized(file_name, Normalizer.Form.NFC)){
            file_name = Normalizer.normalize(file_name, Normalizer.Form.NFC);
        }
        return file_name;
    }
}
