package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// 占쎌돳占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎌돳
	public UsersVO getUsers(String id) {
		return mybatis.selectOne("mappings.users-mapping.getUsers", id);
	}

	// 占쎌돳占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎌넇占쎈쐻占쎈짗占쎌굲
	/*
	 * 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣�ⓦ끉�굲: 占쎌돳占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣筌뤿슣�굲 : 1 占쎌돳占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 : -1
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
	 * 占쎌돳占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	 * 
	 * id占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占�: -1 占쎈쐻占쎈짗占쎌굲占쎌넎 pwd占쎈쐻占쎈짗占쎌굲 占쏙옙占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占� : 0占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎌넎 id,pwd占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲燁살꼪�쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占� : 1占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎌넎
	 */

	public int loginID(UsersVO vo) {
		int result = -1; // 占쎈쐻占쎈짗占쎌굲占쎌돳占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占�

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

	// 占쎌돳占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占�
	public void insertUsers(UsersVO vo) {
		mybatis.insert("mappings.users-mapping.insertUsers", vo);
	}

	// 占쎌돳占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占� 占쎈쐻占쎈짗占쎌굲占쎌돳
	public List<UsersVO> listUsers(String name) {
		return mybatis.selectList("mappings.users-mapping.listUsers", name);
	}

	// 占쎌돳占쎌뜚 ID 筌≪뼐由�
	public UsersVO findId(UsersVO vo) {
		return mybatis.selectOne("mappings.users-mapping.findId",vo);
	}
		
	// 占쎌돳占쎌뜚 �뜮袁⑨옙甕곕뜇�깈 筌≪뼐由� 獄쏉옙 占쎈씜占쎈쑓占쎌뵠占쎈뱜
	public int updatePwd(UsersVO vo) {
		return mybatis.update("mappings.users-mapping.updatePwd", vo);
	}

	public void deleteId(UsersVO vo )throws Exception{
		mybatis.delete("mappings.users-mapping.deleteId", vo);
	}
	
	public void updateUser(UsersVO vo) {
		mybatis.update("mappings.users-mapping.updateUser",vo);
	}
	
	// �쉶�썝 �넻怨�(�궓,�뿬 �꽦鍮�) 議고쉶
	public List<UsersRatio> getGenderRatio(){
		return mybatis.selectList("mappings.users-mapping.getGenderRatio");
	}
	
	// �쉶�썝 �넻怨�(�뿰�졊蹂� �쉶�썝�닔) 議고쉶
	public List<UsersAge> getAge(){
		return mybatis.selectList("mappings.users-mapping.getAge");
	}
	
	// 블랙리스트 설정
	public void statusChangeBlack(String id) {
		mybatis.update("mappings.users-mapping.statusChangeBlack", id);
	}
	
	public void createAuthKey(String usersemail,String authkey) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberemail", usersemail);
		map.put("authKey", authkey);
		
		mybatis.selectOne("mappings.users-mapping.createAuthKey", map);
	}
	
	public void usersAuth(String memberemail) throws Exception {
		mybatis.update("mappings.users-mapping.memberAuth", memberemail);
	}
	
}
