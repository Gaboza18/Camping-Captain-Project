package com.camping.biz.qna;

import java.util.List;

import com.camping.biz.dto.QnaVO;

public interface QnaService {
	
	// QnA ��� ��ȸ
	List<QnaVO> listQna(String id);
	
	// �Խñ� ��ȣ�� �Ѱ� ��ȸ
	QnaVO getQna(int qseq);
	
	// QnA ���
	void insertQna(QnaVO vo);
}
