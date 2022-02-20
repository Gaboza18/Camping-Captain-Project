package com.camping.biz.camporder;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

public interface CampOrderService {
	
	//
	public void insertCampOrder(CampOrderVO vo);

	//
	public List<CampOrderVO> getCampOrderList(String indate);
	
	// ķ���� ���� ��Ȳ(ȸ�� ���̵�� ��ȸ)
	public List<CampOrderVO> getAllCampOrderList(String user_id);
}
