package com.camping.biz.campordercancel;

import com.camping.biz.dto.CampOrderCancelVO;

public interface CampOrderCancelService {
	
	// 마이페이지에서 예약조회 후 취소 신청 시 취소테이블에 insert
	public void insertCancelMyOrder(CampOrderCancelVO vo);
	
	
	
	/*
	 * 관리자 기능
	 */
	
	// 관리자가 예약 취소 시 취소 테이블에 insert
	public void insertOrderCancel(CampOrderCancelVO vo);
}
