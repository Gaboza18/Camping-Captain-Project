package com.camping.biz.admin;

import java.util.List;

import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.RealReviewVO;
import com.camping.biz.dto.UsersVO;

import utils.Criteria;

public interface AdminService {

	public AdminVO getAdmin(String id);
	
	public int confirmID(String id);
	
	public int loginID(AdminVO vo);
	
public int countReviewlist(String title);
	
	public void deletereview(int rseq);
	public List<RealReviewVO> getListWithPaging(Criteria criteria, String title);
	
	
	public RealReviewVO detailReviews(int rseq);
	
	//其捞隆贸府 棺 其捞隆贸府肺 users包府
	public int updateViewCount(int rseq);
	public List<UsersVO> listUsers(UsersVO vo);
	public List<UsersVO> getUsersListWithPaging(Criteria criteria, String id);
	public int countUserslist(String id);
	
	//喉发府胶飘 眠啊
	public void statusChange(UsersVO vo);
	
	
}
