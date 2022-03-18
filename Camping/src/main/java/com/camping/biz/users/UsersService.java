package com.camping.biz.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.camping.biz.dto.UsersAge;
import com.camping.biz.dto.UsersRatio;
import com.camping.biz.dto.UsersVO;

public interface UsersService {
	
	public UsersVO getUsers(String id);

	public int confirmID(String id);

	public int loginID(UsersVO vo);

	public void insertUsers(UsersVO vo);

	public List<UsersVO> listUsers(String name);

	public UsersVO findId(UsersVO vo); 

	public int updatePwd(UsersVO vo); 

	public void sendEmailPwd(UsersVO vo, String div); // 이메일(비밀번호 찾기 -> 임시비밀번호 DB 업데이트 후 업데이트 된 비밀번호 전송)

	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException; 
	
	public void deleteId(UsersVO vo);

	public void updateUser(UsersVO vo);

	public List<UsersRatio> getGenderRatio(); // 회원 통계(남,녀성비) 조회

	public List<UsersAge> getAge(); // 회원 통계(연령별 회원수) 조회

	public void updateemailchk(UsersVO vo);

	public String emailchkok(String id, String email);

	public void updateEmail(UsersVO vo);
	
}
