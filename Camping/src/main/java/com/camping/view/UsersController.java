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
		
		return "Users/login"; // login.jsp 화면을 호출
	} */
	
	
	@GetMapping(value="/login_form")
	public String loginView() {
		return "Users/login";
	}
	/*
	 * 사용자 로그인 처리
	 * vo 객체에서 id, password 정보를 읽어와 사용자 인증
	 */
	
	@PostMapping(value="/login")
	public String loginAction(UsersVO vo, Model model) {
		
		UsersVO loginUser = null;
		
		int result = usersService.loginID(vo);
		
		if(result == 1) { // 인증성공시
			//사용자 정보를 조회하여 Session 객체에 저장
			loginUser = usersService.getUsers(vo.getId());
			//@SessionAttribute로 지정하여 세션에도 저장됨
			model.addAttribute("loginUser", loginUser);
			
			return "index"; // 로그인 성공하면 index.jsp로 이동 
		}else { // 사용자 인증 실패
			return "Users/login_fail";
		}
	}
	

	@GetMapping(value="/logout")
	public String logout(SessionStatus status) {
		//session.invalidate는 완전히 로그아웃하지 않기 때문에 안씀
		status.setComplete();
		
		return "Users/login";
	}
	
	
	@GetMapping(value="/contract")
	public String contractView() {
		
		return "Users/contract";
	}

	

	@PostMapping(value="/join_form") 
	public String joinView() {
		System.out.println("회원가입진입");
		return "Users/join";
	}

//	
//	/*
//	 * ID 중복 체크 화면 출력
//	 */

	@GetMapping(value="/id_check_form")
	public String idCheckView(UsersVO vo, Model model) {
		
		model.addAttribute("id", vo.getId());
		return "Users/idcheck";
	}
	
	/*
	 *  ID 중복체크 수행
	 */
	
	@PostMapping(value="/id_check_form")
	public String idCheckAction(UsersVO vo, Model model) {
		
		int result = usersService.confirmID(vo.getId());
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		return "Users/idcheck";
		
	}

	/*
	 *  사용할 id를 join(회원가입)화면에 전송
	 */

	@GetMapping(value="/id_check_confirmed")
	public String idCheckConfirmed(UsersVO vo, Model model) {
	model.addAttribute("id", vo.getId()); // id 중복확인 필드
	return "Users/join";
	
	}

	/*
	 * 회원가입 처리
	 */
	
	@PostMapping(value="/join")
	public String joinAction (UsersVO vo) {
		usersService.insertUsers(vo);
		
		return "Users/login";
	}

	
	
}
