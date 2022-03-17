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

	// ����¡ó�� �� ����¡ó���� users����
	public int updateViewCount(int rseq);

	public List<UsersVO> listUsers(UsersVO vo);

	public List<UsersVO> getUsersListWithPaging(Criteria criteria, String id);

	public int countUserslist(String id);

	// 블랙리스트 등록
	public void statusChange(UsersVO vo);
	
	// 블랙리스트 해제
	public void statusCancelBlack(int useq);
	
	// 일반 회원 조회
	public List<UsersVO> listGeneralUser(UsersVO vo);
	
	// 블랙리스트 회원 조회
	public List<UsersVO> listBlackUser(UsersVO vo);
	
	public List<UsersVO> statusUserlistWithPaging(Criteria criteria, String id, String blacklist);
	
	public int statusUserlist(UsersVO vo);
	
}
