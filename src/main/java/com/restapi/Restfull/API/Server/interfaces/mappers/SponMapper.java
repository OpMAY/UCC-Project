package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.ArtistSponTotal;
import com.restapi.Restfull.API.Server.models.Spon;

import java.util.List;

public interface SponMapper {
    void insertSpon(Spon spon);

    List<Spon> getSponList();

    List<Spon> getSponListByArtistNo(int artist_no);

    List<Spon> getSponListByUserNo(int user_no);

    List<Spon> getSponListByBoardNo(int board_no);

    List<Spon> getSponAfterSpon(int user_no, int artist_no);

    Spon getSponBySponNo(int spon_no);

    void updateSponByApply(Spon spon);

    void updateSponBySend(Spon spon);

    void deleteSpon(int spon_no);

    List<Spon> getSponListStatusPurchase();

    List<Spon> getSponListStatusApply();

    List<Spon> getSponListStatusSend();

    List<Spon> getSponListStatusComplete();

    void updateSponByPurchaseUpdate(Spon spon);

    boolean isExistAppleReceipt(String receipt_id);

    Spon getSponByReceiptIdForApple(String receipt_id);

    Spon getSponByPurchaseToken(String purchase_token);

    List<ArtistSponTotal> getMonthlySponAmountForArtist(String year, String month, int artist_no);

    List<ArtistSponTotal> getPurchasedSponAmountForArtistAdmin(int artist_no);

    List<Spon> getSponListForIncomePlatform();

    List<Spon> getSponListForSendInDuration(String start_date, String end_date);

    List<ArtistSponTotal> getSponListForArtistSend(int artist_no, String start_date, String end_date);

    List<Spon> getSponListForSendByArtistNo(int artist_no);
}
