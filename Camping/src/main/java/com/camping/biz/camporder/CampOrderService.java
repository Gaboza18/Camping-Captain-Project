package com.camping.biz.camporder;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

public interface CampOrderService {
	
	// �������� ���
	public void insertCampOrder(CampOrderVO vo);
	
	// ���ึ��ó���� ���� üũ�� ��¥ �������� �������� ��ȸ
	public List<CampOrderVO> getCampOrderList(String indate);
	
	// ķ���� ���� ��ȸ(�� Id)
	public List<CampOrderVO> getAllCampOrderList(String user_id);
}
