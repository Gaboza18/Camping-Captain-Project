package com.camping.biz.usersImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.dao.UsersDAO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDAO uDao;
	
	@Override
	public UsersVO getUsers(String id) {
		
		return uDao.getUsers(id);
	}

	@Override
	public int confirmID(String id) {
		
		return uDao.confirmID(id);
	}

	@Override
	public int loginID(UsersVO vo) {
		
		return uDao.loginID(vo);
	}

	@Override
	public void insertUsers(UsersVO vo) {
		uDao.insertUsers(vo);

	}
	
	

	@Override
	public List<UsersVO> listUsers(String name) {

		return uDao.listUsers(name);
	}
	
	//회원 삭제


	@Override
	public void deleteId(String id) throws Exception {
		uDao.deleteId(id);
		
		
	}

	@Override
	public void updateUser(UsersVO vo) {
	
		uDao.updateUser(vo);
		
		
	}

}
