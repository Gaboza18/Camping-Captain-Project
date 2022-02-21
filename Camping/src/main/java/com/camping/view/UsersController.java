package com.camping.view;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;


import utils.PageMaker;

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
	
	
	@GetMapping(value="/login")
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
	
	
//	// ȸ�� Ż�� get
		@RequestMapping(value="/deleteIdView", method = RequestMethod.GET)
		public String usersDeleteView() throws Exception{
			return "Users/deleteIdView";
			
		
		}
		
	
		
		// ȸ�� Ż�� post
		@RequestMapping(value="/usersDelete", method = RequestMethod.POST)
		public String usersDelete(String id, String password, RedirectAttributes rttr, HttpSession session, Model model ,UsersVO vo) throws Exception{
			usersService.deleteId(vo);
			model.addAttribute("password", vo.getPassword());
			model.addAttribute("id", vo.getId());
			session.invalidate();
			rttr.addFlashAttribute("msg", "�̿����ּż� �����մϴ�");
			return "redirect:/index";
			
		}
		
		
		
		
		
		//**********************mypage �̵� 

		//��������Ʈ : https://melonpeach.tistory.com/42
		
		@GetMapping(value="/mypage")
		public String mypageView(UsersVO vo, HttpSession session) {
			
			UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
			if(loginUser ==null) {
				return "Users/login";
			}else {
				return "mypage/mypage";
			}
		}
		
	
		@RequestMapping(value="/usermodify", method = RequestMethod.GET)
		public String registerUpdateView() throws Exception {
			
			return "mypage/userModify";
		}
		

		@RequestMapping(value="/usersUpdate", method = RequestMethod.POST)
		public String userUpdate(UsersVO vo, HttpSession session)  {
			
			session.invalidate();
			usersService.updateUser(vo);
			
						
			return "redirect:/";
			
		}
		
//		// �̸��� ����
//		@RestController
//		public class MemberRController {
//		    @Autowired
//		    private MemberService memberService;
//		    @Autowired
//		    private MailSendService mss;
//
//
//		    @RequestMapping("/member/signUp")
//		     public void signUp(@ModelAttribute UsersVO vo){
//		        // DB�� �⺻���� insert
//		        usersService.signUp(vo);
//
//		        //������ authKey ���� & �̸��� �߼�
//		        String authKey = mss.sendAuthMail(vo.getEmail());
//		        vo.setAuthKey(authKey);
//
//		        Map<String, String> map = new HashMap<String, String>();
//		        map.put("email", vo.getEmail());
//		        map.put("authKey", vo.getAuthKey());
//		        System.out.println(map);
//
//		      //DB�� authKey ������Ʈ
//		      usersService.updateAuthKey(map);
//
//		  	}
//		}
//		
		
	
	

		
		
		
		
		
		 /*
		  @RequestMapping(value="/pwCheck" , method=RequestMethod.POST)
			@ResponseBody
			public int pwCheck(MemberVO memberVO) throws Exception{
				String memberPw = memberService.pwCheck(memberVO.getMemberId());
				
				if(memberVO == null || !BCrypt.checkpw(memberVO.getMemberPw(), memberPw)) {
					return 0;
				}
				
				return 1;
			}

		  }
		  
		*/  
		
		  /*
		   *  @RequestMapping(value="/pwCheck" , method=RequestMethod.POST)
	@ResponseBody
	public int pwCheck(MemberVO memberVO) throws Exception{
		String memberPw = memberService.pwCheck(memberVO.getMemberId());
		
		if(memberVO == null || !BCrypt.checkpw(memberVO.getMemberPw(), memberPw)) {
			return 0;
		}
		
		return 1;
	}
		   * 
		   */
			
		
		/*
		 * // ȸ�� Ż�� get
	@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "member/memberDeleteView";
	}
	
	// ȸ�� Ż�� post
	@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		// ���ǿ� �ִ� member�� ������ member������ �־��ݴϴ�.
		MemberVO member = (MemberVO) session.getAttribute("member");
		// ���ǿ��ִ� ��й�ȣ
		String sessionPass = member.getUserPass();
		// vo�� ������ ��й�ȣ
		String voPass = vo.getUserPass();
		
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/memberDeleteView";
		}
		service.memberDelete(vo);
		session.invalidate();
		 */
	
	
	
}
