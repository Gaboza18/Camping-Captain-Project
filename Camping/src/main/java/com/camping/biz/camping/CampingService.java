package com.camping.biz.camping;

import java.util.List;

import com.camping.biz.dto.CampingVO;

public interface CampingService {
	
	//예약가능한 구역 예약을 위한 정보
	public CampingVO getCamping(String camp_zone);
	
	// 예약가능한 캠핑장 리스트 조회
	public List<CampingVO> campingList(int camp_id);
}
