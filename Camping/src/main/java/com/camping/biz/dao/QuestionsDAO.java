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

	// ��ü ���ֹ������� ��ȸ
	public List<QuestionsVO> listQuestion() {
		return mybatis.selectList("mappings.question-mapping.listAllQuestion");
	}

	// ���ֹ������� �� ����
	public QuestionsVO detailQuestion(int qseq) {
		return mybatis.selectOne("mappings.question-mapping.detailQuestion", qseq);
	}

}
