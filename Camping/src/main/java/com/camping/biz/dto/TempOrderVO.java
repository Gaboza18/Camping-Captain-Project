package com.camping.biz.dto;

import java.sql.Date;

import lombok.*;

@Getter
@Setter
@ToString
public class TempOrderVO {
	private String temp_id;
	private String camp_name;
	private String camp_zone;
	private int total_price;
	private Date indate;
	private Date outdate;
	private String user_id;
	private String order_name;
	private int order_people;
	private String order_phone;
	private String order_email;
}
