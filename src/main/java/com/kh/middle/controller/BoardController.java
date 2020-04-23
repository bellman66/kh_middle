package com.kh.middle.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.middle.api.op_Api.Controller.Api_Controller;



@Controller
@RequestMapping("/board")
public class BoardController extends HttpServlet {

	
	@CrossOrigin(origins = "*")
	@RequestMapping("index.do")
	public ModelAndView Board_indexView(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		mv.setViewName("/board/index");

		return mv;
		
	}
}