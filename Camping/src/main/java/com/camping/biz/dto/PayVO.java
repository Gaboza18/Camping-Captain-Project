package com.camping.biz.dto;

import java.sql.Date;

import lombok.*;

@Getter
@Setter
@ToString
public class PayVO {
	
    private String tid;        // 승인 Id
    private String TotPrice;   // 결제금액
    private Date regdate;      // 결제일
    private String user_id;    // 고객ID
    private String camp_zone;  // 캠핑장 구역
    private Date indate; 	   // 입실 날짜
    
}
