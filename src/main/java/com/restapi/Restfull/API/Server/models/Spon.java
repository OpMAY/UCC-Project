package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Spon {
    private int spon_no;
    private int user_no;
    private int artist_no;
    private String spon_date;
    private int price;
    private String status;
    private String type;
    private int board_no;
    private String purchase_unique;
    private String receipt_id;
    private String purchase_data;
    private String purchase_status;
}
