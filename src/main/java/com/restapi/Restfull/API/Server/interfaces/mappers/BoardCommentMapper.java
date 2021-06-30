package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.BoardComment;

import java.util.List;

public interface BoardCommentMapper {
    List<BoardComment> getCommentListByBoardNo(int board_no, int start_index, int end_index);

    List<BoardComment> getCommentListByUserNo(int user_no);

    void insertComment(BoardComment boardComment);

    void deleteComment(int comment_no);

    BoardComment getCommentByCommentNo(int comment_no);
}
