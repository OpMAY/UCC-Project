package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.SearchMapper;
import com.restapi.Restfull.API.Server.models.Search;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<Search> getKeywords() {
        SearchMapper searchMapper = sqlSession.getMapper(SearchMapper.class);
        return searchMapper.getKeywords();
    }

    public void deleteByWord(String originalWord) {
        SearchMapper searchMapper = sqlSession.getMapper(SearchMapper.class);
        searchMapper.deleteByWord(originalWord);
    }

    public void insertKeyword(Search search) {
        SearchMapper searchMapper = sqlSession.getMapper(SearchMapper.class);
        searchMapper.insertKeyword(search);
    }
}
