package com.camping.biz.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.camping.biz.dto.UsersAge;
import com.camping.biz.dto.UsersRatio;
import com.camping.biz.dto.UsersVO;

public interface UsersService {
	// DAO占쏙옙占쏙옙 占쏙옙체 Users id 占쌨아울옙占쏙옙 - > 占쏙옙占쏙옙占쏙옙 Impl占쏙옙占쏙옙
	public UsersVO getUsers(String id);

	public int confirmID(String id);

	public int loginID(UsersVO vo);

	public void insertUsers(UsersVO vo);

	public List<UsersVO> listUsers(String name);

	public UsersVO findId(UsersVO vo); // 회占쏙옙 ID 찾占쏙옙

	public int updatePwd(UsersVO vo); // 회占쏙옙 Pwd 占쏙옙占쏙옙

	public void sendEmailPwd(UsersVO vo, String div); // 회占쏙옙 Pwd 찾占쏙옙 占싱몌옙占쏙옙 占쌩쇽옙

	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException; // 占쏙옙占싱듸옙/占싱몌옙占쏙옙 占쏙옙회 占싹울옙 占쌈시븝옙橘占싫� 占쏙옙占쏙옙
	
	//회占쏙옙占쏙옙占쏙옙
	public void deleteId(UsersVO vo )throws Exception;
	
	// 회占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	public void updateUser(UsersVO vo);
		
	public List<UsersRatio> getGenderRatio(); // 회원 통계(남,녀성비) 조회
	
	public List<UsersAge> getAge(); // 회원 통계(연령별 회원수) 조회
	
}
