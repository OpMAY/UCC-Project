package com.restapi.Restfull.API.Server.daos;

import com.restapi.Restfull.API.Server.interfaces.mappers.LoudSourcingMapper;
import com.restapi.Restfull.API.Server.models.LoudSourcing;
import com.restapi.Restfull.API.Server.response.DataListSortType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoudSourcingDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        if (this.sqlSession == null)
            this.sqlSession = sqlSession;
    }

    public LoudSourcing getLoudSourcingByLoudsourcingNo(int loudsourcing_no){
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        return loudSourcingMapper.getLoudSourcingByLoudsourcingNo(loudsourcing_no);
    }

    public void insertLoudSourcing(LoudSourcing loudSourcing){
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        loudSourcingMapper.insertLoudSourcing(loudSourcing);
    }

    public void deleteLoudSourcing(int loudsourcing_no){
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        loudSourcingMapper.deleteLoudSourcing(loudsourcing_no);
    }

    public List<LoudSourcing> searchLoudSourcing(String sort, String query, int start_index){
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        String sqlSearch = "%" + query + "%";
        switch (sort) {
            case DataListSortType.SORT_BY_RECRUITMENT:
                return loudSourcingMapper.searchLoudSourcingByStatusRecruitment(sort, sqlSearch, start_index, start_index + 10);
            case DataListSortType.SORT_BY_PROCESS:
                return loudSourcingMapper.searchLoudSourcingByStatusProcess(sort, sqlSearch, start_index, start_index + 10);
            case DataListSortType.SORT_BY_END:
                return loudSourcingMapper.searchLoudSourcingByStatusEnd(sort, sqlSearch, start_index, start_index + 10);
            default:
                return loudSourcingMapper.searchLoudSourcingAll(sqlSearch, start_index, start_index + 10);
        }
    }

    public List<LoudSourcing> getLoudSourcingListByStatus(String sort, int start_index){
        LoudSourcingMapper loudSourcingMapper = sqlSession.getMapper(LoudSourcingMapper.class);
        switch (sort) {
            case DataListSortType.SORT_BY_RECRUITMENT:
                return loudSourcingMapper.getLoudSourcingListByStatusRecruitment(sort, start_index, start_index + 10);
            case DataListSortType.SORT_BY_PROCESS:
                return loudSourcingMapper.getLoudSourcingListByStatusProcess(sort, start_index, start_index + 10);
            case DataListSortType.SORT_BY_END:
                return loudSourcingMapper.getLoudSourcingListByStatusEnd(sort, start_index, start_index + 10);
            default:
                return loudSourcingMapper.getLoudSourcingListSortAll(start_index, start_index + 10);
        }
    }

}
