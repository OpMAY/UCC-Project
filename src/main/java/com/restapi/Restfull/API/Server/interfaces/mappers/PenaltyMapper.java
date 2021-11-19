package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Penalty;

import java.util.List;

public interface PenaltyMapper {
    void insertPenalty(Penalty penalty);

    List<Penalty> getPenaltyListByUserNo(int user_no);

    List<Penalty> getPenaltyListByArtistNo(int artist_no);

    List<Penalty> getPenaltyList();

    void deletePenalty(int penalty_no);
}
