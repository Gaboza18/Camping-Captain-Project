package com.camping.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.TempOrderVO;

@Repository
public class TempOrderDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 임시 예약정보 insert
	public void insertTempOrder(TempOrderVO vo) {
		mybatis.insert("mappings.tempOrder-mapping.insertTempOrder", vo);
	}
	
	// 임시 예약번호를 기준으로 한건 조회
	public TempOrderVO getTempOrder(String temp_id) {
		return mybatis.selectOne("mappings.tempOrder-mapping.getTempOrder", temp_id);
	}
	
	// 실결제 성공 시 임시테이블 삭제
	public void deleteTempOrder(String temp_id) {
		mybatis.delete("mappings.tempOrder-mapping.deleteTempOrder", temp_id);
	}
	
}
