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

import com.camping.biz.adminnotice.AdminNotice;
import com.camping.biz.dto.AdminNoticeVO;
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.RealReviewVO;

import utils.Criteria;
import utils.PageMaker;





@Controller
@SessionAttributes("loginAdmin")
public class AdminNoticeController {
	@Autowired
	private AdminNotice adminnoticeService;
	
	

	@RequestMapping(value = "/adminNotice", method = RequestMethod.GET) public
	 String noticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			 HttpSession session, Model model,int aseq) {
			
		  AdminVO loginAdmin = (AdminVO) session.getAttribute("loginAdmin");
			if (loginAdmin == null) {
				return "admin/admin_login";

			}else {
		  
				List<AdminNoticeVO> adminnoticeList = adminnoticeService.getListWithPaging(criteria, title);
				
				// ȭ�鿡 ǥ���� ������ ��ư ���� ����
				PageMaker pageMaker = new PageMaker();
				int totalCount = adminnoticeService.updateViewCount(aseq);

				pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
				pageMaker.setTotalCount(totalCount); // ��ü �������� ��� ���� ���� �� ������ ���� �ʱ�ȭ

				model.addAttribute("adminnoticeList", adminnoticeList); // ����, �� ������ ���� ������ reviewList���� <for:each>�� ������ ������
				model.addAttribute("noticeListSize", adminnoticeList.size());
				model.addAttribute("pageMaker", pageMaker);

				return "adminnotice/adminnoticeList";
	 
 }
	}
	
}
