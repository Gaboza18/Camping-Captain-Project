package com.camping.biz.dto;

import java.sql.Date;

import lombok.*;

@Getter
@Setter
@ToString
public class PayVO {
    private String tid;        // ���� Id
    private String TotPrice;   // �����ݾ�
    private Date regdate;      // ������
    private String user_id;    // ��ID
    private String camp_zone;  // ķ���� ����
    private Date indate; 	   // �Խ� ��¥
}
