package com.camping.biz.pay;

import com.camping.biz.dto.PayVO;

public interface PayService {
	
	// 실결제 테이블 insert
	public void insertPay(PayVO vo);
	
	// 실결제 한건 조회
	public PayVO getPay(PayVO vo);
	
	// 결제 취소시 실결제 정보 삭제
	public void deletePay(String tid);
	
}
