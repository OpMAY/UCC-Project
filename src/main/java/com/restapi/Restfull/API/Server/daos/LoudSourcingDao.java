package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.LoudSourcingMapper;
import com.restapi.Restfull.API.Server.models.LoudSourcing;
import com.restapi.Restfull.API.Server.response.DataListSortType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoudSourcingDao {
    private SqlSession sqlSession;

    @Autowired
    private SqlSession ROSqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public LoudSourcing getLoudSourcingByLoudsourcingNo(int loudsourcing_no) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        return loudSourcingMapper.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
    }

    public void insertLoudSourcing(LoudSourcing loudSourcing) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        loudSourcingMapper.insertLoudSourcing(loudSourcing);
    }

    public void deleteLoudSourcing(int loudsourcing_no) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        loudSourcingMapper.deleteLoudSourcing(loudsourcing_no);
    }

    public List<LoudSourcing> searchLoudSourcingRefresh(String sort, String query, LoudSourcing loudSourcing) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        String sqlSearch = "%" + query + "%";
        switch (sort) {
            case DataListSortType.SORT_BY_RECRUITMENT:
                return loudSourcingMapper.searchLoudSourcingByStatusRecruitmentRefresh(sort, sqlSearch, loudSourcing.getLoudsourcing_no(), loudSourcing.getStart_date());
            case DataListSortType.SORT_BY_PROCESS:
                return loudSourcingMapper.searchLoudSourcingByStatusProcessRefresh(sort, sqlSearch, loudSourcing.getLoudsourcing_no(), loudSourcing.getProcess_start_date());
            case DataListSortType.SORT_BY_END:
                return loudSourcingMapper.searchLoudSourcingByStatusEndRefresh(sort, sqlSearch, loudSourcing.getLoudsourcing_no(), loudSourcing.getEnd_date());
            default:
                return loudSourcingMapper.searchLoudSourcingAllRefresh(sqlSearch, loudSourcing.getLoudsourcing_no(), loudSourcing.getRevise_date());
        }
    }

    public List<LoudSourcing> getLoudSourcingListByStatusRefresh(String sort, LoudSourcing loudSourcing) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        switch (sort) {
            case DataListSortType.SORT_BY_RECRUITMENT:
                return loudSourcingMapper.getLoudSourcingListByStatusRecruitmentRefresh(sort, loudSourcing.getLoudsourcing_no(), loudSourcing.getStart_date());
            case DataListSortType.SORT_BY_PROCESS:
                return loudSourcingMapper.getLoudSourcingListByStatusProcessRefresh(sort, loudSourcing.getLoudsourcing_no(), loudSourcing.getProcess_start_date());
            case DataListSortType.SORT_BY_END:
                return loudSourcingMapper.getLoudSourcingListByStatusEndRefresh(sort, loudSourcing.getLoudsourcing_no(), loudSourcing.getEnd_date());
            default:
                return loudSourcingMapper.getLoudSourcingListSortAllRefresh(loudSourcing.getLoudsourcing_no(), loudSourcing.getRevise_date());
        }
    }

    public List<LoudSourcing> getRecentLSAdminMain() {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        return loudSourcingMapper.getRecentLSAdminMain("recruitment");
    }

    public List<LoudSourcing> getLoudSourcingListByStatusAdmin(String status) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        return loudSourcingMapper.getRecentLSAdminMain(status);
    }

    public List<LoudSourcing> getLoudSourcingListByStatus(String sort) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        switch (sort) {
            case DataListSortType.SORT_BY_RECRUITMENT:
                return loudSourcingMapper.getLoudSourcingListByStatusRecruitment(sort);
            case DataListSortType.SORT_BY_PROCESS:
                return loudSourcingMapper.getLoudSourcingListByStatusProcess(sort);
            case DataListSortType.SORT_BY_END:
                return loudSourcingMapper.getLoudSourcingListByStatusEnd(sort);
            default:
                return loudSourcingMapper.getLoudSourcingListSortAll();
        }
    }

    public List<LoudSourcing> searchLoudSourcing(String sort, String query) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        String sqlSearch = "%" + query + "%";
        switch (sort) {
            case DataListSortType.SORT_BY_RECRUITMENT:
                return loudSourcingMapper.searchLoudSourcingByStatusRecruitment(sort, sqlSearch);
            case DataListSortType.SORT_BY_PROCESS:
                return loudSourcingMapper.searchLoudSourcingByStatusProcess(sort, sqlSearch);
            case DataListSortType.SORT_BY_END:
                return loudSourcingMapper.searchLoudSourcingByStatusEnd(sort, sqlSearch);
            default:
                return loudSourcingMapper.searchLoudSourcingAll(sqlSearch);
        }
    }

    public List<LoudSourcing> getLoudsourcingForCDN() {
        LoudSourcingMapper loudSourcingMapper = ROSqlSession.getMapper(LoudSourcingMapper.class);
        return loudSourcingMapper.getLoudsourcingForCDN();
    }

    public void updateLoudSourcing(LoudSourcing loudSourcing) {
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        loudSourcingMapper.updateLoudSourcing(loudSourcing);
    }

    public void updateAdvertiser(LoudSourcing loudSourcing){
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        loudSourcingMapper.updateAdvertiser(loudSourcing);
    }
}
