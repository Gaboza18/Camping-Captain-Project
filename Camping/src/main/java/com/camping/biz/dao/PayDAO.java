package com.camping.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.PayVO;

@Repository
public class PayDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// �ǰ��� ���̺� insert
	public void insertPay(PayVO vo) {
		mybatis.insert("mappings.pay-mapping.insertPay", vo);
	}
	
	// �ǰ��� �Ѱ� ��ȸ
	public PayVO getPay(PayVO vo) {
		return mybatis.selectOne("mappings.pay-mapping.getPay", vo);
	}
}
