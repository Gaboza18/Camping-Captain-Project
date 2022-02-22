package com.camping.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.AdminVO;

@Repository
public class AdminDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public AdminVO getAdmin(String id) {
		return mybatis.selectOne("mappings.admin-mapping.getAdmin", id);
	}
	
	public int confirmID(String id) {
		String password = mybatis.selectOne("mappings.admin-mapping.confirmID",id);
		
		if (password != null) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public int loginID(AdminVO vo) {
		int result = -1;
		
		String pwd_in_db = mybatis.selectOne("mappings.admin-mapping.confirmID", vo.getId());
		
		if (pwd_in_db == null) {
			result = -1;
		} else if(vo.getPassword().equals(pwd_in_db)) {
			result = 1;
		} else {
			result = 0;
		}
		
		return result;
	}
}
