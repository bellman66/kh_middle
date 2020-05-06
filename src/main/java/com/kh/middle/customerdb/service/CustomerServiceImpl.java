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

	// 게시물 목록 불러오기
	public List<Customer> select_writingList() throws Exception {

		return customerDao.select_writingList();
	}

	// 게시물 글 쓰기
	public void insert_writerBoard(Customer cus) throws Exception {
		customerDao.insert_writerBoard(cus);
	}

	// 게시물 상세내용 불러오기
	@Override
	public Customer boardRead(int s_no) throws Exception {
		customerDao.updateReadCount(s_no); //조회수 증가
		return customerDao.boardRead(s_no);
	}
	
	// 게시물 수정
	@Override
	public int updateBoard(Customer cus) throws Exception {
		return customerDao.boardUpdate(cus);
	}

	// 게시물 삭제
	@Override
	public void deleteBoard(int s_no) throws Exception {
		customerDao.boardDelete(s_no);
	}
	
	// 게시물 페이징처리
	@Override
	public List<Map<String, Object>> pageList(Criteria cri) {
		return customerDao.pageList(cri);
	}

	// 총 게시물 수
	@Override
	public int countBoardList() {
		return customerDao.countBoardList();
	}

	// 검색한 게시물 수
	@Override
	public List<Customer> searchList(SearchCriteria cri) {
		return customerDao.searchList(cri);
	}

	@Override
	public int countArticle(String searchType, String keyword) {
		return customerDao.countArticle(searchType, keyword);
	}
}
