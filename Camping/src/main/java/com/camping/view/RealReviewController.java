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

import com.camping.biz.admin.AdminService;
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
	

	

	@RequestMapping(value = "/review_list", method = RequestMethod.GET)
	
	public String reviewList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {
		
	
		
		// 공지사항 목록 조회 - 공지사항 10개만 조회

		List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);
		
		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = reviewsService.countReviewlist(title);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
		model.addAttribute("reviewListSize", reviewList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "realreview/reviewList";
			}
	
	



	@RequestMapping(value = "review_detail", method = RequestMethod.GET)
	
	public String reviewDetail(HttpSession session, RealReviewVO vo, Model model, int rseq) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");

		if (loginUser == null && loginAdmin==null) {
			return "Users/login";
			
			//admin이 리얼리뷰로 잘못 접속시 삭제 가능함
		}else if(loginAdmin != null) {
			
			//admin 로그인시 리뷰상세보기
			reviewsService.updateViewCount(vo.getRseq()); // 조회수 증가
			String loginadmin = loginAdmin.getId();
			RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
			model.addAttribute("RealReviewVO", reviewsDetail);
			model.addAttribute("loginAdmin1", loginadmin);
			return "realreview/reviewDetail";
		}else {
					
			reviewsService.updateViewCount(vo.getRseq()); // 조회수 증가
			
			String userid = loginUser.getId();
			
			
			//else 안에 model객체가 생성되서 admin으로 로그인 하면 detail이 안뜨는건가 
			//싶어서 만들어봄
			//String loginadmin = loginAdmin.getId();
			
			RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
			model.addAttribute("RealReviewVO", reviewsDetail);
			model.addAttribute("userid", userid);
			//model.addAttribute("loginAdmin1", loginadmin);
			return "realreview/reviewDetail";

		}
	}


	@GetMapping(value = "/review_write")
	public String reviewWrite(RealReviewVO vo, HttpSession session) {
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {
			return "realreview/reviewWrite";
		}
	}

	@PostMapping(value = "insertReview")
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
	
	

	@RequestMapping(value = "/myreview", method = RequestMethod.GET)
	public String seemyreview (RealReviewVO vo, HttpSession session, Model model, Criteria criteria,String id) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {
			
			vo.setId(loginUser.getId());
		
			reviewsService.seemyreview(vo);
		
			
			List<RealReviewVO> myreviewList = reviewsService.getListWithPaging2(criteria, vo.getId());
			
			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist2(id);

			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

			

			model.addAttribute("myreviewListSize", myreviewList.size());
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("myreviewList", myreviewList);
			

			return "mypage/myreviewList";
		}
	}
	
	@GetMapping(value = "/myreviewWrite")
	public String myreviewWriteVIew(RealReviewVO vo, HttpSession session) {
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {
			return "mypage/myreviewWrite";
		}
	}

	@PostMapping(value = "/insertMyReview")
	public String myreviewWriteAction(RealReviewVO vo, HttpSession session,Model model, Criteria criteria) {
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";

		} else {
			vo.setId(loginUser.getId());
			reviewsService.insertReview(vo);
			List<RealReviewVO> myreviewList = reviewsService.getListWithPaging2(criteria, vo.getId());
			
			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist2(loginUser.getId());

			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

			

			model.addAttribute("myreviewListSize", myreviewList.size());
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("myreviewList", myreviewList);
			
			return "mypage/myreviewList";
		}
	}

	//리뷰삭제(리뷰삭제시 원래 생성했던 reviewsServcice.detail)사용시 login session을 이용하여 정보를
	//저장하여 사용할 수 있음
	@RequestMapping(value="/review_list_re", method = RequestMethod.GET)
	@ResponseBody
	public String deletereviews(@RequestParam(value="rseq") int rseq, HttpSession session, Model model,Criteria criteria, String title) throws Exception {
		

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");
		if (loginUser == null && loginAdmin==null) {
			return "Users/login";
			
			//admin이 리얼리뷰로 잘못 접속시 삭제 가능함
		}else if(loginAdmin != null) {
			
			//model.addAllAttributes(reviewsService.listReview(vo));
			
			//삭제 버튼 클릭시 삭제되는 부분
			reviewsService.deletereviews(rseq);
			// 공지사항 목록 조회 - 공지사항 10개만 조회

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

			model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);
			
			return "admin/managerealreview";
		}else {
			reviewsService.deletereviews(rseq);
			// 공지사항 목록 조회 - 공지사항 10개만 조회

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

			model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);
			
			return "realreview/reviewList";
		}
		
	}
	
	
	@GetMapping(value = "/modi")
	public String modifyreview(int rseq,RealReviewVO vo,  HttpSession session,Model model) {
		
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {

			
			String userid = loginUser.getId();
			RealReviewVO review = reviewsService.detailReviews(vo.getRseq());
			
			model.addAttribute("RealReviewVO",review);
			model.addAttribute("userid", userid);
			

			return "realreview/modifyreview";
		}
	}
	
		@RequestMapping(value = "/modifyReview", method = RequestMethod.GET)
		public String updatereviews (int rseq,RealReviewVO vo, HttpSession session, Criteria criteria, String title, 
		Model model ) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
			if (loginUser == null) {
				return "Users/login";
		} else {
			
			reviewsService.modifyreviews(vo);
			
			title ="";
			
			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);
			
			// 화면에 표시할 페이지 버튼 정보 설정
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

			model.addAttribute("reviewList", reviewList); // 변수, 값 순서임 왼쪽 변수는 reviewList에서 <for:each>의 변수와 동일함
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "realreview/reviewList";
			

		
		}

		}
		
		@RequestMapping(value = "myreview_detail", method = RequestMethod.GET)
		
		public String myreviewDetail(HttpSession session, RealReviewVO vo, Model model, int rseq) {

			UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
			AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");

			if (loginUser == null && loginAdmin==null) {
				return "Users/login";
				
				//admin이 리얼리뷰로 잘못 접속시 삭제 가능함
			}else {
						
				reviewsService.updateViewCount(vo.getRseq()); // 조회수 증가
				
				String userid = loginUser.getId();
				
				
				//else 안에 model객체가 생성되서 admin으로 로그인 하면 detail이 안뜨는건가 
				//싶어서 만들어봄
				//String loginadmin = loginAdmin.getId();
				
				RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
				model.addAttribute("RealReviewVO", reviewsDetail);
				model.addAttribute("userid", userid);
				//model.addAttribute("loginAdmin1", loginadmin);
				return "mypage/myreviewDetail";

			}
		}
		
		
		
		
		
}
