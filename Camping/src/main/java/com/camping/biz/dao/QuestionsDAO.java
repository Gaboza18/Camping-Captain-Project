package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.QuestionsVO;

@Repository
public class QuestionsDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 자주묻는 질문 리스트 조회
	public List<QuestionsVO> listQuestion() {
		return mybatis.selectList("mappings.question-mapping.listAllQuestion");
	}

	// 자주 묻는 질문 상세보기
	public QuestionsVO detailQuestion(int qseq) {
		return mybatis.selectOne("mappings.question-mapping.detailQuestion", qseq);
	}
	
	// 자주 묻는 질문 등록(총관리자)
	public void insertQuestion(QuestionsVO vo) {
		mybatis.insert("mappings.question-mapping.insertQuestion",vo);
	}
	
	// 자주 묻는 질문 수정(총관리자)
	public void updateQuestion(QuestionsVO vo) {
		mybatis.update("mappings.question-mapping.updateQuestion",vo);
	}
	
	// 자무 묻는 질문 삭제(총관리자)
	public void deleteQuestion(int qseq) {
		mybatis.delete("mappings.question-mapping.deleteQuestion", qseq);
	}
	
}
