package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.AdminNoticeVO;

import utils.Criteria;



@Repository
public class AdminNoticeDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<AdminNoticeVO> listNotice(AdminNoticeVO vo) {
		return mybatis.selectList("mappings.adminnotice-mapping.adminNotice" ,vo);
	}
	
	public int updateViewCount(int aseq) {
		return mybatis.update("mappings.adminnotice-mapping.updateViewCount",aseq);
	}
public List<AdminNoticeVO> getListWithPaging(Criteria criteria, String title) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("title", title);
		
		return mybatis.selectList("mappings.adminnotice-mapping.listWithPaging",map);
		
	}
	
}
