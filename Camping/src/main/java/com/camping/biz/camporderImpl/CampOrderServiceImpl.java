package com.camping.biz.camporderImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.camporder.CampOrderService;
import com.camping.biz.dao.CampOrderDAO;
import com.camping.biz.dto.CampOrderVO;

@Service("campOrderService")
public class CampOrderServiceImpl implements CampOrderService {

	@Autowired
	private CampOrderDAO cDao;
	
	@Override
	public void insertCampOrder(CampOrderVO vo) {
		cDao.insertCampOrder(vo);
	}

	@Override
	public List<CampOrderVO> getCampOrderList(String indate) {
		return cDao.getCampOrderList(indate);
	}

	@Override
	public List<CampOrderVO> getAllCampOrderList(String user_id) {
		return cDao.getAllCampOrderList(user_id);
	}

}
