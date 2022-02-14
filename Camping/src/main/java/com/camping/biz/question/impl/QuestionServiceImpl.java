package com.camping.biz.question.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.dao.QuestionsDAO;
import com.camping.biz.dto.QuestionsVO;
import com.camping.biz.question.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionsDAO questionsDao;
	
	@Override
	public List<QuestionsVO> listQuestion() {
		return questionsDao.listQuestion();
	}

	@Override
	public QuestionsVO detailQuestion(int qseq) {
		return questionsDao.detailQuestion(qseq);
	}

}
