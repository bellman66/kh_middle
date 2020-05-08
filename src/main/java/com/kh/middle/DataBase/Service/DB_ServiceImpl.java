package com.kh.middle.DataBase.Service;

import java.util.List;   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.middle.DataBase.Dao.DB_Dao;
import com.kh.middle.bean.board.product;
import com.kh.middle.bean.user.user_main;
import com.kh.middle.review.vo.Review;


// 1. Dao �� ���� ������ ������ ������ �����ϴ� ���񽺸� ����

@Service("DB_Service")
public class DB_ServiceImpl implements DB_Service{

	@Autowired
	protected DB_Dao DB_Dao;

	@Override
	public String selectData() throws Exception {

		String serviceTest = "";
		
		serviceTest = DB_Dao.selectData();
		
		return serviceTest;
	}

	// user Dao start 
	@Override
	public List<user_main> select_user_main() throws Exception {
		// TODO Auto-generated method stub
		
		return DB_Dao.select_user_main();
	}

	// user Dao end
	
	// product Dao Start 
	@Override
	public List<product> select_board_product() throws Exception {
		// TODO Auto-generated method stub
		return DB_Dao.select_board_product();
	}

	@Override
	public List<Review> select_review(String uni_id) throws Exception {
		
		return DB_Dao.select_review(uni_id);
	}

	@Override
	public void insert_review(Review review) throws Exception {

		DB_Dao.insert_review(review);
	}
	
	
}
