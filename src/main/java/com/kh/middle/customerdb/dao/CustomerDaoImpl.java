package com.kh.middle.customerdb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;
import com.kh.middle.bean.customer.SearchCriteria;

@Repository("CustomerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	protected SqlSessionTemplate sqlSession;

	// 게시물 목록 불러오기
	@Override
	public List<Customer> select_writingList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("customer.writingList");
	}

	// 게시물 글쓰기
	@Override
	public void insert_writerBoard(Customer cus) throws Exception {
		sqlSession.insert("customer.boardWriter", cus);
	}

	// 게시물 상세내용 불러오기
	@Override
	public Customer boardRead(int s_no) throws Exception {
		return sqlSession.selectOne("customer.boardRead", s_no);
	}

	// 게시물 조회수 증가
	@Override
	public int updateReadCount(int s_no) throws Exception {
		return sqlSession.update("customer.updateCount", s_no);
	}

	// 게시물 수정
	@Override
	public int boardUpdate(Customer cus) throws Exception {
		return sqlSession.update("customer.boardUpdate", cus);
	}

	// 게시물 삭제
	@Override
	public void boardDelete(int s_no) throws Exception {
		sqlSession.delete("customer.boardDelete", s_no);
	}

	// 게시물 페이징처리
	// Criteria 객체에 담아서 SQL 매핑에 보낼 파라미터
	// 특정 페이지 게시글의 행(pageStart)과 페이지당 보여줄 게시글의 갯수(perPageNum)
	@Override
	public List<Map<String, Object>> pageList(Criteria cri) {
		return sqlSession.selectList("customer.pageList", cri);
	}

	// 총 게시물 수
	@Override
	public int countBoardList() {
		return sqlSession.selectOne("customer.countBoardList");
	}

	// 검색 페이지까지 적용
	@Override
	public List<Customer> searchList(SearchCriteria cri) {
		// 자동 형변환
		return sqlSession.selectList("customer.searchList", cri);
	}

	@Override
	public int countArticle(String searchType, String keyword) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlSession.selectOne("customer.countArticle", map);
	}

}
