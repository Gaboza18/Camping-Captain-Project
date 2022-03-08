package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camping.biz.dto.QuestionsVO;
import com.camping.biz.question.QuestionService;

@Controller
@SessionAttributes("questionBoard")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	/*
	 * 자주묻는질문 조회(비,회원)
	 */
	@RequestMapping(value = "/question_list", method = RequestMethod.GET)
	public String questionList(HttpSession session, Model model) {

		List<QuestionsVO> questionList = questionService.listQuestion();
		model.addAttribute("questionList", questionList);

		return "question/questionList";
	}

	/*
	 * 자주묻는질문 상세보기(비,회원)
	 */
	@RequestMapping(value = "/question_detail", method = RequestMethod.GET)
	public String questionDetail(HttpSession session, QuestionsVO vo, Model model, int qseq) {

		QuestionsVO questionDetail = questionService.detailQuestion(qseq);
		model.addAttribute("questionsVO", questionDetail);

		return "question/questionDetail";
	}

	/*
	 * 자주묻는 질문 조회(총관리자)
	 */
	@RequestMapping(value = "/question_write_form", method = RequestMethod.GET)
	public String questionWriteView(HttpSession session, Model model) {

		List<QuestionsVO> questionList = questionService.listQuestion();
		model.addAttribute("questionList", questionList);
		
		return "admin/question/admin_questionWrite";
		
	}
	
	/*
	 * 자주묻는 질문 상세보기(총관리자)
	 */
	@RequestMapping(value = "/admin_question_detail", method = RequestMethod.GET)
	public String adminQuestionDetail(HttpSession session, QuestionsVO vo, Model model, int qseq) {
		
		QuestionsVO questionDetail = questionService.detailQuestion(qseq);
		model.addAttribute("questionsVO", questionDetail);
		
		return "admin/question/admin_questionDetail";
	}

	/*
	 * 자주묻는 질문 등록 작성페이지 이동
	 */
	@RequestMapping(value = "/admin_question_write_form", method = RequestMethod.GET)
	public String adminQuestionWriteForm(HttpSession session, QuestionsVO vo) {
		return "admin/question/admin_question_write_form";
	}
	
	/*
	 * 자주묻는 질문 등록 
	 */
	@RequestMapping(value = "/question_write", method = RequestMethod.GET)
	public String adminQuestionWrite(HttpSession session, QuestionsVO vo) {
		
		questionService.insertQuestion(vo);
		
		return "redirect:question_write_form";
	}
	
	/*
	 * 자주묻는 질문 수정 페이지 이동
	 */
	@RequestMapping(value = "/admin_question_update_form", method = RequestMethod.GET)
	public String adminQuestionUpdateForm(@RequestParam("qseq") int qseq, Model model) {
		
		QuestionsVO questionDetail = questionService.detailQuestion(qseq);
		model.addAttribute("questionsVO", questionDetail);
		
		return "admin/question/admin_question_update_form";
	}
	
	/*
	 * 자주묻는 질문 수정
	 */
	@RequestMapping(value = "/admin_question_update", method = RequestMethod.GET)
	public String adminQuestionUpdate(HttpSession session, QuestionsVO vo) {
		
		questionService.updateQuestion(vo);
		
		return "redirect:question_write_form";
	}
	
	/*
	 * 자주묻는 질문 삭제
	 */
	@RequestMapping(value = "/admin_question_delete", method = RequestMethod.GET)
	public String adminQuestionDelete(HttpSession session, QuestionsVO vo, int qseq) {
		
		questionService.deleteQuestion(qseq);
		
		return "redirect:question_write_form";
	}
}
