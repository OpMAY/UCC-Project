package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.ArtistMapper;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.response.DataListSortType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

    public List<Artist> getNewArtistList(){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getNewArtistList();
    }

    public List<Artist> SearchArtist(String search){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        String sqlSearch = "%" + search + "%";
        return artistMapper.searchArtist(sqlSearch);
    }

    public List<Artist> SearchArtistLimit(String search){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        String sqlSearch = "%" + search + "%";
        return artistMapper.searchArtistLimit(sqlSearch);
    }

    public List<Artist> getAllArtistRefresh(int start_index, String sort){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        if(sort.equals(DataListSortType.SORT_BY_RECENT))
            return artistMapper.getAllArtistListSortByRecentRefresh(start_index, start_index + 10);
        else if (sort.equals(DataListSortType.SORT_BY_WORD))
            return artistMapper.getAllArtistListSortByNameRefresh(start_index, start_index + 10);
        else
            return artistMapper.getAllArtistListSortByFanNumRefresh(start_index, start_index + 10);
    }

}
