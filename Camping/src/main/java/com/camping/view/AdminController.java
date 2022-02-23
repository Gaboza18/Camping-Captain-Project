package com.camping.view;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camping.biz.admin.AdminService;
import com.camping.biz.camping.CampingService;
import com.camping.biz.camporder.CampOrderService;
import com.camping.biz.campordercancel.CampOrderCancelService;
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.CampOrderCancelVO;
import com.camping.biz.dto.CampOrderVO;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("loginAdmin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private CampingService campingService;
	@Autowired
	private CampOrderService campOrderService;
	@Autowired
	private CampOrderCancelService campOrderCancelService;
	
	@GetMapping(value="/admin_login_form")
	public String AdminLoginView() {
		return "admin/admin_login";
	}
	
	@PostMapping(value="/admin_login")
	public String AdminLoginAction(AdminVO vo, Model model) {
		
		AdminVO loginAdmin = null;
		
		int result = adminService.loginID(vo);
		
		if(result == 1) {
			loginAdmin = adminService.getAdmin(vo.getId());
			model.addAttribute("loginAdmin", loginAdmin);
			
			return "admin/admin_index";
		} else {
			return "admin/login_fail";
		}
	}
	
	@RequestMapping(value="/search_orderList", method = RequestMethod.GET)
	public String orderList(@RequestParam(value = "campName") int camp_id, Criteria criteria,
							HttpSession session, Model model) {
		
			String camp_name = campingService.getCampName(camp_id);
			
			// ������Ȳ 10�� ��ȸ
			List<CampOrderVO> orderList = campOrderService.getListWithPaging(criteria, camp_name);
			
			// ȭ�鿡 ǥ���� ������ ��ư ���� ����
			PageMaker pageMaker = new PageMaker();
			int totalCount = campOrderService.countOrderList(camp_name);
			
			pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
			pageMaker.setTotalCount(totalCount); // ��ü ������Ȳ ��� ���� ���� �� ������ ���� �ʱ�ȭ
			

			model.addAttribute("orderList", orderList);
			model.addAttribute("selected", camp_id);
			model.addAttribute("pageMaker", pageMaker);
		
		return "admin/admin_orderList";
	}
	
	@GetMapping(value="/insert_cancel_reason")
	public String insertReasonView(CampOrderVO vo, Model model) {
		CampOrderVO campOrder = campOrderService.getCampOrder(vo.getOseq());
		
		model.addAttribute("campOrder", campOrder);
		
		return "admin/cancelOrder";
	}
	
	// �˾�â���� �ݷ���ư Ŭ�� �� ����ݷ� ����
	@PostMapping(value="/cancel_order")
	public String cancelOrderAction(CampOrderCancelVO vo, Model model) {
		
//		CampOrderCancelVO cancel = new CampOrderCancelVO();
//		
//		cancel.setOseq(vo.getOseq());
//		cancel.setUsersid(vo.getUser_id());
//		cancel.setCamp_name(vo.getCamp_name());
//		cancel.setCamp_zone(vo.getCamp_zone());
//		cancel.setIndate(vo.getIndate());
//		cancel.setOutdate(vo.getOutdate());
//		cancel.setOrder_name(vo.getOrder_name());
//		cancel.setOrder_people(vo.getOrder_people());
//		cancel.setOrder_phone(vo.getOrder_phone());
//		cancel.setOrder_email(vo.getOrder_email());
//		cancel.setTotal_price(vo.getTotal_price());
//		cancel.setCancelprice(vo.getTotal_price());
//		cancel.setReason);
		
		campOrderCancelService.insertOrderCancel(vo);
		
		return "redirect: admin/admin_orderList";
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("������ �����ϼ���", "0");
		conditionMap.put("ķ������-����������", "1");

		return conditionMap;
	}
}
