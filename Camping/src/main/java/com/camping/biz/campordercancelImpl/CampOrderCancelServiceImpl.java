package com.camping.biz.campordercancelImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.campordercancel.CampOrderCancelService;
import com.camping.biz.dao.CampOrderCancelDAO;
import com.camping.biz.dto.CampOrderCancelVO;

@Service("campOrderCancelService")
public class CampOrderCancelServiceImpl implements CampOrderCancelService {

	@Autowired
	private CampOrderCancelDAO cDao;
	
	@Override
	public void insertCancelMyOrder(CampOrderCancelVO vo) {
		cDao.insertCancelMyOrder(vo);
	}

	@Override
	public void insertOrderCancel(CampOrderCancelVO vo) {
		cDao.insertOrderCancel(vo);
	}

}
