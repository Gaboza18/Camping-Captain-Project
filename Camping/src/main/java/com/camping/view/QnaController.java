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
	 * ȸ�� ID�� �������� �ۼ��� ��� QnA ��ȸ
	 */
	@GetMapping(value = "/qna_list")
	public String qnaList(HttpSession session, Model model) {

		// �α����� ������ ��ü�� ��� �����´�
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		// �α����� ��,�� Ȯ���� �α����� �Ǿ����� ������ �α��� �������� ���� ����
		if (loginUser == null) {
			return "Users/login";
		} else {
			List<QnaVO> qnaList = qnaService.listQna(loginUser.getId()); // �α����� ȸ���� ���̵��� ������ �Խñ� ��ü ��ȸ�Ѵ�

			model.addAttribute("qnaList", qnaList); // �Խñ� ����� ȭ�鿡 �����Ѵ�

			return "qna/qnaList"; // �Խ��� ������� �̵��Ѵ�
		}
	}

	/*
	 * �Խ��� �۾��� �ҷ�����
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
	 * QnA �Խ��� �۵��
	 */
	@PostMapping(value = "/qna_write")
	public String qnaWrite(HttpSession session, QnaVO vo) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "Users/login";
		} else {

			vo.setId(loginUser.getId()); // ȭ�鿡�� �о�� �Ķ����(subject, content) ����� ���̵� ���� QnaVO ��ü ����
			qnaService.insertQna(vo); // qnaService ��ü���� insertQna(qnaVO id) ȣ�� �Խñ� ����

			return "redirect:qna_list";
		}
	}

	/*
	 * QnA �Խ��� �󼼺���
	 */
	@GetMapping(value = "/qna_view")
	public String qnaView(HttpSession session, QnaVO vo, Model model) {

		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "Users/login";
		} else {

			vo.setId(loginUser.getId());

			QnaVO qna = qnaService.getQna(vo.getQseq()); // qnaService ��ü���� getQna() �����Ͽ� ��ȸ�� ��� qnaVO Ű�� model ��ü�� ����
			model.addAttribute("qnaVO", qna);

			return "qna/qnaView";
		}
	}

}
