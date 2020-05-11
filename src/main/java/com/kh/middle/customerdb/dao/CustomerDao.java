package com.kh.middle.customerdb.dao;

import java.util.List;

import com.kh.middle.bean.customer.Comment;
import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;

public interface CustomerDao {

	public List<Customer> pageList(Criteria cri) throws Exception;

	public int countBoardList() throws Exception;

	public List<Customer> searchList(Criteria cri) throws Exception;

	public int countArticle(Criteria cri) throws Exception;

	public void boardWriter(Customer cus) throws Exception;

	public Customer boardRead(int s_no) throws Exception;

	public int updateCount(int s_no) throws Exception;

	public int boardUpdate(Customer cus) throws Exception;

	public void boardDelete(int s_no) throws Exception;
	
	public void insert_comment(Comment comment) throws Exception;
	
	public List<Comment> select_comment(int s_no) throws Exception;
	
	public void delete_comment(int comment_num) throws Exception;
	
	public void update_comment_modify(Comment comment) throws Exception;
}
