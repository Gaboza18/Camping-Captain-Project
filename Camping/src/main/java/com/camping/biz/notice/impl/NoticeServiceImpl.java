package com.camping.biz.notice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.dao.NoticeDAO;
import com.camping.biz.dto.NoticeVO;
import com.camping.biz.notice.NoticeService;

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
}
