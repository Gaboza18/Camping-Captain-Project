package com.camping.biz.notice;

import java.util.List;
import com.camping.biz.dto.NoticeVO;

public interface NoticeService {
	
	// �������� ��ü ��ȸ
	public List<NoticeVO> listNotice();
	
	// �������� �󼼺���
	public NoticeVO detailNotice(int nseq);
	
	// �������� ��ȸ�� ī��Ʈ
	public int updateViewCount(int nseq);
	
}
