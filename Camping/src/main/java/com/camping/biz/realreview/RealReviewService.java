package com.camping.biz.realreview;

import java.util.List;

import com.camping.biz.dto.RealReviewVO;
import com.camping.biz.dto.UsersVO;

import utils.Criteria;

public interface RealReviewService {
	public List<RealReviewVO> listReview();
	
	public RealReviewVO detailReviews(int rseq);
	
	public int updateViewCount(int rseq);
	
	public int countReviewlist(String title);
	
	public List<RealReviewVO> getListWithPaging(Criteria criteria, String title);
	
	void insertReview(RealReviewVO vo);
	
	public List<RealReviewVO> seemyreview(RealReviewVO vo);
}
