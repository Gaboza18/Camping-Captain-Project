package com.camping.biz.question;

import java.util.List;

import com.camping.biz.dto.QuestionsVO;

public interface QuestionService {
	
	// 자주 묻는질문 전체 조회
	public List<QuestionsVO> listQuestion();
	
	// 자주 묻는질문 상세보기
	QuestionsVO detailQuestion(int qseq);
}
