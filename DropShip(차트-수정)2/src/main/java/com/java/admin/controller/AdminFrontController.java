package com.java.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.admin.service.AdminOrderService;
import com.java.admin.service.AdminService;
import com.java.admin.service.BoardEventService;
import com.java.admin.service.BoardNoticeService;
import com.java.admin.service.DropshipMemberService;
import com.java.home.service.BoardService;
import com.java.home.service.MemberService;

import com.java.vo.Count_Order_Price_By_MonthVo;
import com.java.vo.MemberCountDayVo;
import com.java.vo.Order_DetailVo;

@Controller
public class AdminFrontController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	MemberService memberSerivce;
	
	@Autowired
	BoardNoticeService boardNoticeService;
	
	@Autowired
	AdminOrderService adminOrderService;
	
	@Autowired
	BoardEventService boardEventService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	DropshipMemberService dropshipMemberService;
	
	@Autowired
	Order_DetailVo order_DetailVo;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("admin_login")//로그인 페이지 열기
	public String admin_login(Model model) {
		return "admin/admin_login";
	}//admin_login
	
	@RequestMapping("admin_index")//어드민 메인 페이지 열기
	public String admin_index(@RequestParam(defaultValue = "1") int page, Model model) {
		Map<String, Object> map = dropshipMemberService.indexMemberList(page);
		Map<String, Object> order = adminOrderService.selectOrderList(page);
		model.addAttribute("map", map);
		model.addAttribute("order", order);
		model.addAttribute("page", page);
		return "admin/admin_index";
	}
	
	@GetMapping("admin_tables")
	public String admin_tables(Model model) {
		return "admin/admin_tables";
	}
	
	@GetMapping("admin_orderList")//어드민 주문 게시판 리스트 열기
	public String admin_orderList(@RequestParam(defaultValue = "1") int page, Model model) {
		Map<String, Object> map = adminOrderService.selectOrderList(page);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		return "admin/admin_orderList";
	}
	
	@GetMapping("admin_printingList")
	public String admin_printingList(Model model) {
		return "admin/admin_printingList";
	}
	
	@GetMapping("admin_noticeBoardList")//어드민 공지 게시판 리스트 열기
	public String admin_noticeBoardList(@RequestParam(defaultValue = "1") int page, Model model) {
		Map<String, Object> map = boardNoticeService.selectNoticeList(page);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		return "admin/admin_noticeBoardList";
	}
	
	@GetMapping("admin_eventBoardList") //어드민 이벤트 리스트 열기
	public String admin_eventBoardList(@RequestParam(defaultValue = "1") int page, Model model) {
		Map<String, Object> map = boardEventService.selectEventList(page);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		return "admin/admin_eventBoardList";
	}
	
	@GetMapping("admin_freeBoardList") //어드민 일반 게시판 페이지 열기
	public String admin_freeBoardList(@RequestParam(defaultValue = "1") int page, Model model) {
		Map<String, Object> map = boardService.selectBoardAll(page);
		model.addAttribute("map", map);
		return "admin/admin_freeBoardList";
	}
	
	@GetMapping("/adminchartData")
	@ResponseBody // ajax 으로 받을때 무조건
	public Map<String, Object> chartData(@RequestParam String period) {
		System.out.println("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ"+period);
		Map<String , Object> memberCountDayMap =new HashMap<>();
		String [] memberLabel= null; //최기화
		int [] memberData=null;//최기화
		//DB 에 접근을 해서 가져옴 memberLabel, memberData
		List<MemberCountDayVo> list= dropshipMemberService.selectMember_Reg_Date();
		System.out.println("결과값 :  "+ list);
		
		
		List<String> monthDayDateList = new ArrayList<>();
		for(MemberCountDayVo memberCountDayVo : list) {
			String monthDayDate = Integer.toString(memberCountDayVo.getMonth())+"월"+Integer.toString(memberCountDayVo.getDay())+"일";
			monthDayDateList.add(monthDayDate);	// ["3월1일", "3월2일", "3월3일"]
		}
		
		List<Integer> monthDayDateMemberCountList = new ArrayList<>();
		for(MemberCountDayVo memberCountDayVo : list) {
			monthDayDateMemberCountList.add(memberCountDayVo.getMember_count());	// [22, 3, 12,....]
		}
		
		memberCountDayMap.put("dateList", monthDayDateList);
		memberCountDayMap.put("memberCountList", monthDayDateMemberCountList);
		
//		String memberTitle="월별 회원가입";
//		int memberData=1;
//		String memberLabel ="4월";
		
		return memberCountDayMap;
	}
	
	@GetMapping("/adminchartData3")
	@ResponseBody // ajax 으로 받을때 무조건
	public Map<String, Object> chartData3(@RequestParam String period,@RequestParam String period2) {
		Map<String , Object> memberCountDayMap =new HashMap<>();
		String [] memberLabel= null; //최기화
		int [] memberData=null;//최기화
		
		
		chartData(period);
		
		return memberCountDayMap;
	}
	
	
	
//	@GetMapping("/adminchartData2")
//	@ResponseBody // ajax 으로 받을때 무조건
//	public Map<String , Object> chartData2(@RequestParam String period2) {
//		System.out.println(""+period2);
//		Map<String , Object> map =new HashMap<>();
//		int [] orderMonth= null; //최기화
//		int [] totalPrice=null;//최기화
//		//DB 에 접근을 해서 가져옴 memberLabel, memberData
//		List<Count_Order_Price_By_MonthVo> list= dropshipMemberService.selectOrderTotalByMonth();
//		
//		if(list !=null) {
//			orderMonth = new int [list.size()];
//			totalPrice = new int [list.size()];
//			 
//			for(int i=0; i<list.size(); i++) {
//				orderMonth[i] = list.get(i).getOrder_Month();
//				totalPrice[i] = list.get(i).getTotal_Price();
//			}
//		}
//		
//		map.put("orderMonth", orderMonth); //가로축 이름들 
//		map.put("totalPrice", totalPrice); // 세로축 데이터 값
//		
//		return map;
//	}
//	
	
	

	
}
