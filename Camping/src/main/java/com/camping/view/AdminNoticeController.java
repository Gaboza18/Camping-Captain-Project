package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camping.biz.adminnotice.AdminNoticeService;
import com.camping.biz.dto.AdminNoticeVO;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("admin_notice")
public class AdminNoticeController {
	
	@Autowired
	private AdminNoticeService adminNoticeService;
	
	/*
	 * 관리자 공지사항 상세보기, 조회수 증가(총관리자)
	 */
	@RequestMapping(value = "/admin_notice_detail", method = RequestMethod.GET)
	public String adminNoticeDetail(HttpSession session, AdminNoticeVO vo, Model model, int aseq) {

		adminNoticeService.updateViewCount(vo.getAseq()); // 조회수 증가

		AdminNoticeVO AdminNoticeDetail = adminNoticeService.detailNotice(aseq);
		model.addAttribute("adminnoticeVO", AdminNoticeDetail);

		return "admin/adminnotice/admin_notice_detail";
	}
	
	/*
	 * 관리자 공지사항(페이징 처리) 조회(총관리자)
	 */
	@RequestMapping(value = "/admin_notice_list", method = RequestMethod.GET)
	public String adminnoticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		// 공지사항 목록 조회 - 공지사항 10개만 조회
		List<AdminNoticeVO> AdminNoticeList = adminNoticeService.getListWithPaging(criteria, title);

		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = adminNoticeService.countNoticeList(title);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("adminnoticeList", AdminNoticeList); // ${noticeList} 속성값에 담고 화면에 호출한다
		model.addAttribute("adminnoticeListSize", AdminNoticeList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "admin/adminnotice/admin_notice_list"; // 공지사항 리스트 화면으로 전송
	}
	
	/*
	 *  관리자 공지사항 조회(지점관리자)
	 */
	@RequestMapping(value = "/manager_admin_notice_list", method = RequestMethod.GET)
	public String managerAdminNoticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		// 공지사항 목록 조회 - 공지사항 10개만 조회
		List<AdminNoticeVO> AdminNoticeList = adminNoticeService.getListWithPaging(criteria, title);

		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = adminNoticeService.countNoticeList(title);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("adminnoticeList", AdminNoticeList); // ${noticeList} 속성값에 담고 화면에 호출한다
		model.addAttribute("adminnoticeListSize", AdminNoticeList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "admin/adminnotice/manager_admin_notice_list"; // 공지사항 리스트 화면으로 전송
	}
	
	/*
	 * 관리자 공지사항 상세보기(지점 관리자)
	 */
	@RequestMapping(value = "/manager_admin_notice_detail", method = RequestMethod.GET)
	public String managerAdminNoticeDetail(HttpSession session, AdminNoticeVO vo, Model model, int aseq) {

		adminNoticeService.updateViewCount(vo.getAseq()); // 조회수 증가

		AdminNoticeVO AdminNoticeDetail = adminNoticeService.detailNotice(aseq);
		model.addAttribute("adminnoticeVO", AdminNoticeDetail);

		return "admin/adminnotice/manager_admin_notice_detail";
	}
	
	/*
	 * 관리자 공지사항 등록 페이지 이동(총관리자)
	 */
	@RequestMapping(value="/admin_notice_write_form", method=RequestMethod.GET)
	public String adminNoitceWriteForm(HttpSession session, AdminNoticeVO vo) {
		return "admin/adminnotice/admin_notice_write_form";
	}
	
	/*
	 * 관리자 공지사항 등록(총관리자)
	 */
	@RequestMapping(value="/admin_notice_write", method=RequestMethod.GET)
	public String adminNoticeWrite(HttpSession session, AdminNoticeVO vo) {
		
		adminNoticeService.insertAdminNotice(vo);
		return "redirect:admin_notice_list";
	}
	
	/*
	 * 관리자 공지사항 수정 페이지 이동(총관리자)
	 */
	@RequestMapping(value = "/admin_notice_update_form", method = RequestMethod.GET)
	public String adminQuestionUpdateForm(@RequestParam("aseq") int aseq, Model model) {
		
		AdminNoticeVO  adminNoitceDetail = adminNoticeService.detailNotice(aseq);
		model.addAttribute("adminnoticeVO", adminNoitceDetail);
		
		return "admin/adminnotice/admin_notice_update_form";
	}
	
	/*
	 * 관리자 공지사항 수정(총관리자)
	 */
	@RequestMapping(value="/admin_notice_update", method=RequestMethod.GET)
	public String adminNoticeUpdate(HttpSession session, AdminNoticeVO vo) {
		
		adminNoticeService.updateAdminNotice(vo);
		return "redirect:admin_notice_list";
	}
	
	/*
	 * 관리자 공지사항 삭제(총관리자)
	 */
	@RequestMapping(value = "/admin_notice_delete", method = RequestMethod.GET)
	public String adminQuestionDelete(HttpSession session, AdminNoticeVO vo, int aseq) {
		
		adminNoticeService.deleteAdminNotice(aseq);
		return "redirect:admin_notice_list";
	}
	
}