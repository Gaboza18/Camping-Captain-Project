package com.camping.biz.admonnoticeImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camping.biz.dao.AdminNoticeDAO;
import com.camping.biz.adminnotice.AdminNotice;

import com.camping.biz.dto.AdminNoticeVO;

import utils.Criteria;



@Service("adminnoticeService")
public class AdminNoticeImpl implements AdminNotice {

	@Autowired
	private AdminNoticeDAO anDao;

	@Override
	public List<AdminNoticeVO> listNotice(AdminNoticeVO vo) {
		return anDao.listNotice(vo);
		
	}

	@Override
	public int updateViewCount(int aseq) {

		return anDao.updateViewCount(aseq);
	}

	@Override
	public List<AdminNoticeVO> getListWithPaging(Criteria criteria, String title) {
		
		return anDao.getListWithPaging(criteria, title);
	}
	
	
	
	}

	
	
	

