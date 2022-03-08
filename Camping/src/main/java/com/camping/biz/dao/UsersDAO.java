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

	// �뜝�럩�뤂�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�뤂
	public UsersVO getUsers(String id) {
		return mybatis.selectOne("mappings.users-mapping.getUsers", id);
	}

	// �뜝�럩�뤂�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럩�꼪�뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
	/*
	 * �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｏ옙�벀�걠占쎄뎡: �뜝�럩�뤂�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｇ춯琉우뒩占쎄뎡 : 1 �뜝�럩�뤂�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� : -1
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
	 * �뜝�럩�뤂�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
	 * 
	 * id�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占�: -1 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶 pwd�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�룞�삕�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� : 0�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶 id,pwd�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰뇖�궡瑗わ옙�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� : 1�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶
	 */

	public int loginID(UsersVO vo) {
		int result = -1; // �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�뤂�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占�

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

	// �뜝�럩�뤂�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占�
	public void insertUsers(UsersVO vo) {
		mybatis.insert("mappings.users-mapping.insertUsers", vo);
	}

	// �뜝�럩�뤂�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�뤂
	public List<UsersVO> listUsers(String name) {
		return mybatis.selectList("mappings.users-mapping.listUsers", name);
	}

	// �뜝�럩�뤂�뜝�럩�쐸 ID 嶺뚢돦堉먪뵳占�
	public UsersVO findId(UsersVO vo) {
		return mybatis.selectOne("mappings.users-mapping.findId",vo);
	}
		
	// �뜝�럩�뤂�뜝�럩�쐸 占쎈쑏熬곣뫅�삕�뵓怨뺣쐡占쎄퉰 嶺뚢돦堉먪뵳占� �뛾�룊�삕 �뜝�럥�뵜�뜝�럥�몥�뜝�럩逾졾뜝�럥諭�
	public int updatePwd(UsersVO vo) {
		return mybatis.update("mappings.users-mapping.updatePwd", vo);
	}

	public void deleteId(UsersVO vo) {
		mybatis.delete("mappings.users-mapping.deleteId", vo);
	}
	
	public void updateUser(UsersVO vo) {
		mybatis.update("mappings.users-mapping.updateUser",vo);
	}
	
	// 占쎌돳占쎌뜚 占쎈꽰�⑨옙(占쎄텚,占쎈연 占쎄쉐�뜮占�) 鈺곌퀬�돳
	public List<UsersRatio> getGenderRatio(){
		return mybatis.selectList("mappings.users-mapping.getGenderRatio");
	}
	
	// 占쎌돳占쎌뜚 占쎈꽰�⑨옙(占쎈염占쎌죯癰귨옙 占쎌돳占쎌뜚占쎈땾) 鈺곌퀬�돳
	public List<UsersAge> getAge(){
		return mybatis.selectList("mappings.users-mapping.getAge");
	}
	
}
