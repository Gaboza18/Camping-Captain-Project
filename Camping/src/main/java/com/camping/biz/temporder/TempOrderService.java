package com.camping.biz.temporder;


import com.camping.biz.dto.TempOrderVO;

public interface TempOrderService {
	
	// �ӽ� �������� insert
	public void insertTempOrder(TempOrderVO vo);

	// �ӽ� �����ȣ�� �������� �Ѱ� ��ȸ
	public TempOrderVO getTempOrder(String temp_id);
	
	// �ǰ��� ���� �� �ӽ����̺� ����
	public void deleteTempOrder(String temp_id);
}
