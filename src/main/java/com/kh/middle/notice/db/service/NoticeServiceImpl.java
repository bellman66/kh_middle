package com.kh.middle.notice.db.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.middle.notice.db.dao.NoticeDao;
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

}
