package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.EntryComment;

import java.util.List;

public interface EntryCommentMapper {
    List<EntryComment> getCommentListByEntryNo(int entry_no, int start_index, int end_index);

    List<EntryComment> getCommentListByUserNo(int user_no);

    void insertComment(EntryComment entryComment);

    void deleteComment(int entry_comment_no);

    EntryComment getEntryCommentByCommentNo(int entry_comment_no);
}
