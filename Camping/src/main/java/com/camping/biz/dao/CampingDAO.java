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
	
	// 예약가능한 캠핑장 리스트 조회
	public List<CampingVO> campingList(int camp_id) {
		return mybatis.selectList("mappings.camping-mapping.campingList", camp_id);
	}
}
