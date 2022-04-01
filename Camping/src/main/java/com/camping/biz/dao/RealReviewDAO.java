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

	// 전체 리뷰 조회
	public List<RealReviewVO> listReview(RealReviewVO vo) {
		return mybatis.selectList("mappings.review-mapping.listAllReviews", vo);
	}

	// 리뷰 상세보기
	public RealReviewVO detailReviews(int rseq) {
		return mybatis.selectOne("mappings.review-mapping.detailReviews", rseq);
	}

	// 리얼리뷰 조회수 증가
	public int updateViewCount(int rseq) {
		return mybatis.update("mappings.review-mapping.updateViewCount", rseq);
	}

	// 전체 리뷰 갯수 조회
	public int countReviewlist(String title) {
		return mybatis.selectOne("mappings.review-mapping.countReviewlist", title);
	}

	// 페이징 별 리뷰 조회
	public List<RealReviewVO> getListWithPaging(Criteria criteria, String title) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("title", title);

		return mybatis.selectList("mappings.review-mapping.listWithPaging", map);

	}

	public void insertReview(RealReviewVO vo) {
		mybatis.insert("mappings.review-mapping.insertReview", vo);
	}

	public void deletereviews(int rseq) {
		mybatis.delete("mappings.review-mapping.deleteRiviews", rseq);
	}

	// 리뷰 수정하기
	public void modifyreviews(RealReviewVO vo) {
		mybatis.update("mappings.review-mapping.modifyReviews", vo);
	}
	
	// 라디오버튼으로 지점선택해서 지점별 리뷰 보기
	public List<RealReviewVO> areaList(RealReviewVO vo) {
		return mybatis.selectList("mappings.review-mapping.areaList", vo);
	}
	
	/*
	 * 나의 후기
	 */

	// 나의 후기 목록 갯수 조회
	public int countReviewlist2(RealReviewVO vo) {
		return mybatis.selectOne("mappings.review-mapping.countReviewlist2", vo);
	}

	// 리스트 페이징처리(나의 후기 리스트 페이징 처리)
	public List<RealReviewVO> getListWithPaging2(Criteria criteria, String id, String title) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("id", id);
		map.put("title", title);

		return mybatis.selectList("mappings.review-mapping.listWithPaging2", map);
	}
	
	// 나의후기 상세보기
	public List<RealReviewVO> seemyreview(RealReviewVO vo) {
		return mybatis.selectList("mappings.review-mapping.myreview", vo);
	}

}
