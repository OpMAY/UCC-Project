package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.RequestChangeMapper;
import com.restapi.Restfull.API.Server.models.RequestChange;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestChangeDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<RequestChange> getAllRequests(){
        RequestChangeMapper requestChangeMapper = sqlSession.getMapper(RequestChangeMapper.class);
        return requestChangeMapper.getAllRequests();
    }

    public RequestChange getRequestByUserNo(int user_no){
        RequestChangeMapper requestChangeMapper = sqlSession.getMapper(RequestChangeMapper.class);
        return requestChangeMapper.getRequestByUserNo(user_no);
    }

    public void insertRequest(RequestChange rc){
        RequestChangeMapper requestChangeMapper = sqlSession.getMapper(RequestChangeMapper.class);
        requestChangeMapper.insertRequest(rc);
    }
}
