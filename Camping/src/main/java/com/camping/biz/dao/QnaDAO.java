package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.QnaVO;

@Repository
public class QnaDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 전체 QnA 목록 조회
	public List<QnaVO> listQna(String id){
		return mybatis.selectList("mappings.qna-mapping.listQna", id);
	}
	
	// QnA 목록번호로 한건 조회
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("mappings.qna-mapping.getQna", qseq);
	}
	
	// QnA 등록하기
	public void insertQna(QnaVO vo) {
		mybatis.insert("mappings.qna-mapping.insertQna", vo);
	}
}
