package com.camping.biz.question;

import java.util.List;

import com.camping.biz.dto.QuestionsVO;

public interface QuestionService {
	
	// ���� �������� ��ü ��ȸ
	public List<QuestionsVO> listQuestion();
	
	// ���� �������� �󼼺���
	QuestionsVO detailQuestion(int qseq);
}
