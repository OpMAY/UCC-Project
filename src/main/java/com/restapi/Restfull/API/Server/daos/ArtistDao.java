package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.ArtistMapper;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.response.DataListSortType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtistDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<Artist> getAllArtists() {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getAllArtists();
    }

    public Artist getArtistByUserNo(int user_no) {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getArtistByUserNo(user_no);
    }

    public Artist getArtistByArtistNo(int artist_no) {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getArtistByArtistNo(artist_no);
    }

    public void insertArtist(Artist artist) {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        artistMapper.insertArtist(artist);
    }

    public void updateArtist(Artist artist) {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        artistMapper.updateArtist(artist);
    }

    public void deleteArtist(int artist_no) {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        artistMapper.deleteArtist(artist_no);
    }

    public List<Artist> getArtistListByPopular() {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getArtistListByPopular();
    }

    public List<Artist> getNewArtistList() {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getNewArtistList();
    }

    public List<Artist> SearchArtist(String search) {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        String sqlSearch = "%" + search + "%";
        return artistMapper.searchArtist(sqlSearch);
    }

    public List<Artist> SearchArtistLimit(String search) {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        String sqlSearch = "%" + search + "%";
        return artistMapper.searchArtistLimit(sqlSearch);
    }

    public List<Artist> getAllArtistRefresh(int artist_no, String sort, Artist artist) {
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return artistMapper.getAllArtistListSortByRecentRefresh(artist_no, artist.getRecent_act_date());
        else if (sort.equals(DataListSortType.SORT_BY_WORD))
            return artistMapper.getAllArtistListSortByNameRefresh(artist_no, artist.getArtist_name());
        else
            return artistMapper.getAllArtistListSortByFanNumRefresh(artist_no, artist.getFan_number());
    }

    public List<Artist> getAllArtistLimit(String sort){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return artistMapper.getAllArtistListSortByRecent();
        else if (sort.equals(DataListSortType.SORT_BY_WORD))
            return artistMapper.getAllArtistListSortByName();
        else
            return artistMapper.getAllArtistListSortByFanNum();
    }

    public void updateArtistPush(int artist_no, boolean loudsourcing_push){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        artistMapper.updateArtistPush(artist_no, loudsourcing_push);
    }

    public List<Artist> getSubscribedArtistListSortRecent(ArrayList<Integer> artist_list){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getSubscribedArtistListSortRecent(artist_list);
    }
}
