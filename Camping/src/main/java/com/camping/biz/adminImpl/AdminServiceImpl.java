package com.camping.biz.adminImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.admin.AdminService;
import com.camping.biz.dao.AdminDAO;
import com.camping.biz.dto.AdminVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO aDao;
	
	@Override
	public AdminVO getAdmin(String id) {
		return aDao.getAdmin(id);
	}

	@Override
	public int confirmID(String id) {
		return aDao.confirmID(id);
	}

	@Override
	public int loginID(AdminVO vo) {
		return aDao.loginID(vo);
	}

}
