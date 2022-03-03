package com.camping.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.PayVO;

@Repository
public class PayDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 실결제 테이블 insert
	public void insertPay(PayVO vo) {
		mybatis.insert("mappings.pay-mapping.insertPay", vo);
	}
	
	// 실결제 한건 조회
	public PayVO getPay(PayVO vo) {
		return mybatis.selectOne("mappings.pay-mapping.getPay", vo);
	}
}
