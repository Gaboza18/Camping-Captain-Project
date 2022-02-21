package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderVO;

@Repository
public class CampOrderDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 예약 정보 insert
	public void insertCampOrder(CampOrderVO vo) {
		mybatis.insert("mappings.campOrder-mapping.insertCampOrder", vo);
	}
	
	// 예약정보 조회
	public List<CampOrderVO> getCampOrderList(String indate){
		return mybatis.selectList("mappings.campOrder-mapping.getCampOrderList", indate);
	}

	public List<CampOrderVO> getAllCampOrderList(String user_id){
		return mybatis.selectList("mappings.campOrder-mapping.getAllCampOrderList", user_id);
	}

}
