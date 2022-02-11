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

	// 전체 자주묻는질문 조회
	public List<QuestionsVO> listQuestion() {
		return mybatis.selectList("mappings.question-mapping.listAllQuestion");
	}

	// 자주묻는질문 상세 보기
	public QuestionsVO detailQuestion(int qseq) {
		return mybatis.selectOne("mappings.question-mapping.detailQuestion", qseq);
	}

}
