package com.camping.biz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CalculateVO { // 정산 컬럼
	
	private String indate; // 예약날짜
	private String camp_name; // 지점 이름
	private long total_price; // 가격
	
}
