package com.kh.middle.notice.db.service;

import java.util.List;
import java.util.Map;

import com.kh.middle.notice.vo.Notice;
import com.kh.middle.notice.vo.PageDefault;
import com.kh.middle.notice.vo.Paging;

public interface NoticeService { 
	
	public List<Notice> select_notice_paging(PageDefault pageDefault) throws Exception;
	
	public int select_board_total_count() throws Exception;

	public void insert_notice(Notice notice) throws Exception;
	
	public Notice select_notice_detail(int notice_num) throws Exception;
	
	public void update_notice_count(int notice_num) throws Exception;
}
