package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.EntryComment;

import java.util.Collection;
import java.util.List;

public interface EntryCommentMapper {
    List<EntryComment> getCommentListByEntryNoRefresh(int entry_no, String reg_date, int entry_comment_no);

    List<EntryComment> getCommentListByUserNo(int user_no);

    void insertComment(EntryComment entryComment);

    void deleteComment(int entry_comment_no);

    EntryComment getEntryCommentByCommentNo(int entry_comment_no);

    void updateComment(EntryComment entryComment);

    List<EntryComment> getCommentNumberByEntryNo(int entry_no);

    List<EntryComment> getCommentListByEntryNo(int entry_no);

    void updateAllCommentUserInfo(int user_no, String commenter_name, String profile_img);
}
