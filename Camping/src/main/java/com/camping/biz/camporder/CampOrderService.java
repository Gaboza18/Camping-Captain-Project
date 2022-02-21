package com.camping.biz.camporder;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

public interface CampOrderService {
	
	// 예약정보 등록
	public void insertCampOrder(CampOrderVO vo);
	
	// 예약마감처리를 위해 체크인 날짜 기준으로 예약정보 조회
	public List<CampOrderVO> getCampOrderList(String indate);
	
	// 캠핑장 예약 조회(고객 Id)
	public List<CampOrderVO> getAllCampOrderList(String user_id);
}
