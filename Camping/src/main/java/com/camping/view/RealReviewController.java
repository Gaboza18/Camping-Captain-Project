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
		
		// �������� ��� ��ȸ - �������� 10���� ��ȸ
		
	List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);
	
	//ȭ�鿡 ǥ���� ������ ��ư ���� ����
	PageMaker pageMaker = new PageMaker();
	int totalCount= reviewsService.countReviewlist(title);
	
	pageMaker.setCriteria(criteria); //���� �������� �������� �׸� �� ���� ����
	pageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ
	
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
			reviewsService.updateViewCount(vo.getRseq()); //��ȸ�� ����
		
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
