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

    public List<Board> getBoardListByArtistNoForRefresh(int artist_no, int board_no, String reg_date) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        return boardMapper.getBoardListByArtistNoForRefresh(artist_no, board_no, reg_date);
    }

    public List<Board> getBoardListByArtistNoLimit(int artist_no){
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        return boardMapper.getBoardListByArtistNoLimit(artist_no);
    }

    public List<Board> getBoardList(String sort) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return boardMapper.getBoardListSortByRegDate();
        else if (sort.equals(DataListSortType.SORT_BY_FANKOK))
            return boardMapper.getBoardListSortByFanNumber();
        else
            return boardMapper.getBoardListSortByTitle();
    }

    public List<Board> getBoardListRefresh(String sort, Board board) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        if (sort.equals(DataListSortType.SORT_BY_RECENT))
            return boardMapper.getBoardListSortByRegDateRefresh(board.getReg_date(), board.getBoard_no());
        else if (sort.equals(DataListSortType.SORT_BY_FANKOK))
            return boardMapper.getBoardListSortByFanNumberRefresh(board.getFan_number(), board.getBoard_no());
        else
            return boardMapper.getBoardListSortByTitleRefresh(board.getTitle(), board.getBoard_no());
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

    public List<Board> SearchBoard(String query) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        String sqlSearch = "%" + query + "%";
        return boardMapper.searchBoard(sqlSearch);
    }

    public List<Board> SearchBoardRefresh(String query, Board board) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        String sqlSearch = "%" + query + "%";
        return boardMapper.searchBoardRefresh(sqlSearch, board.getBoard_no());
    }

    public void insertFiles(Board board) {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        boardMapper.insertFiles(board);
    }

    public List<Board> getBoardForCDN() {
        BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
        return boardMapper.getBoardForCDN();
    }
}
