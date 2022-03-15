package com.camping.biz.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.camping.biz.dto.UsersAge;
import com.camping.biz.dto.UsersRatio;
import com.camping.biz.dto.UsersVO;



public interface UsersService {
	// DAO占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲筌ｏ옙 Users id 占쎈쐻占쎈솭占쎈툡占쎌뒻占쎌굲占쎈쐻占쎈짗占쎌굲 - > 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 Impl占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	public UsersVO getUsers(String id);

	public int confirmID(String id);

	public int loginID(UsersVO vo);

	public void insertUsers(UsersVO vo);

	public List<UsersVO> listUsers(String name);

	public UsersVO findId(UsersVO vo); // 占쎌돳占쎈쐻占쎈짗占쎌굲 ID 筌≪뼃�쐻占쎈짗占쎌굲

	public int updatePwd(UsersVO vo); // 占쎌돳占쎈쐻占쎈짗占쎌굲 Pwd 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲

	public void sendEmailPwd(UsersVO vo, String div); // 占쎌돳占쎈쐻占쎈짗占쎌굲 Pwd 筌≪뼃�쐻占쎈짗占쎌굲 占쎈쐻占쎈뼓筌뤿슣�굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈솯占쎈닰占쎌굲

	public void findPwd(HttpServletResponse response, UsersVO vo) throws IOException; // 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼓占쎈쿈占쎌굲/占쎈쐻占쎈뼓筌뤿슣�굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎌돳 占쎈쐻占쎈뼣占쎌뒻占쎌굲 占쎈쐻占쎈셽占쎈뻻�뇡�빘�굲�뎲�꼪�쐻占쎈뼍�뜝占� 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	
	//占쎌돳占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	public void deleteId(UsersVO vo);
	
	// 占쎌돳占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	public void updateUser(UsersVO vo);
		
public List<UsersRatio> getGenderRatio(); // 회원 통계(남,녀성비) 조회
	
	public List<UsersAge> getAge(); // 회원 통계(연령별 회원수) 조회



	public void updateemailchk(UsersVO vo);
	
	public String emailchkok(String id, String email);

	
	
	//이메일 업데이트(인서트)추가
	public void updateEmail(UsersVO vo);
	
//	public String statusChange(char status);
	
}
