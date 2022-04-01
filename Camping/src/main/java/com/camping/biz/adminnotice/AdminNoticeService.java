package com.camping.biz.adminnotice;

import java.util.List;

import com.camping.biz.dto.AdminNoticeVO;

import utils.Criteria;


public interface AdminNoticeService {
	
	// 전체 관리자 공지사항 조회
	public List<AdminNoticeVO> listNotice();
	
	// 전체 관리자 공지사항 상세 보기
	public AdminNoticeVO detailNotice(int aseq);
	
	// 전체 관리자 공지사항 조회수 증가
	public int updateViewCount(int aseq);
	
	// 전체 관리자 공지사항 갯수 조회
	public int countNoticeList(String title);
	
	// 페이지별 공지사항 조회
	public List<AdminNoticeVO> getListWithPaging(Criteria criteria, String title);
	
	// 관리자 공지사항 등록
	public void insertAdminNotice(AdminNoticeVO vo);
	
	// 관리자 공지사항 수정
	public void updateAdminNotice(AdminNoticeVO vo);
	
	// 관리자 공지사항 삭제
	public void deleteAdminNotice(int aseq);
	
}
