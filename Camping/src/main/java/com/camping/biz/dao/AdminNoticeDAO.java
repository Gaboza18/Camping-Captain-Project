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
	
	// 전체 관리자 공지사항 조회
	public List<AdminNoticeVO> listNotice() {
		return mybatis.selectList("mappings.adminnotice-mapping.adminNoticeList");
	}
	
	// 전체 관리자 공지사항 상세 보기
	public AdminNoticeVO detailNotice(int aseq) {
		return mybatis.selectOne("mappings.adminnotice-mapping.adminNoticeDetail", aseq);
	}
	
	// 전체 관리자 공지사항 조회수 증가
	public int updateViewCount(int aseq) {
		return mybatis.update("mappings.adminnotice-mapping.updateViewCount", aseq);
	}
	
	// 전체 관리자 공지사항 갯수 조회
	public int countNoticeList(String title) {
		return mybatis.selectOne("mappings.adminnotice-mapping.countAdminNoticelist", title);
	}

	// 페이지별 공지사항 조회
	public List<AdminNoticeVO> getListWithPaging(Criteria criteria, String title) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("title", title);

		return mybatis.selectList("mappings.adminnotice-mapping.listWithPaging", map);
	}
	
	// 관리자 공지사항 등록(총관리자)
	public void insertAdminNotice(AdminNoticeVO vo) {
		mybatis.insert("mappings.adminnotice-mapping.insertAdminNotice",vo);
	}
	
	// 관리자 공지사항 수정(총관리자)
	public void updateAdminNotice(AdminNoticeVO vo) {
		mybatis.update("mappings.adminnotice-mapping.updateAdminNotice",vo);
	}
	
	// 관리자 공지사항 삭제(총관리자)
	public void deleteAdminNotice(int aseq) {
		mybatis.delete("mappings.adminnotice-mapping.deleteAdminNotice", aseq);
	}

}
