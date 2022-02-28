package com.camping.biz.calculate;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

public interface CalculateService {
	
	// 모든 지점 연도별 정산 조회(총관리자)
	public List<CampOrderVO> calculateYear();
	
	// 모든 지점 월별 정산 조회(총관리자)
	public List<CampOrderVO> calculateMonth();
	
	// 각 지점 연도별 정산 조회(지점 관리자)
	public List<CampOrderVO> branchCalculateYear(String name);
	
	// 각 지점 월 별 정산 조회(지점 관리자)
	public List<CampOrderVO> branchCalculateMonth(String name);
}
