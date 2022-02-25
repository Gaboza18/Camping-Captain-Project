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
