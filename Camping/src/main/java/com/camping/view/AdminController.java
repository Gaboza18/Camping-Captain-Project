package com.camping.view;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.camping.biz.admin.AdminService;
import com.camping.biz.calculate.CalculateService;
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.QnaVO;
import com.camping.biz.dto.UsersAge;
import com.camping.biz.dto.UsersRatio;
import com.camping.biz.qna.QnaService;
import com.camping.biz.users.UsersService;

import utils.Criteria;


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
	 * 관리자 로그아웃
	 */
	@GetMapping(value = "/admin_logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "admin/admin_login";
	}
	
	/*
	 * 게시판 관리(QnA 목록조회 처리)
	 */
	@RequestMapping(value = "/admin_qna_list")
	public String adminQnaList(Model model) {

		// QnA 목록을 테이블에서 조회
		List<QnaVO> qnaList = qnaService.listAllQna();

		// 조회 결과를 model 객체에 저장
		model.addAttribute("qnaList", qnaList);

		// QnA 화면 호출
		return "admin/qna/qnaList";
	}
	
	/*
	 * QnA 게시글 상세보기(총관리자)
	 */
	@PostMapping(value = "/admin_qna_detail")
	public String adminQnaDetail(QnaVO vo, Model model) {

		// 게시글 일련번호를 조건으로 게시글 상세 조회
		QnaVO qna = qnaService.getQna(vo.getQseq());

		// 조회 결과를 model 객체에 저장
		model.addAttribute("qnaVO", qna);

		// 게시글 상세화면 호출
		return "admin/qna/qnaDetail";
	}
	
	/*
	 * QnA 관리자 답변 요청 처리
	 */
	@PostMapping(value = "/admin_qna_repsave")
	public String adminQnaRepSave(QnaVO vo) {

		// QnA 서비스의 Update 호출
		qnaService.updateQna(vo);

		// QnA 게시글 목록 호출
		return "redirect:admin_qna_list";
	}
	
	/*
	 *  캠핑족장 회원 성별 화면 출력
	 */
	@RequestMapping(value="/admin_users_gender_ratio")
	public String adminUsersChart() {
		return "admin/users/users_gender_ratio";
	}
	
	/*
	 *  차트를 위한 회원별 성별 조회(JSON 데이터 포멧 전송)
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
	 *  캠핑족장 연령별 회원수 화면 출력
	 */
	@RequestMapping(value="/admin_users_age_ratio")
	public String adminUsersAgeChart() {
		return "admin/users/users_age_ratio";
	}
	
	/*
	 *  차트를 위한 회원별 연령 조회(JSON 데이터 포멧 전송)
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
	 *  각 지점 월 별 정산(총관리자)
	 */
	@RequestMapping(value="/admin_master_calculate_month")
	public String masterCalculateMonth(Model model) {
		
		List<CampOrderVO> calculateList = calculateService.calculateMonth();
		
		model.addAttribute("calculateList",calculateList);
		
		return "admin/calculate/admin_all_point_calculate_month";
	}
	
	/*
	 *  각 지점 월 별 정산(총관리자)
	 */
	@RequestMapping(value="/admin_master_calculate_day")
	public String masterCalculateDay(Model model) {
		
		List<CampOrderVO> calculateList = calculateService.calculateDay();
		
		model.addAttribute("calculateList",calculateList);
		
		return "admin/calculate/admin_all_point_calculate_day";
	}
	
	/*
	 *  각 지점 연도별 정산(지점 관리자): 각 지점 이름을 받아 지점 계정만 조회를 한다
	 */
	@RequestMapping(value="/branch_calculate_year")
	public String managerGwCalculateYear(Model model, @RequestParam (value="name") String name) {
		
		List<CampOrderVO> GwcalculateList = calculateService.branchCalculateYear(name);
		
		model.addAttribute("GwcalculateList",GwcalculateList);
		
		return "admin/calculate/manager_calculate_year";
	}
	
	/*
	 *  각 지점 월 별 정산(지점 관리자)
	 */
	@RequestMapping(value="/branch_calculate_month")
	public String managerGwCalculateMonth(Model model, @RequestParam (value="name") String name) {
		
		List<CampOrderVO> GwcalculateList = calculateService.branchCalculateMonth(name);
		
		model.addAttribute("GwcalculateList",GwcalculateList);
		
		return "admin/calculate/manager_calculate_month";
	}
	
	/*
	 *  각 지점 일일 별 정산(지점 관리자)
	 */
	@RequestMapping(value="/branch_calculate_day")
	public String managerGwCalculateDay(Model model, @RequestParam (value="name") String name) {
		
		List<CampOrderVO> GwcalculateList = calculateService.branchCalculateDay(name);
		
		model.addAttribute("GwcalculateList",GwcalculateList);
		
		return "admin/calculate/manager_calculate_day";
	}
	
	/*
	 * 각 지점 연도별 정산(총관리자) - 서브메뉴 -> 연도별 정산 조회 컨트롤러 
	 */
	
	@RequestMapping(value="/go_admin_master_calculate_year", method=RequestMethod.GET)
	public String masterCalculateYear() {
		return "admin/calculate/admin_all_point_calculate_year";
	}
	
	
	/*
	 * 각 지점 연도별 정산(총관리자) - 조회할 년도 '시작년도 ~ 끝나는 년도'
	 */
	
	@RequestMapping(value="/admin_master_calculate_year", method=RequestMethod.GET)
	public String masterCalculateYear(@RequestParam(value = "startYear") String startYear,
									@RequestParam(value = "endYear") String endYear, Model model) {
		
		String stYear = startYear;
		String edYear = endYear;
		
		List<CampOrderVO> calculateList = calculateService.searchCalculateYear(startYear, endYear);
		
		model.addAttribute("calculateList", calculateList);
		model.addAttribute("stYear", stYear);
		model.addAttribute("edYear", edYear);
		
		return "admin/calculate/admin_all_point_calculate_year";
	}
	
	/*
	 * 각 지점 연도별 정산(총관리자) - 년도 검색 선택박스 항목
	 */
	@ModelAttribute("conditionMapYear")
	public Map<String, String> searchConditionMapYear() {
		Map<String, String> conditionMapYear = new LinkedHashMap<>();

		conditionMapYear.put("2019년", "2019년");
		conditionMapYear.put("2020년", "2020년");
		conditionMapYear.put("2021년", "2021년");
		conditionMapYear.put("2022년", "2022년");
		conditionMapYear.put("2023년", "2023년");

		return conditionMapYear;
	}
	
}
