package com.kh.middle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customerservice")
public class CustomerServiceController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceController.class);
	
	@CrossOrigin(origins = "*")
	@RequestMapping("index.do")
	public ModelAndView Steller_indexview(ModelAndView mv ,HttpServletRequest request , HttpServletResponse response) throws Exception {
		logger.info("customerservice index start === ");

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");

		mv.setViewName("/customerservice/index");
		
		return mv;
	}
	
}
