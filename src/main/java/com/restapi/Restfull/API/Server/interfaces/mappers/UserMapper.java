package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.User;

import java.util.List;

public interface UserMapper {
    void insertUser(User user);

    User selectUserByAccessToken(User user);

    void deleteUser(int user_no);

    User selectUserByUserNo(int user_no);

    void updateUser(User user);

    List<User> selectUserBySNS(String sns);

    List<User> getAllUserList();
}
