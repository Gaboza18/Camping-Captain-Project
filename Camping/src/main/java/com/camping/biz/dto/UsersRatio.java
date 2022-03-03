package com.camping.biz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersRatio { // 회원 남,녀 성별 비율
	
	private String gender; // 회원 성별
	private int count; // 회원(남,녀)회원수
}
