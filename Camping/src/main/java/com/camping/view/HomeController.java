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
	 * index.html���� ����ȭ�� ǥ�ø� ���� index URL ��ûó��
	 */
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "index"; // index.jsp ȭ���� ȣ��
	}
	
}
