package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.BoardComment;

import java.util.Collection;
import java.util.List;

public interface BoardCommentMapper {
    List<BoardComment> getCommentListByBoardNo(int board_no, int start_index, int end_index);

    List<BoardComment> getCommentListByUserNo(int user_no);

    void insertComment(BoardComment boardComment);

    void deleteComment(int comment_no);

    void updateComment(BoardComment boardComment);

    BoardComment getCommentByCommentNo(int comment_no);

    List<BoardComment> getCommentNumberByBoardNo(int board_no);
}
