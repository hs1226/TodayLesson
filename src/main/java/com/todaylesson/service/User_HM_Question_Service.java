
package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;

import com.todaylesson.DTO.Question_1_1DTO;

public interface User_HM_Question_Service {

	public List<Question_1_1DTO> hm_question_list(String member_id, int startrow, int endrow);


	public void hm_question_insert(HashMap<String, Object> map);


	public Question_1_1DTO hm_question_detail(int question_no);


	public int questiondelete(int question_no);


	public int totalCount(String member_id);


	public List<Question_1_1DTO> hm_question_list(String member_id);

}