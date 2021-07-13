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

    public void registerUser(User user) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insertUser(user);
    }

    public User loginUser(User user) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUserByAccessToken(user);
    }

    public User selectUserByUserNo(int user_no) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUserByUserNo(user_no);
    }

    public void deleteUser(int user_no) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUser(user_no);
    }

    public void updateUser(User user){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUser(user);
    }
}
