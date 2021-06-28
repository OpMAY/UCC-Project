package com.restapi.Restfull.API.Server.services;

import com.restapi.Restfull.API.Server.daos.ArtistDao;
import com.restapi.Restfull.API.Server.daos.BoardDao;
import com.restapi.Restfull.API.Server.daos.PortfolioDao;
import com.restapi.Restfull.API.Server.daos.SearchDao;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.Board;
import com.restapi.Restfull.API.Server.models.Portfolio;
import com.restapi.Restfull.API.Server.models.Search;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class SearchService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private PortfolioDao portfolioDao;

    @Autowired
    private BoardDao boardDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity getKeywords() {
        try {
            Message message = new Message();
            searchDao.setSession(sqlSession);
            List<Search> searchList = searchDao.getKeywords();
            List<String> keywordList = new ArrayList<>();
            for (Search search : searchList) {
                String keyword = search.getWord();
                keywordList.add(keyword);
            }
            message.put("Keywords", keywordList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_SEARCH_KEYWORDS, message.getHashMap("GetKeywords()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity Search(String query, int start_index) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);

            List<Artist> artistList = artistDao.SearchArtistLimit(query);
            List<Board> boardList = boardDao.SearchBoard(query);
            List<Portfolio> portfolioList = portfolioDao.SearchPortfolioLimit(query);
            List<Board> resBoardList = new ArrayList<>();
            for(int i = start_index; i < start_index + 10; i++){
                if(boardList.size() <= i)
                    break;
                resBoardList.add(boardList.get(i));
            }
            message.put("Artists", artistList);
            message.put("Boards", resBoardList);
            message.put("Portfolios", portfolioList);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SEARCH_SUCCESS, message.getHashMap("Search()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
