package com.camping.view;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	
	/*
	 * index.html에서 메인화면 표시를 위한 index URL 요청처리
	 */
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "index"; // index.jsp 화면을 호출
	}
	
}
