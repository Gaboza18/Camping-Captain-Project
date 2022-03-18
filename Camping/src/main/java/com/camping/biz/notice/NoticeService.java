package com.camping.biz.notice;

import java.util.List;
import com.camping.biz.dto.NoticeVO;

import utils.Criteria; 

public interface NoticeService {
	
	// 공지사항 조회
	public List<NoticeVO> listNotice();
	
	// 공지사항 상세보기
	public NoticeVO detailNotice(int nseq);
	
	// 공지사항 조회수 증가
	public int updateViewCount(int nseq);
	
	// 공지사항 리스트 갯수 카운트
	public int countNoticetList(String title);
	
	// 공지사항 페이징 처리
	public List<NoticeVO> getListWithPaging(Criteria criteria, String title);
	
	// 공지사항 추가(총관리자, 지점관리자)
	public void insertNotice(NoticeVO vo);
	
	// 공지사항 수정(총관리자, 지점관리자)
	public void updateNotice(NoticeVO vo);
	
	// 공지사항 삭제(총관리자, 지점관리자)
	public void deleteNotice(int nseq);
	
}
