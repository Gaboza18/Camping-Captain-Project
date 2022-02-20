package com.camping.biz.users;

import java.util.List;

import com.camping.biz.dto.UsersVO;

public interface UsersService {
	// DAO에서 객체 Users id 받아오기 - > 구현은 Impl에서
	public UsersVO getUsers(String id);
	public int confirmID(String id);
	public int loginID(UsersVO vo);
	public void insertUsers(UsersVO vo);
	public List<UsersVO> listUsers(String name);
	
	//회원삭제
	public void deleteId(String id)throws Exception;
	
	// 회원정보 수정
	
	public void updateUser(UsersVO vo);
		
		
	
	
	//public void deleteId(UsersVO vo) throws Exception;
	
}
