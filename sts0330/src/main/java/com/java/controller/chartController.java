package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.ChartService;
import com.java.vo.ChartVo;

@Controller
public class chartController {
	
	@Autowired
	ChartService chartservice;
	
	
	@GetMapping("/chart")
	public String chart() {
		return "chart";
	}
	
	@GetMapping("/chartData")
	@ResponseBody // ajax 으로 받을때 무조건
	public Map<String , Object> chartData(@RequestParam String period) {
		System.out.println(""+period);
		Map<String , Object> map =new HashMap<>();
		String [] memberLabel= null; //최기화
		int [] memberData=null;//최기화
		//DB 에 접근을 해서 가져옴 memberLabel, memberData
		List<ChartVo> list= chartservice.SelectAll();
		
		if(list !=null) {
			 memberLabel = new String [list.size()];
			 memberData = new int [list.size()];
			 
			for(int i=0; i<list.size(); i++) {
				memberLabel[i] = list.get(i).getMemberlabel();
				System.out.println("awdawdawd:"+list.get(i).getMemberlabel());
			
				memberData[i] = list.get(i).getMemberData();
			}
		}
		
		String memberTitle="주별 회원가입";
		map.put("memberTitle", memberTitle); //타이틀
		map.put("memberLabel", memberLabel); //가로축 이름들 
		map.put("memberData", memberData); // 세로축 데이터 값
		
		return map;
	}
	
	
	
}
