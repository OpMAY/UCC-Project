package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Artist;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ArtistMapper {
    List<Artist> getAllArtists();

    Artist getArtistByUserNo(int user_no);

    Artist getArtistByArtistNo(int artist_no);

    void insertArtist(Artist artist);

    void updateArtist(Artist artist);

    void deleteArtist(int artist_no);

    List<Artist> getArtistListByPopular(List<Integer> artistList);

    List<Artist> getNewArtistList(List<Integer> artistList);

    List<Artist> searchArtist(String search);

    List<Artist> searchArtistLimit(String search);

    List<Artist> getAllArtistListSortByRecentRefresh(int artist_no, String recent_act_date);

    List<Artist> getAllArtistListSortByNameRefresh(int artist_no, String artist_name);

    List<Artist> getAllArtistListSortByFanNumRefresh(int artist_no, int fan_number);

    List<Artist> getAllArtistListSortByRecent();

    List<Artist> getAllArtistListSortByName();

    List<Artist> getAllArtistListSortByFanNum();

    void updateArtistPush(int artist_no, boolean loudsourcing_push);

    List<Artist> getSubscribedArtistListSortRecent(@Param("artist_list") ArrayList<Integer> artist_list);

    List<Artist> getSubscribedArtistListSortFankok(@Param("artist_list") ArrayList<Integer> artist_list);

    List<Artist> getSubscribedArtistListSortName(@Param("artist_list") ArrayList<Integer> artist_list);

    List<Artist> getAllArtistForCDN();
}
