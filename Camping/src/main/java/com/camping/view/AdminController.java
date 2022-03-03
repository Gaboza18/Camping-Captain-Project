package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camping.biz.admin.AdminService;
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.NoticeVO;
import com.camping.biz.dto.RealReviewVO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.notice.NoticeService;
import com.camping.biz.realreview.RealReviewService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("loginAdmin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private RealReviewService reviewsService;
	@Autowired
	private NoticeService noticeService;
	
	
	
	
	
	@GetMapping(value="/admin_login_form")
	public String AdminLoginView() {
		return "admin/admin_login";
	}
	
	@PostMapping(value="/admin_login")
	public String AdminLoginAction(AdminVO vo, Model model) {
		
		AdminVO loginAdmin = null;
		
		int result = adminService.loginID(vo);
		
		if(result == 1) {
			loginAdmin = adminService.getAdmin(vo.getId());
			model.addAttribute("loginAdmin", loginAdmin);
			
			return "admin/admin_index";
		} else {
			return "admin/login_fail";
		}
	}
	
	@RequestMapping(value = "/adminReview", method = RequestMethod.GET)
	public String reviewList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		//realreviewVo에 디테일 볼 수 있는 로직을 썼는데, 세션에 저장이 안되었을가봐 list볼때에도 if 문으로 login문 구현
		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");
		if (loginAdmin == null) {
			return "admin/admin_login";

		}else {
		
		// 공지사항 목록 조회 - 공지사항 10개만 조회

		List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);
		
		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = reviewsService.countReviewlist(title);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
		model.addAttribute("reviewListSize", reviewList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "realreview/reviewList";
	}
	}
	
	
	 
	  
	  
}

	
	
	
	
	
	

