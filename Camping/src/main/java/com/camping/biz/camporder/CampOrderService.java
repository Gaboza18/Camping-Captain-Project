package com.camping.biz.camporder;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

public interface CampOrderService {
	
	// 게시글 insert
	public void insertCampOrder(CampOrderVO vo);
	
	// 예약정보 조회
	public List<CampOrderVO> getCampOrderList(String indate);
	
	// 캠핑장 예약 현황(회원 아이디로 조회)
	public List<CampOrderVO> getAllCampOrderList(String user_id);
}
