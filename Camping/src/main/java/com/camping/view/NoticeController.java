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
	 * �������� ��ȸ
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
	 * �������� �󼼺���, ��ȸ�� ����
	 */
	@RequestMapping(value = "/notice_detail", method = RequestMethod.GET)
	public String noticeDetail(HttpSession session, NoticeVO vo, Model model, int nseq) {

		noticeService.updateViewCount(vo.getNseq()); // ��ȸ�� ����

		NoticeVO noticeDetail = noticeService.detailNotice(nseq);
		model.addAttribute("noticeVO", noticeDetail);

		return "notice/noticeDetail";
	}

	/*
	 * ��������(����¡ ó��) ��ȸ
	 */

	@RequestMapping(value = "/notice_list", method = RequestMethod.GET)
	public String noticeList(@RequestParam(value = "key", defaultValue = "") String admin_name, Criteria criteria,
			HttpSession session, Model model) {

		// �������� ��� ��ȸ - �������� 10���� ��ȸ
		List<NoticeVO> noticeList = noticeService.getListWithPaging(criteria, admin_name);

		// ȭ�鿡 ǥ���� ������ ��ư ���� ����
		PageMaker pageMaker = new PageMaker();
		int totalCount = noticeService.countNoticetList(admin_name);

		pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
		pageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ

		model.addAttribute("noticeList", noticeList); // ${noticeList} �Ӽ����� ��� ȭ�鿡 ȣ���Ѵ�
		model.addAttribute("noticeListSize", noticeList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "notice/noticeList"; // �������� ����Ʈ ȭ������ ����
	}

}
