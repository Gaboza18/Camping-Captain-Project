package com.camping.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.CampingVO;

@Repository
public class CampOrderDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//예약가능한 구역 예약을 위한 정보
	public CampingVO getCamping(String camp_zone) {
		return mybatis.selectOne("mappings.campOrder-mapping.getCamping",camp_zone);
	}
	
	// 예약정보 insert
	public void insertCampOrder(CampOrderVO vo) {
		mybatis.insert("mappings.campOrder-mapping.insertCampOrder", vo);
	}
	
}
