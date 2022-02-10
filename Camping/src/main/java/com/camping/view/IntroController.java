package com.camping.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IntroController {
	/*
	 *  메인화면에서 "캠핑족장 소개" 메뉴 클릭 시 소개페이지로 이동
	 */
	@RequestMapping(value = "/intro", method = RequestMethod.GET)
	public String introview(Model model) {
		
		return "intro";
	}
}
