package com.camping.biz.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {
	
	private int qseq; // 1��1 ���� ��ȣ
	private String subject; // ����
	private String content; // ���� ����
	private String reply; // �亯����
	private String id; // �ۼ��� ���̵�
	private String rep; // �亯 ����
	private Timestamp indate; // �ۼ��� 
	
}
