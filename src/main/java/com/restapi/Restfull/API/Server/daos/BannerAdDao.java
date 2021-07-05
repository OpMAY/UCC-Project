package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.BannerAdMapper;
import com.restapi.Restfull.API.Server.models.BannerAd;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerAdDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<BannerAd> getBannerList(){
        BannerAdMapper bannerAdMapper = sqlSession.getMapper(BannerAdMapper.class);
        return bannerAdMapper.getBannerList(false);
    }

}
