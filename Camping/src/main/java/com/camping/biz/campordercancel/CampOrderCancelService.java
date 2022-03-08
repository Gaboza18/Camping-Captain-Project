package com.camping.biz.campordercancel;

import java.util.List;

import com.camping.biz.dto.CampOrderCancelVO;
import com.camping.biz.dto.CampOrderVO;

import utils.Criteria;

public interface CampOrderCancelService {
	
	// �������������� ������ȸ �� ��� ��û �� ������̺� insert
	public void insertCancelMyOrder(CampOrderCancelVO vo);
	
	// ������� ��Ҹ�� ���� ��ȸ
	public int  countMyNonCancelList(String usersid);
	
	// �������������� �� ��ҳ��� ��ȸ �� �����Ȳ ���� ��ȸ
	public int countMyCancelList(String usersid);
	
	// �� ��ҳ��� ����¡ ó��
	public List<CampOrderCancelVO> getMyListWithPaging(Criteria criteria, String usersid);
	
	
	/*
	 * ������ ���
	 */
	// ���� ���� ��ü ���� ��� ��Ȳ ���� ��ȸ
	public int countOrderList(String camp_name);
	
	// ��� ���� ����¡ó��
	public List<CampOrderCancelVO> getListWithPaging(Criteria criteria, String camp_name);
	
	// ��ҳ��� �Ѱ� ��ȸ
	public CampOrderCancelVO getCancelOrder(int cseq);
	
	// �����ڰ� ���� ��� �� ��� ���̺� insert
	public void insertOrderCancel(CampOrderCancelVO vo);
	
	// ���������� ����
	public void updateCancelStatus(int cseq);
}
