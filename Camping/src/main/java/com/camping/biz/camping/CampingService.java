package com.camping.biz.camping;

import java.util.List;

import com.camping.biz.dto.CampingVO;

public interface CampingService {
	
	//���డ���� ���� ������ ���� ����
	public CampingVO getCamping(String camp_zone);
	
	// ���డ���� ķ���� ����Ʈ ��ȸ
	public List<CampingVO> campingList(int camp_id);
}
