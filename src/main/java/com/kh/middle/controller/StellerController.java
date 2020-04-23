package com.kh.middle.controller;

import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.google.gson.Gson;
import com.kh.middle.api.op_Api.Controller.Api_Controller;

@Controller
@RequestMapping("/steller")
@Transactional(rollbackFor = {Exception.class})
public class StellerController {

	private static final Logger logger = LoggerFactory.getLogger(StellerController.class);
	private static Api_Controller api_control = Api_Controller.getInstance();
	
	@CrossOrigin(origins = "*")
	@RequestMapping("index.do")
	public ModelAndView Steller_indexview(ModelAndView mv ,HttpServletRequest request , HttpServletResponse response) throws Exception {
		logger.info("steller index start === ");

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");

		mv.setViewName("/steller/index");
		
		mv.addObject("avgAllPrice", api_control.avgAllPrice());
		mv.addObject("avgRecentPrice", api_control.avgRecentPrice());
		mv.addObject("avgSidoPrice", api_control.avgSidoPrice());
		mv.addObject("avgSigunPrice", api_control.avgSigunPrice());
		
		return mv;
	}
	
	@RequestMapping(value="avgAllPrice.do" , method=RequestMethod.POST)
	@ResponseBody // 메소드의 실행결과를 JSON형태로 반환하는 역할을 하는 어노테이션
	public Object avgAllPrice() {
		
		Gson gson = new Gson();
		String jsonstring = "";
		
		try {
			jsonstring = gson.toJson(api_control.avgAllPrice());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ajax에서 값요청시.
		return jsonstring;
	}
	
	@RequestMapping(value= "aroundAll.do" , 
					method= {RequestMethod.GET, RequestMethod.POST} , 
					produces="text/plain;charset=UTF-8")	// alert 한글꺠지는거 방지.
	@ResponseBody	// POST 방식의 경우 response body를 통해 파라미터를 가져옴.
	public Object aroundAll(@RequestBody Map<String,Double> paramData) {
		
		// json -> file 리턴.
		Gson gson = new Gson();
		String jsonstring = "";

		double x = paramData.get("x");
		double y = paramData.get("y");
		
		try {
			jsonstring = gson.toJson(api_control.aroundAll(x, y));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonstring;
	}
	
	@ExceptionHandler(value=Exception.class)
	public ModelAndView handleException(Exception e) {
		
//		String getMessage() : 발생된 예외의 메시지를 리턴한다. 
//		String toString() : 발생된 예외 클래스명과 메시지를 리턴한다. 
//		String pritnStackTrace() : 발생된 예외를 역추적하기 위해 표준 예외 스트림을 출력한다. 
//		예외 발생시 예외가 발생한 곳을 알아낼 때 주로 사용된다. 

			
		
		return null;
		
	}
	
}
