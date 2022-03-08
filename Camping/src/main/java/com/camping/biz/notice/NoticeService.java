package com.camping.biz.notice;

import java.util.List;
import com.camping.biz.dto.NoticeVO;

import utils.Criteria;

public interface NoticeService {
	
	// 공지사항 조회
	public List<NoticeVO> listNotice();
	
	// �������� �󼼺���
	public NoticeVO detailNotice(int nseq);
	
	// �������� ��ȸ�� ī��Ʈ
	public int updateViewCount(int nseq);
	
	// ��ü ���������� ���� ��ȸ
	public int countNoticetList(String title);
	
	// �������� �������� ��ȸ
	public List<NoticeVO> getListWithPaging(Criteria criteria, String title);
}
