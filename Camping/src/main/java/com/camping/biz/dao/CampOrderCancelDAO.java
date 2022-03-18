package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderCancelVO;

import utils.Criteria;

@Repository
public class CampOrderCancelDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 마이페이지에서 예약조회 후 취소 신청 시 취소테이블에 insert
	public void insertCancelMyOrder(CampOrderCancelVO vo) {
		mybatis.insert("mappings.campOrderCancel-mapping.insertCancelMyOrder", vo);
	}

	// 대기중인 취소목록 갯수 조회
	public int countMyNonCancelList(String usersid) {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.countMyNonCancelList", usersid);
	}

	// 총 취소내역 현황 목록 갯수 조회
	public int countMyCancelList(String usersid) {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.countMyCancelList", usersid);
	}

	// 페이징 처리(1~10개씩 출력)
	public List<CampOrderCancelVO> getMyListWithPaging(Criteria criteria, String usersid) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("usersid", usersid);

		return mybatis.selectList("mappings.campOrderCancel-mapping.MylistWithPaging", map);
	}

	/*
	 * 관리자 기능
	 */
	// 본인 지점 전체 예약 취소 현황 갯수 조회
	public int countOrderList(String camp_name) {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.countOrderList", camp_name);
	}

	// 취소 내역 페이징처리
	public List<CampOrderCancelVO> getListWithPaging(Criteria criteria, String camp_name) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("camp_name", camp_name);

		return mybatis.selectList("mappings.campOrderCancel-mapping.listWithPaging", map);
	}

	// 전체 예약취소 현황 목록 갯수 조회
	public int countAllOrderList() {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.countAllOrderList");
	}

	// 전체 취소 내역 페이징 처리
	public List<CampOrderCancelVO> getAllListWithPaging(Criteria criteria) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);

		return mybatis.selectList("mappings.campOrderCancel-mapping.AlllistWithPaging", map);
	}

	// 취소내역 한건 조회
	public CampOrderCancelVO getCancelOrder(int cseq) {
		return mybatis.selectOne("mappings.campOrderCancel-mapping.getCancelOrder", cseq);
	}

	// 관리자가 예약 취소 시 취소 테이블에 insert
	public void insertOrderCancel(CampOrderCancelVO vo) {
		mybatis.insert("mappings.campOrderCancel-mapping.insertOrderCancel", vo);
	}

	// 취소진행상태 변경
	public void updateCancelStatus(int cseq) {
		mybatis.update("mappings.campOrderCancel-mapping.updateCancelStatus", cseq);
	}
	
}
