package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Admin;

public interface AdminMapper {
    Admin loginAdmin(String id, String password);

    void testEncode(String decode);
}
