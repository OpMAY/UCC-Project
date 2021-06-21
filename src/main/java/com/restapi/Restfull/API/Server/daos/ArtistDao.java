package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.ArtistMapper;
import com.restapi.Restfull.API.Server.models.Artist;
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

    public List<Artist> getAllArtists(){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getAllArtists();
    }

    public Artist getArtistByUserNo(int user_no){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getArtistByUserNo(user_no);
    }

    public Artist getArtistByArtistNo(int artist_no){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        return artistMapper.getArtistByArtistNo(artist_no);
    }

    public void insertArtist(Artist artist){
        ArtistMapper artistMapper = sqlSession.getMapper(ArtistMapper.class);
        artistMapper.insertArtist(artist);
    }
}
