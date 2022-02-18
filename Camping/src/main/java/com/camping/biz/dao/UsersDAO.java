package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;

@Repository
public class UsersDAO {
	
	

	@Autowired
	private SqlSessionTemplate mybatis;
	
	//회원 상세정보 조회
	public UsersVO getUsers(String id) {
		
		return mybatis.selectOne("mappings.users-mapping.getUsers", id);
		}
	
	// 회원 존재 여부 확인
	/*
	 * 리턴값:
	 * 		 회원이 존재하면 : 1
	 * 		 회원이 존재하지 않으면 : -1
	 */

	public int confirmID(String id) {
		
		String password = mybatis.selectOne("mappings.users-mapping.confirmID", id);

		if (password != null) {
			return 1;
			
		} else {
			return -1;
		}
	}
	/*
	 * 회원 인증 
	 * 
	 * id가 존재하지 않을 경우: -1 반환
	 * pwd가 틀릴경우 : 0을 반환
	 * id,pwd가 일치할 경우 : 1을 반환
	 */
	
	public int loginID(UsersVO vo) {
		int result = -1; //조회결과

		String pwd_in_db = mybatis.selectOne("mappings.users-mapping.confirmID", vo.getId());
		
		if(pwd_in_db == null) {
			result = -1;
			
		}else if(vo.getPassword().equals(pwd_in_db)) {
			result =1;
		}else {
			result = 0;
		}
		return result;
	}
	
	// 회원 등록
	public void insertUsers(UsersVO vo) {
		mybatis.insert("mappings.users-mapping.insertUsers",vo);
	}
	
	// 회원 목록 조회
	public List<UsersVO> listUsers(String name) {
		return mybatis.selectList("mappings.users-mapping.listUsers", name);
	}
	
	// 회원탈퇴
	
	

	public void deleteId(String id )throws Exception{
		
		
		
		//아이디 삭제라도 되돌리고 싶을때 사릴 코드
		mybatis.delete("mappings.users-mapping.deleteId", id);
	}
	
	public void updateUser(UsersVO vo) {
		
		mybatis.update("mappings.users-mapping.deleteId", vo);
	}
	
	
		// <비밀번호 확인> 변수 : "passwordCheck"
	
	/*public String pwCheck(String password)throws Exception{
		return mybatis.selectOne("mappings.users-mapping.passwordCheck", password);
	}
	*/
}
