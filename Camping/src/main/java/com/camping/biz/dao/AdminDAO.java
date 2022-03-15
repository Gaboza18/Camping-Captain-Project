package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.RealReviewVO;
import com.camping.biz.dto.UsersVO;

import utils.Criteria;

@Repository
public class AdminDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public AdminVO getAdmin(String id) {
		return mybatis.selectOne("mappings.admin-mapping.getAdmin", id);
	}
	
	public int confirmID(String id) {
		String password = mybatis.selectOne("mappings.admin-mapping.confirmID",id);
		
		if (password != null) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public int loginID(AdminVO vo) {
		int result = -1;
		
		String pwd_in_db = mybatis.selectOne("mappings.admin-mapping.confirmID", vo.getId());
		
		if (pwd_in_db == null) {
			result = -1;
		} else if(vo.getPassword().equals(pwd_in_db)) {
			result = 1;
		} else {
			result = 0;
		}
		
		return result;
	}

	//페이징처리
	
public List<RealReviewVO> getListWithPaging(Criteria criteria, String title) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("title", title);
		
		return mybatis.selectList("mappings.admin-mapping.listWithPaging",map);
		
	}
// count리뷰리스트 
public int countReviewlist(String title) {
	return mybatis.selectOne("mappings.admin-mapping.countReviewlist",title);
}

public RealReviewVO detailReviews(int rseq) {
	return mybatis.selectOne("mappings.admin-mapping.detailReviews", rseq);
	
}
	
	
	public void deletereview(int rseq) {
		mybatis.delete("mappings.admin-mapping.deleteRiviews",rseq);
	}
	
	public int updateViewCount(int rseq) {
		return mybatis.update("mappings.admin-mapping.countReviewlist",rseq);
	}

	// 블랙리스트 조회 및 페이징 처리
	public List<UsersVO> listUsers(UsersVO vo) {
		return mybatis.selectList("mappings.admin-mapping.listAllUsers", vo);
	}
	
	// 페이징 별 리뷰 조회
		public List<UsersVO> getUsersListWithPaging(Criteria criteria, String id) {
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("criteria", criteria);
			map.put("id", id);
			
			return mybatis.selectList("mappings.admin-mapping.listWithPaging",map);

	}
		
		public int countUserslist(String id) {
			return mybatis.selectOne("mappings.admin-mapping.countUserslist",id);
		}	
		
		// 블랙리스트 설정
		public void statusChange(UsersVO vo) {
			
			 mybatis.selectOne("mappings.admin-mapping.statuschange", vo);
		}

}
