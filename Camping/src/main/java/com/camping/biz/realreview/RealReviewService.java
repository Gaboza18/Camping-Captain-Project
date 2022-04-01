package com.camping.biz.realreview;

import java.util.List;

import com.camping.biz.dto.RealReviewVO;

import utils.Criteria;

public interface RealReviewService {

	public List<RealReviewVO> listReview(RealReviewVO vo);

	public RealReviewVO detailReviews(int rseq);

	public int updateViewCount(int rseq);

	public int countReviewlist(String title);

	public List<RealReviewVO> getListWithPaging(Criteria criteria, String title);

	void insertReview(RealReviewVO vo);

	public void deletereviews(int rseq);

	public void modifyreviews(RealReviewVO vo);

	public List<RealReviewVO> areaList(RealReviewVO vo);

	public int countReviewlist2(RealReviewVO vo);

	public List<RealReviewVO> getListWithPaging2(Criteria criteria, String id, String title);

	public List<RealReviewVO> seemyreview(RealReviewVO vo);

}
