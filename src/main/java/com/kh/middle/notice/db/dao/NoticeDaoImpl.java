package com.kh.middle.notice.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.middle.notice.vo.Comment;
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

	@Override
	public void update_modify_notice(Notice notice) throws Exception {
		sqlSession.update("sql.update_modify_notice", notice);
	}

	@Override
	public void update_recommend(Notice notice) throws Exception {
		sqlSession.update("sql.update_recommend", notice);
	}

	@Override
	public void insert_notice_comment(Comment comment) throws Exception {
		sqlSession.insert("sql.insert_notice_comment", comment);
	}

	@Override
	public List<Comment> select_notice_comment(int notice_num) throws Exception {
		return sqlSession.selectList("sql.select_notice_comment", notice_num);
	}

	@Override
	public void delete_notice(int notice_num) throws Exception {
		sqlSession.delete("sql.delete_notice", notice_num);
	}

	@Override
	public void delete_notice_comment(int comment_num) throws Exception {
		sqlSession.delete("sql.delete_notice_comment", comment_num);
	}

	@Override
	public int update_notice_recommend(int notice_num) throws Exception {
		return sqlSession.update("sql.update_notice_recommend", notice_num);
	}

	@Override
	public void update_notice_comment_modify(Comment comment) throws Exception {
		sqlSession.update("sql.update_notice_comment_modify",comment);
	}
	
	//미사용
	@Override
	public int select_notice_comment_cnt(int notice_num) throws Exception {
		return sqlSession.selectOne("sql.select_notice_comment_cnt");
	}


}
