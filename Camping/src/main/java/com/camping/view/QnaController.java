package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.camping.biz.dto.QnaVO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.qna.QnaService;

@Controller
public class QnaController {

	@Autowired
	QnaService qnaService;

	/*
	 * 회원 ID를 조건으로 작성한 모든 QnA 조회
	 */
	@GetMapping(value = "/qna_list")
	public String qnaList(HttpSession session, Model model) {

		// 로그인한 정보를 객체에 담아 가져온다
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		// 로그인이 유,무 확인후 로그인이 되어있지 않으면 로그인 페이지로 세션 전달
		if (loginUser == null) {
			return "Users/login";
		} else {
			List<QnaVO> qnaList = qnaService.listQna(loginUser.getId()); // 로그인한 회원의 아이디값을 가져와 게시글 전체 조회한다

			model.addAttribute("qnaList", qnaList); // 게시글 목록을 화면에 전송한다

			return "qna/qnaList"; // 게시판 목록으로 이동한다
		}
	}

	/*
	 * 게시판 글쓰기 불러오기
	 */
	@GetMapping(value = "/qna_write_form")
	public String qnaWriteView(HttpSession session) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			return "qna/qnaWrite";
		}
	}

	/*
	 * QnA 게시판 글등록
	 */
	@PostMapping(value = "/qna_write")
	public String qnaWrite(HttpSession session, QnaVO vo) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "Users/login";
		} else {

			vo.setId(loginUser.getId()); // 화면에서 읽어온 파라미터(subject, content) 사용자 아이디 정보 QnaVO 객체 저장
			qnaService.insertQna(vo); // qnaService 객체에서 insertQna(qnaVO id) 호출 게시글 저장

			return "redirect:qna_list";
		}
	}

	/*
	 * QnA 게시판 상세보기
	 */
	@GetMapping(value = "/qna_view")
	public String qnaView(HttpSession session, QnaVO vo, Model model) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "Users/login";
		} else {

			vo.setId(loginUser.getId());

			QnaVO qna = qnaService.getQna(vo.getQseq()); // qnaService 객체에서 getQna() 실행하여 조회한 결과 qnaVO 키로 model 객체에 저장
			model.addAttribute("qnaVO", qna);

			return "qna/qnaView";
		}
	}

}
