package com.camping.biz.camporder;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

public interface CampOrderService {
	
	// �Խñ� insert
	public void insertCampOrder(CampOrderVO vo);
	
	// �������� ��ȸ
	public List<CampOrderVO> getCampOrderList(String indate);
}
