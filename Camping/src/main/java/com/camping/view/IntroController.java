package com.camping.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IntroController {
	/*
	 *  ����ȭ�鿡�� "ķ������ �Ұ�" �޴� Ŭ�� �� �Ұ��������� �̵�
	 */
	@RequestMapping(value = "/intro", method = RequestMethod.GET)
	public String introview(Model model) {
		
		return "intro";
	}
}
