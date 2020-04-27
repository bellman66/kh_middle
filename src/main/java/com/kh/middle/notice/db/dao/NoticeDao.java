package com.kh.middle.notice.db.dao;

import java.util.List;
import java.util.Map;

import com.kh.middle.notice.vo.Notice;

public interface NoticeDao {
	
	public List<Notice> select_notice_list();
	
	public void insert_notice(Notice notice);

}
