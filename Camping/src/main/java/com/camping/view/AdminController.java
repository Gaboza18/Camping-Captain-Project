package com.camping.view;

import java.util.LinkedHashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.camping.biz.admin.AdminService;
import com.camping.biz.calculate.CalculateService;
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.QnaVO;
import com.camping.biz.dto.RealReviewVO;
import com.camping.biz.dto.UsersAge;
import com.camping.biz.dto.UsersRatio;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.qna.QnaService;
import com.camping.biz.realreview.RealReviewService;
import com.camping.biz.users.UsersService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("loginAdmin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private RealReviewService reviewsService;

	@Autowired
	private QnaService qnaService;

	@Autowired
	private UsersService userService;

	@Autowired
	private CalculateService calculateService;

	@GetMapping(value = "/admin_login_form")
	public String AdminLoginView() {
		return "admin/admin_login";
	}

	@PostMapping(value = "/admin_login")
	public String AdminLoginAction(AdminVO vo, Model model) {

		AdminVO loginAdmin = null;

		int result = adminService.loginID(vo);

		if (result == 1) {
			loginAdmin = adminService.getAdmin(vo.getId());
			model.addAttribute("loginAdmin", loginAdmin);

			return "admin/admin_index";
		} else {
			return "admin/login_fail";
		}
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
	 * 캠핑족장 회원 성별 화면 출력
	 */
	@RequestMapping(value = "/admin_users_gender_ratio")
	public String adminUsersChart() {
		return "admin/users/users_gender_ratio";
	}

	/*
	 * 차트를 위한 회원별 성별 조회(JSON 데이터 포멧 전송)
	 */
	@RequestMapping(value = "/users_gender_ratio_chart", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<UsersRatio> usersRatioChart() {

		List<UsersRatio> listRatio = userService.getGenderRatio();

		for (UsersRatio item : listRatio) {
			System.out.println(item);
		}
		return listRatio;
	}

	/*
	 * 캠핑족장 연령별 회원수 화면 출력
	 */
	@RequestMapping(value = "/admin_users_age_ratio")
	public String adminUsersAgeChart() {
		return "admin/users/users_age_ratio";
	}

	/*
	 * 차트를 위한 회원별 연령 조회(JSON 데이터 포멧 전송)
	 */
	@RequestMapping(value = "/users_age_ratio_chart", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<UsersAge> usersAgeChart() {

		List<UsersAge> listAge = userService.getAge();

		for (UsersAge item : listAge) {
			System.out.println(item);
		}
		return listAge;
	}

	/*
	 * 각 지점 일일 별 정산(총관리자)
	 */
	@RequestMapping(value = "/admin_master_calculate_day")
	public String masterCalculateDay(Model model) {

		List<CampOrderVO> calculateList = calculateService.calculateDay();

		model.addAttribute("calculateList", calculateList);

		return "admin/calculate/admin_all_point_calculate_day";
	}

	/*
	 * 각 지점 연도별 정산(지점 관리자): 각 지점 이름을 받아 지점 계정만 조회를 한다
	 */
	@RequestMapping(value = "/branch_calculate_year")
	public String managerGwCalculateYear(Model model, @RequestParam(value = "name") String name) {

		List<CampOrderVO> GwcalculateList = calculateService.branchCalculateYear(name);

		model.addAttribute("GwcalculateList", GwcalculateList);

		return "admin/calculate/manager_calculate_year";
	}

	/*
	 * 각 지점 월 별 정산(지점 관리자)
	 */
	@RequestMapping(value = "/branch_calculate_month")
	public String managerGwCalculateMonth(Model model, @RequestParam(value = "name") String name) {

		List<CampOrderVO> GwcalculateList = calculateService.branchCalculateMonth(name);

		model.addAttribute("GwcalculateList", GwcalculateList);

		return "admin/calculate/manager_calculate_month";
	}

	/*
	 * 각 지점 일일 별 정산(지점 관리자)
	 */
	@RequestMapping(value = "/branch_calculate_day")
	public String managerGwCalculateDay(Model model, @RequestParam(value = "name") String name) {

		List<CampOrderVO> GwcalculateList = calculateService.branchCalculateDay(name);

		model.addAttribute("GwcalculateList", GwcalculateList);

		return "admin/calculate/manager_calculate_day";
	}

	/*
	 * 각 지점 연도별 정산(총관리자) - 서브메뉴 -> 연도별 정산 조회 컨트롤러
	 */

	@RequestMapping(value = "/go_admin_master_calculate_year", method = RequestMethod.GET)
	public String masterCalculateYear() {
		return "admin/calculate/admin_all_point_calculate_year";
	}

	/*
	 * 각 지점 연도별 정산(총관리자) - 조회할 년도 '시작년도 ~ 끝나는 년도'
	 */

	@RequestMapping(value = "/admin_master_calculate_year", method = RequestMethod.GET)
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

		conditionMapYear.put("2019년", "2019");
		conditionMapYear.put("2020년", "2020");
		conditionMapYear.put("2021년", "2021");
		conditionMapYear.put("2022년", "2022");
		conditionMapYear.put("2023년", "2023");

		return conditionMapYear;
	}

	/*
	 * 각 지점 연도별 정산(총관리자) - 서브메뉴 -> 연도/월 별 정산 조회 컨트롤러
	 */
	@RequestMapping(value = "/go_admin_master_calculate_month", method = RequestMethod.GET)
	public String masterCalculateMonth() {
		return "admin/calculate/admin_all_point_calculate_month";
	}

	/*
	 * 각 지점 연도/월별 정산(총관리자) - 조회할 년도 '시작년도/시작하는 월 ~ 끝나는 년도/끝나는 월'
	 */
	@RequestMapping(value = "/admin_master_calculate_yearmonth", method = RequestMethod.GET)
	public String masterCalculateMonth(@RequestParam(value = "startYear") String startYear,
			@RequestParam(value = "startMonth") String startMonth, @RequestParam(value = "endYear") String endYear,
			@RequestParam(value = "endMonth") String endMonth, Model model) {

		String stYear = startYear; // 조회할 시작년도
		String stMonth = startMonth; // 조회할 시작월
		String edYear = endYear; // 조회할 끝나는 년도
		String edMonth = endMonth; // 조회할 끝나는 월

		String stYearMonth = startYear + startMonth; // 조회할 시작년도+월
		String edYearMonth = endYear + endMonth; // 조회할 끝나는년도+월

		List<CampOrderVO> calculateList = calculateService.searchCalculateYearMonth(stYearMonth, edYearMonth);

		model.addAttribute("calculateList", calculateList);
		model.addAttribute("stYearMonth", stYearMonth);
		model.addAttribute("edYearMonth", edYearMonth);

		model.addAttribute("stYear", stYear);
		model.addAttribute("stMonth", stMonth);
		model.addAttribute("edYear", edYear);
		model.addAttribute("edMonth", edMonth);

		return "admin/calculate/admin_all_point_calculate_month";
	}

	/*
	 * 각 지점 연도/월 별 정산(총관리자) - 월 검색 선택박스 항목
	 */
	@ModelAttribute("conditionMapMonth")
	public Map<String, String> searchConditionMapYearMonth() {
		Map<String, String> conditionMapMonth = new LinkedHashMap<>();

		conditionMapMonth.put("01월", "01");
		conditionMapMonth.put("02월", "02");
		conditionMapMonth.put("03월", "03");
		conditionMapMonth.put("04월", "04");
		conditionMapMonth.put("05월", "05");
		conditionMapMonth.put("06월", "06");
		conditionMapMonth.put("07월", "07");
		conditionMapMonth.put("08월", "08");
		conditionMapMonth.put("09월", "09");
		conditionMapMonth.put("10월", "10");
		conditionMapMonth.put("11월", "11");
		conditionMapMonth.put("12월", "12");

		return conditionMapMonth;
	}

	/*
	 * 관리자 - 리얼후기 관리 페이지로 이동
	 */
	@RequestMapping(value = "/adminReview", method = RequestMethod.GET)
	public String reviewList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			RealReviewVO vo, HttpSession session, Model model) {

		// realreviewVo에 디테일 볼 수 있는 로직을 썼는데, 세션에 저장이 안되었을가봐 list볼때에도 if 문으로 login문 구현
		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");

		if (loginAdmin == null) {
			return "admin/admin_login";

		} else {
			vo.setRseq(vo.getRseq());

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 리얼후기 목록 갯수 설정 및 페이지 정보 초기화
			
			model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("admin", loginAdmin.getName());

			return "admin/managerealreview";
		}
	}

	/*
	 * 관리자 - 리얼후기 상세보기 페이지로 이동
	 */
	@RequestMapping(value = "manage_review_detail", method = RequestMethod.GET)
	public String reviewDetail(HttpSession session, RealReviewVO vo, Model model, int rseq) {

		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");

		if (loginAdmin == null) {
			return "admin/admin_login";
		} else {
			// admin 로그인시 리뷰상세보기
			reviewsService.updateViewCount(vo.getRseq()); // 조회수 증가
			
			String loginadmin = loginAdmin.getId();
			RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
			
			model.addAttribute("RealReviewVO", reviewsDetail);
			model.addAttribute("loginAdmin1", loginadmin);
			model.addAttribute("admin", loginAdmin.getName());
			
			return "admin/manageReviewDetail";
		}
	}

	// 지점별 리뷰 필터링(지점별 선택시 필터링됨)
	@RequestMapping(value = "/arealist", method = RequestMethod.GET)
	@ResponseBody
	public List<RealReviewVO> areaList(@RequestParam(value = "campingname") String campingname, RealReviewVO vo,
			Model model) {

		System.out.println("area=" + campingname);
		vo.setCampingname(campingname + "지점");

		List<RealReviewVO> reviewlist = reviewsService.areaList(vo);

		for (RealReviewVO review : reviewlist) {
			System.out.println(review);
		}
		return reviewlist;
	}

	/*
	 * 회원관리 페이지로 이동
	 */
	@RequestMapping(value = "/users_list", method = RequestMethod.GET)
	public String usersList(@RequestParam(value = "key", defaultValue = "") String id, Criteria criteria, Model model) {
		
		List<UsersVO> usersList = adminService.getUsersListWithPaging(criteria, id);

		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = adminService.countUserslist(id);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 회원관리 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("usersList", usersList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
		model.addAttribute("usersListSize", usersList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "admin/usersblacklist/usersblacklist";

	}

	/*
	 * 회원 전체 조회(일반회원, 블랙리스트 회원)
	 */
	@RequestMapping(value = "/users_status_list", method = RequestMethod.GET)
	public String usersList(@RequestParam(value = "key", defaultValue = "") String id,
			@RequestParam(value = "searchUserlist", defaultValue = "") String searchUserlist, Criteria criteria,
			Model model) {
		UsersVO user = new UsersVO();

		System.out.println(searchUserlist);

		user.setId(id);
		user.setBlacklist(searchUserlist);

		List<UsersVO> usersList = adminService.statusUserlistWithPaging(criteria, id, searchUserlist);

		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = adminService.statusUserlist(user);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 회원관리 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("usersList", usersList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
		model.addAttribute("usersListSize", usersList.size());
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("searchUserlist", searchUserlist);

		return "admin/usersblacklist/usersblacklist";

	}

	/*
	 * 블랙리스트 등록창 열기
	 */
	@RequestMapping(value = "insertblacklist", method = RequestMethod.GET)
	public String insertblacklist(UsersVO vo, Model model) {

		UsersVO user = userService.getUsers(vo.getId());

		String blackid = user.getId();
		int blackuseq = user.getUseq();

		System.out.println(blackid);
		System.out.println(blackuseq);

		model.addAttribute("blackid", blackid);
		model.addAttribute("blackuseq", blackuseq);

		return "admin/usersblacklist/inputblacklist";
	}

	/*
	 * 회원 테이블 블랙리스트 회원 등록
	 */
	@RequestMapping(value = "changestatus", method = RequestMethod.GET)
	public String statusChange(UsersVO vo, Criteria criteria, Model model, @RequestParam(value = "useq") int useq,
			HttpSession session) {

		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");

		if (loginAdmin == null) {
			return "admin/admin_login";
		} else {
			UsersVO users = new UsersVO();
			users.setUseq(useq);
			users.setBlackreason(vo.getBlackreason());

			adminService.statusChange(users);

			return "admin/usersblacklist/usersblacklist";
		}
	}

	/*
	 * 블랙리스트 취소
	 */
	@RequestMapping(value = "/cancelBlack", method = RequestMethod.GET)
	public String cancelBlack(@RequestParam(value = "key", defaultValue = "") String id, UsersVO vo, Criteria criteria,
			Model model, @RequestParam(value = "useq") int useq, HttpSession session) {

		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");

		if (loginAdmin == null) {
			return "admin/admin_login";
		} else {

			adminService.statusCancelBlack(useq);

			UsersVO user = new UsersVO();
			String searchUserlist = "y";
			System.out.println(searchUserlist);

			user.setId(id);
			user.setBlacklist(searchUserlist);

			List<UsersVO> usersList = adminService.statusUserlistWithPaging(criteria, id, searchUserlist);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = adminService.statusUserlist(user);

			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(totalCount);

			model.addAttribute("usersList", usersList);
			model.addAttribute("usersListSize", usersList.size());
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("searchUserlist", searchUserlist);

			return "admin/usersblacklist/usersblacklist";
		}
	}
}
