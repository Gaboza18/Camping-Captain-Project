package com.camping.biz.temporderImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.dao.TempOrderDAO;
import com.camping.biz.dto.TempOrderVO;
import com.camping.biz.temporder.TempOrderService;

@Service("tempOrderService")
public class TempOrderServiceImpl implements TempOrderService {

	@Autowired
	private TempOrderDAO tDao;

	@Override
	public void insertTempOrder(TempOrderVO vo) {
		tDao.insertTempOrder(vo);
	}

	@Override
	public TempOrderVO getTempOrder(String temp_id) {
		return tDao.getTempOrder(temp_id);
	}

	@Override
	public void deleteTempOrder(String temp_id) {
		tDao.deleteTempOrder(temp_id);
	}
	
}
