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
    private String price;
    private boolean status;
    private String type;
    private int board_no;
    private String purchase_token;
    private String receipt_id;
    private boolean purchase_status;
    private boolean verify_status;
    private String apply_date;
    private String send_date;
    private String price_send;
    private String user_name;
    private String artist_name;
    private String platform;
    private String currency;
    private String product_id;
}
