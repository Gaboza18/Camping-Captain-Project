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

	// ȸ�� ������ ��ȸ
	public UsersVO getUsers(String id) {
		return mybatis.selectOne("mappings.users-mapping.getUsers", id);
	}

	// ȸ�� ���� ���� Ȯ��
	/*
	 * ���ϰ�: ȸ���� �����ϸ� : 1 ȸ���� �������� ������ : -1
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
	 * ȸ�� ����
	 * 
	 * id�� �������� ���� ���: -1 ��ȯ pwd�� Ʋ����� : 0�� ��ȯ id,pwd�� ��ġ�� ��� : 1�� ��ȯ
	 */

	public int loginID(UsersVO vo) {
		int result = -1; // ��ȸ���

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

	// ȸ�� ���
	public void insertUsers(UsersVO vo) {
		mybatis.insert("mappings.users-mapping.insertUsers", vo);
	}

	// ȸ�� ��� ��ȸ
	public List<UsersVO> listUsers(String name) {
		return mybatis.selectList("mappings.users-mapping.listUsers", name);
	}

	// ȸ�� ID ã��
	public UsersVO findId(UsersVO vo) {
		return mybatis.selectOne("mappings.users-mapping.findId",vo);
	}
		
	// ȸ�� ��й�ȣ ����
	public int updatePwd(UsersVO vo) {
		return mybatis.update("mappings.users-mapping.updatePwd", vo);
	}

	
	// ȸ��Ż��
	
	

	public void deleteId(UsersVO vo )throws Exception{
	
		mybatis.delete("mappings.users-mapping.deleteId", vo);
	}
	
	public void updateUser(UsersVO vo) {

		mybatis.update("mappings.users-mapping.updateUser",vo);
	}
	
	
	
	
		// <��й�ȣ Ȯ��> ���� : "passwordCheck"
	
	/*public String pwCheck(String password)throws Exception{
		return mybatis.selectOne("mappings.users-mapping.passwordCheck", password);
	}
	*/
}
