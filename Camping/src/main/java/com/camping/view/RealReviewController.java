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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.RealReviewVO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.realreview.RealReviewService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("loginUser")

public class RealReviewController {

	@Autowired
	private RealReviewService reviewsService;

	/*
	 * 전체 리얼 후기 리스트 조회 페이지로 이동 (회원, 비회원)
	 */
	@RequestMapping(value = "/review_list", method = RequestMethod.GET)
	public String reviewList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = reviewsService.countReviewlist(title);

		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(totalCount);

		model.addAttribute("reviewList", reviewList);
		model.addAttribute("reviewListSize", reviewList.size());
		model.addAttribute("pageMaker", pageMaker);
		
		return "realreview/reviewList";
	}

	/*
	 * 전체 리얼 후기 리스트 상세보기 (회원)
	 */
	@RequestMapping(value = "/review_detail", method = RequestMethod.GET)
	public String reviewDetail(HttpSession session, RealReviewVO vo, Model model, int rseq) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "Users/login";
		}  else {

			reviewsService.updateViewCount(vo.getRseq()); // 조회수 증가

			String userid = loginUser.getId();

			RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
			
			model.addAttribute("RealReviewVO", reviewsDetail);
			model.addAttribute("userid", userid);
			
			return "realreview/reviewDetail";

		}
	}

	/*
	 * 리얼리뷰 작성페이지로 이동(회원)
	 */
	@GetMapping(value = "/review_write")
	public String reviewWrite(RealReviewVO vo, HttpSession session) {
		
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";
		} else {
			return "realreview/reviewWrite";
		}
	}
	
	/*
	 * 리얼리뷰 작성(회원)
	 */
	@PostMapping(value = "/insertReview")
	public String reviewwWrite(RealReviewVO vo, HttpSession session) {
		
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";

		} else {
			
			vo.setId(loginUser.getId());
			reviewsService.insertReview(vo);
			
			return "redirect:review_list";
		}
	}

	/*
	 * 마이페이지 나의 후기 리스트 페이지로 이동(회원)
	 */
	@RequestMapping(value = "/myreview", method = RequestMethod.GET)
	public String seemyreview(RealReviewVO vo, HttpSession session, Model model, Criteria criteria, String id,
			@RequestParam(value = "key", defaultValue = "") String title) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {

			// vo.setId(loginUser.getId());

			// reviewsService.seemyreview(vo);
			RealReviewVO rVo = new RealReviewVO();
			rVo.setId(loginUser.getId());
			rVo.setTitle(title);
			

			List<RealReviewVO> myreviewList = reviewsService.getListWithPaging2(criteria, loginUser.getId(), title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist2(rVo);

			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(totalCount);

			model.addAttribute("myreviewListSize", myreviewList.size());
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("myreviewList", myreviewList);

			return "mypage/myreviewList";
		}
	}

	/*
	 * 마이페이지 나의 후기 작성페이지로 이동(회원)
	 */
	@GetMapping(value = "/myreviewWrite")
	public String myreviewWriteVIew(RealReviewVO vo, HttpSession session) {
		
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";
		} else {
			return "mypage/myreviewWrite";
		}
	}

	/*
	 * 마이페이지 나의 후기 작성(회원)
	 */
	@PostMapping(value = "/insertMyReview")
	public String myreviewWriteAction(RealReviewVO vo, HttpSession session, Model model, Criteria criteria, @RequestParam(value = "key", defaultValue = "") String title) {
		
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";

		} else {
			vo.setId(loginUser.getId());
			reviewsService.insertReview(vo);
			
			RealReviewVO rVo = new RealReviewVO();
			rVo.setId(loginUser.getId());
			rVo.setTitle(title);
			

			List<RealReviewVO> myreviewList = reviewsService.getListWithPaging2(criteria, loginUser.getId(), title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist2(rVo);

			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(totalCount);

			model.addAttribute("myreviewListSize", myreviewList.size());
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("myreviewList", myreviewList);

			return "mypage/myreviewList";
		}
	}

	/*
	 * 리뷰 삭제(회원 -> 본인이 작성한 리뷰만 삭제가능 / 지점관리자 -> 본인지점 리뷰만 삭제가능 / 총관리자 -> 전체리뷰 삭제가능)
	 */
	@RequestMapping(value = "/review_list_re", method = RequestMethod.GET)
	public String deletereviews(@RequestParam(value = "rseq") int rseq, HttpSession session, Model model,
			Criteria criteria, String title) throws Exception {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");
		
		if (loginUser == null && loginAdmin == null) {
			return "Users/login";

			// admin이 리얼리뷰로 잘못 접속시 삭제 가능함
		} else if (loginAdmin != null) {

			// 삭제 버튼 클릭시 삭제되는 부분
			reviewsService.deletereviews(rseq);
			
			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(totalCount);

			model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "admin/managerealreview";
			
		} else {
			reviewsService.deletereviews(rseq);

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(totalCount);

			model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "realreview/reviewList";
		}

	}
	
	/*
	 * 리얼리뷰 수정페이지로 이동(회원)
	 */
	@GetMapping(value = "/modi")
	public String modifyreview(RealReviewVO vo, HttpSession session, Model model) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";
		} else {

			String userid = loginUser.getId();
			RealReviewVO review = reviewsService.detailReviews(vo.getRseq());

			model.addAttribute("RealReviewVO", review);
			model.addAttribute("userid", userid);

			return "realreview/modifyreview";
		}
	}

	/*
	 * 리얼리뷰 수정(회원 - 본인이 작성한 리뷰만 수정 가능)
	 */
	@RequestMapping(value = "/modifyReview", method = RequestMethod.GET)
	public String updatereviews(RealReviewVO vo, HttpSession session, Criteria criteria, String title, Model model) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";
		} else {

			reviewsService.modifyreviews(vo);

			title = "";

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(totalCount);

			model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "realreview/reviewList";

		}

	}

	/*
	 * 나의 후기 상세보기 페이지로 이동(회원)
	 */
	@RequestMapping(value = "myreview_detail", method = RequestMethod.GET)
	public String myreviewDetail(HttpSession session, RealReviewVO vo, Model model, int rseq) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "Users/login";
		} else {

			reviewsService.updateViewCount(vo.getRseq()); // 조회수 증가

			String userid = loginUser.getId();
			RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
			model.addAttribute("RealReviewVO", reviewsDetail);
			model.addAttribute("userid", userid);
			
			return "mypage/myreviewDetail";

		}
	}
	
	/*
	 * 나의 후기 수정페이지로 이동(회원)
	 */
	@GetMapping(value = "/modiMyReview")
	public String modifymyreview(RealReviewVO vo, HttpSession session, Model model) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";
		} else {

			String userid = loginUser.getId();
			RealReviewVO review = reviewsService.detailReviews(vo.getRseq());

			model.addAttribute("RealReviewVO", review);
			model.addAttribute("userid", userid);

			return "mypage/modifymyreview";
		}
	}

	/*
	 * 나의 후기 수정(회원 - 본인이 작성한 리뷰만 수정 가능)
	 */
	@RequestMapping(value = "/modifyMyReview", method = RequestMethod.GET)
	public String updatemyreviews(RealReviewVO vo, HttpSession session, Criteria criteria, String title, Model model) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";
		} else {

			reviewsService.modifyreviews(vo);

			title = "";

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(totalCount);

			model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "redirect: myreview";

		}

	}
	
	@RequestMapping(value = "/delete_myreview", method = RequestMethod.GET)
	public String deleteMyreviews(@RequestParam(value = "rseq") int rseq, HttpSession session, Model model,
			Criteria criteria, String title) throws Exception {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "Users/login";
		} else {
			reviewsService.deletereviews(rseq);

			RealReviewVO rVo = new RealReviewVO();
			rVo.setId(loginUser.getId());
			rVo.setTitle(title);
			

			List<RealReviewVO> myreviewList = reviewsService.getListWithPaging2(criteria, loginUser.getId(), title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist2(rVo);

			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(totalCount);

			model.addAttribute("myreviewListSize", myreviewList.size());
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("myreviewList", myreviewList);

			return "mypage/myreviewList";
		}

	}
	
	@ModelAttribute("conditionBranchMap")
	public Map<String, String> searchConditionBranchMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("강원도지점", "강원도지점");
		conditionMap.put("경기도지점", "경기도지점");
		conditionMap.put("충청도지점", "충청도지점");
		conditionMap.put("경상도지점", "경상도지점");
		conditionMap.put("전라도지점", "전라도지점");
		conditionMap.put("제주도지점", "제주도지점");

		return conditionMap;
	}

}
