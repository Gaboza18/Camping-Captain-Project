package com.camping.biz.admin;

import com.camping.biz.dto.AdminVO;

public interface AdminService {

	public AdminVO getAdmin(String id);
	
	public int confirmID(String id);
	
	public int loginID(AdminVO vo);
}
