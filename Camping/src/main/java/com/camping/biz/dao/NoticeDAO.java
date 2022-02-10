package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.NoticeVO;

import utils.Criteria;

@Repository
public class NoticeDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	// 전체 공지사항 조회
	public List<NoticeVO> listNotice() {
		return mybatis.selectList("mappings.notice-mapping.listAllNotice");
	}

	// 공지사항 상세 보기
	public NoticeVO detailNotice(int nseq) {
		return mybatis.selectOne("mappings.notice-mapping.detailNotice", nseq);
	}

	// 공지사항 조회수 증가
	public int updateViewCount(int nseq) {
		return mybatis.update("mappings.notice-mapping.updateViewCount", nseq);
	}
	
	// 전체 공지사항 갯수 조회
	public int countNoticeList(String admin_name) {
		return mybatis.selectOne("mappings.notice-mapping.countNoticelist", admin_name);
	}
	
	// 페이지별 공지사항 조회
	public List<NoticeVO> getListWithPaging(Criteria criteria, String admin_name){
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("admin_name", admin_name);
		
		return mybatis.selectList("mappings.notice-mapping.listWithPaging", map);
	}
	
}
