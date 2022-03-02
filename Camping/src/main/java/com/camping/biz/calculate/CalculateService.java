package com.camping.biz.calculate;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

public interface CalculateService {
		
	// 모든 지점 월별 정산 조회(총관리자)
	public List<CampOrderVO> calculateMonth();
	
	// 모든 지점 일일 정산 조회(총관리자)
	public List<CampOrderVO> calculateDay();
	
	// 각 지점 연도별 정산 조회(지점 관리자)
	public List<CampOrderVO> branchCalculateYear(String name);
	
	// 각 지점 월 별 정산 조회(지점 관리자)
	public List<CampOrderVO> branchCalculateMonth(String name);
	
	// 각 지점 일일 별 정산 조회(지점 관리자)
	public List<CampOrderVO> branchCalculateDay(String name);
	
	// 모든 지점 조회할 년도 '시작년도' ~ '끝나는 년도' 사이 정산 조회(총관리자)
	public List<CampOrderVO> searchCalculateYear(String startYear, String endYear);
	
}
