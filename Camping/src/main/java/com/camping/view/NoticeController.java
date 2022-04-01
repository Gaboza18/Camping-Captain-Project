package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.NoticeVO;
import com.camping.biz.notice.NoticeService;

import utils.Criteria;
import utils.PageMaker;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/*
	 * 공지사항 상세보기, 조회수 증가 - 비회원,회원
	 */
	@RequestMapping(value = "/notice_detail", method = RequestMethod.GET)
	public String noticeDetail(HttpSession session, NoticeVO vo, Model model, int nseq) {

		noticeService.updateViewCount(vo.getNseq()); // 조회수 증가

		NoticeVO noticeDetail = noticeService.detailNotice(nseq);
		model.addAttribute("noticeVO", noticeDetail);

		return "notice/noticeDetail";
	}

	/*
	 * 공지사항 상세보기, 조회수 증가 - 관리자
	 */
	@RequestMapping(value = "/master_manage_notice_detail", method = RequestMethod.GET)
	public String masterNoticeDetail(HttpSession session, NoticeVO vo, Model model, int nseq) {

		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");
		noticeService.updateViewCount(vo.getNseq()); // 조회수 증가

		NoticeVO noticeDetail = noticeService.detailNotice(nseq);
		model.addAttribute("noticeVO", noticeDetail);
		model.addAttribute("admin", loginAdmin.getName());

		return "notice/master_noticeDetail";
	}

	/*
	 * 공지사항(페이징 처리) 조회 - 비회원,회원
	 */
	@RequestMapping(value = "/notice_list", method = RequestMethod.GET)
	public String noticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		List<NoticeVO> noticeList = noticeService.getListWithPaging(criteria, title);

		PageMaker pageMaker = new PageMaker();
		int totalCount = noticeService.countNoticetList(title);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("noticeList", noticeList);
		model.addAttribute("noticeListSize", noticeList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "notice/noticeList"; // 공지사항 리스트 화면으로 전송
	}

	/*
	 * 공지사항(페이징 처리) 조회 - 관리자
	 */

	@RequestMapping(value = "/master_manage_notice_list", method = RequestMethod.GET)
	public String masterNoticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		// 공지사항 목록 조회 - 공지사항 10개만 조회
		List<NoticeVO> noticeList = noticeService.getListWithPaging(criteria, title);

		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = noticeService.countNoticetList(title);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("noticeList", noticeList); // ${noticeList} 속성값에 담고 화면에 호출한다
		model.addAttribute("noticeListSize", noticeList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "notice/master_manage_notice_list"; // 공지사항 리스트 화면으로 전송
	}

	/*
	 * 회원 공지사항 등록 페이지 이동(총관리자)
	 */
	@RequestMapping(value = "/master_notice_manage_form", method = RequestMethod.GET)
	public String masterNoitceManageForm(HttpSession session, NoticeVO vo) {
		return "notice/master_notice_manage_form";
	}

	/*
	 * 회원 공지사항 등록(총관리자)
	 */
	@RequestMapping(value = "/master_notice_manage", method = RequestMethod.GET)
	public String masterNoticeWrite(HttpSession session, NoticeVO vo) {

		noticeService.insertNotice(vo);
		return "redirect:master_manage_notice_list";

	}

	/*
	 * 회원 공지사항 수정 페이지 이동(총관리자)
	 */
	@RequestMapping(value = "/master_manage_notice_update_form", method = RequestMethod.GET)
	public String masterQuestionUpdateForm(@RequestParam("nseq") int nseq, Model model) {

		NoticeVO NoitceDetail = noticeService.detailNotice(nseq);
		model.addAttribute("noticeVO", NoitceDetail);

		return "notice/master_notice_update_form";
	}

	/*
	 * 회원 공지사항 수정(총관리자)
	 */
	@RequestMapping(value = "/master_manage_notice_update", method = RequestMethod.GET)
	public String masterManageNoticeUpdate(HttpSession session, NoticeVO vo) {

		noticeService.updateNotice(vo);
		return "redirect:master_manage_notice_list";
	}

	/*
	 * 회원 공지사항 삭제(총관리자)
	 */
	@RequestMapping(value = "/master_manage_notice_delete", method = RequestMethod.GET)
	public String masterManageNoticeDelete(HttpSession session, NoticeVO vo, int nseq) {

		noticeService.deleteNotice(nseq);
		return "redirect:master_manage_notice_list";
	}

}
