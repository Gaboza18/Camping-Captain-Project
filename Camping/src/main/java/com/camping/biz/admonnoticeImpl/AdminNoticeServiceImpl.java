package com.camping.biz.admonnoticeImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.adminnotice.AdminNoticeService;
import com.camping.biz.dao.AdminNoticeDAO;
import com.camping.biz.dto.AdminNoticeVO;

import utils.Criteria;

@Service("adminNoticeService")
public class AdminNoticeServiceImpl implements AdminNoticeService {
	
	@Autowired
	private AdminNoticeDAO adminNoticeDao;
	
	@Override
	public List<AdminNoticeVO> listNotice() {
		return adminNoticeDao.listNotice();
	}

	@Override
	public AdminNoticeVO detailNotice(int aseq) {
		return adminNoticeDao.detailNotice(aseq);
	}

	@Override
	public int updateViewCount(int aseq) {
		return adminNoticeDao.updateViewCount(aseq);
	}

	@Override
	public int countNoticeList(String title) {
		return adminNoticeDao.countNoticeList(title);
	}

	@Override
	public List<AdminNoticeVO> getListWithPaging(Criteria criteria, String title) {
		return adminNoticeDao.getListWithPaging(criteria, title);
	}

	@Override
	public void insertAdminNotice(AdminNoticeVO vo) {
		adminNoticeDao.insertAdminNotice(vo);
	}

	@Override
	public void updateAdminNotice(AdminNoticeVO vo) {
		adminNoticeDao.updateAdminNotice(vo);
	}

	@Override
	public void deleteAdminNotice(int aseq) {
		adminNoticeDao.deleteAdminNotice(aseq);
	}

}
