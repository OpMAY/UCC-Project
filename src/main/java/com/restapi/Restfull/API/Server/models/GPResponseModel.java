package com.restapi.Restfull.API.Server.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GPResponseModel {
    private boolean consumptionState;
    private String developerPayload;
    private boolean purchaseState;
    private Long purchaseTimeMillis;
    private String purchaseTimeString;
}
