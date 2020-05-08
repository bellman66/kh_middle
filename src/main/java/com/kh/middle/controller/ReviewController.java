package com.kh.middle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.middle.DataBase.Service.DB_Service;
import com.kh.middle.review.vo.Review;

@Controller
@RequestMapping("/review")
@Transactional(rollbackFor = { Exception.class }) // exception -> rollback
public class ReviewController {

	@Resource(name="DB_Service")
	private DB_Service db_Service;

	@RequestMapping(value = "index.do",
			method= {RequestMethod.GET, RequestMethod.POST}, 
			produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object review_show(@RequestParam(value = "uni_id") String uni_id)
			throws Exception {

		Gson gson = new Gson();
		String jsonstring = "";
		
		// 화면에 보여줄 기존 리뷰들
		jsonstring = gson.toJson(db_Service.select_review(uni_id));
		return jsonstring;
	}

	@RequestMapping(value = "review_insert.do",
			method= {RequestMethod.GET, RequestMethod.POST}, 
			produces="text/plain;charset=UTF-8")
	@ResponseBody
	public int review_insert(
							@RequestParam(value = "content") String content ,
							@RequestParam(value = "uni_id") String uni_id ,
							@RequestParam(value = "rating") int rating
							)
			throws Exception {

		int result = -1;
		
		Review review = new Review(uni_id, "test_user" , rating, content);
		db_Service.insert_review(review);
		
		return result;
	}


	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e) {

//			String getMessage() : 발생된 예외의 메시지를 리턴한다. 
//			String toString() : 발생된 예외 클래스명과 메시지를 리턴한다. 
//			String pritnStackTrace() : 발생된 예외를 역추적하기 위해 표준 예외 스트림을 출력한다. 
//			예외 발생시 예외가 발생한 곳을 알아낼 때 주로 사용된다.
		ModelAndView mv = new ModelAndView();
		
		System.out.println(e.getMessage());
		System.out.println(e.toString());
		mv.setViewName("/steller/index");
		return null;
	}

}
