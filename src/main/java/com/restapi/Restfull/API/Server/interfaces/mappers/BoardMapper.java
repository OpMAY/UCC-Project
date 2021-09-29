package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Board;

import java.util.List;

public interface BoardMapper {
    List<Board> getBoardListByArtistNo(int artist_no);

    List<Board> getBoardListByArtistNoForRefresh(int artist_no, int board_no, String reg_date);

    List<Board> getBoardListByArtistNoLimit(int artist_no);

    Board getBoardByBoardNo(int board_no);

    void insertBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(int board_no);

    void updateBoardByComment(Board board);

    void updateBoardByLike(Board board);

    void updateBoardByVisit(Board board);

    void updateBoardByFankok(Board board);

    List<Board> getRecentBoardList();

    List<Board> searchBoard(String query);

    List<Board> searchBoardRefresh(String query, int board_no);

    List<Board> getBoardListSortByRegDateRefresh(String reg_date, int board_no);

    List<Board> getBoardListSortByFanNumberRefresh(int fan_number, int board_no);

    List<Board> getBoardListSortByTitleRefresh(String title, int board_no);

    List<Board> getBoardListSortByRegDate();

    List<Board> getBoardListSortByFanNumber();

    List<Board> getBoardListSortByTitle();

    void insertFiles(Board board);

    List<Board> getBoardForCDN();

    void updateBoardByPenalty(Board board);

    void updateContentProfile(int artist_no, String artist_name, String artist_profile_img);
}
