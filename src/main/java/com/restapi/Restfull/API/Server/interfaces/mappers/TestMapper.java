package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Test;

public interface TestMapper {
    int updateTest(Test test);

    void insertTest(Test test);
}
