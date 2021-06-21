package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistDao;
import com.restapi.Restfull.API.Server.models.Artist;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class ArtistService {
    @Autowired
    SqlSession sqlSession;

    @Autowired
    ArtistDao artistDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Artist> getAllArtists(){
        artistDao.setSession(sqlSession);
        return artistDao.getAllArtists();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Artist getArtistByUserNo(int user_no){
        artistDao.setSession(sqlSession);
        return artistDao.getArtistByUserNo(user_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Artist getArtistByArtistNo(int artist_no){
        artistDao.setSession(sqlSession);
        return artistDao.getArtistByArtistNo(artist_no);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertArtist(Artist artist){
        artistDao.setSession(sqlSession);
        artistDao.insertArtist(artist);
    }

    //TODO 아티스트 목록 정렬 방식에 따라 서버에서 그에 맞게 데이터를 뿌려줄지, 앱단에서 처리할지?
}
