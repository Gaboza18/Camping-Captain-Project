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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;

@Controller
@SessionAttributes("loginUser")
public class UsersController {

	@Autowired
	private UsersService usersService;

	/*
	 * @RequestMapping(value = "/index", method = RequestMethod.GET) public String
	 * login(Model model) {
	 * 
	 * return "Users/login"; // login.jsp �씠�룞�븳�떎}
	 */

	@GetMapping(value = "/login")
	public String loginView() {
		return "Users/login";
	}
	
	/*
	 * 占쎄텢占쎌뒠占쎌쁽 嚥≪뮄�젃占쎌뵥 筌ｌ꼶�봺 VO, 揶쏆빘猿쒙옙肉됵옙苑� id, password 占쎌젟癰귣�占쏙옙 占쎌뵭占쎈선占쏙옙 占쎄텢占쎌뒠占쎌쁽 占쎌뵥筌앾옙
	 */

	@PostMapping(value = "/login")
	public String loginAction(UsersVO vo, Model model) {

		UsersVO loginUser = null;

		int result = usersService.loginID(vo);

		if (result == 1) { // 占쎌뵥筌앹빘苑��⑤벊�뻻
			// 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 鈺곌퀬�돳占쎈릭占쎈연 Session 揶쏆빘猿쒙옙肉� 占쏙옙占쎌삢
			loginUser = usersService.getUsers(vo.getId());
			// @SessionAttribute嚥∽옙 筌욑옙占쎌젟占쎈릭占쎈연 占쎄쉭占쎈�∽옙肉됵옙猷� 占쏙옙占쎌삢占쎈쭡
			model.addAttribute("loginUser", loginUser);

			return "index"; // 嚥≪뮄�젃占쎌뵥 占쎄쉐�⑤벏釉�筌롳옙 index.jsp嚥∽옙 占쎌뵠占쎈짗
		} else { // 占쎄텢占쎌뒠占쎌쁽 占쎌뵥筌앾옙 占쎈뼄占쎈솭
			return "Users/login_fail";
		}
	}

	@GetMapping(value = "/logout")
	public String logout(SessionStatus status) {
		// session.invalidate占쎈뮉 占쎌끏占쎌읈占쎌뿳 嚥≪뮄�젃占쎈툡占쎌뜍占쎈릭筌욑옙 占쎈륫疫뀐옙 占쎈르�눧紐꾨퓠 占쎈툧占쏙옙
		status.setComplete();

		return "Users/login";
	}

	@GetMapping(value = "/contract")
	public String contractView() {
		return "Users/contract";
	}

	@PostMapping(value = "/join_form")
	public String joinView() {
		System.out.println("占쎌돳占쎌뜚揶쏉옙占쎌뿯筌욊쑴�뿯");
		return "Users/join";
	}

	/*
	 * ID 餓λ쵎�궗 筌ｋ똾寃� 占쎌넅筌롳옙 �빊�뮆�젾 
	 */

	@GetMapping(value = "/id_check_form")
	public String idCheckView(UsersVO vo, Model model) {

		model.addAttribute("id", vo.getId());
		return "Users/idcheck";
	}

	/*
	 * ID 餓λ쵎�궗筌ｋ똾寃� 占쎈땾占쎈뻬
	 */

	@PostMapping(value = "/id_check_form")
	public String idCheckAction(UsersVO vo, Model model) {

		int result = usersService.confirmID(vo.getId());
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		return "Users/idcheck";

	}

	/*
	 * 占쎄텢占쎌뒠占쎈막 id�몴占� join(占쎌돳占쎌뜚揶쏉옙占쎌뿯)占쎌넅筌롫똻肉� 占쎌읈占쎈꽊
	 */

	@GetMapping(value = "/id_check_confirmed")
	public String idCheckConfirmed(UsersVO vo, Model model) {
		model.addAttribute("id", vo.getId()); // id 餓λ쵎�궗占쎌넇占쎌뵥 占쎈툡占쎈굡
		return "Users/join";

	}

	/*
	 * 占쎌돳占쎌뜚揶쏉옙占쎌뿯 筌ｌ꼶�봺
	 */

	@PostMapping(value = "/join")
	public String joinAction(UsersVO vo) {
		usersService.insertUsers(vo);
		return "Users/login";
	}

	// 占쎌돳占쎌뜚占쎄퉱占쎈닚 get
	@RequestMapping(value = "/deleteIdView", method = RequestMethod.GET)
	public String usersDeleteView() throws Exception {
		return "Users/deleteIdView";
	}

	// 占쎌돳占쎌뜚占쎄퉱占쎈닚 post
	@RequestMapping(value = "/usersDelete", method = RequestMethod.POST)
	public String usersDelete(String id, String password, RedirectAttributes rttr, HttpSession session, Model model,
			UsersVO vo) throws Exception {
		usersService.deleteId(vo);
		model.addAttribute("password", vo.getPassword());
		model.addAttribute("id", vo.getId());
		session.invalidate();
		rttr.addFlashAttribute("msg", "占쎌뵠占쎌뒠占쎈퉸 雅뚯눘�쏉옙苑� 揶쏅Ŋ沅쀯옙鍮�占쎈빍占쎈뼄");
		return "redirect:/index";

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

	@RequestMapping(value = "/usermodify", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception {
		return "mypage/userModify";
	}

	/*
	 * 占쎈툡占쎌뵠占쎈탵 筌≪뼐由� 占쎈읂占쎌뵠筌욑옙 占쎌뵠占쎈짗
	 */
	@RequestMapping(value = "/find_id")
	public String findView() {
		return "Users/find_id";
	}

	/*
	 * 占쎈툡占쎌뵠占쎈탵 筌≪뼐由� 占쎈뼄占쎈뻬
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
	 * �뜮袁⑨옙甕곕뜇�깈 筌≪뼐由� 占쎈읂占쎌뵠筌욑옙 占쎌뵠占쎈짗
	 */
	@RequestMapping(value = "/find_pwd")
	public String findPwdView() {
		return "Users/find_pwd";
	}

	/*
	 * �뜮袁⑨옙甕곕뜇�깈 筌≪뼐由경에占� 占쎈뼄占쎈뻬
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
