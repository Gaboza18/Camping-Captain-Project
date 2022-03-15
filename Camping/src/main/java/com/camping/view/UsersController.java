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
  
	// �씠硫붿씪 �씤利� �씠硫붿씪 蹂대궪�븣 �븘�슂�븳 媛앹껜
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
	 * return "Users/login"; // login.jsp �뵠 猷� 釉� �뼄}
	 */

	// 濡쒓렇�씤�럹�씠吏�濡� �씠�룞
	@GetMapping(value = "/login")
	public String loginView(HttpSession session) {
		// �씪諛� 濡쒓렇�씤李� 吏꾩엯�떆 admin�꽭�뀡 �궘�젣 => �씠�쑀: admin怨� user �븘�씠�뵒 �룞�떆�뿉 �젒�냽�떆
		// admin 沅뚰븳�쓣 �뾾�븷湲� �쐞�빐
		session.removeAttribute("loginAdmin");

		return "Users/login";
	}

	/*
	 * �궗�슜�옄 濡쒓렇�씤 泥섎━ VO, 媛앹껜�뿉�꽌 id, password �젙蹂대�� �씫�뼱�� �궗�슜�옄 �씤利� �궗�슜�옄 濡쒓렇�씤 �몴�떆 vo媛앹껜�뿉�꽌 �궗�슜�옄id,
	 * password �젙蹂대�� �씫�뼱�� �궗�슜�옄 �씤利�
	 */
	@PostMapping(value = "/login_action")
	public String loginAction(UsersVO vo, Model model, HttpSession session) {

		UsersVO loginUser = null;

		int result = usersService.loginID(vo);

		if (result == 1) { // �씤利앹꽦怨듭떆
			// �궗�슜�옄 �젙蹂대�� 議고쉶�븯�뿬 Session 媛앹껜�뿉 ���옣
			loginUser = usersService.getUsers(vo.getId());

			// admin session �궘�젣
			session.removeAttribute("loginAdmin");

			// @SessionAttribute濡� 吏��젙�븯�뿬 �꽭�뀡�뿉�룄 ���옣�맖
			model.addAttribute("loginUser", loginUser);

			return "NewFile"; // 濡쒓렇�씤 �꽦怨듯븯硫� index.jsp濡� �씠�룞
		} else { // �궗�슜�옄 �씤利� �떎�뙣
			return "Users/login_fail";
		}
	}

	@GetMapping(value = "/logout")
	public String logout(SessionStatus status) {

		// session.invalidate�뒗 �셿�쟾�엳 濡쒓렇�븘�썐�븯吏� �븡湲� �븣臾몄뿉 �븞��

		// session.invalidate // �셿�쟾�븯寃� 濡쒓렇�븘�썐�씠 �룞�옉�븯吏��븡�쓬
		status.setComplete();

		return "Users/login";
	}

	// �쉶�썝媛��엯 �떆, �빟愿��룞�쓽 �럹�씠吏�濡� �씠�룞
	@GetMapping(value = "/contract")
	public String contractView() {
		return "Users/contract";
	}

	// �빟愿� �룞�쓽�븯硫� �쉶�썝媛��엯�럹�씠吏�濡� �씠�룞
	@PostMapping(value = "/join_form")
	public String joinView() {
		System.out.println("�쉶�썝媛��엯吏꾩엯");
		return "Users/join";
	}

	/*
	 * ID 以묐났 泥댄겕 �솕硫� 異쒕젰 ID 以묐났泥댄겕 �솕硫� 異쒕젰
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
		model.addAttribute("id", vo.getId()); // id 媛믪쓣 紐⑤뜽�뿉 ���옣�빐�꽌 �쉶�썝媛��엯 �솕硫댁쑝濡� �쟾�떖, id 以묐났�솗�씤 �븘�뱶
		return "Users/join";
	}

	/*
	 * �쉶�썝媛��엯 泥섎━
	 */

	
	@PostMapping(value = "/join")
	public String joinAction(@RequestParam(value = "birth") String birth,@RequestParam(value = "id") String id,
			@RequestParam(value = "birth_gen") String birth_gen, UsersVO vo, HttpSession session,Model model) {
		
		//�엫�떆�쉶�썝媛��엯 濡쒓렇�씤 �꽭�뀡
		session.getAttribute("loginUser");
		
		vo.setBirthday(birth + birth_gen);
		
		usersService.insertUsers(vo);
		UsersVO users = usersService.getUsers(id);
		model.addAttribute("id", vo.getId());
		model.addAttribute("status",users.getStatus());
		
		return "Users/inputemail";
	}
	
	// �씠硫붿씪 泥댄겕 �솕硫�

		// 李멸퀬 �옄猷� �솃�럹�씠吏� �궡�슜 : 3-1. 
		@GetMapping(value = "/signUpConfirm")
		public String emailView(UsersVO vo, Model model, @RequestParam(value = "email") String email, String id,HttpSession session) {
			session.getAttribute("loginUser");
			
			// �씠硫붿씪 �씤利�
			String emailchk = mss.sendemailchkMail(vo.getId(), vo.getEmail()); //�씤利앺궎
			UsersVO users = usersService.getUsers(id);
			System.out.println("�씠硫붿씪 �씤利�: vo"+vo);
			
			
			// �궗�슜�옄 �뀒�씠釉붿뿉 �씤利앺궎(emailchk) �뾽�뜲�씠�듃
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
			
			
			//key�뒗 �뀒�씠釉붿뿉�꽌 �씤利앺궎 議고쉶�븳寃�
			String key=usersService.emailchkok(vo.getId(),vo.getEmail());
			
			
			System.out.println("�씤利앺궎 �솗�씤: key="+key);
			System.out.println("�씤利앺궎 �솗�씤: emailChk="+vo.getEmailchk());
			
			//�쉶�썝 �닔�젙�뿉�꽌 id媛� 諛쏆븘�삤湲�
			//model.addAttribute("id",loginUser.getId());
			
			//vo�뿉�꽌 諛붾줈 媛� 諛쏆븘 �삤湲�
			model.addAttribute("id", vo.getId());
			
			if (key.equals(vo.getEmailchk())) {
				UsersVO users = usersService.getUsers(id);
				model.addAttribute("message", "�솚�쁺�빀�땲�떎! �씠硫붿씪�씠 �씤利앹셿猷� �릺�뿀�뒿�땲�떎!.");
				usersService.updateEmail(vo);
			
				model.addAttribute("status",users.getStatus());
				model.addAttribute("email",vo.getEmail());
				return "Users/inputemail2";
				
			} else {
				model.addAttribute("message", "�씠硫붿씪�쓣 �떎�떆 �씤利앺빐 二쇱꽭�슂!");
				return "Users/inputemail";
			}
			
			
		}
		
		@GetMapping(value = "/finaljoin")
		public String finaljoinAction(UsersVO vo,HttpSession session,SessionStatus status) {
			
			//�씠硫붿씪 �씤利앹쓣 �빐�룄 �꽭�뀡�븣臾몄뿉 �씠硫붿씪 �씤利앹쓣 �빐�씪�뒗 留곹겕媛� �굹�삤湲곕븣臾몄뿉 �떎�떆 濡쒓렇�씤 �떆�궡
			status.setComplete();
			

			return "Users/login";
		}
		
		//媛꾪렪 �쉶�썝媛��엯 �긽�깭�뿉�꽌 eamil �씤利�	
		@GetMapping(value = "/rejoin")
		public String rejoinAction(HttpSession session, Model model,UsersVO vo) {
			
			UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
			
			loginUser = usersService.getUsers(loginUser.getId());
			
		
			model.addAttribute("id",loginUser.getId());
			return "Users/inputemail";
	
		}

	// �쉶�썝�깉�눜�럹�씠吏�濡� �씠�룞
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
	
	

	// �쉶�썝 �깉�눜 �떎�뻾
	@RequestMapping(value = "/usersDelete", method = RequestMethod.POST)
	public String usersDelete(SessionStatus status, UsersVO vo) {

		usersService.deleteId(vo);
		status.setComplete();

		return "redirect:/index";
	}

	// 留덉씠�럹�씠吏�濡� �씠�룞
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
	// �쉶�썝媛��엯 �닔�젙

	@RequestMapping(value = "/usermodify", method = RequestMethod.GET)
	public String registerUpdateView(UsersVO vo,HttpSession session, Model model) throws Exception {
		
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		
		// users 蹂��닔瑜� 留뚮뱾�뼱�꽌 �븳爰쇰쾲�뿉 model濡� jsp�뿉 �몴�떆�븯�젮怨� 
		UsersVO users = usersService.getUsers(loginUser.getId());
		
		String birthday = users.getBirthday();
		String birth = birthday.substring(0, 6);
		String birth_gen = birthday.substring(6);
		
		//�씠硫붿씪 �씤利� 踰꾪듉怨� 愿��젴�맂 紐⑤뜽 異붽�
		model.addAttribute("users", users);
		
		//洹몃�濡� emailcheck�뿉 媛믪쓣 �꽆寃⑥＜湲� �쐞�빐
		model.addAttribute("id",loginUser.getId());
		model.addAttribute("email" ,loginUser.getEmail());
	    model.addAttribute("birth", birth);
	    model.addAttribute("birth_gen", birth_gen);

		return "mypage/userModify";
	}



	// update 援щЦ
	@RequestMapping(value = "/usersUpdate", method = RequestMethod.POST)
	public String userUpdate(UsersVO vo, SessionStatus status,HttpSession session,Model model) {

		usersService.updateUser(vo);
	
		//�꽭�뀡�쓣 吏��슫 �씠�쑀:�씠由꾩쓣 �닔�젙�븯怨� 諛붾줈 �씠由꾩씠 �븞�뼚�꽌 
		status.setComplete();
		return "Users/login";

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

		conditionMap.put("吏��젏�쓣 �꽑�깮�븯�꽭�슂", "0");
		conditionMap.put("罹좏븨議깆옣-媛뺤썝�룄吏��젏", "1");
		conditionMap.put("罹좏븨議깆옣-寃쎄린�룄吏��젏", "2");
		conditionMap.put("罹좏븨議깆옣-異⑹껌�룄吏��젏", "3");
		conditionMap.put("罹좏븨議깆옣-寃쎌긽�룄吏��젏", "4");
		conditionMap.put("罹좏븨議깆옣-�쟾�씪�룄吏��젏", "5");
		conditionMap.put("罹좏븨議깆옣-�젣二쇰룄吏��젏", "6");

		return conditionMap;
	}
}