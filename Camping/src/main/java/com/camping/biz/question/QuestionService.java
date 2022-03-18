package com.camping.biz.question;

import java.util.List;

import com.camping.biz.dto.QuestionsVO;

public interface QuestionService {
	
	// 자주묻는 질문 조회
	public List<QuestionsVO> listQuestion();
	
	// 자주묻는 질문 상세보기
	QuestionsVO detailQuestion(int qseq);
	
	// 자주묻는 질문 등록: 총관리자
	public void insertQuestion(QuestionsVO vo);
	
	// 자주묻는 질문 삭제: 총관리자
	public void deleteQuestion(int qseq);
	
	// 자주묻는 질문 수정: 총관리자
	public void updateQuestion(QuestionsVO vo);
	
}
