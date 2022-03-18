package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderVO;

import utils.Criteria;

@Repository
public class CampOrderDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 예약 정보 INSERT
	public void insertCampOrder(CampOrderVO vo) {
		mybatis.insert("mappings.campOrder-mapping.insertCampOrder", vo);
	}

	// 예약마감 처리를 위해 체크인날짜를 기준으로 예약내역 조회
	public List<CampOrderVO> getCampOrderList(String indate) {
		return mybatis.selectList("mappings.campOrder-mapping.getCampOrderList", indate);
	}

	// 예약번호를 조건으로 예약내역 한건 조회
	public CampOrderVO getMyCampOrder(int oseq) {
		return mybatis.selectOne("mappings.campOrder-mapping.getMyCampOrder", oseq);
	}

	// 회원 id를 기준으로 총 예약목록 갯수 조회
	public int countMyOrderList(String user_id) {
		return mybatis.selectOne("mappings.campOrder-mapping.countMyOrderList", user_id);
	}

	// 페이징 처리(1~10개씩 출력) / 지점 선택 기능
	public List<CampOrderVO> getMyListWithPaging(Criteria criteria, String user_id) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("user_id", user_id);

		return mybatis.selectList("mappings.campOrder-mapping.MylistWithPaging", map);
	}

	/*
	 * 관리자 기능
	 */
	
	// 본인 지점 전체 예약 현황 갯수 조회
	public int countOrderList(String camp_name) {
		return mybatis.selectOne("mappings.campOrder-mapping.countOrderList", camp_name);
	}

	// 예약내역 페이징처리
	public List<CampOrderVO> getListWithPaging(Criteria criteria, String camp_name) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("camp_name", camp_name);

		return mybatis.selectList("mappings.campOrder-mapping.listWithPaging", map);
	}

	// 전체지점 총 예액현황 갯수조회
	public int countAllOrderList() {
		return mybatis.selectOne("mappings.campOrder-mapping.countAllOrderList");
	}

	// 전체 예약내역 페이징처리
	public List<CampOrderVO> getAllListWithPaging(Criteria criteria) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);

		return mybatis.selectList("mappings.campOrder-mapping.AlllistWithPaging", map);
	}

	// 예약내역 한건 조회
	public CampOrderVO getCampOrder(int oseq) {
		return mybatis.selectOne("mappings.campOrder-mapping.getCampOrder", oseq);
	}

	// 예약상태 변경
	public void updateOrderStatus(int oseq) {
		mybatis.update("mappings.campOrder-mapping.updateOrderStatus", oseq);
	}

	// 예약내역 삭제
	public void deleteOrderByOseq(int oseq) {
		mybatis.delete("mappings.campOrder-mapping.deleteOrderByOseq", oseq);
	}
	
}
