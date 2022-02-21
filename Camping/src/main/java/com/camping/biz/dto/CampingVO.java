package com.camping.biz.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class CampingVO {
	private int camp_id;
	private String camp_name;
	private String camp_addr;
	private String camp_zone;
	private int base_people;
	private int max_people;
	private int weekdays_price;
	private int weekend_price;
	private String car_camp;
	private String image;
}
