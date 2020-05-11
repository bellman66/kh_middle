package com.kh.middle.customerdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.middle.bean.customer.Comment;
import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;
import com.kh.middle.customerdb.dao.CustomerDao;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	protected CustomerDao customerDao;

	@Override
	public List<Customer> pageList(Criteria cri) throws Exception {
		return customerDao.pageList(cri);
	}

	@Override
	public int countBoardList() throws Exception {
		return customerDao.countBoardList();
	}
	
	@Override
	public List<Customer> searchList(Criteria cri) throws Exception {
		return customerDao.searchList(cri);
	}

	@Override
	public int countArticle(Criteria cri) throws Exception {
		return customerDao.countArticle(cri);
	}
	
	@Override
	public void boardWriter(Customer cus) throws Exception {
		customerDao.boardWriter(cus);
	}

	@Override
	public Customer boardRead(int s_no) throws Exception {
		customerDao.updateCount(s_no); 
		return customerDao.boardRead(s_no);
	}
	
	@Override
	public int updateCount(int s_no) throws Exception {
		return customerDao.updateCount(s_no);
	}
	
	@Override
	public int boardUpdate(Customer cus) throws Exception {
		return customerDao.boardUpdate(cus);
	}

	@Override
	public void boardDelete(int s_no) throws Exception {
		customerDao.boardDelete(s_no);
	}
	
	@Override
	public void insert_comment(Comment comment) throws Exception {
		customerDao.insert_comment(comment);
	}
	
	@Override
	public List<Comment> select_comment(int s_no) throws Exception {
		return customerDao.select_comment(s_no);
	}
	
	@Override
	public void delete_comment(int comment_num) throws Exception {
		customerDao.delete_comment(comment_num);
	}
	
	@Override
	public void update_comment_modify(Comment comment) throws Exception {
		customerDao.update_comment_modify(comment);
	}
}
