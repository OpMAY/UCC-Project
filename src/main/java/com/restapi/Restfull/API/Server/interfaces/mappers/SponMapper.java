package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Spon;

import java.util.Date;
import java.util.List;

public interface SponMapper {
    void insertSpon(Spon spon);
    List<Spon> getSponList();
    List<Spon> getSponListByArtistNo(int artist_no);
    List<Spon> getSponListByUserNo(int user_no);
    List<Spon> getSponListByBoardNo(int board_no);
    Spon getSponAfterSpon(int user_no, int artist_no, Date spon_date);
}
