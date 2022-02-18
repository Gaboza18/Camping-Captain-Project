package com.camping.biz.dao;

import java.sql.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderVO;

@Repository
public class CampOrderDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// �������� insert
	public void insertCampOrder(CampOrderVO vo) {
		mybatis.insert("mappings.campOrder-mapping.insertCampOrder", vo);
	}
	
	// �������� ��ȸ
	public List<CampOrderVO> getCampOrderList(String indate){
		return mybatis.selectList("mappings.campOrder-mapping.getCampOrder", indate);
	}
	
}
