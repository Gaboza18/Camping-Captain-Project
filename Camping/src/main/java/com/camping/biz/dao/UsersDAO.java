package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.UsersAge;
import com.camping.biz.dto.UsersRatio;
import com.camping.biz.dto.UsersVO;

@Repository
public class UsersDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// �쉶�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�쉶
	public UsersVO getUsers(String id) {
		return mybatis.selectOne("mappings.users-mapping.getUsers", id);
	}

	// �쉶�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �솗�뜝�룞�삕
	/*
	 * �뜝�룞�삕�뜝�떦怨ㅼ삕: �쉶�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦紐뚯삕 : 1 �쉶�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 : -1
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
	 * �쉶�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
	 * 
	 * id�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�: -1 �뜝�룞�삕�솚 pwd�뜝�룞�삕 ���뜝�룞�삕�뜝�룞�삕�뜝占� : 0�뜝�룞�삕 �뜝�룞�삕�솚 id,pwd�뜝�룞�삕 �뜝�룞�삕移섇뜝�룞�삕 �뜝�룞�삕�뜝占� : 1�뜝�룞�삕 �뜝�룞�삕�솚
	 */

	public int loginID(UsersVO vo) {
		int result = -1; // �뜝�룞�삕�쉶�뜝�룞�삕�뜝占�

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

	// �쉶�뜝�룞�삕 �뜝�룞�삕�뜝占�
	public void insertUsers(UsersVO vo) {
		mybatis.insert("mappings.users-mapping.insertUsers", vo);
	}

	// �쉶�뜝�룞�삕 �뜝�룞�삕�뜝占� �뜝�룞�삕�쉶
	public List<UsersVO> listUsers(String name) {
		return mybatis.selectList("mappings.users-mapping.listUsers", name);
	}

	// �쉶�썝 ID 李얘린
	public UsersVO findId(UsersVO vo) {
		return mybatis.selectOne("mappings.users-mapping.findId",vo);
	}
		
	// �쉶�썝 鍮꾨�踰덊샇 李얘린 諛� �뾽�뜲�씠�듃
	public int updatePwd(UsersVO vo) {
		return mybatis.update("mappings.users-mapping.updatePwd", vo);
	}

	public void deleteId(UsersVO vo )throws Exception{
		mybatis.delete("mappings.users-mapping.deleteId", vo);
	}
	
	public void updateUser(UsersVO vo) {
		mybatis.update("mappings.users-mapping.updateUser",vo);
	}
	
	// 회원 통계(남,여 성비) 조회
	public List<UsersRatio> getGenderRatio(){
		return mybatis.selectList("mappings.users-mapping.getGenderRatio");
	}
	
	// 회원 통계(연령별 회원수) 조회
	public List<UsersAge> getAge(){
		return mybatis.selectList("mappings.users-mapping.getAge");
	}
	
}
