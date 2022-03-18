package com.camping.biz.temporder;


import com.camping.biz.dto.TempOrderVO;

public interface TempOrderService {
	
	// 임시 예약정보 insert
	public void insertTempOrder(TempOrderVO vo);

	// 임시 예약번호를 기준으로 한건 조회
	public TempOrderVO getTempOrder(String temp_id);
	
	// 실결제 성공 시 임시테이블 삭제
	public void deleteTempOrder(String temp_id);
	
}
