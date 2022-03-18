package com.camping.biz.calculateImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.calculate.CalculateService;
import com.camping.biz.dao.CalculateDAO;
import com.camping.biz.dto.CampOrderVO;

@Service("calculateService")
public class CalculateServiceImpl implements CalculateService {
	
	@Autowired
	private CalculateDAO calDao; 
	
	@Override
	public List<CampOrderVO> calculateDay() {	
		return calDao.calculateDay();
	}
	
	@Override
	public List<CampOrderVO> branchCalculateYear(String name) {
		return calDao.branchCalculateYear(name);
	}

	@Override
	public List<CampOrderVO> branchCalculateMonth(String name) {
		return calDao.branchCalculateMonth(name);
	}

	@Override
	public List<CampOrderVO> branchCalculateDay(String name) {
		return calDao.branchCalculateDay(name);
	}

	@Override
	public List<CampOrderVO> searchCalculateYear(String startYear, String endYear) {
		return calDao.searchCalculateYear(startYear, endYear);
	}

	@Override
	public List<CampOrderVO> searchCalculateYearMonth(String startYearMonth, String endYearMonth) {
		return calDao.searchCalculateYearMonth(startYearMonth, endYearMonth);
	}

}
