package com.restapi.Restfull.API.Server.models;

import lombok.Data;

@Data
public class SponSummaryArtistResponse {
    private Artist artist;
    private int spon_amount;
    private int spon_count;
}
