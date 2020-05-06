package com.kh.middle.customerdb.dao;

import java.util.List;
import java.util.Map;

import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;
import com.kh.middle.bean.customer.SearchCriteria;

public interface CustomerDao {

	// 게시물 목록 보기
	public List<Customer> select_writingList() throws Exception;

	// 게시물 작성
	public void insert_writerBoard(Customer cus) throws Exception;

	// 게시물 상세내용 불러오기
	public Customer boardRead(int s_no) throws Exception;
	
	// 게시물 조회수 증가
	public int updateReadCount(int s_no) throws Exception;

	// 게시물 수정
	public int boardUpdate(Customer cus) throws Exception;
	
	 // 게시물 삭제
    public void boardDelete(int s_no) throws Exception;
    
    // 게시물 페이징처리
    public List<Map<String, Object>> pageList(Criteria cri);
	
    // 총 게시물수
    public int countBoardList();
    
    // 검색한 게시물수
    public List<Customer> searchList(SearchCriteria cri);
    
	public int countArticle(String searchType, String keyword);
    
}
