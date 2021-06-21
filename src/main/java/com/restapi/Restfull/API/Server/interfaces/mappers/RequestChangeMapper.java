package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.RequestChange;

import java.util.List;

public interface RequestChangeMapper {
    List<RequestChange> getAllRequests();
    RequestChange getRequestByUserNo(int user_no);
    void insertRequest(RequestChange rc);
}
