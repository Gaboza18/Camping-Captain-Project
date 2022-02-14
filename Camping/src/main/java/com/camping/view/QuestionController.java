package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.camping.biz.dto.QuestionsVO;
import com.camping.biz.question.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	/*
	 * 자주묻는 질문 조회
	 */
	@RequestMapping(value = "/question_list", method = RequestMethod.GET)
	public String questionList(HttpSession session, Model model) {

		List<QuestionsVO> questionList = questionService.listQuestion();
		model.addAttribute("questionList", questionList);

		return "question/questionList";
	}
	
	/*
	 * 자주묻는 질문 상세보기 
	 */
	@RequestMapping(value = "/question_detail", method = RequestMethod.GET)
	public String questionDetail(HttpSession session, QuestionsVO vo, Model model, int qseq) {
		
		QuestionsVO questionDetail = questionService.detailQuestion(qseq);
		model.addAttribute("questionsVO",questionDetail);
		
		return "question/questionDetail";
	}
}
