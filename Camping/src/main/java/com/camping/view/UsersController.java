package com.camping.view;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;


@Controller
@SessionAttributes("loginUser")

public class UsersController {
	// 이메일 인증 이메일 보낼때 필요한 객체
//    @Autowired
//    private MailSendService mss;

	@Autowired
	private UsersService usersService;

	@GetMapping(value = "/login")
	public String loginView(HttpSession session) {
		//일반 로그인창 진입시 admin세션 삭제 => 이유: admin과 user 아이디 동시에 접속시
		//admin 권한을 없애기 위해
		session.removeAttribute("loginAdmin");
		
		return "Users/login";
	}
	
	/*
	 * 사용자 로그인 처리 VO, 객체에서 id, password 정보를 읽어와 사용자 인증
	 */

	@PostMapping(value = "/login")
	public String loginAction(UsersVO vo, Model model,HttpSession session) {

		UsersVO loginUser = null;
	
		int result = usersService.loginID(vo);

		if (result == 1) { // 인증성공시
			// 사용자 정보를 조회하여 Session 객체에 저장
			loginUser = usersService.getUsers(vo.getId());
			
			//admin session 삭제
			session.removeAttribute("loginAdmin");

			// @SessionAttribute로 지정하여 세션에도 저장됨
			model.addAttribute("loginUser", loginUser);

			return "index"; // 로그인 성공하면 index.jsp로 이동
		} else { // 사용자 인증 실패
			return "Users/login_fail";
		}
	}

	@GetMapping(value = "/logout")
	public String logout(SessionStatus status) {
		// session.invalidate는 완전히 로그아웃하지 않기 때문에 안씀
		status.setComplete();

		return "Users/login";
	}

	@GetMapping(value = "/contract")
	public String contractView() {
		return "Users/contract";
	}

	@PostMapping(value = "/join_form")
	public String joinView() {
		System.out.println("회원가입진입");
		return "Users/join";
	}

	/*
	 * ID 중복 체크 화면 출력 
	 */

	@GetMapping(value = "/id_check_form")
	public String idCheckView(UsersVO vo, Model model) {

		model.addAttribute("id", vo.getId());
		return "Users/idcheck";
	}

	/*
	 * ID 중복체크 수행
	 */

	@PostMapping(value = "/id_check_form")
	public String idCheckAction(UsersVO vo, Model model) {

		int result = usersService.confirmID(vo.getId());
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		return "Users/idcheck";

	}
	
	//이메일 체크 화면
	
	
	//참고 자료 홈페이지 내용 : 3-1. DB에 기본 정보 저장 이메일 인증실패-전송만가능
//	@GetMapping(value="/signUpConfirm")
//	public String emailView(UsersVO vo, Model model) {
//		
//		//이메일 인증
//		 String emailchk = mss.sendemailchkMail(vo.getEmail());
//	     vo.setEmailchk(emailchk);   
//		 
//		 
//
//	        Map<String, String> map = new HashMap<String, String>();
//	        map.put("email", vo.getEmail());
//	        map.put("authKey", vo.getEmailchk());
//	        System.out.println(map);
//
//	      //DB에 emailchk 업데이트
//	     usersService.emailchk(map);
//		
//	
//		model.addAttribute("email", vo.getEmail());
//		return "Users/emailcheck";
//	}
	
	

//	 @PostMapping(value="/resignUpConfirm") public String
//	  signUpConfirm(UsersVO vo){
//	  //email, authKey 가 일치할경우 authStatus 업데이트 
//		 usersService.updateemailchk(vo);
//	  
//	
//		 return "Users/signUp_confirm";
//	  }
	 

	/*
	 * 사용할 id를 join(회원가입)화면에 전송
	 */

	@GetMapping(value = "/id_check_confirmed")
	public String idCheckConfirmed(UsersVO vo, Model model) {
		model.addAttribute("id", vo.getId()); // id 중복확인 필드
		return "Users/join";

	}

	/*
	 * 회원가입 처리
	 */

	
	@PostMapping(value = "/join") // 이메일 인증위해 modellattribute설정
	public String joinAction(UsersVO vo, Model model) {
	
		usersService.insertUsers(vo);
	
		return "Users/login";
	}
	

	
	
	
	@RequestMapping(value= "deleteIdView")
	
	public String uersDeleteId() throws Exception {
		return "Users/deleteIdlogin";
	}
	
	@RequestMapping(value= "deleteIdlogin")
	public String uersDeleteIdLogin(UsersVO vo, Model model) throws Exception {
		UsersVO loginUser = null;

		int result = usersService.loginID(vo);

		if (result == 1) { // 인증성공시
			// 사용자 정보를 조회하여 Session 객체에 저장
			loginUser = usersService.getUsers(vo.getId());
			// @SessionAttribute로 지정하여 세션에도 저장됨
			model.addAttribute("loginUser", loginUser);

			return "Users/deleteIdView";
		} else { // 사용자 인증 실패
			return "Users/login_fail";
		}
	}
		
	

	
	//----------------- 회원탈퇴 post
	@RequestMapping(value = "/usersDelete", method = RequestMethod.POST)
	public String usersDelete(UsersVO vo, HttpSession session, SessionStatus status
			) throws Exception {
		
		UsersVO deleteUser = (UsersVO) session.getAttribute("loginUser");
		
	if (deleteUser == null) {
			return "Users/deleteIdView"; 
				
			}else {
			
				vo.setId(deleteUser.getId());
				vo.setPassword(deleteUser.getPassword());
				
				
				
//				model.addAttribute("id", vo.getId());
//				model.addAttribute("password", vo.getPassword());
				usersService.deleteId(vo);
				status.setComplete();

				return "redirect:/index";

			}
			
			
			
	}
	


	@GetMapping(value = "/mypage")
	public String mypageView(UsersVO vo, HttpSession session) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {
			return "mypage/mypage";
		}
	}
	//회원가입 수정

	@RequestMapping(value = "/usermodify", method = RequestMethod.GET)
	public String registerUpdateView(HttpSession session, Model model) throws Exception {
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		UsersVO users = usersService.getUsers(loginUser.getId());
		model.addAttribute("users",users);
		
		return "mypage/userModify";
	}
	
	// update 구문
	@RequestMapping(value="/usersUpdate", method = RequestMethod.POST)
	public String userUpdate(UsersVO vo, SessionStatus status )  {
		
		
		usersService.updateUser(vo);
		status.setComplete();
					
		return "redirect:/";
		
	}

	/*
	 * 아이디 찾기 페이지 이동
	 */
	@RequestMapping(value = "/find_id")
	public String findView() {
		return "Users/find_id";
	}

	/*
	 * 아이디 찾기 실행
	 */

	@RequestMapping(value = "/find_id", method = RequestMethod.POST)
	public String findIdAction(UsersVO vo, Model model) {

		UsersVO user = usersService.findId(vo);

		if (user == null) {
			model.addAttribute("check", 1);
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("id", user.getId());
		}
		return "Users/find_id";
	}

	

	/*
	 * 비밀번호 찾기 페이지 이동
	 */
	@RequestMapping(value = "/find_pwd")
	public String findPwdView() {
		return "Users/find_pwd";
	}

	/*
	 * 비밀번호 찾기로 실행
	 */

	@RequestMapping(value = "Users/find_pwd", method = RequestMethod.POST)
	public void findPwdPOST(@ModelAttribute UsersVO user, HttpServletResponse response) throws IOException {
		usersService.findPwd(response, user);
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("지점을 선택하세요", "0");
		conditionMap.put("캠핑족장-강원도지점", "1");

		return conditionMap;
	}
}
