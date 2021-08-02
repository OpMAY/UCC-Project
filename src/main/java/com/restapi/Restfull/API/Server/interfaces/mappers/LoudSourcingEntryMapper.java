package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.LoudSourcingEntry;

import java.util.List;

public interface LoudSourcingEntryMapper {
    List<LoudSourcingEntry> getEntryListByLoudSourcingNoSortFankokRefresh(int loudsourcing_no, int entry_no, int fan_number);

    List<LoudSourcingEntry> getEntryListByLoudSourcingNoSortRecentRefresh(int loudsourcing_no, int entry_no, String reg_date);

    List<LoudSourcingEntry> getEntryListByLoudSourcingNoSortFankok(int loudsourcing_no);

    List<LoudSourcingEntry> getEntryListByLoudSourcingNoSortRecent(int loudsourcing_no);

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
