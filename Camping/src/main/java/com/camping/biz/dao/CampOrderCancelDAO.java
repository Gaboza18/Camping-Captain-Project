package com.camping.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderCancelVO;

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
	
	// �����ڰ� ���� ��� �� ��� ���̺� insert
	public void insertOrderCancel(CampOrderCancelVO vo) {
		mybatis.insert("mappings.campOrderCancel-mapping.insertOrderCancel", vo);
	}
}
