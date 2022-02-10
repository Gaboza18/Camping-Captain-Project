package com.camping.biz.notice;

import java.util.List;
import com.camping.biz.dto.NoticeVO;

import utils.Criteria;

public interface NoticeService {
	
	// �������� ��ü ��ȸ
	public List<NoticeVO> listNotice();
	
	// �������� �󼼺���
	public NoticeVO detailNotice(int nseq);
	
	// �������� ��ȸ�� ī��Ʈ
	public int updateViewCount(int nseq);
	
	// ��ü ���������� ���� ��ȸ
	public int countNoticetList(String admin_name);
	
	// �������� �������� ��ȸ
	public List<NoticeVO> getListWithPaging(Criteria criteria, String admin_name);
}
