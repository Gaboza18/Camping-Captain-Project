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

	// ��ü �������� ��ȸ
	public List<NoticeVO> listNotice() {
		return mybatis.selectList("mappings.notice-mapping.listAllNotice");
	}

	// �������� �� ����
	public NoticeVO detailNotice(int nseq) {
		return mybatis.selectOne("mappings.notice-mapping.detailNotice", nseq);
	}

	// �������� ��ȸ�� ����
	public int updateViewCount(int nseq) {
		return mybatis.update("mappings.notice-mapping.updateViewCount", nseq);
	}

	// ��ü �������� ���� ��ȸ
	public int countNoticeList(String title) {
		return mybatis.selectOne("mappings.notice-mapping.countNoticelist", title);
	}

	// �������� �������� ��ȸ
	public List<NoticeVO> getListWithPaging(Criteria criteria, String title) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("title", title);

		return mybatis.selectList("mappings.notice-mapping.listWithPaging", map);
	}
	
	// ȸ�� �������� ���(����������,�Ѱ�����)
	public void insertNotice(NoticeVO vo) {
		mybatis.insert("mappings.notice-mapping.insertNotice",vo);
	}
	
	// ȸ�� �������� ����(����������,�Ѱ�����)
	public void updateNotice(NoticeVO vo) {
		mybatis.update("mappings.notice-mapping.updateNotice",vo);
	}
	
	// ȸ�� �������� ����(����������,�Ѱ�����)
	public void deleteNotice(int nseq) {
		mybatis.delete("mappings.notice-mapping.deleteNotice", nseq);
	}

}
