package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.BannerAd;

import java.util.List;

public interface BannerAdMapper {
    List<BannerAd> getBannerList(boolean status);

    void insertBanner(BannerAd bannerAd);

    void updateBanner(BannerAd bannerAd);

    void deleteBanner(int banner_ad_no);

    List<BannerAd> getBannerForCDN();

    List<BannerAd> getActiveBannerList();

    List<BannerAd> getDisableBannerList();

    BannerAd getBannerAdByBannerAdNo(int banner_ad_no);
}
