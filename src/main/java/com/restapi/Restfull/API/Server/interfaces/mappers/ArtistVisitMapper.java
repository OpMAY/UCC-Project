package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.ArtistVisit;

import java.util.Date;
import java.util.List;

public interface ArtistVisitMapper {
    void insertVisit(ArtistVisit artistVisit);

    List<ArtistVisit> getArtistVisitByArtistNo(int artist_no, Date visit_date);

    ArtistVisit getArtistVisit(int artist_no, int user_no, Date visit_date);
}
