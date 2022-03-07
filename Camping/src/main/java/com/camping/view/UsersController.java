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
	 * return "Users/login"; // login.jsp �씠�룞�븳�떎}
	 */
	
	// 로그인페이지로 이동
	@GetMapping(value = "/login")
	public String loginView() {
		return "Users/login";
	}
	
	/*
	 * 사용자 로그인 표시 
	 * vo객체에서 사용자id, password 정보를 읽어와 사용자 인증
	 */

	@PostMapping(value = "/login")
	public String loginAction(UsersVO vo, Model model) {

		UsersVO loginUser = null;

		int result = usersService.loginID(vo);

		if (result == 1) { // 사용자 인증 성공
			// 사용자 정보를 조회하여 Session 객체에 저장
			loginUser = usersService.getUsers(vo.getId());
			//@SessionAttributes로 지정하여 세션에도 저장됨 
			model.addAttribute("loginUser", loginUser);

			return "index"; // 사용자 인증 성공시  index.jsp 페이지로 이동
		} else { // 사용자 인증 실패
			return "Users/login_fail";
		}
	}

	@GetMapping(value = "/logout")
	public String logout(SessionStatus status) {
		// session.invalidate  // 완전하게 로그아웃이 동작하지않음
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
		return "Users/join";
	}

	/*
	 * ID 중복체크 화면 출력
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
		
		model.addAttribute("id", vo.getId()); // id 값을 모델에 저장해서 회원가입 화면으로 전달
		
		return "Users/join";

	}

	/*
	 * 회원가입 처리
	 */
	@PostMapping(value = "/join")
	public String joinAction(@RequestParam(value="birth") String birth, @RequestParam(value="birth_gen") String birth_gen, UsersVO vo) {
		vo.setBirthday(birth+birth_gen);
		usersService.insertUsers(vo);
		
		return "Users/login";
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

	@RequestMapping(value = "/usermodify", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception {
		return "mypage/userModify";
	}

	/*
	 * id 찾기 페이지로 이동
	 */
	@RequestMapping(value = "/find_id")
	public String findView() {
		return "Users/find_id";
	}

	/*
	 * id 찾기 수행
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

	@RequestMapping(value = "/usersUpdate", method = RequestMethod.POST)
	public String userUpdate(UsersVO vo, HttpSession session) {

		session.invalidate();
		usersService.updateUser(vo);

		return "redirect:/";

	}

	/*
	 * 비밀번호 찾기 페이지로 이동
	 */
	@RequestMapping(value = "/find_pwd")
	public String findPwdView() {
		return "Users/find_pwd";
	}

	/*
	 * 비밀번호 찾기 
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
