package com.camping.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camping.biz.admin.AdminService;
import com.camping.biz.calculate.CalculateService;
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.QnaVO;
import com.camping.biz.dto.UsersAge;
import com.camping.biz.dto.UsersRatio;
import com.camping.biz.qna.QnaService;
import com.camping.biz.users.UsersService;


@Controller
@SessionAttributes("loginAdmin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private CalculateService calculateService;
	
	
	@GetMapping(value="/admin_login_form")
	public String AdminLoginView() {
		return "admin/admin_login";
	}
	
	@PostMapping(value="/admin_login")
	public String AdminLoginAction(AdminVO vo, Model model) {
		
		AdminVO loginAdmin = null;
		
		int result = adminService.loginID(vo);
		
		if(result == 1) {
			loginAdmin = adminService.getAdmin(vo.getId());
			model.addAttribute("loginAdmin", loginAdmin);
			
			return "admin/admin_index";
		} else {
			return "admin/login_fail";
		}
	}
	
	/*
	 * �Խ��� ����(QnA �����ȸ ó��)
	 */
	@RequestMapping(value = "/admin_qna_list")
	public String adminQnaList(Model model) {

		// QnA ����� ���̺��� ��ȸ
		List<QnaVO> qnaList = qnaService.listAllQna();

		// ��ȸ ����� model ��ü�� ����
		model.addAttribute("qnaList", qnaList);

		// QnA ȭ�� ȣ��
		return "admin/qna/qnaList";
	}
	
	/*
	 * QnA �Խñ� �󼼺���(�Ѱ�����)
	 */
	@PostMapping(value = "/admin_qna_detail")
	public String adminQnaDetail(QnaVO vo, Model model) {

		// �Խñ� �Ϸù�ȣ�� �������� �Խñ� �� ��ȸ
		QnaVO qna = qnaService.getQna(vo.getQseq());

		// ��ȸ ����� model ��ü�� ����
		model.addAttribute("qnaVO", qna);

		// �Խñ� ��ȭ�� ȣ��
		return "admin/qna/qnaDetail";
	}
	
	/*
	 * QnA ������ �亯 ��û ó��
	 */
	@PostMapping(value = "/admin_qna_repsave")
	public String adminQnaRepSave(QnaVO vo) {

		// QnA ������ Update ȣ��
		qnaService.updateQna(vo);

		// QnA �Խñ� ��� ȣ��
		return "redirect:admin_qna_list";
	}
	
	/*
	 *  ķ������ ȸ�� ���� ȭ�� ���
	 */
	@RequestMapping(value="/admin_users_gender_ratio")
	public String adminUsersChart() {
		return "admin/users/users_gender_ratio";
	}
	
	/*
	 *  ��Ʈ�� ���� ȸ���� ���� ��ȸ(JSON ������ ���� ����)
	 */
	@RequestMapping(value="/users_gender_ratio_chart",
			produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<UsersRatio> usersRatioChart(){
		
		List<UsersRatio> listRatio = userService.getGenderRatio();
		
		for(UsersRatio item:listRatio) {
			System.out.println(item);
		}
		return listRatio;
	}
	
	/*
	 *  ķ������ ���ɺ� ȸ���� ȭ�� ���
	 */
	@RequestMapping(value="/admin_users_age_ratio")
	public String adminUsersAgeChart() {
		return "admin/users/users_age_ratio";
	}
	
	/*
	 *  ��Ʈ�� ���� ȸ���� ���� ��ȸ(JSON ������ ���� ����)
	 */
	@RequestMapping(value="/users_age_ratio_chart",
			produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<UsersAge> usersAgeChart(){
		
		List<UsersAge> listAge = userService.getAge();
		
		for(UsersAge item:listAge) {
			System.out.println(item);
		}
		return listAge;
	}
	
	/*
	 * �� ���� ������ ����(�Ѱ�����)
	 */
	@RequestMapping(value="/admin_master_calculate_year")
	public String masterCalculateYear(Model model) {
		
		List<CampOrderVO> calculateList = calculateService.calculateYear();
		
		model.addAttribute("calculateList", calculateList);
		
		return "admin/calculate/admin_all_point_calculate_year";
	}
	
	/*
	 *  �� ���� �� �� ����(�Ѱ�����)
	 */
	@RequestMapping(value="/admin_master_calculate_month")
	public String masterCalculateMonth(Model model) {
		
		List<CampOrderVO> calculateList = calculateService.calculateMonth();
		
		model.addAttribute("calculateList",calculateList);
		
		return "admin/calculate/admin_all_point_calculate_month";
	}
	
	/*
	 *  �� ���� ������ ����(���� ������): �� ���� �̸��� �޾� ���� ������ ��ȸ�� �Ѵ�
	 */
	@RequestMapping(value="/branch_calculate_year")
	public String managerGwCalculateYear(Model model, @RequestParam (value="name") String name) {
		
		List<CampOrderVO> GwcalculateList = calculateService.branchCalculateYear(name);
		
		model.addAttribute("GwcalculateList",GwcalculateList);
		
		return "admin/calculate/manager_gangwondo_calculate_year";
	}
	
	/*
	 *  �� ���� �� �� ����(���� ������)
	 */
	@RequestMapping(value="/branch_calculate_month")
	public String managerGwCalculateMonth(Model model, @RequestParam (value="name") String name) {
		
		List<CampOrderVO> GwcalculateList = calculateService.branchCalculateMonth(name);
		
		model.addAttribute("GwcalculateList",GwcalculateList);
		
		return "admin/calculate/manager_gangwondo_calculate_month";
	}
	
}
