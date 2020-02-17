package com.kh.middle.DataBase.Dao;

import java.util.List;  

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.middle.bean.board.product;
import com.kh.middle.bean.user.user_main;

@Repository("DB_Dao")
public class DB_DaoImpl implements DB_Dao {
	
	@Autowired
	protected SqlSessionTemplate sqlsession;

	@Override
	public String selectData() throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("sql.selectDisposableTable");
	}

	// user Dao start 
	@Override
	public List<user_main> select_user_main() throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList("sql.select_user_main");
	}
	
	// user Dao end

	// Product Dao start
	@Override
	public List<product> select_board_product() throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList("sql.select_board_product");
		
	}
	
	// Product Dao end

}
