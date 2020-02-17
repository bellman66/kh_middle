package com.kh.middle.DataBase.Service;

import java.util.List;

import com.kh.middle.bean.board.product;
import com.kh.middle.bean.user.user_main;   


public interface DB_Service {

	public String selectData() throws Exception;
	
	// user Service start 
	public List<user_main> select_user_main() throws Exception;

	// user Service end
	
	// product Service start
	public List<product> select_board_product() throws Exception;
	
	// product Service end
	
}
