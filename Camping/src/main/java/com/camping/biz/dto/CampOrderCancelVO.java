package com.camping.biz.dto;

import java.sql.Date;

import lombok.*;

@Getter
@Setter
@ToString
public class CampOrderCancelVO {
	private int cseq;
	private int oseq;
    private String usersid;
    private String camp_name;
    private String camp_zone;
    private Date indate;
    private Date outdate;
    private String order_name;   
    private int order_people;   
    private String order_phone;  
    private String order_email;  
    private int total_price;  
    private int cancelprice;   
    private int cancelnet; 
    private Date cancel_date; 
    private String reason; 
    private String status;
}
