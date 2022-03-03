package com.camping.biz.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminNoticeVO {

	private int aseq;
	private String title;
	private String content;
	private String admin_name;
	private Timestamp indate;
	private int count;
	private String id;
	private int status;
}
