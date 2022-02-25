package com.camping.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.camping.biz.camping.CampingService;
import com.camping.biz.camporder.CampOrderService;
import com.camping.biz.dto.CampOrderVO;
import com.camping.biz.dto.CampingVO;
import com.camping.biz.dto.UsersVO;
import com.camping.biz.users.UsersService;

@Controller
public class CampingController {
	
	@Autowired
	private CampingService campingService;
	@Autowired
	private CampOrderService campOrderService;
	@Autowired
	private UsersService usersService;
	
	/*
	 *  ���డ���� ķ���� ���� ����Ʈ ��ȸ
	 */
	@RequestMapping(value="/camp_list", method=RequestMethod.GET)
	public String campingListView() {
		return "camping/campingList";
	}
	
	@RequestMapping(value="/camp_search", method=RequestMethod.GET)
	public String campingList(@RequestParam(value="checkin_date") String checkin_date,
								@RequestParam(value="checkout_date") String checkout_date, 
								@RequestParam(value = "search_camp_name") int camp_id, Model model) {
		String indate = checkin_date;
		String outdate = checkout_date;
		int search_camp_name = camp_id;
		List<CampingVO> campingList = campingService.campingList(camp_id);
		
		List<CampOrderVO> orderList = campOrderService.getCampOrderList(indate);
		
		model.addAttribute("campingList", campingList);
		model.addAttribute("indate", indate);
		model.addAttribute("outdate", outdate);
		model.addAttribute("selected", search_camp_name);
		model.addAttribute("order", orderList);
		
		return "camping/campingList";
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		
		//camping_search.jsp���� �ݺ������� �����Ǿ� �־
		//conditionMap.put ���� �޴��� ���� ������ �� ���� 
		conditionMap.put("������ �����ϼ���", "0");
		conditionMap.put("ķ������-����������", "1");

		return conditionMap;
	}
	
	/*
	 * ������������ �̵�
	 */
	@RequestMapping(value="/order_insert_form", method=RequestMethod.POST)
	public String campingOrderView(String camp_zone, String indate, String outdate, Model model, HttpSession session) throws ParseException {
		UsersVO loginUser =(UsersVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {   
			return "Users/login";
		} else { 
			
			UsersVO uVo = usersService.getUsers(loginUser.getId());
			
			CampingVO camp = new CampingVO();
			camp = campingService.getCamping(camp_zone);
			
			String day = "";
			String inputDate= indate;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(inputDate);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			    
			int dayNum = cal.get(Calendar.DAY_OF_WEEK);
		        
		    switch(dayNum){
			    case 1:
			    	day = "��";
			        break;
			    case 2:
			        day = "��";
			        break;
			    case 3:
			        day = "ȭ";
			        break;
			    case 4:
			        day = "��";
			        break;
			    case 5:
			        day = "��";
			        break;
		 	    case 6:
			        day = "��";
			        break;
			    case 7:
			        day = "��";
			        break;
		    }
			
			model.addAttribute("camp", camp);
			model.addAttribute("users", uVo);
			model.addAttribute("indate", indate);
			model.addAttribute("outdate", outdate);
			model.addAttribute("day", day);
			
			return "camping/campOrder";
		}
	}
	
	@RequestMapping(value="/order_insert", method=RequestMethod.POST)
	public String campingOrderAction(String in, String out, CampOrderVO vo, HttpSession session) {
		UsersVO loginUser =(UsersVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {   
			return "Users/login";
		} else { 
			
			vo.setUser_id(loginUser.getId());
			
			campOrderService.insertCampOrder(vo);
			
			return "index";
		}
	}
}


