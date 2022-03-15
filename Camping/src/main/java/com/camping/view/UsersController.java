package com.camping.view;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.camping.biz.camporder.CampOrderService;
import com.camping.biz.campordercancel.CampOrderCancelService;
import com.camping.biz.dto.UsersVO;

import com.camping.biz.users.UsersService;

@Controller
@SessionAttributes("loginUser")

public class UsersController {
  
	// 이메일 인증 이메일 보낼때 필요한 객체
	@Autowired
	private MailSendService mss;
	@Autowired
	private UsersService usersService;
	@Autowired
	private CampOrderService campOrderService;
	@Autowired
	private CampOrderCancelService campOrderCancelService;

	/*
	 * @RequestMapping(value = "/index", method = RequestMethod.GET) public String
	 * login(Model model) {
	 * 
	 * return "Users/login"; // login.jsp 씠 룞 븳 떎}
	 */

	// 로그인페이지로 이동
	@GetMapping(value = "/login")
	public String loginView(HttpSession session) {
		// 일반 로그인창 진입시 admin세션 삭제 => 이유: admin과 user 아이디 동시에 접속시
		// admin 권한을 없애기 위해
		session.removeAttribute("loginAdmin");

		return "Users/login";
	}

	/*
	 * 사용자 로그인 처리 VO, 객체에서 id, password 정보를 읽어와 사용자 인증 사용자 로그인 표시 vo객체에서 사용자id,
	 * password 정보를 읽어와 사용자 인증
	 */
	@PostMapping(value = "/login_action")
	public String loginAction(UsersVO vo, Model model, HttpSession session) {

		UsersVO loginUser = null;

		int result = usersService.loginID(vo);

		if (result == 1) { // 인증성공시
			// 사용자 정보를 조회하여 Session 객체에 저장
			loginUser = usersService.getUsers(vo.getId());

			// admin session 삭제
			session.removeAttribute("loginAdmin");

			// @SessionAttribute로 지정하여 세션에도 저장됨
			model.addAttribute("loginUser", loginUser);

			return "NewFile"; // 로그인 성공하면 index.jsp로 이동
		} else { // 사용자 인증 실패
			return "Users/login_fail";
		}
	}

	@GetMapping(value = "/logout")
	public String logout(SessionStatus status) {

		// session.invalidate는 완전히 로그아웃하지 않기 때문에 안씀

		// session.invalidate // 완전하게 로그아웃이 동작하지않음
		status.setComplete();

		return "Users/login";
	}

	// 회원가입 시, 약관동의 페이지로 이동
	@GetMapping(value = "/contract")
	public String contractView() {
		return "Users/contract";
	}

	// 약관 동의하면 회원가입페이지로 이동
	@PostMapping(value = "/join_form")
	public String joinView() {
		System.out.println("회원가입진입");
		return "Users/join";
	}

	/*
	 * ID 중복 체크 화면 출력 ID 중복체크 화면 출력
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

	

	/*
	 * 사용할 id를 join(회원가입)화면에 전송
	 */
	@GetMapping(value = "/id_check_confirmed")
	public String idCheckConfirmed(UsersVO vo, Model model) {
		model.addAttribute("id", vo.getId()); // id 값을 모델에 저장해서 회원가입 화면으로 전달, id 중복확인 필드
		return "Users/join";
	}

	/*
	 * 회원가입 처리
	 */

	
	@GetMapping(value = "/join")
	public String joinAction(@RequestParam(value = "birth") String birth,@RequestParam(value = "id") String id,
			@RequestParam(value = "birth_gen") String birth_gen, UsersVO vo, HttpSession session,Model model) {
		
		//임시회원가입 로그인 세션
		session.getAttribute("loginUser");
		
		vo.setBirthday(birth + birth_gen);
		
		usersService.insertUsers(vo);
		UsersVO users = usersService.getUsers(id);
		model.addAttribute("id", vo.getId());
		model.addAttribute("status",users.getStatus());
		
		return "Users/inputemail";
	}
	
	// 이메일 체크 화면

		// 참고 자료 홈페이지 내용 : 3-1. 
		@GetMapping(value = "/signUpConfirm")
		public String emailView(UsersVO vo, Model model, @RequestParam(value = "email") String email, String id,HttpSession session) {
			session.getAttribute("loginUser");
			
			// 이메일 인증
			String emailchk = mss.sendemailchkMail(vo.getId(), vo.getEmail()); //인증키
			UsersVO users = usersService.getUsers(id);
			System.out.println("이메일 인증: vo"+vo);
			
			
			// 사용자 테이블에 인증키(emailchk) 업데이트
			vo.setEmailchk(emailchk);
			usersService.updateemailchk(vo);
			
			model.addAttribute("email",vo.getEmail());
			model.addAttribute("id",vo.getId());
			model.addAttribute("status",users.getStatus());
			//model.addAttribute("email", vo.getEmail());
		
			return "Users/emailcheck";
		
		}

		@GetMapping(value = "/resignUpConfirm")
		public String signUpConfirm(UsersVO vo, Model model, HttpSession session, String email, String id) {
			
			//UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
			
			
			//key는 테이블에서 인증키 조회한것
			String key=usersService.emailchkok(vo.getId(),vo.getEmail());
			
			
			System.out.println("인증키 확인: key="+key);
			System.out.println("인증키 확인: emailChk="+vo.getEmailchk());
			
			//회원 수정에서 id값 받아오기
			//model.addAttribute("id",loginUser.getId());
			
			//vo에서 바로 값 받아 오기
			model.addAttribute("id", vo.getId());
			
			if (key.equals(vo.getEmailchk())) {
				UsersVO users = usersService.getUsers(id);
				model.addAttribute("message", "환영합니다! 이메일이 인증완료 되었습니다!.");
				usersService.updateEmail(vo);
			
				model.addAttribute("status",users.getStatus());
				model.addAttribute("email",vo.getEmail());
				return "Users/inputemail2";
				
			} else {
				model.addAttribute("message", "이메일을 다시 인증해 주세요!");
				return "Users/inputemail";
			}
			
			
		}
		
		@GetMapping(value = "/finaljoin")
		public String finaljoinAction(UsersVO vo,HttpSession session,SessionStatus status) {
			
			//이메일 인증을 해도 세션때문에 이메일 인증을 해라는 링크가 나오기때문에 다시 로그인 시킴
			status.setComplete();
			

			return "Users/login";
		}
		
		//간편 회원가입 상태에서 eamil 인증	
		@GetMapping(value = "/rejoin")
		public String rejoinAction(HttpSession session, Model model,UsersVO vo) {
			
			UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
			
			loginUser = usersService.getUsers(loginUser.getId());
			
		
			model.addAttribute("id",loginUser.getId());
			return "Users/inputemail";
	
		}

	// 회원탈퇴페이지로 이동
	@RequestMapping(value = "/deleteIdView", method = RequestMethod.GET)
	public String usersDeleteView(Model model, HttpSession session) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		UsersVO users = usersService.getUsers(loginUser.getId());
		int OrderCount = campOrderService.countMyOrderList(loginUser.getId());
		int CancelCount = campOrderCancelService.countMyNonCancelList(loginUser.getId());

		model.addAttribute("users", users);
		model.addAttribute("OrderCount", OrderCount);
		model.addAttribute("CancelCount", CancelCount);

		return "Users/deleteIdView";

	}
	
	

	// 회원 탈퇴 실행
	@RequestMapping(value = "/usersDelete", method = RequestMethod.POST)
	public String usersDelete(SessionStatus status, UsersVO vo) {

		usersService.deleteId(vo);
		status.setComplete();

		return "redirect:/index";
	}

	// 마이페이지로 이동
	@GetMapping(value = "/mypage")
	public String mypageView(UsersVO vo, HttpSession session, Model model) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {
			UsersVO users = usersService.getUsers(loginUser.getId());
			
			model.addAttribute("users", users);

			return "mypage/mypage";
		}
	}
	// 회원가입 수정

	@RequestMapping(value = "/usermodify", method = RequestMethod.GET)
	public String registerUpdateView(UsersVO vo,HttpSession session, Model model) throws Exception {
		
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		
		// users 변수를 만들어서 한꺼번에 model로 jsp에 표시하려고 
		UsersVO users = usersService.getUsers(loginUser.getId());
		
		String birthday = users.getBirthday();
		String birth = birthday.substring(0, 6);
		String birth_gen = birthday.substring(6);
		
		//이메일 인증 버튼과 관련된 모델 추가
		model.addAttribute("users", users);
		
		//그대로 emailcheck에 값을 넘겨주기 위해
		model.addAttribute("id",loginUser.getId());
		model.addAttribute("email" ,loginUser.getEmail());
	    model.addAttribute("birth", birth);
	    model.addAttribute("birth_gen", birth_gen);

		return "mypage/userModify";
	}



	// update 구문
	@RequestMapping(value = "/usersUpdate", method = RequestMethod.POST)
	public String userUpdate(UsersVO vo, SessionStatus status,HttpSession session,Model model) {

		usersService.updateUser(vo);
	
		//세션을 지운 이유:이름을 수정하고 바로 이름이 안떠서 
		status.setComplete();
		return "Users/login";

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
		conditionMap.put("캠핑족장-경기도지점", "2");
		conditionMap.put("캠핑족장-충청도지점", "3");
		conditionMap.put("캠핑족장-경상도지점", "4");
		conditionMap.put("캠핑족장-전라도지점", "5");
		conditionMap.put("캠핑족장-제주도지점", "6");

		return conditionMap;
	}
}