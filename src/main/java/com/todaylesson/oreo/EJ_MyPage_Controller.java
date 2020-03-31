package com.todaylesson.oreo;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todaylesson.DTO.CartDTO;
import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.DTO.MyLikeDTO;
import com.todaylesson.DTO.OrderDetailDTO;
import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.service.EJ_All_Product_Service;

@RequestMapping("/todaylessonmypage/")
@Controller
public class EJ_MyPage_Controller {
	
	@Resource(name="service")
	private EJ_All_Product_Service service;
	
	@RequestMapping("/mylike/{member_id}")
	public String mylike(@PathVariable(value="member_id") String member_id,Model model)
	{
		List<MyLikeDTO> likedto=service.selectMyLike(member_id);
		model.addAttribute("list",likedto);
		return "TodayLesson_UserPage/ej_us_mylike.us_my_section";
	}

	@RequestMapping("/mycart/{member_id}")
	public String mycart(@PathVariable(value="member_id") String member_id,Model model)
	{
		List<CartDTO> cartdto=service.selectMyCart(member_id);
		model.addAttribute("list",cartdto);
		model.addAttribute("memberid",member_id);
		MemberDTO mdto = service.selectm(member_id);
		model.addAttribute("mdto",mdto);
		
		return "TodayLesson_UserPage/ej_us_mycart.us_my_section";
	}
	

	
	@RequestMapping("/order_cart")
	public String  order_cart(OrderListDTO oldto
			
			, @RequestParam(value="addrselect") int addrselect
			,@RequestParam(value="roadaddr",required=false) String roadaddr
			,@RequestParam(value="jibunaddr",required=false) String jibunaddr
			, @RequestParam(value="detailaddr",required=false) String detailaddr
			
			,@RequestParam(value="orderlist_cost", required=false) int orderlist_cost
			,@RequestParam(value="orderlist_usepoint", required=false) int orderlist_usepoint
			,@RequestParam(value="remainpoint", required=false) int remainpoint
			
			,@RequestParam(value="member_id") String member_id
			, Model model)
	{
		
		System.out.println("addrselect"+addrselect);
		 
	if(addrselect>0)
		{
		 String fulladdr= "";	
		 if(addrselect==1) 
				{fulladdr=roadaddr;}
				else if(addrselect==2)
				{fulladdr=jibunaddr;}
		 oldto.setOrderlist_addr(fulladdr+" "+detailaddr);
		}//주소
	
	
	MemberDTO memberdto=new MemberDTO();
		int updatedpoint=(int) (remainpoint+(0.1*orderlist_cost));
		//remainpoint는 포인트 사용후 남은 금액
		//남은 금액에 결제금액의 10%를 적립해서 update해줌
		memberdto.setMember_id(member_id);
		memberdto.setMember_point(updatedpoint);
		service.updatepoint(memberdto);
	
	
	//주문번호 생성
		 Calendar cal = Calendar.getInstance();
		 int year1 = cal.get(Calendar.YEAR);
		 String year2=Integer.toString(year1);
		 String year=year2.substring(2, 4);
		 
		 System.out.println("두자릿수년도:"+year);
		 String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		 System.out.println();
		 System.out.println(ym);
		 String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		 String subNum = "";
		 
		 for(int i = 1; i <= 4; i ++) {
		  subNum += (int)(Math.random() * 10);
		 }
		 
		 String orderId =ymd+subNum;
		 System.out.println("오더아이디:"+orderId);
		 System.out.println("데이터타입확인:"+orderId instanceof String);
		int orderlist_no=Integer.parseInt(orderId);
			System.out.println("orderlist_NO:"+orderlist_no);
		oldto.setOrderlist_no(orderlist_no); //주문번호지정
		service.insertorderlist(oldto);//주문정보 등록
		
		
		OrderDetailDTO oddto=new OrderDetailDTO();//주문디테일 객체 생성
		List<CartDTO> cartdto=service.selectMyCart(member_id);
		//cart테이블에 잇는거 다 받아오기
		
	
		for(CartDTO cart: cartdto)
		{
		service.insertorder_cart(cart, orderlist_no);
		//받아온 리스트를 order_detail테이블에 insert하기
		}
		
		
		service.delet_all_cart(member_id);//카트에 담겨 있는것 다 delete
	
		OrderListDTO orderlistdto=service.selectorderlist(orderlist_no);//오더정보 받아오기
		List<OrderDetailDTO> list=service.selectorderdetail(orderlist_no);//오더 디테일 정보 받아오기
		model.addAttribute("list",list);
		model.addAttribute("orderlistdto",orderlistdto);
		
		return "TodayLesson_UserPage/ej_us_ordercart.us_my_section";
	}
	
	

}
