package com.restapi.Restfull.API.Server.interfaces.mappers;

import com.restapi.Restfull.API.Server.models.ExchangeRate;

public interface ExchangeRateMapper {
    void insertExchangeRate(ExchangeRate exchangeRate);

    String getExchangeRate(String now);
}
