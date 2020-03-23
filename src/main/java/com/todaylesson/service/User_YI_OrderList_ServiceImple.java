package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.todaylesson.DTO.SQLjoin_OrderList_Order_detail_MemberDTO;
import com.todaylesson.Mapper.User_YI_OrderList_Mapper;

@Service(value="user_YI_OrderList_Service")
public class User_YI_OrderList_ServiceImple implements User_YI_OrderList_Service{


	@Resource(name="user_YI_OrderList_Mapper")
	private User_YI_OrderList_Mapper mapper;

	@Override
	public int totalCount(String start_date, String end_date) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("start_date", start_date);
		hm.put("end_date", end_date);
		return mapper.getCount(hm);
	}

	@Override
	public List<SQLjoin_OrderList_Order_detail_MemberDTO> orderlist(String start_date, String end_date, int startRow,
			int endRow) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("start_date", start_date);
		hm.put("end_date", end_date);
		hm.put("startrow", startRow);
		hm.put("endrow", endRow);
		return mapper.list(hm);
	}

	@Override
	public List<SQLjoin_OrderList_Order_detail_MemberDTO> orderdetail() {
		// TODO Auto-generated method stub
		return mapper.detailList();
	}
}
