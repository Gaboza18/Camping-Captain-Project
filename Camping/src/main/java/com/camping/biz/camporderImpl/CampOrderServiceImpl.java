package com.camping.biz.camporderImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.camporder.CampOrderService;
import com.camping.biz.dao.CampOrderDAO;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.CampingVO;

@Service("campOrderService")
public class CampOrderServiceImpl implements CampOrderService {

	@Autowired
	private CampOrderDAO cDao;

	@Override
	public CampingVO getCamping(String camp_zone) {
		return cDao.getCamping(camp_zone);
	}
	
	@Override
	public void insertCampOrder(CampOrderVO vo) {
		cDao.insertCampOrder(vo);
	}

}
