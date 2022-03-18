package com.camping.biz.qna;

import java.util.List;

import com.camping.biz.dto.QnaVO;

public interface QnaService {
	
	// QnA 목록 조회
	List<QnaVO> listQna(String id);
	
	// QnA 번호로 한건 조회
	QnaVO getQna(int qseq);
	
	// QnA 등록
	void insertQna(QnaVO vo);
	
	// QnA 조회(총관리자)
	public List<QnaVO> listAllQna();
	
	// QnA 답변처리(총관리자)
	public void updateQna(QnaVO vo);
	
}
