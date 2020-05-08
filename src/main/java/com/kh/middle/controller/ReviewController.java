package com.kh.middle.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	
	@RequestMapping(value = "review_avg.do",
			method= {RequestMethod.GET, RequestMethod.POST}, 
			produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object review_avg(@RequestParam(value = "uni_id") String uni_id)
			throws Exception {

		Gson gson = new Gson();
		float jsonstring = 0;
		
		// 주유소의 평균점수
		jsonstring = db_Service.review_avg(uni_id);
		return String.valueOf(jsonstring);
	}

	@RequestMapping(value = "review_insert.do",
			method= {RequestMethod.GET, RequestMethod.POST}, 
			produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String review_insert(
							@RequestParam(value = "content") String content ,
							@RequestParam(value = "uni_id") String uni_id ,
							@RequestParam(value = "rating") int rating ,
							@RequestParam(value = "user_id") String user_id
							)
			throws Exception {
		
		Review review = new Review(uni_id, user_id , rating, content);
		db_Service.insert_review(review);
		
		return uni_id;
	}


	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e) {

//			String getMessage() : 발생된 예외의 메시지를 리턴한다. 
//			String toString() : 발생된 예외 클래스명과 메시지를 리턴한다. 
//			String pritnStackTrace() : 발생된 예외를 역추적하기 위해 표준 예외 스트림을 출력한다. 
//			예외 발생시 예외가 발생한 곳을 알아낼 때 주로 사용된다.
		ModelAndView mv = new ModelAndView();
		
		if(!e.getMessage().equals("null")) {
		System.out.println(e.getMessage());
		}
		mv.setViewName("/steller/index");
		return null;
	}

}
