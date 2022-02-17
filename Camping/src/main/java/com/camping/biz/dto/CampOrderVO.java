package com.camping.biz.dto;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@ToString
public class CampOrderVO {
	private int oseq;
	private String camp_name;
	private String camp_zone;
	private int total_price;
	private Timestamp indate;
	private Timestamp outdate;
	private String user_id;
	private String order_name;
	private int order_people;
	private String order_phone;
	private String order_email;
}
