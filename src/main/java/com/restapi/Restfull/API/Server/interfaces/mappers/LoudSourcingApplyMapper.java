package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.LoudSourcingApply;

import java.util.List;

public interface LoudSourcingApplyMapper {
    LoudSourcingApply getLoudSourcingApplyByArtistNoAndLoudSourcingNo(int artist_no, int loudsourcing_no);

    List<LoudSourcingApply> getLoudSourcingApplyListByArtistNo(int artist_no);

    List<LoudSourcingApply> getLoudSourcingApplyListByLoudSourcingNo(int loudsourcing_no);

    void insertLoudSourcingApply(LoudSourcingApply loudSourcingApply);

    void deleteLoudSourcingApply(int artist_no, int loudsourcing_no);

    void updateApply(LoudSourcingApply loudSourcingApply);

    List<LoudSourcingApply> getEntryNum(int loudsourcing_no);

    void updateApplyForJudge(LoudSourcingApply apply);

    void updateApplyForEnd(LoudSourcingApply apply);

    List<LoudSourcingApply> getLoudSourcingApplyListByLoudSourcingNoPreSelected(int loudsourcing_no);

    List<LoudSourcingApply> getLoudSourcingApplyListByLoudSourcingNoUnPreSelected(int loudsourcing_no);

    List<LoudSourcingApply> getLoudSourcingApplyListByLoudSourcingNoSelected(int loudsourcing_no);
}
