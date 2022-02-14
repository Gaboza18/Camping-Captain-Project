package com.camping.view;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.camping.biz.dto.NoticeVO;
import com.camping.biz.notice.NoticeService;

import utils.Criteria;
import utils.PageMaker;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/*
	 * 공지사항 조회
	 */
	
	/*
	 * @RequestMapping(value = "/notice_list", method = RequestMethod.GET) public
	 * String noticeList(HttpSession session, Model model) {
	 * 
	 * List<NoticeVO> noticeList = noticeService.listNotice();
	 * model.addAttribute("noticeList", noticeList);
	 * 
	 * return "notice/noticeList"; }
	 */
	
	/*
	 * 공지사항 상세보기, 조회수 증가
	 */
	@RequestMapping(value = "/notice_detail", method = RequestMethod.GET)
	public String noticeDetail(HttpSession session, NoticeVO vo, Model model, int nseq) {

		noticeService.updateViewCount(vo.getNseq()); // 조회수 증가

		NoticeVO noticeDetail = noticeService.detailNotice(nseq);
		model.addAttribute("noticeVO", noticeDetail);

		return "notice/noticeDetail";
	}

	/*
	 * 공지사항(페이징 처리) 조회
	 */

	@RequestMapping(value = "/notice_list", method = RequestMethod.GET)
	public String noticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		// 공지사항 목록 조회 - 공지사항 10개만 조회
		List<NoticeVO> noticeList = noticeService.getListWithPaging(criteria, title);

		// 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		int totalCount = noticeService.countNoticetList(title);

		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 공지사항 목록 갯수 설정 및 페이지 정보 초기화

		model.addAttribute("noticeList", noticeList); // ${noticeList} 속성값에 담고 화면에 호출한다
		model.addAttribute("noticeListSize", noticeList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "notice/noticeList"; // 공지사항 리스트 화면으로 전송
	}
	
	// 검색 조건 목록 설정
	/*
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();
		
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}
	*/
	
	/*
	// 게시글 목록 조회
		@RequestMapping(value="/getBoardList.do")
		public String getBoardList(
				// @RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition,
				// @RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword,
				NoticeVO vo,
				Model model) {
			System.out.println("게시글 목록조회 처리");

			if (vo.getSearchCondition() == null)
				vo.setSearchCondition("TITLE");
			if (vo.getSearchKeyword() == null)
				vo.setSearchKeyword("");
			
			System.out.println("검색 조건: " + vo.getSearchCondition());
			System.out.println("검색 단어: " + vo.getSearchKeyword());
			
			List<NoticeVO> noticeList = noticeService.getListWithPaging(criteria, admin_name);
			
			// 검색결과를 세션에 저장하고 목록화면을 호출한다.
			model.addAttribute("boardList", boardList);
			
			return "getBoardList.jsp";
		}
*/
}
