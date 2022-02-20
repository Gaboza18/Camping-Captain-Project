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
	 *  �� ������Ȳ ����
	 */
	
	@RequestMapping(value = "/my_reservation", method = RequestMethod.GET)
	public String my_reservation(HttpSession session, Model model) {
		
		// �α����� ������ ��ü�� ��� �����´�
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		// �α����� ��,�� Ȯ���� �α����� �Ǿ����� ������ �α��� �������� ���� ����
		if (loginUser == null) {
			return "Users/login";
		} else {
			List<CampOrderVO> campOrderList = campOrderService.getAllCampOrderList(loginUser.getId());
			
			model.addAttribute("campOrderList", campOrderList);
			
			return "camping/campOrderList";
		}
	}
}