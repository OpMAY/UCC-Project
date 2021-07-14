package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.LoudSourcingEntry;

import java.util.List;

public interface LoudSourcingEntryMapper {
    List<LoudSourcingEntry> getEntryListByLoudSourcingNoSortTitle(int loudsourcing_no, int start_index, int end_index);

    List<LoudSourcingEntry> getEntryListByLoudSourcingNoSortFankok(int loudsourcing_no, int start_index, int end_index);

    List<LoudSourcingEntry> getEntryListByLoudSourcingNoSortRecent(int loudsourcing_no, int start_index, int end_index);

    List<LoudSourcingEntry> getEntryListByArtistNo(int artist_no);

    LoudSourcingEntry getEntryByEntryNo(int entry_no);

    LoudSourcingEntry getEntryByArtistNOAndLoudSourcingNo(int artist_no, int loudsourcing_no);

    void insertEntry(LoudSourcingEntry loudSourcingEntry);

    void updateEntry(LoudSourcingEntry loudSourcingEntry);

    void updateEntryByComment(LoudSourcingEntry loudSourcingEntry);

    void updateEntryByVote(LoudSourcingEntry loudSourcingEntry);

    void updateEntryByVisit(LoudSourcingEntry loudSourcingEntry);

    void updateEntryByFankok(LoudSourcingEntry loudSourcingEntry);

    void deleteEntry(int entry_no);

    List<LoudSourcingEntry> getEntryListNum(int loudsourcing_no);
}
