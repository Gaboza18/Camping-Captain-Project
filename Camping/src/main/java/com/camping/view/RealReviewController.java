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
import org.springframework.web.bind.annotation.SessionAttributes;

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
	

	@RequestMapping(value="/review_list", method = RequestMethod.GET)
	public String reviewList(@RequestParam(value = "key", defaultValue="") 
		String title, Criteria criteria,
	HttpSession session, Model model) {
		
		// 공지사항 목록 조회 - 공지사항 10개만 조회
		
	List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);
	
	//화면에 표시할 페이지 버튼 정보 설정
	PageMaker pageMaker = new PageMaker();
	int totalCount= reviewsService.countReviewlist(title);
	
	pageMaker.setCriteria(criteria); //현재 페이지와 페이지당 항목 수 정보 설정
	pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화
	
	model.addAttribute("reviewList", reviewList);
	model.addAttribute("reviewListSize", reviewList.size());
	model.addAttribute("pageMaker", pageMaker);
	
	return "realreview/reviewList";
	
			
	}
	
	@RequestMapping(value= "review_detail" ,method = RequestMethod.GET)
	public String reviewDetail(HttpSession session, RealReviewVO vo, Model model, int rseq) {
		
		UsersVO loginUser = (UsersVO)session.getAttribute("loginUser");
		if(loginUser ==null) {
			return "Users/login";
		}else {
			reviewsService.updateViewCount(vo.getRseq()); //조회수 증가
		
		RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
		model.addAttribute("RealReviewVO", reviewsDetail);
		
		return "realreview/reviewDetail";
			
		}
		
		
		
	}
	
	@GetMapping(value="/review_write")
	public String reviewWrite(RealReviewVO vo, HttpSession session) {
			UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
			if(loginUser ==null) {
				return "Users/login";
			}else {
				return "realreview/reviewWrite";
			}

	}
	@PostMapping(value="review_write")
	public String reviewwWrite(RealReviewVO vo, HttpSession session) {
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if(loginUser ==null ) {
			return "Users/login";
			
		}else {
			vo.setId(loginUser.getId());
			reviewsService.insertReview(vo);
			return "realreview/reviewList";
		} 
	}
	
	
}
