package com.todaylesson.service;

import java.util.List;

import com.todaylesson.DTO.OrderListDTO;

public interface Admin_HS_Statsales_Service {

	//�������(�Ϻ�, �ֺ�, ����, �⺰)
	public List<OrderListDTO> StatSalesAllChart(String ymd);

	//AmChart
	public List<OrderListDTO> chartOutput();

}