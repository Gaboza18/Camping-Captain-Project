package com.camping.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderCancelVO;

@Repository
public class CampOrderCancelDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 마이페이지에서 예약조회 후 취소 신청 시 취소테이블에 insert
	public void insertCancelMyOrder(CampOrderCancelVO vo) {
		mybatis.insert("mappings.campOrderCancel-mapping.insertCancelMyOrder", vo);
	}
	
	
	
	/*
	 * 관리자 기능
	 */
	
	// 관리자가 예약 취소 시 취소 테이블에 insert
	public void insertOrderCancel(CampOrderCancelVO vo) {
		mybatis.insert("mappings.campOrderCancel-mapping.insertOrderCancel", vo);
	}
}
