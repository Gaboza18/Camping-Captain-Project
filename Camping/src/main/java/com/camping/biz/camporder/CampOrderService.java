package com.camping.biz.camporder;

import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.CampingVO;

public interface CampOrderService {
	
	//���డ���� ���� ������ ���� ����
		public CampingVO getCamping(String camp_zone);
	
	// �Խñ� insert
	public void insertCampOrder(CampOrderVO vo);
}
