package com.camping.view;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camping.biz.camping.CampingService;
import com.camping.biz.camporder.CampOrderService;
import com.camping.biz.campordercancel.CampOrderCancelService;
import com.camping.biz.dto.CampOrderCancelVO;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.TempOrderVO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.temporder.TempOrderService;
import com.camping.biz.users.UsersService;

import utils.Criteria;
import utils.HttpUtil;
import utils.PageMaker;
import utils.SHA256;

@Controller
@SessionAttributes("loginUser")
public class CampOrderController {
	
	@Autowired
	private CampOrderService campOrderService;
	@Autowired
	private CampingService campingService;
	@Autowired
	private CampOrderCancelService campOrderCancelService;
	@Autowired
	private TempOrderService tempOrderService;
	@Autowired
	private UsersService usersService;
	
	/*
	 *  ķ���� �����ϱ�
	 */
	@RequestMapping(value="go_payForm", method=RequestMethod.POST)
	public String goPayFormView(TempOrderVO vo, Model model, HttpSession session) {
		UsersVO loginUser =(UsersVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "Users/login";
		} else {
			SHA256 sha256 = new SHA256();
			
			vo.setUser_id(loginUser.getId());
			// 17�ڸ� ��¥ ���ڿ� ����.
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = sdf.format(date);
			// vo�� setTempId
			vo.setTemp_id(today);
			
			tempOrderService.insertTempOrder(vo);

			String mid = "INIpayTest";  // ������ Id(�߱� �ʿ�, ����� �׽�Ʈ��)
			String mKey = "3a9503069192f207491d4b19bd743fc249a761ed94246c8c42fed06c3cd15a33"; // �׽�Ʈ��
			String oid = vo.getTemp_id();  // �ӽ� �ֹ���ȣ
			Long timestamp = System.currentTimeMillis();  // ������ �ð���
			String sha = "oid="+oid+"&price="+vo.getTotal_price()+"&timestamp="+timestamp;
			String signature = "";
			
			try {
				// signature ������ ����(��⿡�� �ڵ����� signParam�� ���ĺ������� ���� �� NVP ������� ������ hash
				signature = sha256.encrypt(sha);
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			model.addAttribute("tempOrder", vo);
			model.addAttribute("mid", mid);
			model.addAttribute("mKey", mKey);
			model.addAttribute("oid", oid);
			model.addAttribute("timestamp", timestamp);
			model.addAttribute("signature", signature);
			
			return "camping/inicis";
		}
	}
	
	@RequestMapping(value="/order_insert", method=RequestMethod.POST)
	public String campingOrderAction(String temp_id, CampOrderVO vo, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, HttpSession session, Criteria criteria, Model model) {
		UsersVO loginUser = null;
		
		TempOrderVO tVo = tempOrderService.getTempOrder(temp_id);
		loginUser = usersService.getUsers(tVo.getUser_id());
		
		
		vo.setCamp_name(tVo.getCamp_name());
		vo.setCamp_zone(tVo.getCamp_zone());
		vo.setIndate(tVo.getIndate());
		vo.setOutdate(tVo.getOutdate());
		vo.setUser_id(tVo.getUser_id());
		vo.setOrder_name(tVo.getOrder_name());
		vo.setOrder_people(tVo.getOrder_people());
		vo.setOrder_phone(tVo.getOrder_phone());
		vo.setOrder_email(tVo.getOrder_email());
		vo.setTotal_price(tVo.getTotal_price());
		
		/*
		 * ���� �� ó��
		 */
		String retUrl = "";
		
		HttpSession hSession = request.getSession();
		
		try{

			//#############################
			// ������� �Ķ���� �ϰ� ����
			//#############################
			request.setCharacterEncoding("UTF-8");
			Map<String,String> paramMap = new Hashtable<String,String>();
			Enumeration elems = request.getParameterNames();
			
			String temp = "";
			
			while(elems.hasMoreElements()) {
				temp = (String) elems.nextElement();
				paramMap.put(temp, request.getParameter(temp));
			}
			
			System.out.println("paramMap : "+ paramMap.toString());
	
			
			// STEP2 �� �̾� ��������� ������ ��� STEP2 ���� ���� ��������� �Ʒ� ���ο�û ����
			
			//#####################
			// ������ ������ ��츸
			//#####################
				
			// 1. ���� �ʵ� �� ����(*** ������ ���߼��� ***)
		
			System.out.println("## ���������� �ϰ����� ##");
			System.out.println("<p>"+paramMap.toString()+"</p>");
			
			String mKey = "3a9503069192f207491d4b19bd743fc249a761ed94246c8c42fed06c3cd15a33"; // �׽�Ʈ��
			String mid = paramMap.get("mid");               // ������ ID ���� ���� �����ͷ� ����
			Long timestamp= System.currentTimeMillis();     // util�� ���ؼ� �ڵ�����
			String charset = "UTF-8";                       // ��������[UTF-8,EUC-KR](������ ������ ����)
			String format = "JSON";                         // ��������[XML,JSON,NVP](������ ������ ����)
			String authToken= paramMap.get("authToken");    // ��� ��û tid�� ���� ������(������ ������ ����)
			String authUrl= paramMap.get("authUrl");        // ���ο�û API url(���� ���� ������ ����, ���� ���� ����)
			String netCancel= paramMap.get("netCancelUrl"); // ����� API url(���� ���� ������ ����, ���� ���� ����)
			
			//#####################
			// 2.signature ����
			//#####################
			SHA256 sha256 = new SHA256();
			// signature ������ ���� (��⿡�� �ڵ����� signParam�� ���ĺ� ������ ������ NVP ������� ������ hash)
			String sha = "authToken="+authToken+"&timestamp="+timestamp;
			String signature = "";
			
			try {
				// signature ������ ����(��⿡�� �ڵ����� signParam�� ���ĺ������� ���� �� NVP ������� ������ hash
				signature = sha256.encrypt(sha);
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			//#####################
			// 3.API ��û ���� ����
			//#####################
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			
			// BasicNameValuePair�� Key = input�±��� name,
			// BasicNameValuePair�� value = input�±��� value
			param.add(new BasicNameValuePair("mid",mid));
			param.add(new BasicNameValuePair("authToken",authToken));
			param.add(new BasicNameValuePair("signature",signature));
			param.add(new BasicNameValuePair("timestamp",timestamp.toString()));
			param.add(new BasicNameValuePair("charset",charset));
			param.add(new BasicNameValuePair("format",format));
			
			            
			System.out.println("##���ο�û API ��û##");
			
			
			HttpUtil httpUtil = new HttpUtil();
			String result = httpUtil.sendRequest(authUrl, param);
			//#####################
			// 4.API ��� ����
			//#####################
			String authResultString = "";
			
			// 1. result�� map ���·� ��ȯ parse String to map �Ұ�.
			 
			// 2. ���� ���� �Ǵ��ϴ� ������ map.get(Ű)�� �����ͼ� ���� ���� �Ǵ� resultCode = 0000
			if("0000".equals(paramMap.get("resultCode"))){

			 // ������ ��, �ǰ������̺� insert
			}else{
			 // ������ ��, 
			//#############
			// ���� ���н�
			//#############
//			out.println("<br/>");
//			out.println("####��������####");
//			out.println("<p>"+paramMap.toString()+"</p>");
			
			}
			
			}catch(Exception e){
			System.out.println(e);
			}
		
		
		
		
		campOrderService.insertCampOrder(vo);
		
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
	
	// ��ҳ��� ��ȸ�ϱ�
	@RequestMapping(value = "/my_cancel", method = RequestMethod.GET)
	public String my_cnacel(HttpSession session, Criteria criteria, Model model) {
		// �α����� ������ ��ü�� ��� �����´�
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		// �α����� ��,�� Ȯ���� �α����� �Ǿ����� ������ �α��� �������� ���� ����
		if (loginUser == null) {
			return "Users/login";
		} else {
			// ���� ��� 10�� ��ȸ
			List<CampOrderCancelVO> cancelOrderList = campOrderCancelService.getMyListWithPaging(criteria, loginUser.getId());
			
			// ȭ�鿡 ǥ���� ������ ��ư���� ����
			PageMaker pageMaker = new PageMaker();
			int totalCount = campOrderCancelService.countMyCancelList(loginUser.getId());
			
			pageMaker.setCriteria(criteria); // ���� �������� �������� �׸� �� ���� ����
			pageMaker.setTotalCount(totalCount); // ��ü ������Ȳ ��� ���� ���� �� ������ ���� �ʱ�ȭ
		
			model.addAttribute("cancelOrderList", cancelOrderList);
			model.addAttribute("pageMaker", pageMaker);
			
			return "camping/cancelOrderList";
		}
	}
	
	// �� ��Ҹ�Ͽ��� ������ ����
	@GetMapping(value="/myCancel_detail")
	public String myCancelDetailView(CampOrderCancelVO vo, Model model) {
		CampOrderCancelVO myCancelDetail = campOrderCancelService.getCancelOrder(vo.getCseq());
		
		model.addAttribute("myCancelDetail", myCancelDetail);
		
		return "camping/myCancelDetail";
	}
	
	// �� ���� ����ϱ��˾�â ���� (���ȯ�� ���� ���� ȭ��)
	@GetMapping(value="/go_myOrder_cancel")
	public String gotMyorderCancelView(CampOrderVO vo, Model model) {
		
		model.addAttribute("oseq", vo.getOseq());
		
		return "camping/refundPolicy";
	}
	
	// ȯ�� ���� Ȯ���� ��ưŬ���ϸ� ���ȭ������ �Ѿ
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
	
	
	/*
	 * ������ ���
	 */
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
	
	@ModelAttribute("searchMap")
	public Map<String, String> searchMap() {
		Map<String, String> searchMap = new LinkedHashMap<>();
		
		searchMap.put("üũ��", "INDATE");
		searchMap.put("ȸ��ID", "USER_ID");
		searchMap.put("�����ڸ�", "ORDER_NAME");
		
		return searchMap;
	}
}