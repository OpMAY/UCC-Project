package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Subscribe;

import java.util.List;

public interface SubscribeMapper {
    void insertSubscribe(Subscribe subscribe);
    void deleteSubscribe(int user_no, int artist_no);
    Subscribe getSubscribeInfoByUserNoANDArtistNo(int user_no, int artist_no);
    List<Subscribe> getSubscribeListByArtistNo(int artist_no);
    List<Subscribe> getSubscribeListByUserNo(int user_no);
}
