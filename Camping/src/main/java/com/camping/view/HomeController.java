package com.camping.view;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	
	/**
	 * index.html���� ����ȭ�� ǥ�ø� ���� index URL ��ûó��
	 */
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "index"; // index.jsp ȭ���� ȣ��
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("������ �����ϼ���", "0");
		conditionMap.put("ķ������-����������", "1");
		conditionMap.put("ķ������-��⵵����", "2");
		conditionMap.put("ķ������-��û������", "3");
		conditionMap.put("ķ������-�������", "4");
		conditionMap.put("ķ������-��������", "5");
		conditionMap.put("ķ������-���ֵ�����", "6");

		return conditionMap;
	}
	
}
