package com.kh.middle.notice.db.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.middle.notice.db.dao.NoticeDao;
import com.kh.middle.notice.vo.Notice;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDao noticeDao;

	@Override
	public List<Notice> select_notice_list() {
		return noticeDao.select_notice_list();
	}

	@Override
	public void insert_notice(Notice notice) {
		noticeDao.insert_notice(notice);
	}

}
