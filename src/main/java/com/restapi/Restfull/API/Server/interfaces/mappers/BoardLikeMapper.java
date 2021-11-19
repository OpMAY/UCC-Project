package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.BoardLike;

import java.util.List;

public interface BoardLikeMapper {
    void insertLike(BoardLike boardLike);

    void deleteLike(int board_no, int user_no);

    List<BoardLike> getBoardLikeByBoardNo(int board_no);

    List<BoardLike> getBoardLikeByUserNo(int user_no);

    BoardLike getBoardLike(int board_no, int user_no);
}
