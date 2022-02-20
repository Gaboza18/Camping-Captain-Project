package com.camping.biz.qna;

import java.util.List;

import com.camping.biz.dto.QnaVO;

public interface QnaService {
	
	// QnA 목록 조회
	List<QnaVO> listQna(String id);
	
	// 게시글 번호로 한건 조회
	QnaVO getQna(int qseq);
	
	// QnA 등록
	void insertQna(QnaVO vo);
}
