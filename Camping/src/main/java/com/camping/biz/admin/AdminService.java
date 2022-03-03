package com.camping.biz.admin;

import java.util.List;

import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.RealReviewVO;

import utils.Criteria;

public interface AdminService {

	public AdminVO getAdmin(String id);
	
	public int confirmID(String id);
	
	public int loginID(AdminVO vo);
	
public int countReviewlist(String title);
	
	public void deletereview(int rseq);
	public List<RealReviewVO> getListWithPaging(Criteria criteria, String title);
	
	
	public RealReviewVO detailReviews(int rseq);
	
	public int updateViewCount(int rseq);
}
