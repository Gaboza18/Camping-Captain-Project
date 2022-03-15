package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampingVO;

@Repository
public class CampingDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 캠핑 지점 이름  조회 
	public String getCampName(int camp_id) {
		return mybatis.selectOne("mappings.camping-mapping.getCampName",camp_id);
	}
	
	//예약가능한 구역 예약을 위한 정보
	public CampingVO getCamping(String camp_zone) {
		return mybatis.selectOne("mappings.camping-mapping.getCamping",camp_zone);
	}
	
	// 예약가능한 캠핑장 리스트 조회
	public List<CampingVO> campingList(int camp_id) {
		return mybatis.selectList("mappings.camping-mapping.campingList", camp_id);
	}
}
