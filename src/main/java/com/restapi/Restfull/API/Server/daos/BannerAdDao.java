package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.BannerAdMapper;
import com.restapi.Restfull.API.Server.models.BannerAd;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerAdDao {
    private SqlSession sqlSession;

    @Autowired
    private SqlSession ROSqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<BannerAd> getBannerList() {
        BannerAdMapper bannerAdMapper = sqlSession.getMapper(BannerAdMapper.class);
        return bannerAdMapper.getBannerList(true);
    }

    public List<BannerAd> getBannerForCDN() {
        BannerAdMapper bannerAdMapper = ROSqlSession.getMapper(BannerAdMapper.class);
        return bannerAdMapper.getBannerForCDN();
    }

    public List<BannerAd> getActiveBannerList() {
        BannerAdMapper bannerAdMapper = sqlSession.getMapper(BannerAdMapper.class);
        return bannerAdMapper.getActiveBannerList();
    }

    public List<BannerAd> getDisableBannerList() {
        BannerAdMapper bannerAdMapper = sqlSession.getMapper(BannerAdMapper.class);
        return bannerAdMapper.getDisableBannerList();
    }

    public void deleteBanner(int banner_ad_no) {
        BannerAdMapper bannerAdMapper = sqlSession.getMapper(BannerAdMapper.class);
        bannerAdMapper.deleteBanner(banner_ad_no);
    }

    public BannerAd getBannerAdByBannerAdNo(int banner_ad_no) {
        BannerAdMapper bannerAdMapper = sqlSession.getMapper(BannerAdMapper.class);
        return bannerAdMapper.getBannerAdByBannerAdNo(banner_ad_no);
    }

    public void updateBanner(BannerAd bannerAd) {
        BannerAdMapper bannerAdMapper = sqlSession.getMapper(BannerAdMapper.class);
        bannerAdMapper.updateBanner(bannerAd);
    }

    public void insertBanner(BannerAd bannerAd) {
        BannerAdMapper bannerAdMapper = sqlSession.getMapper(BannerAdMapper.class);
        bannerAdMapper.insertBanner(bannerAd);
    }
}
