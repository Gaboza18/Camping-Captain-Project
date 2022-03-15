package com.camping.biz.adminImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.admin.AdminService;
import com.camping.biz.dao.AdminDAO;
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.RealReviewVO;
import com.camping.biz.dto.UsersVO;

import utils.Criteria;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO aDao;
	
	@Override
	public AdminVO getAdmin(String id) {
		return aDao.getAdmin(id);
	}

	@Override
	public int confirmID(String id) {
		return aDao.confirmID(id);
	}

	@Override
	public int loginID(AdminVO vo) {
		return aDao.loginID(vo);
	}
	

	@Override
	public void deletereview(int rseq) {
		 aDao.deletereview(rseq);
		
	}

	@Override
	public List<RealReviewVO> getListWithPaging(Criteria criteria, String title) {
		return aDao.getListWithPaging(criteria, title);
	}

	@Override
	public int countReviewlist(String title) {
		

			return aDao.countReviewlist(title);
		}

	@Override
	public RealReviewVO detailReviews(int rseq) {
		return aDao.detailReviews(rseq);
	}

	@Override
	public int updateViewCount(int rseq) {
		return aDao.updateViewCount(rseq);
	}

	@Override
	public List<UsersVO> listUsers(UsersVO vo) {
		// TODO Auto-generated method stub
		return aDao.listUsers(vo);
	}

	@Override
	public List<UsersVO> getUsersListWithPaging(Criteria criteria, String id) {
		
		return aDao.getUsersListWithPaging(criteria, id);
	}

	@Override
	public int countUserslist(String id) {
		
		return aDao.countUserslist(id);
	}

	@Override
	public void statusChange(UsersVO vo) {
		
		aDao.statusChange(vo);
		
	}
	
	

}
