package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Board;

import java.util.List;

public interface BoardMapper {
    List<Board> getBoardListByArtistNo(int artist_no);

    List<Board> getBoardListByArtistNoForRefresh(int artist_no, int start_index, int end_index);

    Board getBoardByBoardNo(int board_no);

    void insertBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(int board_no);

    void updateBoardByComment(Board board);

    void updateBoardByLike(Board board);

    void updateBoardByVisit(Board board);

    void updateBoardByFankok(Board board);

    List<Board> getRecentBoardList();

    List<Board> searchBoard(String query, int start_index, int end_index);

    List<Board> getBoardListSortByRegDate(int start_index, int end_index);

    List<Board> getBoardListSortByFanNumber(int start_index, int end_index);

    List<Board> getBoardListSortByTitle(int start_index, int end_index);

    void insertFiles(Board board);
}
