package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class PurchaseData {
    private String action;
    private String receipt_id;
    private String amount;
    private String vat;
    private String tax_free;
    private String card_no;
    private String card_code;
    private String card_name;
    private String card_quota;
    private String billing_key;
    private String params;
    private String item_name;
    private String order_id;
    private String url;
    private int price;
    private String payment_name;
    private String pg_name;
    private String pg;
    private String method;
    private String method_name;
    private String payment_group;
    private String payment_group_name;
    private String requested_at;
    private String purchased_at;
    private int status;
}
