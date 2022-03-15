package com.camping.biz.camporder;

import java.util.List;

import com.camping.biz.dto.CampOrderVO;

import utils.Criteria;

public interface CampOrderService {
	
	// 예약정보 등록
	public void insertCampOrder(CampOrderVO vo);
	
	// 예약마감처리를 위해 체크인 날짜 기준으로 예약정보 조회
	public List<CampOrderVO> getCampOrderList(String indate);

	
	// 예약번호를 조건으로 예약내역 한건 조회
	public CampOrderVO getMyCampOrder(int oseq);
	
	// 회원 id를 기준으로 총 예약목록 갯수 조회
	public int countMyOrderList(String user_id);
	
	// 페이징 처리(1~10개씩 출력) / 지점 선택 기능
	public List<CampOrderVO> getMyListWithPaging(Criteria criteria, String user_id);



	/*
	 * 관리자 기능
	 */
	// 본인 지점 전체 예약 현황 갯수 조회
	public int countOrderList(String camp_name);
	
	// 공지사항 페이징처리
	public List<CampOrderVO> getListWithPaging(Criteria criteria, String camp_name);
	
	// 전체지점 총 예액현황 갯수조회
	public int countAllOrderList();
	
	// 전체 예약내역 페이징처리
	public List<CampOrderVO> getAllListWithPaging(Criteria criteria);

	// 예약내역 한건 조회
	public CampOrderVO getCampOrder(int oseq);
	
	// 예약상태 변경
	public void updateOrderStatus(int oseq);
	
	// 예약내역 삭제
	public void deleteOrderByOseq(int oseq);
}
