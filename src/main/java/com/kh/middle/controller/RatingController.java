package com.kh.middle.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.google.gson.Gson;
import com.kh.middle.api.op_Api.Controller.Api_Controller;

@Controller
@RequestMapping("/rating")
public class RatingController {

	private static final Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	@CrossOrigin(origins = "*")
	@RequestMapping("index.do")
	public ModelAndView Rating_indexview(ModelAndView mv ,HttpServletRequest request , HttpServletResponse response) throws Exception {
		logger.info("rating index start === ");

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");

		mv.setViewName("/rating/index");
		
		//mv.addObject("기능 이름","본인이 만든 오브젝트");
		
		
		
		return mv;
		
	}

	
}
