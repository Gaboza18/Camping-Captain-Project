package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.RealReviewVO;

import utils.Criteria;

@Repository
public class RealReviewDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	//��ü ���� ��ȸ(���� title ��������. 2022-02-16)--- title ��������ϳ�
	public List<RealReviewVO> listReview() {
		return mybatis.selectList("mappings.review-mapping.listAllReviews");
	}
	
	// ���� �󼼺���
	public RealReviewVO detailReviews(int rseq) {
		return mybatis.selectOne("mappings.review-mapping.detailReviews", rseq);
		
	}
	// ���󸮺� ��ȸ�� ����
	public int updateViewCount(int rseq) {
		return mybatis.update("mappings.review-mapping.updateViewCount",rseq);
	}
	
	// ��ü ���� ���� ��ȸ
	public int countReviewlist(String title) {
		return mybatis.selectOne("mappings.review-mapping.countReviewlist",title);
	}
	
	// ����¡ �� ���� ��ȸ
	public List<RealReviewVO> getListWithPaging(Criteria criteria, String title) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("title", title);
		
		return mybatis.selectList("mappings.review-mapping.listWithPaging",map);
		
	}
	
	public void insertReview(RealReviewVO vo) {
		mybatis.insert("mappings.review-mapping.insertReview",vo);
	}
	

}
