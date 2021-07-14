package com.restapi.Restfull.API.Server.services;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.daos.InquiryDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.FileJson;
import com.restapi.Restfull.API.Server.models.Inquiry;
import com.restapi.Restfull.API.Server.models.Upload;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class InquiryService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private InquiryDao inquiryDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getInquiryList(int user_no, int start_index) {
        try {
            inquiryDao.setSession(sqlSession);
            Message message = new Message();
            List<Inquiry> myInquiryList = inquiryDao.getInquiryListByUserNo(user_no, start_index);
            for (Inquiry inquiry : myInquiryList) {
                Message file_msg = new Message();
                if (!inquiry.getFile().isEmpty()) {
                    String jsonString = inquiry.getFile();
                    Gson gson = new Gson();
                    FileJson[] fileJson = gson.fromJson(jsonString, FileJson[].class);
                    ArrayList<Upload> uploads = new ArrayList<>();
                    for (FileJson json : fileJson) {
                        uploads.add(new Upload(json.getName().substring(9), json.getUrl()));
                    }
                    file_msg.put("files", uploads);
                    inquiry.setFiles(file_msg.getMap());
                }
            }
            message.put("inquiries", myInquiryList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.USER_INQUIRY_LIST, message.getHashMap("GetInquiryList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity uploadInquiry(Inquiry inquiry, ArrayList<Upload> uploads) {
        try {
            inquiryDao.setSession(sqlSession);
            Message message = new Message();
            // Inquiry SET
            inquiry.set_answered(false);
            List<String> file_msgList = new ArrayList<>();
            for (Upload upload : uploads) {
                Gson gson = new Gson();
                FileJson fileJson = new FileJson();
                String name = upload.getName();
                String url = upload.getUrl();
                fileJson.setName(name);
                fileJson.setUrl(url);
                String jsonString = gson.toJson(fileJson);
                file_msgList.add(jsonString);
            }
            message.put("files", uploads);
            inquiry.setFile(file_msgList.toString());
            inquiryDao.insertInquiry(inquiry);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.INQUIRY_UPLOAD_SUCCESS, message.getHashMap("GetInquiryList()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}