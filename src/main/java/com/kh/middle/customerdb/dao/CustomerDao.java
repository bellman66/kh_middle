package com.kh.middle.customerdb.dao;

import java.util.List;
import java.util.Map;

import com.kh.middle.bean.customer.Criteria;
import com.kh.middle.bean.customer.Customer;
import com.kh.middle.bean.customer.SearchCriteria;

public interface CustomerDao {

	// �Խù� ��� ����
	public List<Customer> select_writingList() throws Exception;

	// �Խù� �ۼ�
	public void insert_writerBoard(Customer cus) throws Exception;

	// �Խù� �󼼳��� �ҷ�����
	public Customer boardRead(int s_no) throws Exception;
	
	// �Խù� ��ȸ�� ����
	public int updateReadCount(int s_no) throws Exception;

	// �Խù� ����
	public int boardUpdate(Customer cus) throws Exception;
	
	 // �Խù� ����
    public void boardDelete(int s_no) throws Exception;
    
    // �Խù� ����¡ó��
    public List<Map<String, Object>> pageList(Criteria cri);
	
    // �� �Խù���
    public int countBoardList();
    
    // �˻��� �Խù���
    public List<Customer> searchList(SearchCriteria cri);
    
	public int countArticle(String searchType, String keyword);
    
}
