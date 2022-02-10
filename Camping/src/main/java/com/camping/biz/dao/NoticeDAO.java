package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.NoticeVO;

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
}
