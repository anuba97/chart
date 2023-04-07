package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.mapper.ChartMapper;
import com.java.vo.ChartVo;


@Service
public class chartServiceImpl implements ChartService {
	
	@Autowired
	ChartMapper chartmapper;
	
	@Override // 차트 데이터 전체 가져오기
	public List<ChartVo> SelectAll() {
		List<ChartVo> list = chartmapper.SelectAll();
		return list;
	}

	


}
