package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.User;

public interface UserMapper {
    void insertUser(User user);

    User selectUserByEmail(User user);

    void deleteUser(int user_no);

    User selectUserByUserNo(int user_no);
}
