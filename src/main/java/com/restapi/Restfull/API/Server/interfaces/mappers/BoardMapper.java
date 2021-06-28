package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Board;

import java.util.List;

public interface BoardMapper {
    List<Board> getBoardListByArtistNo(int artist_no);

    List<Board> getBoardList();

    Board getBoardByBoardNo(int board_no);

    void insertBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(int board_no);

    void updateBoardByComment(Board board);

    void updateBoardByLike(Board board);

    void updateBoardByVisit(Board board);

    List<Board> getRecentBoardList();

    List<Board> searchBoard(String query);
}
