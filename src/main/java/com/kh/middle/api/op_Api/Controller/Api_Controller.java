package com.kh.middle.api.op_Api.Controller;

import java.util.ArrayList;

import com.kh.middle.api.op_Api.model.avgAllPrice;
import com.kh.middle.api.op_Api.model.avgSidoPrice;
import com.kh.middle.bean.api.opNet.avgAllPrice_obj;
import com.kh.middle.bean.api.opNet.avgSidoPrice_obj;

public class Api_Controller {
	
	public ArrayList<avgAllPrice_obj> avgAllPrice() throws Exception {
		
		return new avgAllPrice().getavgAllPrice();
	}
	
	public ArrayList<avgSidoPrice_obj> avgSidoPrice() throws Exception {
		
		return new avgSidoPrice().getavgSidoPrice();
	}

}
