package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.EntryVote;

import java.util.List;

public interface EntryVoteMapper {
    void insertVote(EntryVote entryVote);

    void deleteVote(int entry_no, int user_no);

    List<EntryVote> getEntryVoteByEntryNo(int entry_no);

    List<EntryVote> getEntryVoteByUserNo(int user_no);

    EntryVote getEntryVote(int user_no, int entry_no);
}
