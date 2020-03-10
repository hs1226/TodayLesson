package com.todaylesson.oreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todaylesson.DTO.SeniorDTO;
import com.todaylesson.service.JY_US_SeniorService;

@Controller
public class JY_US_Senior_Request_Controller {

	@Resource(name="senior_service")
	private JY_US_SeniorService seniorservice;
	
	//  ������Ʈ�� ��ū �� �޾ƿ���
	public String getToken(HttpServletRequest request,HttpServletResponse response,JSONObject json,String requestURL) throws Exception{
		String _token = "";

		try{

			String requestString = "";
			URL url = new URL(requestURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); 				
			connection.setInstanceFollowRedirects(false);  
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStream os= connection.getOutputStream();
			os.write(json.toString().getBytes());
			connection.connect();
			StringBuilder sb = new StringBuilder(); 
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line = null;  
				while ((line = br.readLine()) != null) { 
					sb.append(line + "\n");  
				}
				br.close();
				requestString = sb.toString();
			}
			os.flush();
			connection.disconnect();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(requestString);

			if((Long)jsonObj.get("code")  == 0){
				JSONObject getToken = (JSONObject) jsonObj.get("response");
				System.out.println("getToken==>>"+getToken.get("access_token") );
				_token = (String)getToken.get("access_token");
			}
 
		}catch(Exception e){
			e.printStackTrace();
			_token = "";
		}
		return _token;
	}
	
	
	
	// �ôϾ� ���� ��ư
	@RequestMapping("senior_request")
	public String senior_Request_PopUp() {
		return "TodayLesson_UserPage/jy_us_senior_request";
	}
	
	// �ôϾ� ���� ��ư �˾� > �� ������ �ôϾ�� ��ȯ��
	@RequestMapping("senior_request_form/{member_id}")
	public String senior_Request_Button(@PathVariable String member_id, Model model) {
		int mem_level=seniorservice.check_senior(member_id);
		
		if (mem_level == 1) {
			// �ôϾ�� ��������
			seniorservice.new_senior(member_id);
			model.addAttribute("mem_level",mem_level);

		} else {
			model.addAttribute("mem_level",mem_level);
		}


		return "TodayLesson_UserPage/jy_us_senior_form";

	}
	
	// �ôϾ� ���� ��ư �˾����� �� ������ ������ ��
	@RequestMapping("senior_switch/{member_id}")
	public String senior_Switch(Model model,@PathVariable String member_id, HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String imp_key 		=	"5422837446408379";
		String imp_secret	=	"FhzhNcakGqAxLiWaXndMLWKpsouBVOQB5pTTC3eitOPe6Mp39CPVyAl1YPCUEtwJTpDvsSOWGEaNqzQz";

		JSONObject json = new JSONObject();
		json.put("imp_key", imp_key);
		json.put("imp_secret", imp_secret);
	
		String token = getToken(request, response, json, "https://api.iamport.kr/users/getToken"); 
		model.addAttribute("token",token);
		
		return "TodayLesson_UserPage/jy_us_senior_switch";
	}
	
	// ����� �̹� �ôϾ� �Դϴ�.
	@RequestMapping("you_are_senior")
	public String you_are_senior() {
		return "TodayLesson_SeniorPage/jy_sn_you_are_senior";
	}
	
	// ���� ���� �Է��ϸ� �ôϾ� ���� ������Ʈ
	@RequestMapping("plus_senior")
	public String plus_senior(SeniorDTO dto, Model model) {
		
		
		int result = seniorservice.plus_senior(dto);
		model.addAttribute("result",result);
		
		return "TodayLesson_UserPage/jy_us_insertresult";
	}
	
	
}