package com.camping.biz.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.camping.biz.dto.UsersAge;
import com.camping.biz.dto.UsersRatio;
import com.camping.biz.dto.UsersVO;

public interface UsersService {
	// DAO�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕泥� Users id �뜝�뙣�븘�슱�삕�뜝�룞�삕 - > �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 Impl�뜝�룞�삕�뜝�룞�삕
	public UsersVO getUsers(String id);

	public int confirmID(String id);

	public int loginID(UsersVO vo);

	public void insertUsers(UsersVO vo);

	public List<UsersVO> listUsers(String name);

	public UsersVO findId(UsersVO vo); // �쉶�뜝�룞�삕 ID 李얍뜝�룞�삕

	public int updatePwd(UsersVO vo); // �쉶�뜝�룞�삕 Pwd �뜝�룞�삕�뜝�룞�삕

	public void sendEmailPwd(UsersVO vo, String div); // �쉶�뜝�룞�삕 Pwd 李얍뜝�룞�삕 �뜝�떛紐뚯삕�뜝�룞�삕 �뜝�뙥�눦�삕

	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException; // �뜝�룞�삕�뜝�떛�벝�삕/�뜝�떛紐뚯삕�뜝�룞�삕 �뜝�룞�삕�쉶 �뜝�떦�슱�삕 �뜝�뙂�떆釉앹삕艅섇뜝�떕占� �뜝�룞�삕�뜝�룞�삕
	
	//�쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
	public void deleteId(UsersVO vo);
	
	// �쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
	public void updateUser(UsersVO vo);
		
	public List<UsersRatio> getGenderRatio(); // �쉶�썝 �넻怨�(�궓,���꽦鍮�) 議고쉶
	
	public List<UsersAge> getAge(); // �쉶�썝 �넻怨�(�뿰�졊蹂� �쉶�썝�닔) 議고쉶
	
}
