package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.UserMapper;
import com.restapi.Restfull.API.Server.models.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void registerUser(User user){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insertUser(user);
    }

    public User loginUser(String email, String sns){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByEmail(email, sns);
        return user;
    }
}
