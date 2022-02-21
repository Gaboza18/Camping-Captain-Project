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
	
	// 예약정보 등록
	public void insertCampOrder(CampOrderVO vo) {
		mybatis.insert("mappings.campOrder-mapping.insertCampOrder", vo);
	}
	
	// 예약마감처리를 위해 체크인 날짜 기준으로 예약정보 조회
	public List<CampOrderVO> getCampOrderList(String indate){
		return mybatis.selectList("mappings.campOrder-mapping.getCampOrderList", indate);
	}
	
	// 캠핑장 예약 조회(고객 Id)
	public List<CampOrderVO> getAllCampOrderList(String user_id){
		return mybatis.selectList("mappings.campOrder-mapping.getAllCampOrderList", user_id);
	}

}
