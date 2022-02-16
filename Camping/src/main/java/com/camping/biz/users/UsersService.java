package com.camping.biz.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.camping.biz.dto.UsersVO;

public interface UsersService {
	// DAO���� ��ü Users id �޾ƿ��� - > ������ Impl����
	public UsersVO getUsers(String id);

	public int confirmID(String id);

	public int loginID(UsersVO vo);

	public void insertUsers(UsersVO vo);

	public List<UsersVO> listUsers(String name);

	public UsersVO findId(UsersVO vo); // ȸ�� ID ã��
 
	public int updatePwd(UsersVO vo); // ȸ�� Pwd ����
	
	public void sendEmail(UsersVO vo, String div); // ȸ�� Pwd ã�� �̸��� �߼�
	
	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException;

}
