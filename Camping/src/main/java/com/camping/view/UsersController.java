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
	 * return "Users/login"; // login.jsp 이동한다}
	 */

	@GetMapping(value = "/login")
	public String loginView() {
		return "Users/login";
	}
	
	/*
	 * �궗�슜�옄 濡쒓렇�씤 泥섎━ VO, 媛앹껜�뿉�꽌 id, password �젙蹂대�� �씫�뼱�� �궗�슜�옄 �씤利�
	 */

	@PostMapping(value = "/login")
	public String loginAction(UsersVO vo, Model model) {

		UsersVO loginUser = null;

		int result = usersService.loginID(vo);

		if (result == 1) { // �씤利앹꽦怨듭떆
			// �궗�슜�옄 �젙蹂대�� 議고쉶�븯�뿬 Session 媛앹껜�뿉 ���옣
			loginUser = usersService.getUsers(vo.getId());
			// @SessionAttribute濡� 吏��젙�븯�뿬 �꽭�뀡�뿉�룄 ���옣�맖
			model.addAttribute("loginUser", loginUser);

			return "index"; // 濡쒓렇�씤 �꽦怨듯븯硫� index.jsp濡� �씠�룞
		} else { // �궗�슜�옄 �씤利� �떎�뙣
			return "Users/login_fail";
		}
	}

	@GetMapping(value = "/logout")
	public String logout(SessionStatus status) {
		// session.invalidate�뒗 �셿�쟾�엳 濡쒓렇�븘�썐�븯吏� �븡湲� �븣臾몄뿉 �븞��
		status.setComplete();

		return "Users/login";
	}

	@GetMapping(value = "/contract")
	public String contractView() {
		return "Users/contract";
	}

	@PostMapping(value = "/join_form")
	public String joinView() {
		System.out.println("�쉶�썝媛��엯吏꾩엯");
		return "Users/join";
	}

	/*
	 * ID 以묐났 泥댄겕 �솕硫� 異쒕젰 
	 */

	@GetMapping(value = "/id_check_form")
	public String idCheckView(UsersVO vo, Model model) {

		model.addAttribute("id", vo.getId());
		return "Users/idcheck";
	}

	/*
	 * ID 以묐났泥댄겕 �닔�뻾
	 */

	@PostMapping(value = "/id_check_form")
	public String idCheckAction(UsersVO vo, Model model) {

		int result = usersService.confirmID(vo.getId());
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		return "Users/idcheck";

	}

	/*
	 * �궗�슜�븷 id瑜� join(�쉶�썝媛��엯)�솕硫댁뿉 �쟾�넚
	 */

	@GetMapping(value = "/id_check_confirmed")
	public String idCheckConfirmed(UsersVO vo, Model model) {
		model.addAttribute("id", vo.getId()); // id 以묐났�솗�씤 �븘�뱶
		return "Users/join";

	}

	/*
	 * �쉶�썝媛��엯 泥섎━
	 */

	@PostMapping(value = "/join")
	public String joinAction(UsersVO vo) {
		usersService.insertUsers(vo);
		return "Users/login";
	}

	// �쉶�썝�깉�눜 get
	@RequestMapping(value = "/deleteIdView", method = RequestMethod.GET)
	public String usersDeleteView() throws Exception {
		return "Users/deleteIdView";
	}

	// �쉶�썝�깉�눜 post
	@RequestMapping(value = "/usersDelete", method = RequestMethod.POST)
	public String usersDelete(String id, String password, RedirectAttributes rttr, HttpSession session, Model model,
			UsersVO vo) throws Exception {
		usersService.deleteId(vo);
		model.addAttribute("password", vo.getPassword());
		model.addAttribute("id", vo.getId());
		session.invalidate();
		rttr.addFlashAttribute("msg", "�씠�슜�빐 二쇱뀛�꽌 媛먯궗�빀�땲�떎");
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
	 * �븘�씠�뵒 李얘린 �럹�씠吏� �씠�룞
	 */
	@RequestMapping(value = "/find_id")
	public String findView() {
		return "Users/find_id";
	}

	/*
	 * �븘�씠�뵒 李얘린 �떎�뻾
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
	 * 鍮꾨�踰덊샇 李얘린 �럹�씠吏� �씠�룞
	 */
	@RequestMapping(value = "/find_pwd")
	public String findPwdView() {
		return "Users/find_pwd";
	}

	/*
	 * 鍮꾨�踰덊샇 李얘린濡� �떎�뻾
	 */

	@RequestMapping(value = "Users/find_pwd", method = RequestMethod.POST)
	public void findPwdPOST(@ModelAttribute UsersVO user, HttpServletResponse response) throws IOException {
		usersService.findPwd(response, user);
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("지점을 선택하세요", "0");
		conditionMap.put("캠핑족장-강원도 지점", "1");

		return conditionMap;
	}
}
