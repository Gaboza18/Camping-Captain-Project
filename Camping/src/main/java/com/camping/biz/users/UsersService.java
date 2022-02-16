package com.camping.biz.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.camping.biz.dto.UsersVO;

public interface UsersService {
	// DAO에서 객체 Users id 받아오기 - > 구현은 Impl에서
	public UsersVO getUsers(String id);

	public int confirmID(String id);

	public int loginID(UsersVO vo);

	public void insertUsers(UsersVO vo);

	public List<UsersVO> listUsers(String name);

	public UsersVO findId(UsersVO vo); // 회원 ID 찾기
 
	public int updatePwd(UsersVO vo); // 회원 Pwd 변경
	
	public void sendEmail(UsersVO vo, String div); // 회원 Pwd 찾기 이메일 발송
	
	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException;

}
