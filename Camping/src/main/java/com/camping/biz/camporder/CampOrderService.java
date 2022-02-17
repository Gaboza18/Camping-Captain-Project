package com.camping.biz.camporder;

import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.CampingVO;

public interface CampOrderService {
	
	//예약가능한 구역 예약을 위한 정보
		public CampingVO getCamping(String camp_zone);
	
	// 게시글 insert
	public void insertCampOrder(CampOrderVO vo);
}
