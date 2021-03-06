package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.NoticeDTO;
import com.todaylesson.DTO.SQLjoin_Member_FreeBoardDTO;

@Mapper
public interface Admin_YI_FreeBoard_Mapper {

	int getCount(HashMap<String, Object> hm);

	List<SQLjoin_Member_FreeBoardDTO> list(HashMap<String, Object> hm);

	List<NoticeDTO> notice();

	int freeboard_replycount(int hidden_freeboard_no);

	int notice_insert(NoticeDTO dto);

	List<SQLjoin_Member_FreeBoardDTO> boardreply_list(int freeboard_no);

	void delete_reply(int boardreply_no);

	int insert_reply(SQLjoin_Member_FreeBoardDTO dto);

	int freeboard_delete(int freeboard_no);

	NoticeDTO notice_modifyPlacehold(int notice_no);
	
	int notice_modify(NoticeDTO dto);

	int notice_delete(int notice_no);





}
