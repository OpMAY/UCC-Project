package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.LoudSourcing;

import java.util.List;

public interface LoudSourcingMapper {
    List<LoudSourcing> getLoudSourcingListSortAllRefresh(int loudsourcing_no, String revise_date);

    LoudSourcing getLoudSourcingByLoudsourcingNo(int loudsourcing_no);

    void insertLoudSourcing(LoudSourcing loudSourcing);

    void deleteLoudSourcing(int loudsourcing_no);

    List<LoudSourcing> searchLoudSourcing(String query);

    List<LoudSourcing> getLoudSourcingListByStatusProcessRefresh(String status, int loudsourcing_no, String process_start_date);

    List<LoudSourcing> getLoudSourcingListByStatusEndRefresh(String status, int loudsourcing_no, String end_date);

    List<LoudSourcing> getLoudSourcingListByStatusRecruitmentRefresh(String status, int loudsourcing_no, String start_date);

    List<LoudSourcing> getLoudSourcingListByStatusProcess(String status);

    List<LoudSourcing> getLoudSourcingListByStatusEnd(String status);

    List<LoudSourcing> getLoudSourcingListByStatusRecruitment(String status);

    List<LoudSourcing> getLoudSourcingListSortAll();

    List<LoudSourcing> searchLoudSourcingByStatusProcessRefresh(String status, String query, int loudsourcing_no, String process_start_date);

    List<LoudSourcing> searchLoudSourcingByStatusEndRefresh(String status, String query, int loudsourcing_no, String end_date);

    List<LoudSourcing> searchLoudSourcingByStatusRecruitmentRefresh(String status, String query, int loudsourcing_no, String start_date);

    List<LoudSourcing> searchLoudSourcingAllRefresh(String query, int loudsourcing_no, String revise_date);

    List<LoudSourcing> searchLoudSourcingByStatusProcess(String status, String query);

    List<LoudSourcing> searchLoudSourcingByStatusEnd(String status, String query);

    List<LoudSourcing> searchLoudSourcingByStatusRecruitment(String status, String query);

    List<LoudSourcing> searchLoudSourcingAll(String query);

    List<LoudSourcing> getRecentLSAdminMain(String status);

    List<LoudSourcing> getLoudsourcingForCDN();
}
