package com.camping.biz.campordercancelImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.campordercancel.CampOrderCancelService;
import com.camping.biz.dao.CampOrderCancelDAO;
import com.camping.biz.dto.CampOrderCancelVO;
import com.camping.biz.dto.CampOrderVO;

import utils.Criteria;

@Service("campOrderCancelService")
public class CampOrderCancelServiceImpl implements CampOrderCancelService {

	@Autowired
	private CampOrderCancelDAO cDao;
	
	@Override
	public void insertCancelMyOrder(CampOrderCancelVO vo) {
		cDao.insertCancelMyOrder(vo);
	}

	@Override
	public int countMyCancelList(String usersid) {
		return cDao.countMyCancelList(usersid);
	}

	@Override
	public List<CampOrderCancelVO> getMyListWithPaging(Criteria criteria, String usersid) {
		return cDao.getMyListWithPaging(criteria, usersid);
	}

	@Override
	public int countOrderList(String camp_name) {
		return cDao.countOrderList(camp_name);
	}

	@Override
	public List<CampOrderCancelVO> getListWithPaging(Criteria criteria, String camp_name) {
		return cDao.getListWithPaging(criteria, camp_name);
	}

	@Override
	public int countAllOrderList() {
		return cDao.countAllOrderList();
	}

	@Override
	public List<CampOrderCancelVO> getAllListWithPaging(Criteria criteria) {
		return cDao.getAllListWithPaging(criteria);
	}

	@Override
	public CampOrderCancelVO getCancelOrder(int cseq) {
		return cDao.getCancelOrder(cseq);
	}

	@Override
	public void insertOrderCancel(CampOrderCancelVO vo) {
		cDao.insertOrderCancel(vo);
	}

	@Override
	public void updateCancelStatus(int cseq) {
		cDao.updateCancelStatus(cseq);
	}

	@Override
	public int countMyNonCancelList(String usersid) {
		return cDao.countMyNonCancelList(usersid);
	}

}
