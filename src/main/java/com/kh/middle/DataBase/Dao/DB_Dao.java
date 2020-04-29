package com.kh.middle.DataBase.Dao;

import java.util.List;   

import com.kh.middle.bean.board.product;
import com.kh.middle.bean.user.user_main;
import com.kh.middle.review.vo.Review;

// 1. Dao ��ü 

public interface DB_Dao {

	public String selectData() throws Exception;
	
	// user Dao start 
	public List<user_main> select_user_main() throws Exception;

	// user Dao end
	
	// Product Dao start
	public List<product> select_board_product() throws Exception;
	
	// reviewDao_select
	public List<Review> select_review(String uni_id) throws Exception;
	
	// reviewDao_insert
	public void  insert_review(Review review) throws Exception;	
	
}
