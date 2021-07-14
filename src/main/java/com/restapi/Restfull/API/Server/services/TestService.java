package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.TestDao;
import com.restapi.Restfull.API.Server.models.Test;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class TestService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private TestDao testDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void sqlRollbackTest() {
        testDao.setSession(sqlSession);
        Test test = new Test();
        test.setNo(1);
        test.setTestcol("updated");
        testDao.updateTest(test);
        testDao.insertTest();
    }
}
