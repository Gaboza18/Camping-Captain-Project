package com.camping.biz.users;

import java.util.List;

import com.camping.biz.dto.UsersVO;

public interface UsersService {
	// DAO���� ��ü Users id �޾ƿ��� - > ������ Impl����
	public UsersVO getUsers(String id);
	public int confirmID(String id);
	public int loginID(UsersVO vo);
	public void insertUsers(UsersVO vo);
	public List<UsersVO> listUsers(String name);
	
	//ȸ������
	public void deleteId(String id)throws Exception;
	
	// ȸ������ ����
	
	public void updateUser(UsersVO vo);
		
		
	
	
	//public void deleteId(UsersVO vo) throws Exception;
	
}
