package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.SubscribeMapper;
import com.restapi.Restfull.API.Server.models.Subscribe;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscribeDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertSubscribe(Subscribe subscribe){
        SubscribeMapper subscribeMapper = sqlSession.getMapper(SubscribeMapper.class);
        subscribeMapper.insertSubscribe(subscribe);
    }

    public void deleteSubscribe(int user_no, int artist_no){
        SubscribeMapper subscribeMapper = sqlSession.getMapper(SubscribeMapper.class);
        subscribeMapper.deleteSubscribe(user_no, artist_no);
    }

    public Subscribe getSubscribeInfoByUserNoANDArtistNo(int user_no, int artist_no){
        SubscribeMapper subscribeMapper = sqlSession.getMapper(SubscribeMapper.class);
        return subscribeMapper.getSubscribeInfoByUserNoANDArtistNo(user_no, artist_no);
    }

    public List<Subscribe> getSubscribeListByArtistNo(int artist_no){
        SubscribeMapper subscribeMapper = sqlSession.getMapper(SubscribeMapper.class);
        return subscribeMapper.getSubscribeListByArtistNo(artist_no);
    }

    public List<Subscribe> getSubscribeListByUserNo(int user_no){
        SubscribeMapper subscribeMapper = sqlSession.getMapper(SubscribeMapper.class);
        return subscribeMapper.getSubscribeListByUserNo(user_no);
    }

}
