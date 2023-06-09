package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.BoardComment;

import java.util.Collection;
import java.util.List;

public interface BoardCommentMapper {
    List<BoardComment> getCommentListByBoardNoRefresh(int board_no, String reg_date, int comment_no);

    List<BoardComment> getCommentListByBoardNo(int board_no);

    List<BoardComment> getCommentListByUserNo(int user_no);

    void insertComment(BoardComment boardComment);

    void deleteComment(int comment_no);

    void updateComment(BoardComment boardComment);

    BoardComment getCommentByCommentNo(int comment_no);

    List<BoardComment> getCommentNumberByBoardNo(int board_no);

    void updateAllCommentUserInfo(int user_no, String commenter_name, String profile_img);
}
