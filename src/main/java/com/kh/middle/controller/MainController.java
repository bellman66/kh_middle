package com.kh.middle.controller;

import java.util.List;   
import java.util.Locale; 

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.middle.DataBase.Service.DB_Service;
import com.kh.middle.bean.board.product;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Resource(name = "DB_Service")
	private DB_Service sampleService;

	@RequestMapping("index.do")
	public ModelAndView indexview(ModelAndView mv) throws Exception {
		logger.info("start start start  === start start start ");

		// View ������ �̸��� ����.
		mv.setViewName("index");
		
		// test1. 현재 유저 리스트를 가져옴 
		mv.addObject("user_list", sampleService.select_user_main());
		
		List<product> list = sampleService.select_board_product();
		mv.addObject("board_product" , list );	
		
		return mv;
	}

	@RequestMapping("home.do")
	public ModelAndView homeview(ModelAndView mv) throws Exception {
		
		logger.info("HOME.DO 실행");
		
		mv.setViewName("home");
		mv.addObject("sampleAttribute", sampleService.selectData());

		return mv;
	}
	
	@RequestMapping("Zombie_Cube_Escape.do")
	public ModelAndView Zombie_Cube_Escapeview(ModelAndView mv) throws Exception {
		
		logger.info("Zombie_Cube_Escape.do 실행");
		
		mv.setViewName("Zombie_Cube_Escape");

		return mv;
	}

	@RequestMapping("projects.do")
	public String projects(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "projects";
	}

}
