package com.java.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.admin.service.AdminOrderService;
import com.java.vo.Order_DetailVo;

@Controller
public class AdminOrderController {

	@Autowired
	AdminOrderService adminOrderService;
	
	@Autowired
	Order_DetailVo order_DetailVo;

	@GetMapping("admin_orderView")//어드민 주문 현황 보기
	public String admin_orderView(String id, @RequestParam(defaultValue = "1") int page, Model model) {
		order_DetailVo = adminOrderService.adminSelectOrderOne(Integer.parseInt(id));
		model.addAttribute("order_DetailVo", order_DetailVo);
		model.addAttribute("page", page);
		return "admin/admin_orderView";
	}
	
	@RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
	@ResponseBody
	public int updateOrderStatus(@RequestParam("id") int id, @RequestParam("order_status") int orderStatus) {
		int success = adminOrderService.updateOrderStatus(id, orderStatus);
	    return success;
	}

	
	
}//AdminBoardController
