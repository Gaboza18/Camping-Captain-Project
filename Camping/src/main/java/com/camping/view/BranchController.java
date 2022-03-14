package com.camping.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BranchController {
	
	@RequestMapping(value = "/branch_Gangwon_do", method = RequestMethod.GET)
	public String branchGangwondo(Model model) {
		return "branch/branch_Gangwon_do_introduce";
	}
	
	@RequestMapping(value = "/branch_Gyeonggi_do", method = RequestMethod.GET)
	public String branchGyeonggido(Model model) {
		return "branch/branch_Gyeonggi_do_introduce";
	}
	
	@RequestMapping(value = "/branch_Chungcheong_do", method = RequestMethod.GET)
	public String branchChungcheongdo(Model model) {
		return "branch/branch_Chungcheong_do_introduce";
	}
	
	@RequestMapping(value = "/branch_Gyeongsang_do", method = RequestMethod.GET)
	public String branchGyeongsangdo(Model model) {
		return "branch/branch_Gyeongsang_do_introduce";
	}
	
	@RequestMapping(value = "/branch_Jeolla_do", method = RequestMethod.GET)
	public String branchJeollado(Model model) {
		return "branch/branch_Jeolla_do_introduce";
	}
	
	@RequestMapping(value = "/branch_Jeju-do", method = RequestMethod.GET)
	public String branchJejudo(Model model) {
		return "branch/branch_Jeju-do_introduce";
	}
	
}
