package com.camping.biz.camporder;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

import utils.Criteria;

public interface CampOrderService {
	
	// �������� ���
	public void insertCampOrder(CampOrderVO vo);
	
	// ���ึ��ó���� ���� üũ�� ��¥ �������� �������� ��ȸ
	public List<CampOrderVO> getCampOrderList(String indate);

	
	// �����ȣ�� �������� ���೻�� �Ѱ� ��ȸ
	public CampOrderVO getMyCampOrder(int oseq);
	
	// ȸ�� id�� �������� �� ������ ���� ��ȸ
	public int countMyOrderList(String user_id);
	
	// ����¡ ó��(1~10���� ���) / ���� ���� ���
	public List<CampOrderVO> getMyListWithPaging(Criteria criteria, String user_id);



	/*
	 * ������ ���
	 */
	// ���� ���� ��ü ���� ��Ȳ ���� ��ȸ
	public int countOrderList(String camp_name);
	
	// �������� ����¡ó��
	public List<CampOrderVO> getListWithPaging(Criteria criteria, String camp_name);
	
	// ��ü���� �� ������Ȳ ������ȸ
	public int countAllOrderList();
	
	// ��ü ���೻�� ����¡ó��
	public List<CampOrderVO> getAllListWithPaging(Criteria criteria);

	// ���೻�� �Ѱ� ��ȸ
	public CampOrderVO getCampOrder(int oseq);
	
	// ������� ����
	public void updateOrderStatus(int oseq);
	
	// ���೻�� ����
	public void deleteOrderByOseq(int oseq);
}
