package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.TestDao;
import com.restapi.Restfull.API.Server.daos.UserDao;
import com.restapi.Restfull.API.Server.models.User;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class UserService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void registerUser(User user){
        userDao.setSession(sqlSession);
        userDao.registerUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User loginUser(String email, String sns){
        userDao.setSession(sqlSession);
        User user = userDao.loginUser(email, sns);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(int user_no){
        userDao.setSession(sqlSession);
        userDao.deleteUser(user_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User selectUserByUserNo(int user_no){
        userDao.setSession(sqlSession);
        User user = userDao.selectUserByUserNo(user_no);
        return user;
    }
}
