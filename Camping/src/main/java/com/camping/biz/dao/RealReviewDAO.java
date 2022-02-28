package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.RealReviewVO;
import com.camping.biz.dto.UsersVO;

import utils.Criteria;

@Repository
public class RealReviewDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	//전체 리뷰 조회(원래 title 없었었음. 2022-02-16)--- title 적어줘야하나
	public List<RealReviewVO> listReview(RealReviewVO vo) {
		return mybatis.selectList("mappings.review-mapping.listAllReviews" ,vo);
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
	
	//리스트 페이징처리 2
	
public List<RealReviewVO> getListWithPaging2(Criteria criteria, String title) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("title", title);
		
		return mybatis.selectList("mappings.review-mapping.listWithPaging2",map);
}

	public void insertReview(RealReviewVO vo) {
		mybatis.insert("mappings.review-mapping.insertReview",vo);
	}
	
	public List<RealReviewVO> seemyreview(RealReviewVO vo) {
		return mybatis.selectList("mappings.review-mapping.myreview", vo); 
	}
	
	public void deletereviews(int rseq) {
		mybatis.delete("mappings.review-mapping.deleteRiviews",rseq);
	}
	
	//modifyRiviews
	public void  modifyreviews(RealReviewVO vo) {
		 mybatis.update("mappings.review-mapping.modifyRiviews",vo);
	
	
	}
}
