package com.camping.biz.campordercancel;

import com.camping.biz.dto.CampOrderCancelVO;

public interface CampOrderCancelService {
	
	// �������������� ������ȸ �� ��� ��û �� ������̺� insert
	public void insertCancelMyOrder(CampOrderCancelVO vo);
	
	
	
	/*
	 * ������ ���
	 */
	
	// �����ڰ� ���� ��� �� ��� ���̺� insert
	public void insertOrderCancel(CampOrderCancelVO vo);
}
