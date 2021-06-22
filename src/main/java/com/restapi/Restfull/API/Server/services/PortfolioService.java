package com.restapi.Restfull.API.Server.services;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PortfolioService {
    @Autowired
    private SqlSession sqlSession;
}
