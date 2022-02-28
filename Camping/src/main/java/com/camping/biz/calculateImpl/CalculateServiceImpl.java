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
	public List<CampOrderVO> calculateYear() {
		return calDao.calculateYear();
	}

	@Override
	public List<CampOrderVO> calculateMonth() {
		return calDao.calculateMonth();
	}

	@Override
	public List<CampOrderVO> branchCalculateYear(String name) {
		return calDao.branchCalculateYear(name);
	}

	@Override
	public List<CampOrderVO> branchCalculateMonth(String name) {
		return calDao.branchCalculateMonth(name);
	}

}
