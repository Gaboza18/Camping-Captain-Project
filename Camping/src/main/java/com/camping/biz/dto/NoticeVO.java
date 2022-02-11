package com.camping.biz.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeVO {
	private int nseq;
	private String title;
	private String content;
	private String admin_name;
	private Timestamp indate;
	private int count;
	private String searchCondition;
	private String searchKeyword;
}
