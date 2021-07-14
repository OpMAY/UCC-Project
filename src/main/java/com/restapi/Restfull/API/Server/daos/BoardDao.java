package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.BoardMapper;
import com.restapi.Restfull.API.Server.models.Board;
import com.restapi.Restfull.API.Server.response.DataListSortType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public List<Board> getBoardListByArtistNo(int artist_no) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        return boardMapper.getBoardListByArtistNo(artist_no);
    }

    public List<Board> getBoardListByArtistNoForRefresh(int artist_no, int start_index, int end_index) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        return boardMapper.getBoardListByArtistNoForRefresh(artist_no, start_index, end_index);
    }

    public List<Board> getBoardList(String sort, int start_index) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return boardMapper.getBoardListSortByRegDate(start_index, start_index + 10);
        else if (sort.equals(DataListSortType.SORT_BY_FANKOK))
            return boardMapper.getBoardListSortByFanNumber(start_index, start_index + 10);
        else
            return boardMapper.getBoardListSortByTitle(start_index, start_index + 10);
    }

    public Board getBoardByBoardNo(int board_no) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        return boardMapper.getBoardByBoardNo(board_no);
    }

    public void insertBoard(Board board) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.insertBoard(board);
    }

    public void updateBoard(Board board) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.updateBoard(board);
    }

    public void deleteBoard(int board_no) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.deleteBoard(board_no);
    }

    public void updateBoardByComment(int board_no, int number) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        Board board = boardMapper.getBoardByBoardNo(board_no);
        board.setComment_number(number);
        boardMapper.updateBoardByComment(board);
    }

    public void updateBoardByLike(int board_no, int number) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        Board board = boardMapper.getBoardByBoardNo(board_no);
        board.setLike_number(number);
        boardMapper.updateBoardByLike(board);
    }

    public void updateBoardByVisit(Board board) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.updateBoardByVisit(board);
    }

    public void updateBoardByFankok(Board board) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.updateBoardByFankok(board);
    }

    public List<Board> getRecentBoardList() {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        return boardMapper.getRecentBoardList();
    }

    public List<Board> SearchBoard(String query, int start_index) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        String sqlSearch = "%" + query + "%";
        return boardMapper.searchBoard(sqlSearch, start_index, start_index + 10);
    }

    public void insertFiles(Board board) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.insertFiles(board);
    }
}
