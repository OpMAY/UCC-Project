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
            message.put("keywords", keywordList);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_SEARCH_KEYWORDS, message.getHashMap("GetKeywords()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity Search(String query, int last_index) {
        try {
            Message message = new Message();
            artistDao.setSession(sqlSession);
            boardDao.setSession(sqlSession);
            portfolioDao.setSession(sqlSession);

            //LIMIT 15
            List<Artist> artistList = artistDao.SearchArtistLimit(query);

            //LIMIT 15
            List<Portfolio> portfolioList = portfolioDao.SearchPortfolioLimit(query);
            if (last_index > -1) {
                //LIMIT start_index + 10
                if(last_index == 0) {
                    List<Board> boardList = boardDao.SearchBoard(query);
                    message.put("boards", boardList);
                    if(boardList.size() > 0)
                        message.put("last_index", boardList.get(boardList.size() - 1).getBoard_no());
                } else {
                    Board board = boardDao.getBoardByBoardNo(last_index);
                    if(board == null){
                        return new ResponseEntity(DefaultRes.res(StatusCode.RETRY_RELOAD, ResMessage.NO_CONTENT_DETECTED), HttpStatus.OK);
                    }
                    List<Board> boardList = boardDao.SearchBoardRefresh(query, board);
                    message.put("boards", boardList);
                    if(boardList.size() > 0)
                        message.put("last_index", boardList.get(boardList.size() - 1).getBoard_no());
                }

            } else {
                message.put("artists", artistList);
                message.put("portfolios", portfolioList);
            }

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.SEARCH_SUCCESS, message.getHashMap("Search()")), HttpStatus.OK);
        } catch (JSONException e) {
            throw new BusinessException(e);
        }
    }
}
