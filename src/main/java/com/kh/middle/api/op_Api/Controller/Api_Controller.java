package com.kh.middle.api.op_Api.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.kh.middle.api.op_Api.model.*;

import com.kh.middle.bean.api.opNet.*;

public class Api_Controller {
	
	private static Api_Controller ac = null;
	
	private Api_Controller() {}
	
	private static class ApiHolder {
		// 밑에 getinstance에서 부를떄 선언 시작.
		private static final Api_Controller apiInstance = new Api_Controller();
	}
	
	public static Api_Controller getInstance() {
		return ApiHolder.apiInstance;
	}
	
	public static void main(String[] args) throws Exception {
		
		Api_Controller ac = Api_Controller.getInstance();
		
	}
	
	public ArrayList<avgAllPrice_obj> avgAllPrice() throws Exception {
		
		return new avgAllPrice().getavgAllPrice();
	}
	
	public ArrayList<avgSidoPrice_obj> avgSidoPrice() throws Exception {
		
		return new avgSidoPrice().getavgSidoPrice();
	}
	
	public ArrayList<avgRecentPrice_obj> avgRecentPrice() throws Exception {
		
		return new avgRecentPrice().getavgRecentPrice();
	}
	
	public ArrayList<avgSigunPrice_obj> avgSigunPrice() throws Exception {
		
		return new avgSigunPrice().getavgSigunPrice("01");
	}
	
	public ArrayList<aroundAll_obj> aroundAll(String prodcd , double x , double y) throws Exception {
		return new aroundAll().getaroundAll(prodcd , x , y);
	}

}
