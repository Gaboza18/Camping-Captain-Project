package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderCancelVO;
import com.camping.biz.dto.CampOrderVO;

import utils.Criteria;

@Repository
public class CampOrderCancelDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// �������������� ������ȸ �� ��� ��û �� ������̺� insert
	public void insertCancelMyOrder(CampOrderCancelVO vo) {
		mybatis.insert("mappings.campOrderCancel-mapping.insertCancelMyOrder", vo);
	}
	
	// ������� ��Ҹ�� ���� ��ȸ
	public int  countMyNonCancelList(String usersid) {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.countMyNonCancelList", usersid);
	}
	
	// �� ��ҳ��� ��Ȳ ��� ���� ��ȸ
	public int countMyCancelList(String usersid) {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.countMyCancelList", usersid);
	}
	
	// ����¡ ó��(1~10���� ���)
	public List<CampOrderCancelVO> getMyListWithPaging(Criteria criteria, String usersid){
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("usersid", usersid);

		return mybatis.selectList("mappings.campOrderCancel-mapping.MylistWithPaging", map);
	}
	
	
	
	/*
	 * ������ ���
	 */
	// ���� ���� ��ü ���� ��� ��Ȳ ���� ��ȸ
	public int countOrderList(String camp_name) {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.countOrderList", camp_name);
	}
	
	// ��� ���� ����¡ó��
	public List<CampOrderCancelVO> getListWithPaging(Criteria criteria, String camp_name) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("camp_name", camp_name);

		return mybatis.selectList("mappings.campOrderCancel-mapping.listWithPaging", map);
	}
	
	// ��ҳ��� �Ѱ� ��ȸ
	public CampOrderCancelVO getCancelOrder(int cseq) {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.getCancelOrder", cseq);
	}
	
	// �����ڰ� ���� ��� �� ��� ���̺� insert
	public void insertOrderCancel(CampOrderCancelVO vo) {
		mybatis.insert("mappings.campOrderCancel-mapping.insertOrderCancel", vo);
	}
	
	// ���������� ����
	public void updateCancelStatus(int cseq) {
		mybatis.update("mappings.campOrderCancel-mapping.updateCancelStatus", cseq);
	}
}
