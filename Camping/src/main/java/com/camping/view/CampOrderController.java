package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.camping.biz.camporder.CampOrderService;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.UsersVO;

@Controller
public class CampOrderController {
	
	@Autowired
	CampOrderService campOrderService;
	
	/*
	 *  내 예약현황 보기
	 */
	
	@RequestMapping(value = "/my_reservation", method = RequestMethod.GET)
	public String my_reservation(HttpSession session, Model model) {
		
		// 로그인한 정보를 객체에 담아 가져온다
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		// 로그인이 유,무 확인후 로그인이 되어있지 않으면 로그인 페이지로 세션 전달
		if (loginUser == null) {
			return "Users/login";
		} else {
			List<CampOrderVO> campOrderList = campOrderService.getAllCampOrderList(loginUser.getId());
			
			model.addAttribute("campOrderList", campOrderList);
			
			return "camping/campOrderList";
		}
	}
}