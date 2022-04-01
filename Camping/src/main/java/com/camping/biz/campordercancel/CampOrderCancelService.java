package com.camping.biz.campordercancel;

import java.util.List;

import com.camping.biz.dto.CampOrderCancelVO;

import utils.Criteria;

public interface CampOrderCancelService {
	
	// 마이페이지에서 예약조회 후 취소 신청 시 취소테이블에 insert
	public void insertCancelMyOrder(CampOrderCancelVO vo);
	
	// 대기중인 취소목록 갯수 조회
	public int  countMyNonCancelList(String usersid);
	
	// 마이페이지에서 내 취소내역 조회 시 취소현황 갯수 조회
	public int countMyCancelList(String usersid);
	
	// 내 취소내역 페이징 처리
	public List<CampOrderCancelVO> getMyListWithPaging(Criteria criteria, String usersid);
	
	/*
	 * 관리자 기능
	 */
	// 본인 지점 전체 예약 취소 현황 갯수 조회
	public int countOrderList(String camp_name);
	
	// 취소 내역 페이징처리
	public List<CampOrderCancelVO> getListWithPaging(Criteria criteria, String camp_name);
	
	// 전체 예약취소 현황 목록 갯수 조회
	public int countAllOrderList();
	
	// 전체 취소 내역 페이징 처리
	public List<CampOrderCancelVO> getAllListWithPaging(Criteria criteria);
	
	// 취소내역 한건 조회
	public CampOrderCancelVO getCancelOrder(int cseq);
	
	// 관리자가 예약 취소 시 취소 테이블에 insert
	public void insertOrderCancel(CampOrderCancelVO vo);
	
	// 취소진행상태 변경
	public void updateCancelStatus(int cseq);
	
}
