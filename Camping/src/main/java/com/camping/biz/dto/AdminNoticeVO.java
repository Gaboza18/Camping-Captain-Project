package com.camping.biz.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 

@Getter
@Setter
@ToString
public class AdminNoticeVO {

	private int aseq; // 관리자  공지사항 시퀀스 번호
	private String title; // 관리자 공지사항 제목
	private String content; // 내용
	private String admin_name; // 작성자(관리자)
	private Timestamp indate; // 작성일
	private int count; // 조회수
	private String id; // 관리자 id
	private int status; // 관리자 구분
	private String searchCondition;
	private String searchKeyword;
	
}
