package com.camping.view;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	 *  예약가능한 캠핑장 구역 리스트 조회
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
		
		model.addAttribute("campingList", campingList);
		model.addAttribute("indate", indate);
		model.addAttribute("outdate", outdate);
		model.addAttribute("selected", search_camp_name);
		
		return "camping/campingList";
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<>();

		conditionMap.put("지점을 선택하세요", "0");
		conditionMap.put("캠핑족장-강원도지점", "1");

		return conditionMap;
	}
	
	/*
	 * 예약페이지로 이동
	 */
	@PostMapping(value="/order_insert_form")//, method=RequestMethod.POST)
	public String campingOrderView(HttpSession session) {
		UsersVO loginUser =(UsersVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {   
			return "Users/login";
		} else { 
			return "camping/campOrder";
		}
	}
	
//	@RequestMapping(value="order_insert", method=RequestMethod.POST)
//	public String campingOrderAction(@RequestParam(value="indate") String indate,Model model) {
//		  
//		String day = "";
//		String inputDate= indate;
//		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
//		try {
//			Date date = df.parse(inputDate);
//			
//			Calendar cal = Calendar.getInstance();
//		    cal.setTime(date);
//		    
//		    int dayNum = cal.get(Calendar.DAY_OF_WEEK);
//	        
//		       switch(dayNum){
//		       case 1:
//		           day = "일";
//		           break;
//		       case 2:
//		           day = "월";
//		           break;
//		       case 3:
//		           day = "화";
//		           break;
//		       case 4:
//		           day = "수";
//		           break;
//		       case 5:
//		           day = "목";
//		           break;
//		       case 6:
//		           day = "금";
//		           break;
//		       case 7:
//		           day = "토";
//		           break;
//		       }
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		model.addAttribute("day", day);
//		
//		return "";
//	}
}


