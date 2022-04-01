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

	// ID를 기준으로 유저정보 가져오기
	public UsersVO getUsers(String id) {
		return mybatis.selectOne("mappings.users-mapping.getUsers", id);
	}

	// 회원 존재여부 확인

	/*
	 * 리턴값 : 회원이 존재하면: 1, 회원이 존재하지 않으면: -1
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
	 * id가 존재하지 않을경우: -1 / pwd가 틀릴경우: 0 / id,pwd가 일치할 경우: 1 반환
	 */
	public int loginID(UsersVO vo) {

		int result = -1; // 조회 결과

		String pwd_in_db = mybatis.selectOne("mappings.users-mapping.confirmID", vo.getId());

		// DB와 사용자가 입력한 id/pwd 비교
		if (pwd_in_db == null) { // id가 존재하지 않을경우
			result = -1;
		} else if (vo.getPassword().equals(pwd_in_db)) { // 입력한 pwd와 DB의 pwd 일치할 경우
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}

	// 회원 등록
	public void insertUsers(UsersVO vo) {
		mybatis.insert("mappings.users-mapping.insertUsers", vo);
	}

	// 전체회원 리스트 조회
	public List<UsersVO> listUsers(String name) {
		return mybatis.selectList("mappings.users-mapping.listUsers", name);
	}

	// 회원 ID 찾기
	public UsersVO findId(UsersVO vo) {
		return mybatis.selectOne("mappings.users-mapping.findId", vo);
	}

	// 비밀번호 변경(이메일 임시번호로 수정)
	public int updatePwd(UsersVO vo) {
		return mybatis.update("mappings.users-mapping.updatePwd", vo);
	}

	// 회원 ID 삭제
	public void deleteId(UsersVO vo) {
		mybatis.delete("mappings.users-mapping.deleteId", vo);
	}

	// 회원정보 수정
	public void updateUser(UsersVO vo) {
		mybatis.update("mappings.users-mapping.updateUser", vo);
	}

	// 성별 통계 조회
	public List<UsersRatio> getGenderRatio() {
		return mybatis.selectList("mappings.users-mapping.getGenderRatio");
	}

	// 연령 통계 조회
	public List<UsersAge> getAge() {
		return mybatis.selectList("mappings.users-mapping.getAge");
	}

	// 이메일 인증(인증키 생성하여 이메일 보내기)
	public void updateemailchk(UsersVO vo) {
		mybatis.update("mappings.users-mapping.updateEmailchk", vo);
	}

	// 이메일 인증(생성된 인증키,아이디 와 메일로 보낸 인증키 비교)
	public String emailchkok(String id, String email) {

		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("email", email);

		return mybatis.selectOne("mappings.users-mapping.emailchkOk", map);
	}

	// 이메일 인증(이메일 인증 완료시 이메일 정보 업데이트)
	public void updateEmail(UsersVO vo) {
		mybatis.update("mappings.users-mapping.updateEmail", vo);
	}

}
