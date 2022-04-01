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
	public int countNoticeList(String title) {
		return mybatis.selectOne("mappings.notice-mapping.countNoticelist", title);
	}

	// 페이지별 공지사항 조회
	public List<NoticeVO> getListWithPaging(Criteria criteria, String title) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("title", title);

		return mybatis.selectList("mappings.notice-mapping.listWithPaging", map);
	}
	
	// 회원 공지사항 등록(지점관리자,총관리자)
	public void insertNotice(NoticeVO vo) {
		mybatis.insert("mappings.notice-mapping.insertNotice",vo);
	}
	
	// 회원 공지사항 수정(지점관리자,총관리자)
	public void updateNotice(NoticeVO vo) {
		mybatis.update("mappings.notice-mapping.updateNotice",vo);
	}
	
	// 회원 공지사항 삭제(지점관리자,총관리자)
	public void deleteNotice(int nseq) {
		mybatis.delete("mappings.notice-mapping.deleteNotice", nseq);
	}

}
