package com.camping.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;

@Controller
@SessionAttributes("loginUser")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	/*
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login(Model model) {
		
		return "Users/login"; // login.jsp ȭ���� ȣ��
	} */
	
	
	@GetMapping(value="/login_form")
	public String loginView() {
		return "Users/login";
	}
	/*
	 * ����� �α��� ó��
	 * vo ��ü���� id, password ������ �о�� ����� ����
	 */
	
	@PostMapping(value="/login")
	public String loginAction(UsersVO vo, Model model) {
		
		UsersVO loginUser = null;
		
		int result = usersService.loginID(vo);
		
		if(result == 1) { // ����������
			//����� ������ ��ȸ�Ͽ� Session ��ü�� ����
			loginUser = usersService.getUsers(vo.getId());
			//@SessionAttribute�� �����Ͽ� ���ǿ��� �����
			model.addAttribute("loginUser", loginUser);
			
			return "index"; // �α��� �����ϸ� index.jsp�� �̵� 
		}else { // ����� ���� ����
			return "Users/login_fail";
		}
	}
	

	@GetMapping(value="/logout")
	public String logout(SessionStatus status) {
		//session.invalidate�� ������ �α׾ƿ����� �ʱ� ������ �Ⱦ�
		status.setComplete();
		
		return "Users/login";
	}
	
	
	@GetMapping(value="/contract")
	public String contractView() {
		
		return "Users/contract";
	}

	

	@PostMapping(value="/join_form") 
	public String joinView() {
		System.out.println("ȸ����������");
		return "Users/join";
	}

//	
//	/*
//	 * ID �ߺ� üũ ȭ�� ���
//	 */

	@GetMapping(value="/id_check_form")
	public String idCheckView(UsersVO vo, Model model) {
		
		model.addAttribute("id", vo.getId());
		return "Users/idcheck";
	}
	
	/*
	 *  ID �ߺ�üũ ����
	 */
	
	@PostMapping(value="/id_check_form")
	public String idCheckAction(UsersVO vo, Model model) {
		
		int result = usersService.confirmID(vo.getId());
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		return "Users/idcheck";
		
	}

	/*
	 *  ����� id�� join(ȸ������)ȭ�鿡 ����
	 */

	@GetMapping(value="/id_check_confirmed")
	public String idCheckConfirmed(UsersVO vo, Model model) {
	model.addAttribute("id", vo.getId()); // id �ߺ�Ȯ�� �ʵ�
	return "Users/join";
	
	}

	/*
	 * ȸ������ ó��
	 */
	
	@PostMapping(value="/join")
	public String joinAction (UsersVO vo) {
		usersService.insertUsers(vo);
		
		return "Users/login";
	}

	
	
}
