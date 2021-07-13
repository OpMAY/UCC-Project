package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.AdminDao;
import com.restapi.Restfull.API.Server.models.Admin;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class AdminService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private AdminDao adminDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public Admin loginAdmin(String id, String password){
        adminDao.setSession(sqlSession);
        return adminDao.loginAdmin(id, password);
    }

}
