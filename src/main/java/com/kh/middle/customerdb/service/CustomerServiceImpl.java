package com.kh.middle.customerdb.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;
import com.kh.middle.bean.customer.SearchCriteria;
import com.kh.middle.customerdb.dao.CustomerDao;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	protected CustomerDao customerDao;

	// �Խù� ��� �ҷ�����
	public List<Customer> select_writingList() throws Exception {

		return customerDao.select_writingList();
	}

	// �Խù� �� ����
	public void insert_writerBoard(Customer cus) throws Exception {
		customerDao.insert_writerBoard(cus);
	}

	// �Խù� �󼼳��� �ҷ�����
	@Override
	public Customer boardRead(int s_no) throws Exception {
		customerDao.updateReadCount(s_no); //��ȸ�� ����
		return customerDao.boardRead(s_no);
	}
	
	// �Խù� ����
	@Override
	public int updateBoard(Customer cus) throws Exception {
		return customerDao.boardUpdate(cus);
	}

	// �Խù� ����
	@Override
	public void deleteBoard(int s_no) throws Exception {
		customerDao.boardDelete(s_no);
	}
	
	// �Խù� ����¡ó��
	@Override
	public List<Map<String, Object>> pageList(Criteria cri) {
		return customerDao.pageList(cri);
	}

	// �� �Խù� ��
	@Override
	public int countBoardList() {
		return customerDao.countBoardList();
	}

	// �˻��� �Խù� ��
	@Override
	public List<Customer> searchList(SearchCriteria cri) {
		return customerDao.searchList(cri);
	}

	@Override
	public int countArticle(String searchType, String keyword) {
		return customerDao.countArticle(searchType, keyword);
	}
}
