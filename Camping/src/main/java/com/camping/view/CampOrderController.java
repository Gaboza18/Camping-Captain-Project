package com.camping.view;

import java.io.IOException;
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
import com.camping.biz.dto.AdminVO;
import com.camping.biz.dto.CampOrderCancelVO;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.PayVO;
import com.camping.biz.dto.TempOrderVO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.pay.PayService;
import com.camping.biz.temporder.TempOrderService;
import com.camping.biz.users.UsersService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@Autowired
	private PayService payService;
	
	/*
	 *  캠핑장 예약하기
	 */
	@RequestMapping(value="go_payForm", method=RequestMethod.POST)
	public String goPayFormView(TempOrderVO vo, Model model, HttpSession session) {
		UsersVO loginUser =(UsersVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "Users/login";
		} else {
			SHA256 sha256 = new SHA256();
			
			vo.setUser_id(loginUser.getId());
			// 날짜 문자열 설정.
			Date now = new Date();
			SimpleDateFormat formmat = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = formmat.format(now);
			// vo에 setTempId
			vo.setTemp_id(today);
			
			tempOrderService.insertTempOrder(vo);
			
			/*
			 * INIpay 모듈 세팅 시작
			 * 
			 *  위변조 방지 체크를 위해 signature 생성 
			 *  	-> signature : oid, price, timestamp 3개의 키와 값을 "key=value" 형식으로 하여 '&'으로 연결하고 SHA-256으로 암호화한 값
			 *  	-> key를 기준으로 알파벳 순서대로 정렬하는 것임.
			 *  	-> timestamp는 반드시 signature 생성에 사용한 값을 그대로 timestamp input에 사용하여야 함.
			 *  
			 */
			
			
			// 1. 전문 필드값 설정(가맹점 개발수정)
			String mid = "INIpayTest";  // 가맹점 Id(발급 필요, 현재는 테스트용)
			String mKey = "3a9503069192f207491d4b19bd743fc249a761ed94246c8c42fed06c3cd15a33"; // 테스트용
			String oid = vo.getTemp_id();  // 임시 주문번호
			Long timestamp = System.currentTimeMillis();  // 검증용 시간값
			String sha = "oid="+oid+"&price="+vo.getTotal_price()+"&timestamp="+timestamp;
			String signature = "";
			// SAH 256 을 이용해 signature 데이터 생성(모듈에서 자동으로 signParam을 알파벳순으로 정렬 후 NVP 방식으로 나열해 hash
			try {
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
		
		TempOrderVO tVo = tempOrderService.getTempOrder(temp_id);
		UsersVO loginUser = usersService.getUsers(tVo.getUser_id());
		
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
		 * 결제 후 처리
		 */
		String retUrl = "index";
		
		HttpSession hSession = request.getSession();
		
		try{
			/*
			 *  인증결과 파라미터 일괄 수신
			 */
			request.setCharacterEncoding("UTF-8");
			Map<String,String> paramMap = new Hashtable<String,String>();
			Enumeration elems = request.getParameterNames();
			
			String temp = "";
			
			while(elems.hasMoreElements()) {
				temp = (String) elems.nextElement();
				paramMap.put(temp, request.getParameter(temp));
			}
			
			System.out.println("paramMap : "+ paramMap.toString());
	
			
			// 인증결과가 성공일 경우, 전달받은 인증결과로 아래 승인요청 진행
			
			/*
			 *  인증이 성공일 경우만
			 */
			if("0000".equals(paramMap.get("resultCode"))){
				// 1. 전문 필드 값 설정(*** 가맹점 개발수정 ***)
			
				System.out.println("## 인증데이터 일괄수신 ##");
				System.out.println("<p>"+paramMap.toString()+"</p>");
				
				String mKey = "3a9503069192f207491d4b19bd743fc249a761ed94246c8c42fed06c3cd15a33"; // 테스트용
				String mid = paramMap.get("mid");               // 가맹점 ID 수신 받은 데이터로 설정
				Long timestamp= System.currentTimeMillis();     // util에 의해서 자동생성
				String charset = "UTF-8";                       // 리턴형식[UTF-8,EUC-KR](가맹점 수정후 고정)
				String format = "JSON";                         // 리턴형식[XML,JSON,NVP](가맹점 수정후 고정)
				String authToken= paramMap.get("authToken");    // 취소 요청 tid에 따라서 유동적(가맹점 수정후 고정)
				String authUrl= paramMap.get("authUrl");        // 승인요청 API url(수신 받은 값으로 설정, 임의 세팅 금지)
				String netCancel= paramMap.get("netCancelUrl"); // 망취소 API url(수신 받은 값으로 설정, 임의 세팅 금지)
				
				/*
				 *  2.signature 생성
				 */
				SHA256 sha256 = new SHA256();
				String sha = "authToken="+authToken+"&timestamp="+timestamp;
				String signature = "";
				// SAH 256 을 이용해 signature 데이터 생성(모듈에서 자동으로 signParam을 알파벳순으로 정렬 후 NVP 방식으로 나열해 hash
				try {
					signature = sha256.encrypt(sha);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
				/*
				 *  3.API 요청 전문 생성
				 */
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				
				// BasicNameValuePair의 Key = input태그의 name,
				// BasicNameValuePair의 value = input태그의 value
				param.add(new BasicNameValuePair("mid",mid));
				param.add(new BasicNameValuePair("authToken",authToken));
				param.add(new BasicNameValuePair("signature",signature));
				param.add(new BasicNameValuePair("timestamp",timestamp.toString()));
				param.add(new BasicNameValuePair("charset",charset));
				param.add(new BasicNameValuePair("format",format));
				
				            
				System.out.println("##승인요청 API 요청##");
				
				
				/*
				 *  4.API 통신 시작
				 */
				HttpUtil httpUtil = new HttpUtil();
				String result = httpUtil.sendRequest(authUrl, param);
				
				// result를 map 형태로 변환 parse String to map 할것.
				ObjectMapper mapper = new ObjectMapper();
	
				Map<String, String> resultMap =  new HashMap<String, String>();
				
			    try{
			    	resultMap  = mapper.readValue(result, new TypeReference<Map<String, String>>() {});

	
			    } catch (IOException e){
			        e.printStackTrace();
			    }
				
				 
				//  성공 실패 판단하는 변수를 map.get(키)로 가져와서 성공 실패 판단 resultCode = 0000
				if("0000".equals(resultMap.get("resultCode"))) {
					// 성공일 때, 실결제테이블 insert
					PayVO pVo = new PayVO();
					
					pVo.setTid(resultMap.get("tid"));
					pVo.setTotPrice(resultMap.get("TotPrice"));
					pVo.setUser_id(tVo.getUser_id());
					pVo.setCamp_zone(tVo.getCamp_zone());
					pVo.setIndate(tVo.getIndate());
					
					payService.insertPay(pVo);
					
					campOrderService.insertCampOrder(vo);
					
					retUrl = "camping/successOrder";
				} else {
				    // 인증 실패시
					System.out.println("<br/>");
					System.out.println("####인증실패####");
					System.out.println("<p>"+paramMap.toString()+"</p>");
					
					model.addAttribute("errorMsg", resultMap.get("resultMsg"));
					
					retUrl = "index";
				} 
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		tempOrderService.deleteTempOrder(tVo.getTemp_id());
		
		session.setAttribute("loginUser", loginUser);
		
		return retUrl;
	}
	
	
	/*
	 *  내 예약현황 보기
	 */
	
	@RequestMapping(value = "/my_reservation", method = RequestMethod.GET)
	public String my_reservation(HttpSession session, Criteria criteria, Model model) {
		
		// 로그인한 정보를 객체에 담아 가져온다
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		// 로그인이 유,무 확인후 로그인이 되어있지 않으면 로그인 페이지로 세션 전달
		if (loginUser == null) {
			return "Users/login";
		} else {
			// 예약 목록 10개 조회
			List<CampOrderVO> campOrderList = campOrderService.getMyListWithPaging(criteria, loginUser.getId());
			
			// 화면에 표시할 페이지 버튼정보 생성
			PageMaker pageMaker = new PageMaker();
			int totalCount = campOrderService.countMyOrderList(loginUser.getId());
		
			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 예약현황 목록 갯수 설정 및 페이지 정보 초기화
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date);
		
			model.addAttribute("today", today);
			model.addAttribute("campOrderList", campOrderList);
			model.addAttribute("pageMaker", pageMaker);
			
			return "camping/campOrderList";
		}
	}
	
	// 내 예약목록에서 상세정보 보기
	@GetMapping(value="/myOrder_detail")
	public String myOrderDetailView(CampOrderVO vo, Model model) {
		CampOrderVO myOrderDetail = campOrderService.getMyCampOrder(vo.getOseq());
		
		model.addAttribute("myOrderDetail", myOrderDetail);
		
		return "camping/myOrderDetail";
	}
	
	// 취소내역 조회하기
	@RequestMapping(value = "/my_cancel", method = RequestMethod.GET)
	public String my_cnacel(HttpSession session, Criteria criteria, Model model) {
		// 로그인한 정보를 객체에 담아 가져온다
		UsersVO loginUser = (UsersVO) session.getAttribute("loginUser");
		
		// 로그인이 유,무 확인후 로그인이 되어있지 않으면 로그인 페이지로 세션 전달
		if (loginUser == null) {
			return "Users/login";
		} else {
			// 예약 목록 10개 조회
			List<CampOrderCancelVO> cancelOrderList = campOrderCancelService.getMyListWithPaging(criteria, loginUser.getId());
			
			// 화면에 표시할 페이지 버튼정보 생성
			PageMaker pageMaker = new PageMaker();
			int totalCount = campOrderCancelService.countMyCancelList(loginUser.getId());
			
			pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
			pageMaker.setTotalCount(totalCount); // 전체 예약현황 목록 갯수 설정 및 페이지 정보 초기화
		
			model.addAttribute("cancelOrderList", cancelOrderList);
			model.addAttribute("pageMaker", pageMaker);
			
			return "camping/cancelOrderList";
		}
	}
	
	// 내 취소목록에서 상세정보 보기
	@GetMapping(value="/myCancel_detail")
	public String myCancelDetailView(CampOrderCancelVO vo, Model model) {
		CampOrderCancelVO myCancelDetail = campOrderCancelService.getCancelOrder(vo.getCseq());
		
		model.addAttribute("myCancelDetail", myCancelDetail);
		
		return "camping/myCancelDetail";
	}
	
	// 내 예약 취소하기팝업창 오픈 (취소환불 규정 공지 화면)
	@GetMapping(value="/go_myOrder_cancel")
	public String gotMyorderCancelView(CampOrderVO vo, Model model) {
		
		model.addAttribute("oseq", vo.getOseq());
		
		return "camping/refundPolicy";
	}
	
	// 환불 규정 확인후 버튼클릭하면 취소화면으로 넘어감
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
	
	// 팝업창에서 '취소' 버튼 클릭 시 내 예약취소 실행(취소 테이블로 이동) + 예약테이블에서 삭제
	@RequestMapping(value="/cancel_myOrder", method=RequestMethod.GET)
	public String cancelMyOrderAction(CampOrderCancelVO vo) {
		
		campOrderCancelService.insertCancelMyOrder(vo);
		campOrderService.deleteOrderByOseq(vo.getOseq());
		
		return "camping/campOrderList";
	}
	
	
	/*
	 * 관리자 기능
	 */
	// 예약 현황 조회 페이지로 이동
	@RequestMapping(value="/search_order", method = RequestMethod.GET)
	public String orderList(Model model,HttpSession session, Criteria criteria) {
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
		
		// 예약현황 10개 조회
		List<CampOrderVO> orderList = campOrderService.getAllListWithPaging(criteria);
		
		//화면에 표시할 페이지 버튼 정보 생성
		PageMaker pageMaker = new PageMaker();
		int totalCount = campOrderService.countAllOrderList();
		
		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 예약현황 목록 갯수 설정 및 페이지 정보 초기화
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("admin_name", loginAdmin.getName());
		
		return "admin/campOrder/admin_orderList";
	}
	
	//  예약 현황 조회페이지에서 지점 선택시 지점별 예약현황 리스트 조회
	@RequestMapping(value="/search_orderList", method = RequestMethod.GET)
	public String orderList(@RequestParam(value = "campName") int camp_id, Criteria criteria,
							HttpSession session, Model model) {
			
		String camp_name = campingService.getCampName(camp_id);
		
		// 예약현황 10개 조회
		List<CampOrderVO> orderList = campOrderService.getListWithPaging(criteria, camp_name);
		
		// 화면에 표시할 페이지 버튼 정보 생성
		PageMaker pageMaker = new PageMaker();
		int totalCount = campOrderService.countOrderList(camp_name);
		
		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 예약현황 목록 갯수 설정 및 페이지 정보 초기화
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
	
		model.addAttribute("today", today);
		model.addAttribute("orderList", orderList);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("selected", camp_id);
		
		return "admin/campOrder/admin_orderList";
	}
	
	// 예약 현황 리스트에서 '취소'버튼 클릭 시 사유 입력하는 페이지 오픈
	@GetMapping(value="/insert_cancel_reason")
	public String insertReasonView(@RequestParam(value = "camp_idRe") int camp_id, CampOrderVO vo, Model model) {
		CampOrderVO campOrder = campOrderService.getCampOrder(vo.getOseq());
		
		model.addAttribute("campOrder", campOrder);
		model.addAttribute("selected", camp_id);
		
		return "admin/campOrder/admin_cancelOrder";
	}
	
	// 팝업창에서 '취소' 버튼 클릭 시  결제 취소 실행
	@RequestMapping(value="/go_cancel", method=RequestMethod.GET)
	public String cancelOrder(CampOrderCancelVO vo, Model model, HttpServletRequest request) throws NoSuchAlgorithmException {
		PayVO pVo = new PayVO();
		pVo.setUser_id(vo.getUsersid());
		pVo.setCamp_zone(vo.getCamp_zone());
		pVo.setIndate(vo.getIndate());
		
		PayVO pay = payService.getPay(pVo);
		
		Date now = new Date();
		SimpleDateFormat formmat = new SimpleDateFormat("yyyyMMddHHmmss"); 
		
		
	    //step1. 요청을 위한 파라미터 설정
	    String mid="INIpayTest";
	    String Key="ItEQKi3rY7uvDS8l"; // INIpayTest 의 INIAPI key
	    String type="Refund";          // "Refund" 고정
	    String paymethod="Card";
	    String timestamp = formmat.format(now);  // 검증용 시간값
	    String clientIp="127.0.0.1";
	    String tid=pay.getTid();      // 40byte 승인 TID 입력
	    String msg="거래취소요청";
	    
	    System.out.println(timestamp);
	    System.out.println(tid);
	    
	    //Hash 암호화
	    String data_hash=Key+type+paymethod+timestamp+clientIp+mid+tid;
	    SHA256 sha256 = new SHA256();
	    String hashData = sha256.getSHA512(data_hash); // SHA_512_Util 을 이용하여 hash암호화(해당 LIB는 직접구현 필요)
	    
	    System.out.println(hashData);
	         
	    // 전송 URL
	    String APIURL="https://iniapi.inicis.com/api/v1/refund"; // 전송 URL
	    
	    List<NameValuePair> param = new ArrayList<NameValuePair>();
	    
		// BasicNameValuePair의 Key = input태그의 name,
		// BasicNameValuePair의 value = input태그의 value
		param.add(new BasicNameValuePair("type",type));
		param.add(new BasicNameValuePair("paymethod",paymethod));
		param.add(new BasicNameValuePair("timestamp",timestamp));
		param.add(new BasicNameValuePair("clientIp",clientIp));
		param.add(new BasicNameValuePair("mid",mid));
		param.add(new BasicNameValuePair("tid",tid));
		param.add(new BasicNameValuePair("msg",msg));
		param.add(new BasicNameValuePair("hashData",hashData));
		
		
	     //step2. key=value 로 post 요청

	    HttpUtil httpUtil=new HttpUtil();
	    
	    try{
		     String authResultString = "";
		     authResultString = httpUtil.sendRequest(APIURL , param);
		     System.out.println(authResultString);
		    
		    //step3. 요청 결과
		     System.out.println("<h1>"+authResultString+"</h2>");
		     
	    }catch (Exception ex) {
	    	ex.printStackTrace();
	    }

		campOrderCancelService.insertOrderCancel(vo);
		campOrderService.deleteOrderByOseq(vo.getOseq());
		payService.deletePay(pay.getTid());
	    
	    return "admin/campOrder/admin_orderList";
	}
	
	// 결제 취소 완료된 후 예약취소 실행(취소 테이블로 이동) + 예약테이블에서 삭제
//	@RequestMapping(value="/cancel_order", method=RequestMethod.GET)
//	public String cancelOrderAction(CampOrderCancelVO vo) {
//		campOrderCancelService.insertOrderCancel(vo);
//		campOrderService.deleteOrderByOseq(vo.getOseq());
//		
//		return "admin/admin_orderList";
//	}
	
	// 예약 현황 리스트에서 '예약완료' 버튼 클릭 시 예약 상세내용확인을 위한 팝업창 오픈
	@GetMapping(value="/confirm_order_check")
	public String confirmOrderView(@RequestParam(value = "camp_idRe") int camp_id, CampOrderVO vo, Model model) {
		CampOrderVO campOrder = campOrderService.getCampOrder(vo.getOseq());
		
		model.addAttribute("campOrder", campOrder);
		model.addAttribute("selected", camp_id);
		
		return "admin/campOrder/admin_confirmOrder";
	}
	
	// 예약 상세내용 팝업창에서 '예약확정'버튼 클릭 시 실행
	@RequestMapping(value="/confirm_order", method=RequestMethod.GET)
	public String confirmOrderAction(CampOrderVO vo) {
		campOrderService.updateOrderStatus(vo.getOseq());
		
		return "admin/campOrder/admin_orderList";
	}
	
	// 예약 취소내역 조회 페이지로 이동
	@RequestMapping(value="/search_cancel", method = RequestMethod.GET)
	public String cancelOrderList(Model model) {
		
		return "admin/campOrder/admin_cancelList";
	}
	
	// 예약 취소현황 조회페이지에서 지점 선택시 지점별 취소현황 리스트 조회
	@RequestMapping(value="/search_cancelList", method = RequestMethod.GET)
	public String cancelList(@RequestParam(value = "campName") int camp_id, Criteria criteria,
							HttpSession session, Model model) {
			
		String camp_name = campingService.getCampName(camp_id);
		
		// 예약 취소 현황 10개 조회
		List<CampOrderCancelVO> cancelList = campOrderCancelService.getListWithPaging(criteria, camp_name);
		
		// 화면에 표시할 페이지 버튼 정보 생성
		PageMaker pageMaker = new PageMaker();
		int totalCount = campOrderCancelService.countOrderList(camp_name);
		
		pageMaker.setCriteria(criteria); // 현재 페이지와 페이지당 항목 수 정보 설정
		pageMaker.setTotalCount(totalCount); // 전체 예약현황 목록 갯수 설정 및 페이지 정보 초기화
	
		model.addAttribute("cancelList", cancelList);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("selected", camp_id);
		
		return "admin/campOrder/admin_cancelList";
	}
	
	// 전체 취소내역 리스트에서 '취소하기' 버튼 클릭시 취소 신청이 들어온 예약내역이 담긴 팝업창 오픈
	@GetMapping(value="/confirm_cancel_check")
	public String confirmCancelView(@RequestParam(value = "camp_idRe") int camp_id, CampOrderCancelVO vo, Model model) {
		CampOrderCancelVO cancelOrder = campOrderCancelService.getCancelOrder(vo.getCseq());
		
		model.addAttribute("cancelOrder", cancelOrder);
		model.addAttribute("selected", camp_id);
		
		return "admin/campOrder/admin_confirmCancel";
	}
	
	// 취소 확정하는 팝업창에서 '취소하기'버튼 클릭 시 취소 확정 완료
	@RequestMapping(value="/confirm_cancel_order", method=RequestMethod.GET)
	public String confirmCancelOrderAction(CampOrderCancelVO vo, HttpServletRequest request) throws NoSuchAlgorithmException {
		PayVO pVo = new PayVO();
		pVo.setUser_id(vo.getUsersid());
		pVo.setCamp_zone(vo.getCamp_zone());
		pVo.setIndate(vo.getIndate());
		
		PayVO pay = payService.getPay(pVo);
		
		Date now = new Date();
		SimpleDateFormat formmat = new SimpleDateFormat("yyyyMMddHHmmss"); 
		
		
	    //step1. 요청을 위한 파라미터 설정
	    String mid="INIpayTest";
	    String Key="ItEQKi3rY7uvDS8l"; // INIpayTest 의 INIAPI key
	    String type="Refund";          // "Refund" 고정
	    String paymethod="Card";
	    String timestamp = formmat.format(now);  // 검증용 시간값
	    String clientIp="127.0.0.1";
	    String tid=pay.getTid();      // 40byte 승인 TID 입력
	    String msg="거래취소요청";
	    
	    System.out.println(timestamp);
	    System.out.println(tid);
	    
	    //Hash 암호화
	    String data_hash=Key+type+paymethod+timestamp+clientIp+mid+tid;
	    SHA256 sha256 = new SHA256();
	    String hashData = sha256.getSHA512(data_hash); // SHA_512_Util 을 이용하여 hash암호화(해당 LIB는 직접구현 필요)
	    
	    System.out.println(hashData);
	         
	    // 전송 URL
	    String APIURL="https://iniapi.inicis.com/api/v1/refund"; // 전송 URL
	    
	    List<NameValuePair> param = new ArrayList<NameValuePair>();
	    
		// BasicNameValuePair의 Key = input태그의 name,
		// BasicNameValuePair의 value = input태그의 value
		param.add(new BasicNameValuePair("type",type));
		param.add(new BasicNameValuePair("paymethod",paymethod));
		param.add(new BasicNameValuePair("timestamp",timestamp));
		param.add(new BasicNameValuePair("clientIp",clientIp));
		param.add(new BasicNameValuePair("mid",mid));
		param.add(new BasicNameValuePair("tid",tid));
		param.add(new BasicNameValuePair("msg",msg));
		param.add(new BasicNameValuePair("hashData",hashData));
		
		
	     //step2. key=value 로 post 요청

	    HttpUtil httpUtil=new HttpUtil();
	    
	    try{
		     String authResultString = "";
		     authResultString = httpUtil.sendRequest(APIURL , param);
		     System.out.println(authResultString);
		    
		    //step3. 요청 결과
		     System.out.println("<h1>"+authResultString+"</h2>");
		     
	    }catch (Exception ex) {
	    	ex.printStackTrace();
	    }

		campOrderCancelService.updateCancelStatus(vo.getCseq());
		payService.deletePay(pay.getTid());
		
		return "admin/campOrder/admin_cancelList";
	}
	
	// 취소내용 상세보기
	@GetMapping(value="/cancel_detail")
	public String cancelDetailView(CampOrderCancelVO vo, Model model) {
		CampOrderCancelVO cancelDetail = campOrderCancelService.getCancelOrder(vo.getCseq());
		
		model.addAttribute("cancelDetail", cancelDetail);
		
		return "admin/campOrder/admin_cancelDetail";
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("지점을 선택하세요", "0");
		conditionMap.put("캠핑족장-강원도지점", "1");
		conditionMap.put("캠핑족장-경기도지점", "2");
		conditionMap.put("캠핑족장-충청도지점", "3");
		conditionMap.put("캠핑족장-경상도지점", "4");
		conditionMap.put("캠핑족장-전라도지점", "5");
		conditionMap.put("캠핑족장-제주도지점", "6");

		return conditionMap;
	}
	
	@ModelAttribute("searchMap")
	public Map<String, String> searchMap() {
		Map<String, String> searchMap = new LinkedHashMap<>();
		
		searchMap.put("체크인", "INDATE");
		searchMap.put("회원ID", "USER_ID");
		searchMap.put("예약자명", "ORDER_NAME");
		
		return searchMap;
	}
}