package com.camping.biz.qna;

import java.util.List;

import com.camping.biz.dto.QnaVO;

public interface QnaService {
	
	// QnA ��� ��ȸ
	List<QnaVO> listQna(String id);
	
	// QnA ��ȣ�� �Ѱ� ��ȸ
	QnaVO getQna(int qseq);
	
	// QnA ���
	void insertQna(QnaVO vo);
	
	// QnA ��ȸ(�Ѱ�����)
	public List<QnaVO> listAllQna();
	
	// QnA �亯ó��(�Ѱ�����)
	public void updateQna(QnaVO vo);
}
