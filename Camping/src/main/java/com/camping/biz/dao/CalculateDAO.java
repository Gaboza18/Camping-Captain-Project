package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderVO;

@Repository
public class CalculateDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 연도 별 전체 정산내역 조회(총관리자)
	public List<CampOrderVO> calculateYear(){
		return mybatis.selectList("mappings.calculate-mapping.getAllpointCalculateYear");
	}
	
	// 월 별 전체 정산내역 조회(총관리자)
	public List<CampOrderVO> calculateMonth(){
		return mybatis.selectList("mappings.calculate-mapping.getAllpointCalculateMonth");
	}
	
	// 연도 별 각 지점 정산내역 조회(지점 관리자)
	public List<CampOrderVO> branchCalculateYear(String name){
		return mybatis.selectList("mappings.calculate-mapping.branchCalculateYear",name);
	}
	
	// 월 별 각 지점 정산내역 조회(지점 관리자)
	public List<CampOrderVO> branchCalculateMonth(String name){
		return mybatis.selectList("mappings.calculate-mapping.branchCalculateMonth",name);
	}
}
