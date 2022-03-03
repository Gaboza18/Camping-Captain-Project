package com.camping.biz.temporder;


import com.camping.biz.dto.TempOrderVO;

public interface TempOrderService {
	
	// 임시 예약정보 insert
	public void insertTempOrder(TempOrderVO vo);
	
	public TempOrderVO getTempOrder(String temp_id);
}
