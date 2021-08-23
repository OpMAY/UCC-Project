package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.Search;

import java.util.List;

public interface SearchMapper {
    List<Search> getKeywords();

    void deleteByWord(String originalWord);

    void insertKeyword(Search search);
}
