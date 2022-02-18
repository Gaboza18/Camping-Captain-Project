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
	
	//전체 리뷰 조회(원래 title 없었었음. 2022-02-16)--- title 적어줘야하나
	public List<RealReviewVO> listReview() {
		return mybatis.selectList("mappings.review-mapping.listAllReviews");
	}
	
	// 리뷰 상세보기
	public RealReviewVO detailReviews(int rseq) {
		return mybatis.selectOne("mappings.review-mapping.detailReviews", rseq);
		
	}
	// 리얼리뷰 조회수 증가
	public int updateViewCount(int rseq) {
		return mybatis.update("mappings.review-mapping.updateViewCount",rseq);
	}
	
	// 전체 리뷰 갯수 조회
	public int countReviewlist(String title) {
		return mybatis.selectOne("mappings.review-mapping.countReviewlist",title);
	}
	
	// 페이징 별 리뷰 조회
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
