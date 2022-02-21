package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.UsersVO;

@Repository
public class UsersDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 회占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙회
	public UsersVO getUsers(String id) {
		return mybatis.selectOne("mappings.users-mapping.getUsers", id);
	}

	// 회占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 확占쏙옙
	/*
	 * 占쏙옙占싹곤옙: 회占쏙옙占쏙옙 占쏙옙占쏙옙占싹몌옙 : 1 회占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 : -1
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
	 * 회占쏙옙 占쏙옙占쏙옙
	 * 
	 * id占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�: -1 占쏙옙환 pwd占쏙옙 틀占쏙옙占쏙옙占� : 0占쏙옙 占쏙옙환 id,pwd占쏙옙 占쏙옙치占쏙옙 占쏙옙占� : 1占쏙옙 占쏙옙환
	 */

	public int loginID(UsersVO vo) {
		int result = -1; // 占쏙옙회占쏙옙占�

		String pwd_in_db = mybatis.selectOne("mappings.users-mapping.confirmID", vo.getId());

		if (pwd_in_db == null) {
			result = -1;
		} else if (vo.getPassword().equals(pwd_in_db)) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}

	// 회占쏙옙 占쏙옙占�
	public void insertUsers(UsersVO vo) {
		mybatis.insert("mappings.users-mapping.insertUsers", vo);
	}

	// 회占쏙옙 占쏙옙占� 占쏙옙회
	public List<UsersVO> listUsers(String name) {
		return mybatis.selectList("mappings.users-mapping.listUsers", name);
	}

	// 회원 ID 찾기
	public UsersVO findId(UsersVO vo) {
		return mybatis.selectOne("mappings.users-mapping.findId",vo);
	}
		
	// 회원 비밀번호 찾기 및 업데이트
	public int updatePwd(UsersVO vo) {
		return mybatis.update("mappings.users-mapping.updatePwd", vo);
	}

	public void deleteId(UsersVO vo )throws Exception{
		mybatis.delete("mappings.users-mapping.deleteId", vo);
	}
	
	public void updateUser(UsersVO vo) {
		mybatis.update("mappings.users-mapping.updateUser",vo);
	}
	
}
