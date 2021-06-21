package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.User;

public interface UserMapper {
    void insertUser(User user);
    User selectUserByEmail(String email, String sns);
}
