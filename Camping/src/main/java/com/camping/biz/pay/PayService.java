package com.camping.biz.pay;

import com.camping.biz.dto.PayVO;

public interface PayService {
	
	// �ǰ��� ���̺� insert
	public void insertPay(PayVO vo);
	
	// �ǰ��� �Ѱ� ��ȸ
	public PayVO getPay(PayVO vo);
}
