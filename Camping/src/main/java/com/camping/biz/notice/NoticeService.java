package com.camping.biz.notice;

import java.util.List;
import com.camping.biz.dto.NoticeVO;

public interface NoticeService {
	
	// 공지사항 전체 조회
	public List<NoticeVO> listNotice();
	
	// 공지사항 상세보기
	public NoticeVO detailNotice(int nseq);
	
	// 공지사항 조회수 카운트
	public int updateViewCount(int nseq);
	
}
