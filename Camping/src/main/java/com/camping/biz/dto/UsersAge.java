package com.camping.biz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersAge {
	
	private String age; // 연령
	private int count; // n연령 회원 수(10,20,30,40대...회원 수)
	
}
