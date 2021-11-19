package com.restapi.Restfull.API.Server.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Data
@Getter
@Setter
@ToString
public class FAQ {
    private int faq_no;
    private String question;
    private String answer;
    private String img;
    private String reg_date;

    private ArrayList<String> imgList;
}
