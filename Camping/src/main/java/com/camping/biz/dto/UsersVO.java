package com.camping.biz.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersVO {
	private String id;
	private String password;
	private String name;
	private String email;
	private int birthday;
	private String phone;
	private Timestamp regdate;
	private String status;
	private String emailchk;
}
