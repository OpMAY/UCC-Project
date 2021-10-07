package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.SponMapper;
import com.restapi.Restfull.API.Server.models.Spon;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SponDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public void insertSpon(Spon spon) {
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        sponMapper.insertSpon(spon);
    }

    public List<Spon> getSponList() {
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponList();
    }

    public List<Spon> getSponListByArtistNo(int artist_no) {
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponListByArtistNo(artist_no);
    }

    public List<Spon> getSponListByUserNo(int user_no) {
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponListByUserNo(user_no);
    }

    public List<Spon> getSponListByBoardNo(int board_no) {
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponListByBoardNo(board_no);
    }

    public List<Spon> getSponByArtistNoANDUserNo(int user_no, int artist_no) {
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponAfterSpon(user_no, artist_no);
    }

    public Spon getSponBySponNo(int spon_no) {
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponBySponNo(spon_no);
    }

    public void updateSponByApply(Spon spon){
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        sponMapper.updateSponByApply(spon);
    }

    public void updateSponBySend(Spon spon){
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        sponMapper.updateSponBySend(spon);
    }

    public void deleteSpon(int spon_no){
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        sponMapper.deleteSpon(spon_no);
    }

    public List<Spon> getSponListStatusPurchase(){
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponListStatusPurchase();
    }

    public List<Spon> getSponListStatusApply(){
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponListStatusApply();
    }

    public List<Spon> getSponListStatusSend(){
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponListStatusSend();
    }

    public List<Spon> getSponListStatusComplete(){
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        return sponMapper.getSponListStatusComplete();
    }

    public void updateSponByPurchaseUpdate(Spon spon) {
        SponMapper sponMapper = sqlSession.getMapper(SponMapper.class);
        sponMapper.updateSponByPurchaseUpdate(spon);
    }
}
