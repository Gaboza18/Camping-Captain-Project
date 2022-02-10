package com.camping.biz.notice;

import java.util.List;
import com.camping.biz.dto.NoticeVO;

import utils.Criteria;

public interface NoticeService {
	
	// 공지사항 전체 조회
	public List<NoticeVO> listNotice();
	
	// 공지사항 상세보기
	public NoticeVO detailNotice(int nseq);
	
	// 공지사항 조회수 카운트
	public int updateViewCount(int nseq);
	
	// 전체 공지사항의 갯수 조회
	public int countNoticetList(String admin_name);
	
	// 페이지별 공지사항 조회
	public List<NoticeVO> getListWithPaging(Criteria criteria, String admin_name);
}
