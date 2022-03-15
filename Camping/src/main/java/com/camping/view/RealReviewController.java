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

		// �������� ��� ��ȸ - �������� 10���� ��ȸ

		List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

		// ȭ�鿡 ǥ���� ������ ��ư ���� ����
		PageMaker pageMaker = new PageMaker();
		int totalCount = reviewsService.countReviewlist(title);

		pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
		pageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ

		model.addAttribute("reviewList", reviewList); // ����, �� ������ ���� ������ reviewList���� <for:each>�� ������
														// ������
		model.addAttribute("reviewListSize", reviewList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "realreview/reviewList";
	}

	@RequestMapping(value = "review_detail", method = RequestMethod.GET)

	public String reviewDetail(HttpSession session, RealReviewVO vo, Model model, int rseq) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");

		if (loginUser == null && loginAdmin == null) {
			return "Users/login";

			// admin�� ���󸮺�� �߸� ���ӽ� ���� ������
		} else if (loginAdmin != null) {

			// admin �α��ν� ����󼼺���
			reviewsService.updateViewCount(vo.getRseq()); // ��ȸ�� ����
			String loginadmin = loginAdmin.getId();
			RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
			model.addAttribute("RealReviewVO", reviewsDetail);
			model.addAttribute("loginAdmin1", loginadmin);
			return "realreview/reviewDetail";
		} else {

			reviewsService.updateViewCount(vo.getRseq()); // ��ȸ�� ����

			String userid = loginUser.getId();

			// else �ȿ� model��ü�� �����Ǽ� admin���� �α��� �ϸ� detail�� �ȶߴ°ǰ�
			// �; ����
			// String loginadmin = loginAdmin.getId();

			RealReviewVO reviewsDetail = reviewsService.detailReviews(rseq);
			model.addAttribute("RealReviewVO", reviewsDetail);
			model.addAttribute("userid", userid);
			// model.addAttribute("loginAdmin1", loginadmin);
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
	public String seemyreview(RealReviewVO vo, HttpSession session, Model model, Criteria criteria, String id) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {

			vo.setId(loginUser.getId());

			reviewsService.seemyreview(vo);

			List<RealReviewVO> myreviewList = reviewsService.getListWithPaging2(criteria, vo.getId());

			// ȭ�鿡 ǥ���� ������ ��ư ���� ����
			PageMaker mypageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist2(id);

			mypageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
			mypageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ

			model.addAttribute("myreviewListSize", myreviewList.size());
			model.addAttribute("mypageMaker", mypageMaker);
			model.addAttribute("myreviewList", myreviewList);

			return "mypage/myreviewList";
		}
	}

	// �������(��������� ���� �����ߴ� reviewsServcice.detail)���� login session��
	// �̿��Ͽ� ������
	// �����Ͽ� ����� �� ����
	@RequestMapping(value = "/review_list_re", method = RequestMethod.GET)
	@ResponseBody
	public String deletereviews(@RequestParam(value = "rseq") int rseq, HttpSession session, Model model,
			Criteria criteria, String title) throws Exception {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");
		if (loginUser == null && loginAdmin == null) {
			return "Users/login";

			// admin�� ���󸮺�� �߸� ���ӽ� ���� ������
		} else if (loginAdmin != null) {

			// model.addAllAttributes(reviewsService.listReview(vo));

			// ���� ��ư Ŭ���� �����Ǵ� �κ�
			reviewsService.deletereviews(rseq);
			// �������� ��� ��ȸ - �������� 10���� ��ȸ

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// ȭ�鿡 ǥ���� ������ ��ư ���� ����
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
			pageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ

			model.addAttribute("reviewList", reviewList); // ����, �� ������ ���� ������ reviewList���� <for:each>��
															// ������ ������
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "admin/managerealreview";
		} else {
			reviewsService.deletereviews(rseq);
			// �������� ��� ��ȸ - �������� 10���� ��ȸ

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// ȭ�鿡 ǥ���� ������ ��ư ���� ����
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
			pageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ

			model.addAttribute("reviewList", reviewList); // ����, �� ������ ���� ������ reviewList���� <for:each>��
															// ������ ������
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "realreview/reviewList";
		}

	}

	@GetMapping(value = "/modi")
	public String modifyreview(int rseq, RealReviewVO vo, HttpSession session, Model model) {

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

	@RequestMapping(value = "/modifyReview", method = RequestMethod.GET)
	public String updatereviews(int rseq, RealReviewVO vo, HttpSession session, Criteria criteria, String title,
			Model model) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "Users/login";
		} else {

			reviewsService.modifyreviews(vo);

			title = "";

			List<RealReviewVO> reviewList = reviewsService.getListWithPaging(criteria, title);

			// ȭ�鿡 ǥ���� ������ ��ư ���� ����
			PageMaker pageMaker = new PageMaker();
			int totalCount = reviewsService.countReviewlist(title);

			pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
			pageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ

			model.addAttribute("reviewList", reviewList); // ����, �� ������ ���� ������ reviewList���� <for:each>��
															// ������ ������
			model.addAttribute("reviewListSize", reviewList.size());
			model.addAttribute("pageMaker", pageMaker);

			return "realreview/reviewList";

		}

	}

}
