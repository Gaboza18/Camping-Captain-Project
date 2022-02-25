package com.camping.view;

import java.text.SimpleDateFormat;
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

import com.camping.biz.camping.CampingService;
import com.camping.biz.camporder.CampOrderService;
import com.camping.biz.campordercancel.CampOrderCancelService;
import com.camping.biz.dto.CampOrderCancelVO;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.UsersVO;

import utils.Criteria;
import utils.PageMaker;

@Controller
public class CampOrderController {
	
	@Autowired
	CampOrderService campOrderService;
	@Autowired
	private CampingService campingService;
	@Autowired
	private CampOrderCancelService campOrderCancelService;
	
	/*
	 *  �� ������Ȳ ����
	 */
	
	@RequestMapping(value = "/my_reservation", method = RequestMethod.GET)
	public String my_reservation(HttpSession session, Criteria criteria, Model model) {
		
		// �α����� ������ ��ü�� ��� �����´�
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		// �α����� ��,�� Ȯ���� �α����� �Ǿ����� ������ �α��� �������� ���� ����
		if (loginUser == null) {
			return "Users/login";
		} else {
			// ���� ��� 10�� ��ȸ
			List<CampOrderVO> campOrderList = campOrderService.getMyListWithPaging(criteria, loginUser.getId());
			
			// ȭ�鿡 ǥ���� ������ ��ư���� ����
			PageMaker pageMaker = new PageMaker();
			int totalCount = campOrderService.countMyOrderList(loginUser.getId());
			
			pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
			pageMaker.setTotalCount(totalCount); // ��ü ������Ȳ ��� ���� ���� �� ������ ���� �ʱ�ȭ
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date);
		
			model.addAttribute("today", today);
			model.addAttribute("campOrderList", campOrderList);
			model.addAttribute("pageMaker", pageMaker);
			
			return "camping/campOrderList";
		}
	}
	
	// �� �����Ͽ��� ������ ����
	@GetMapping(value="/myOrder_detail")
	public String myOrderDetailView(CampOrderVO vo, Model model) {
		CampOrderVO myOrderDetail = campOrderService.getMyCampOrder(vo.getOseq());
		
		model.addAttribute("myOrderDetail", myOrderDetail);
		
		return "camping/myOrderDetail";
	}
	
	// �� ���� ����ϱ��˾�â ���� (���ȯ�� ���� ���� �� ���ȭ������ �Ѿ)
	@GetMapping(value="/go_myOrder_cancel")
	public String gotMyorderCancelView(CampOrderVO vo, Model model) {
		
		model.addAttribute("oseq", vo.getOseq());
		
		return "camping/refundPolicy";
	}
	@GetMapping(value="/insert_myOrder_cancel")
	public String insertMyorderCancelView(CampOrderVO vo, Model model) {
		CampOrderVO campOrder = campOrderService.getCampOrder(vo.getOseq());
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		
		model.addAttribute("today", today);
		model.addAttribute("campOrder", campOrder);
		
		return "camping/cancelMyOrder";
	}
	
	// �˾�â���� '���' ��ư Ŭ�� �� �� ������� ����(��� ���̺�� �̵�) + �������̺��� ����
	@RequestMapping(value="/cancel_myOrder", method=RequestMethod.GET)
	public String cancelMyOrderAction(CampOrderCancelVO vo) {
		
		campOrderCancelService.insertCancelMyOrder(vo);
		campOrderService.deleteOrderByOseq(vo.getOseq());
		
		return "camping/campOrderList";
	}
	
	// ���� ��Ȳ ��ȸ �������� �̵�
	@RequestMapping(value="/search_order", method = RequestMethod.GET)
	public String orderList(Model model) {
		
		return "admin/admin_orderList";
	}
	
	//  ���� ��Ȳ ��ȸ���������� ���� ���ý� ������ ������Ȳ ����Ʈ ��ȸ
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
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
	
		model.addAttribute("today", today);
		model.addAttribute("orderList", orderList);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("selected", camp_id);
		
		return "admin/admin_orderList";
	}
	
	// ���� ��Ȳ ����Ʈ���� '���'��ư Ŭ�� �� ���� �Է��ϴ� ������ ����
	@GetMapping(value="/insert_cancel_reason")
	public String insertReasonView(@RequestParam(value = "camp_idRe") int camp_id, CampOrderVO vo, Model model) {
		CampOrderVO campOrder = campOrderService.getCampOrder(vo.getOseq());
		
		model.addAttribute("campOrder", campOrder);
		model.addAttribute("selected", camp_id);
		
		return "admin/cancelOrder";
	}
	
	// �˾�â���� '���' ��ư Ŭ�� �� ������� ����(��� ���̺�� �̵�) + �������̺��� ����
	@RequestMapping(value="/cancel_order", method=RequestMethod.GET)
	public String cancelOrderAction(CampOrderCancelVO vo) {
		campOrderCancelService.insertOrderCancel(vo);
		campOrderService.deleteOrderByOseq(vo.getOseq());
		
		return "admin/admin_orderList";
	}
	
	// ���� ��Ȳ ����Ʈ���� '����Ϸ�' ��ư Ŭ�� �� ���� �󼼳���Ȯ���� ���� �˾�â ����
	@GetMapping(value="/confirm_order_check")
	public String confirmOrderView(@RequestParam(value = "camp_idRe") int camp_id, CampOrderVO vo, Model model) {
		CampOrderVO campOrder = campOrderService.getCampOrder(vo.getOseq());
		
		model.addAttribute("campOrder", campOrder);
		model.addAttribute("selected", camp_id);
		
		return "admin/confirmOrder";
	}
	
	// ���� �󼼳��� �˾�â���� '����Ȯ��'��ư Ŭ�� �� ����
	@RequestMapping(value="/confirm_order", method=RequestMethod.GET)
	public String confirmOrderAction(CampOrderVO vo) {
		campOrderService.updateOrderStatus(vo.getOseq());
		
		return "admin/admin_orderList";
	}
	
	// ���� ��ҳ��� ��ȸ �������� �̵�
	@RequestMapping(value="/search_cancel", method = RequestMethod.GET)
	public String cancelOrderList(Model model) {
		
		return "admin/admin_cancelList";
	}
	
	// ���� �����Ȳ ��ȸ���������� ���� ���ý� ������ �����Ȳ ����Ʈ ��ȸ
	@RequestMapping(value="/search_cancelList", method = RequestMethod.GET)
	public String cancelList(@RequestParam(value = "campName") int camp_id, Criteria criteria,
							HttpSession session, Model model) {
			
		String camp_name = campingService.getCampName(camp_id);
		
		// ���� ��� ��Ȳ 10�� ��ȸ
		List<CampOrderCancelVO> cancelList = campOrderCancelService.getListWithPaging(criteria, camp_name);
		
		// ȭ�鿡 ǥ���� ������ ��ư ���� ����
		PageMaker pageMaker = new PageMaker();
		int totalCount = campOrderCancelService.countOrderList(camp_name);
		
		pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
		pageMaker.setTotalCount(totalCount); // ��ü ������Ȳ ��� ���� ���� �� ������ ���� �ʱ�ȭ
	
		model.addAttribute("cancelList", cancelList);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("selected", camp_id);
		
		return "admin/admin_cancelList";
	}
	
	// ��ü ��ҳ��� ����Ʈ���� '����ϱ�' ��ư Ŭ���� ��� ��û�� ���� ���೻���� ��� �˾�â ����
	@GetMapping(value="/confirm_cancel_check")
	public String confirmCancelView(@RequestParam(value = "camp_idRe") int camp_id, CampOrderCancelVO vo, Model model) {
		CampOrderCancelVO cancelOrder = campOrderCancelService.getCancelOrder(vo.getCseq());
		
		model.addAttribute("cancelOrder", cancelOrder);
		model.addAttribute("selected", camp_id);
		
		return "admin/admin_confirmCancel";
	}
	
	// ��� Ȯ���ϴ� �˾�â���� '����ϱ�'��ư Ŭ�� �� ��� Ȯ�� �Ϸ�
	@RequestMapping(value="/confirm_cancel_order", method=RequestMethod.GET)
	public String confirmCancelOrderAction(CampOrderCancelVO vo) {
		campOrderCancelService.updateCancelStatus(vo.getCseq());
		
		return "admin/admin_cancelList";
	}
	
	// ��ҳ��� �󼼺���
	@GetMapping(value="/cancel_detail")
	public String cancelDetailView(CampOrderCancelVO vo, Model model) {
		CampOrderCancelVO cancelDetail = campOrderCancelService.getCancelOrder(vo.getCseq());
		
		model.addAttribute("cancelDetail", cancelDetail);
		
		return "admin/cancelDetail";
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("������ �����ϼ���", "0");
		conditionMap.put("ķ������-����������", "1");

		return conditionMap;
	}
}