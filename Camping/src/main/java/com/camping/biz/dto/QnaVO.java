package com.camping.biz.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {
	
	private int qseq; // 1대1 질문 번호
	private String subject; // 제목
	private String content; // 문의 내용
	private String reply; // 답변내용
	private String id; // 작성자 아이디
	private String rep; // 답변 유무
	private Timestamp indate; // 작성일 
	
}
