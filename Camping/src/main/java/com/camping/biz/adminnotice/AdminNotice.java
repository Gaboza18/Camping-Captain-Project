package com.camping.biz.adminnotice;

import java.util.List;

import com.camping.biz.dto.AdminNoticeVO;

import utils.Criteria;


public interface AdminNotice {
	public List<AdminNoticeVO> listNotice(AdminNoticeVO vo);
	public int updateViewCount(int aseq);
	public List<AdminNoticeVO> getListWithPaging(Criteria criteria, String title);
	
	
	
	
	
	
}
