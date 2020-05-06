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

	// �Խù� ��� �ҷ�����
	@Override
	public List<Customer> select_writingList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("customer.writingList");
	}

	// �Խù� �۾���
	@Override
	public void insert_writerBoard(Customer cus) throws Exception {
		sqlSession.insert("customer.boardWriter", cus);
	}

	// �Խù� �󼼳��� �ҷ�����
	@Override
	public Customer boardRead(int s_no) throws Exception {
		return sqlSession.selectOne("customer.boardRead", s_no);
	}

	// �Խù� ��ȸ�� ����
	@Override
	public int updateReadCount(int s_no) throws Exception {
		return sqlSession.update("customer.updateCount", s_no);
	}

	// �Խù� ����
	@Override
	public int boardUpdate(Customer cus) throws Exception {
		return sqlSession.update("customer.boardUpdate", cus);
	}

	// �Խù� ����
	@Override
	public void boardDelete(int s_no) throws Exception {
		sqlSession.delete("customer.boardDelete", s_no);
	}

	// �Խù� ����¡ó��
	// Criteria ��ü�� ��Ƽ� SQL ���ο� ���� �Ķ����
	// Ư�� ������ �Խñ��� ��(pageStart)�� �������� ������ �Խñ��� ����(perPageNum)
	@Override
	public List<Map<String, Object>> pageList(Criteria cri) {
		return sqlSession.selectList("customer.pageList", cri);
	}

	// �� �Խù� ��
	@Override
	public int countBoardList() {
		return sqlSession.selectOne("customer.countBoardList");
	}

	// �˻� ���������� ����
	@Override
	public List<Customer> searchList(SearchCriteria cri) {
		// �ڵ� ����ȯ
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
