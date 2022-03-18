package com.camping.biz.dto;

import lombok.*; 

@Getter
@Setter
@ToString
public class AdminVO {
	private String id;
	private String password;
	private String name;
	private String phone;
	private String email;
	private int status;
}
