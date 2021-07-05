package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.LoudSourcing;

import java.util.List;

public interface LoudSourcingMapper {
    List<LoudSourcing> getLoudSourcingListSortAll(int start_index, int end_index);

    LoudSourcing getLoudSourcingByLoudsourcingNo(int loudsourcing_no);

    void insertLoudSourcing(LoudSourcing loudSourcing);

    void deleteLoudSourcing(int loudsourcing_no);

    List<LoudSourcing> searchLoudSourcing(String query);

    List<LoudSourcing> getLoudSourcingListByStatusProcess(String status, int start_index, int end_index);

    List<LoudSourcing> getLoudSourcingListByStatusEnd(String status, int start_index, int end_index);

    List<LoudSourcing> getLoudSourcingListByStatusRecruitment(String status, int start_index, int end_index);

    List<LoudSourcing> searchLoudSourcingByStatusProcess(String status, String query, int start_index, int end_index);

    List<LoudSourcing> searchLoudSourcingByStatusEnd(String status, String query, int start_index, int end_index);

    List<LoudSourcing> searchLoudSourcingByStatusRecruitment(String status, String query, int start_index, int end_index);

    List<LoudSourcing> searchLoudSourcingAll(String query, int start_index, int end_index);
}
