package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.models.Test;
import com.restapi.Restfull.API.Server.interfaces.mappers.TestMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertTest() {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        Test test = new Test();
        test.setNo(1);
        test.setTestcol("test_updated");
        testMapper.insertTest(test);
    }

    public void updateTest(Test test) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.updateTest(test);
    }
}
