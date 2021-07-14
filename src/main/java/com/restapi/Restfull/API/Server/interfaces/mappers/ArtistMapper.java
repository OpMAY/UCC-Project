package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Artist;

import java.util.List;

public interface ArtistMapper {
    List<Artist> getAllArtists();

    Artist getArtistByUserNo(int user_no);

    Artist getArtistByArtistNo(int artist_no);

    void insertArtist(Artist artist);

    void updateArtist(Artist artist);

    void deleteArtist(int artist_no);

    List<Artist> getArtistListByPopular();

    List<Artist> getNewArtistList();

    List<Artist> searchArtist(String search);

    List<Artist> searchArtistLimit(String search);

    List<Artist> getAllArtistListSortByRecentRefresh(int start_index, int end_index);

    List<Artist> getAllArtistListSortByNameRefresh(int start_index, int end_index);

    List<Artist> getAllArtistListSortByFanNumRefresh(int start_index, int end_index);

    void updateArtistPush(int artist_no, boolean loudsourcing_push);
}
