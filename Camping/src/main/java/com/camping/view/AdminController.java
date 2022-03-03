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

		//realreviewVo�� ������ �� �� �ִ� ������ ��µ�, ���ǿ� ������ �ȵǾ������� list�������� if ������ login�� ����
		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");
		if (loginAdmin == null) {
			return "admin/admin_login";

		}else {
		
		// �������� ��� ��ȸ - �������� 10���� ��ȸ

		List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);
		
		// ȭ�鿡 ǥ���� ������ ��ư ���� ����
		PageMaker pageMaker = new PageMaker();
		int totalCount = reviewsService.countReviewlist(title);

		pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
		pageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ

		model.addAttribute("reviewList", reviewList); // ����, �� ������ ���� ������ reviewList���� <for:each>�� ������ ������
		model.addAttribute("reviewListSize", reviewList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "realreview/reviewList";
	}
	}
	
	
	 
	  
	  
}

	
	
	
	
	
	

