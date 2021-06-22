package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Spon;

import java.util.List;

public interface SponMapper {
    void insertSpon(Spon spon);
    List<Spon> getSponList();
    List<Spon> getSponListByArtistNo(int artist_no);
    List<Spon> getSponListByUserNo(int user_no);

}
