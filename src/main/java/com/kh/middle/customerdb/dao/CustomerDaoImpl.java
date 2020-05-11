package com.kh.middle.customerdb.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.middle.bean.customer.Comment;
import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;

@Repository("CustomerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	protected SqlSessionTemplate sqlSession;

	@Override
	public List<Customer> pageList(Criteria cri) throws Exception {
		return sqlSession.selectList("customer.pageList", cri);
	}

	@Override
	public int countBoardList() throws Exception {
		return sqlSession.selectOne("customer.countBoardList");
	}
	
	@Override
	public List<Customer> searchList(Criteria cri) throws Exception {
		return sqlSession.selectList("customer.searchList", cri);
	}

	@Override
	public int countArticle(Criteria cri) throws Exception {
		return sqlSession.selectOne("boardDAO.countArticle", cri);
	}

	@Override
	public void boardWriter(Customer cus) throws Exception {
		sqlSession.insert("customer.boardWriter", cus);
	}

	@Override
	public Customer boardRead(int s_no) throws Exception {
		return sqlSession.selectOne("customer.boardRead", s_no);
	}

	@Override
	public int updateCount(int s_no) throws Exception {
		return sqlSession.update("customer.updateCount", s_no);
	}

	@Override
	public int boardUpdate(Customer cus) throws Exception {
		return sqlSession.update("customer.boardUpdate", cus);
	}

	@Override
	public void boardDelete(int s_no) throws Exception {
		sqlSession.delete("customer.boardDelete", s_no);
	}

	@Override
	public void insert_comment(Comment comment) throws Exception {
		sqlSession.insert("customer.insert_comment", comment);
	}
	
	@Override
	public List<Comment> select_comment(int s_no) throws Exception {
		return sqlSession.selectList("customer.select_comment", s_no);
	}
	
	@Override
	public void delete_comment(int comment_num) throws Exception {
		sqlSession.delete("customer.delete_comment", comment_num);
	}
	
	@Override
	public void update_comment_modify(Comment comment) throws Exception {
		sqlSession.update("customer.update_comment_modify",comment);
	}
	
}
