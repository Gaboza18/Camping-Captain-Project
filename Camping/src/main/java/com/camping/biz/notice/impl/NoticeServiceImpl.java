package com.camping.biz.notice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.dao.NoticeDAO;
import com.camping.biz.dto.NoticeVO;
import com.camping.biz.notice.NoticeService;

import utils.Criteria;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO noticeDao; 

	@Override
	public List<NoticeVO> listNotice() {
		return noticeDao.listNotice();
	}

	@Override
	public NoticeVO detailNotice(int nseq) {
		return noticeDao.detailNotice(nseq);
	}

	@Override
	public int updateViewCount(int nseq) {
		return noticeDao.updateViewCount(nseq);
	}

	@Override
	public List<NoticeVO> getListWithPaging(Criteria criteria, String title) {
		return noticeDao.getListWithPaging(criteria, title);
	}

	@Override
	public int countNoticetList(String title) {
		return noticeDao.countNoticeList(title);
	}

	@Override
	public void insertNotice(NoticeVO vo) {
		noticeDao.insertNotice(vo);
	}

	@Override
	public void updateNotice(NoticeVO vo) {
		noticeDao.updateNotice(vo);
	}

	@Override
	public void deleteNotice(int nseq) {
		noticeDao.deleteNotice(nseq);
	}

}
