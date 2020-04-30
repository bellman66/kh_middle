package com.kh.middle.notice.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.middle.notice.vo.Notice;
import com.kh.middle.notice.vo.PageDefault;
import com.kh.middle.notice.vo.Paging;

@Repository("NoticeDao")
public class NoticeDaoImpl implements NoticeDao { 

	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public List<Notice> select_notice_paging(PageDefault pageDefault) throws Exception {
		return sqlSession.selectList("sql.select_notice_paging", pageDefault);
	}

	@Override
	public void insert_notice(Notice notice) throws Exception {
		sqlSession.insert("sql.insert_notice", notice);
	}

	@Override
	public Notice select_notice_detail(int notice_num) throws Exception {
		return sqlSession.selectOne("sql.select_notice_detail", notice_num);
	}

	@Override
	public void update_notice_count(int notice_num) throws Exception {
		sqlSession.update("sql.update_notice_count", notice_num);
	}

	@Override
	public int select_board_total_count() throws Exception {
		return sqlSession.selectOne("sql.select_board_total_count");
	}

}
