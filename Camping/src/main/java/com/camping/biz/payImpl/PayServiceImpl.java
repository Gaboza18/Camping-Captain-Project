package com.camping.biz.payImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.dao.PayDAO;
import com.camping.biz.dto.PayVO;
import com.camping.biz.pay.PayService;

@Service("payService")
public class PayServiceImpl implements PayService {

	@Autowired
	private PayDAO pDao;
	
	@Override
	public void insertPay(PayVO vo) {
		pDao.insertPay(vo);
	}

	@Override
	public PayVO getPay(PayVO vo) {
		return pDao.getPay(vo);
	}

	@Override
	public void deletePay(String tid) {
			pDao.deletePay(tid);
	}

}
