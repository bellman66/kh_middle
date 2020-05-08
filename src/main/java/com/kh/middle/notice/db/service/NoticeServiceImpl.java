package com.kh.middle.notice.db.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.middle.notice.db.dao.NoticeDao;
import com.kh.middle.notice.vo.Comment;
import com.kh.middle.notice.vo.Notice;
import com.kh.middle.notice.vo.PageDefault;
import com.kh.middle.notice.vo.Paging;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
 
	@Autowired
	NoticeDao noticeDao;

	@Override
	public List<Notice> select_notice_paging(PageDefault pageDefault) throws Exception {
		return noticeDao.select_notice_paging(pageDefault);
	}
	
	@Override
	public void insert_notice(Notice notice) throws Exception {
		noticeDao.insert_notice(notice);
	}

	@Override
	public Notice select_notice_detail(int notice_num) throws Exception {
		return noticeDao.select_notice_detail(notice_num);
	}

	@Override
	public void update_notice_count(int notice_num) throws Exception {
		noticeDao.update_notice_count(notice_num);
	}

	@Override
	public int select_board_total_count() throws Exception {
		return noticeDao.select_board_total_count();
	}

	@Override
	public void update_modify_notice(Notice notice) throws Exception {
		noticeDao.update_modify_notice(notice);
	}

	@Override
	public void update_recommend(Notice notice) throws Exception {
		noticeDao.update_recommend(notice);
	}

	@Override
	public List<Comment> select_notice_comment(int notice_num) throws Exception {
		return noticeDao.select_notice_comment(notice_num);
	}

	@Override
	public void insert_notice_comment(Comment comment) throws Exception {
		noticeDao.insert_notice_comment(comment);
	}

	@Override
	public void delete_notice(int notice_num) throws Exception {
		noticeDao.delete_notice(notice_num);
	}

	@Override
	public void delete_notice_comment(int comment_num) throws Exception {
		noticeDao.delete_notice_comment(comment_num);
	}

	@Override
	public int update_notice_recommend(int notice_num) throws Exception {
		return noticeDao.update_notice_recommend(notice_num);
	}

	@Override
	public void update_notice_comment_modify(Comment comment) throws Exception {
		noticeDao.update_notice_comment_modify(comment);
	}
	
	//미사용
	@Override
	public int select_notice_comment_cnt(int notice_num) throws Exception {
		return noticeDao.select_notice_comment_cnt(notice_num);
	}

}
