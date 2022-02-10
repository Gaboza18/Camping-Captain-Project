package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.camping.biz.dto.NoticeVO;
import com.camping.biz.notice.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/*
	 * 공지사항 조회
	 */

	@RequestMapping(value = "/notice_list", method = RequestMethod.GET)
	public String noticeList(HttpSession session, Model model) {

		List<NoticeVO> noticeList = noticeService.listNotice();
		model.addAttribute("noticeList", noticeList);

		return "notice/noticeList";
	}

	/*
	 * 공지사항 상세보기, 조회수 증가
	 */
	@RequestMapping(value = "/notice_detail", method = RequestMethod.GET)
	public String noticeDetail(HttpSession session, NoticeVO vo, Model model, int nseq) {

		noticeService.updateViewCount(vo.getNseq()); // 조회수 증가

		NoticeVO noticeDetail = noticeService.detailNotice(nseq);
		model.addAttribute("noticeDetail", noticeDetail);
		
		return "notice/noticeDetail";
	}

}
