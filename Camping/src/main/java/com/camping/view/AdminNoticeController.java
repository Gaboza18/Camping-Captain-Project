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

import com.camping.biz.adminnotice.AdminNotice;
import com.camping.biz.dto.AdminNoticeVO;
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.RealReviewVO;

import utils.Criteria;
import utils.PageMaker;





@Controller
@SessionAttributes("loginAdmin")
public class AdminNoticeController {
	@Autowired
	private AdminNotice adminnoticeService;
	
	

	@RequestMapping(value = "/adminNotice", method = RequestMethod.GET) public
	 String noticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			 HttpSession session, Model model,int aseq) {
			
		  AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");
			if (loginAdmin == null) {
				return "admin/admin_login";

			}else {
		  
				List<AdminNoticeVO> adminnoticeList = adminnoticeService.getListWithPaging(criteria, title);
				
				// 화면에 표시할 페이지 버튼 정보 설정
				PageMaker pageMaker = new PageMaker();
				int totalCount = adminnoticeService.updateViewCount(aseq);

				pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
				pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

				model.addAttribute("adminnoticeList", adminnoticeList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
				model.addAttribute("noticeListSize", adminnoticeList.size());
				model.addAttribute("pageMaker", pageMaker);

				return "adminnotice/adminnoticeList";
	 
 }
	}
	
}
