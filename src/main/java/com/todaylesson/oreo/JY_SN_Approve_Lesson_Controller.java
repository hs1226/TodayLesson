package com.todaylesson.oreo;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todaylesson.DTO.LessonDTO;
import com.todaylesson.DTO.LessonDetailDTO;
import com.todaylesson.service.JY_SN_Approve_LessonService;

@Controller
public class JY_SN_Approve_Lesson_Controller {

	@Autowired
	private JY_SN_Approve_LessonService approve_service;
	
	@RequestMapping("my_approve_lesson/{member_id}")
	public String approve_list(@PathVariable String member_id,Model model) {
		
		List<LessonDTO> list = approve_service.apl_list(member_id);
		
		model.addAttribute("list",list);
		
		return"TodayLesson_SeniorPage/jy_sn_approve_list";
	}
	
	
	@RequestMapping("approve_lesson_detail/{lesson_no}")
	public String approve_lesson_detail(@PathVariable int lesson_no, Model model) {
		
		LessonDTO dto = approve_service.apl_detail(lesson_no);
		
		model.addAttribute("dto",dto);
		
		return "TodayLesson_SeniorPage/jy_sn_approve_detail";
	}
	
	@RequestMapping("my_approve_lesson_upload/{lesson_no}")
	public String approve_lesson_upload(@PathVariable int lesson_no, Model model) {	

		LessonDTO dto = approve_service.apl_detail(lesson_no);
		
		model.addAttribute("dto",dto);

		return "TodayLesson_SeniorPage/jy_sn_approve_lesson_upload";
	}
	
	@RequestMapping("mal_lesson_upload_result")
	public String approve_lesson_upload(LessonDetailDTO dto, Model model) {	

		int result = approve_service.apl_upload(dto);
		
		int lesson_no = dto.getLesson_no();
		
		model.addAttribute("result",result);
		model.addAttribute("lesson_no",lesson_no);
		
		return "TodayLesson_SeniorPage/jy_sn_mal_lesson_upload_result";
	}
	
	
	@RequestMapping("facebook_login")
	public String facebook_login() {	
		
		return "facebook_login";
	}
	
	
	
	
}