package com.camping.biz.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RealReviewVO {

	private int rseq;
	private String title;
	private int area;
	private String campingname;
	private int category;
	private String content;
	private Date indate;
	private String id;
	private int count;
	private String searchCondition;
	private String searchkeyword;

}
