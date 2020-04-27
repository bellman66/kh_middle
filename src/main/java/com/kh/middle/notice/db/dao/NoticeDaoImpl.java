package com.kh.middle.notice.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.middle.notice.vo.Notice;

@Repository("NoticeDao")
public class NoticeDaoImpl implements NoticeDao {

	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public List<Notice> select_notice_list() {
		return sqlSession.selectList("sql.select_notice_list");
	}

	@Override
	public void insert_notice(Notice notice) {
		sqlSession.insert("sql.insert_notice", notice);
	}

}
