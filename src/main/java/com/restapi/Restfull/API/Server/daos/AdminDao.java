package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.AdminMapper;
import com.restapi.Restfull.API.Server.models.Admin;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public Admin loginAdmin(String id, String password) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        return adminMapper.loginAdmin(id, password);
    }

    public void testEncode(String decode){
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        adminMapper.testEncode(decode);
    }

}
