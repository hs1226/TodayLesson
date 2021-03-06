package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.LessonDTO;
import com.todaylesson.DTO.SeniorDTO;


@Mapper
public interface JY_SN_LessonMapper {

	public int insert_lesson(LessonDTO dto);

	
	public List<LessonDTO> list_lesson(HashMap<String, Object> hm);


	public int select_senior_no(String member_id);


	public LessonDTO detail_lesson(int lesson_no);


	public int update_lesson(LessonDTO dto);


	public int delete_lesson(int lesson_no);


	public List<LessonDTO> reject_lesson_list(int senior_no);


	public SeniorDTO select_senior_info(int senior_no);


	public int totalCount(HashMap<String, Object> hm);


	public String select_senior_nick(int senior_no);

}
