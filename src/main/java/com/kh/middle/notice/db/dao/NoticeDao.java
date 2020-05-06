package com.kh.middle.notice.db.dao;

import java.util.List;

<<<<<<< HEAD
import com.kh.middle.notice.vo.Notice;
import com.kh.middle.notice.vo.PageDefault;

public interface NoticeDao { 
	

	public List<Notice> select_notice_paging(PageDefault pageDefault) throws Exception;
	
	public int select_board_total_count() throws Exception;
	
	
	public void insert_notice(Notice notice) throws Exception;
	
	public Notice select_notice_detail(int notice_num) throws Exception;
	
	public void update_notice_count(int notice_num) throws Exception;

=======
import com.kh.middle.notice.vo.Comment;
import com.kh.middle.notice.vo.Notice;
import com.kh.middle.notice.vo.PageDefault;

public interface NoticeDao { 

	public List<Notice> select_notice_paging(PageDefault pageDefault) throws Exception;
	
	public int select_board_total_count() throws Exception;
	
	public void insert_notice(Notice notice) throws Exception;
	
	public Notice select_notice_detail(int notice_num) throws Exception;
	
	public void update_notice_count(int notice_num) throws Exception;

	public void update_modify_notice(Notice notice) throws Exception;
	
	public void update_recommend(Notice notice) throws Exception;
	
	public void insert_notice_comment(Comment comment) throws Exception;
	
	public List<Comment> select_notice_comment(int notice_num) throws Exception;
	
	public void delete_notice(int notice_num) throws Exception;
	
	public void delete_notice_comment(int comment_num) throws Exception;
	
	public int update_notice_recommend(int notice_num) throws Exception;
	
	public void update_notice_comment_modify(Comment comment) throws Exception;
	
	public int select_notice_comment_cnt(int notice_num) throws Exception;
	
>>>>>>> branch 'master' of https://github.com/rndso15/kh_middle.git
}
