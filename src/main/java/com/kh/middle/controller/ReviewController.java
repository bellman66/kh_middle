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
import org.springframework.web.servlet.ModelAndView;

import com.kh.middle.DataBase.Service.DB_Service;
import com.kh.middle.review.vo.Review;

@Controller
@RequestMapping("/review")
@Transactional(rollbackFor = { Exception.class }) // exception -> rollback
public class ReviewController {

	@Resource(name="DB_Service")
	private DB_Service db_Service;

	@RequestMapping("index.do")
	public ModelAndView Steller_indexview(ModelAndView mv, HttpServletRequest request, HttpServletResponse response
			,@RequestParam(value = "uni_id") String uni_id)
			throws Exception {

		// 화면에 보여줄 기존 리뷰들
		System.out.println("uni : " + uni_id);
		List<Review> reviews = db_Service.select_review(uni_id);
		mv.setViewName("/steller/index");
		if (reviews.size() != 0) {
			
			for (Review review : reviews) {
				System.out.println(review.toString());
			}
			mv.addObject("reviews", reviews);
		} else {
			System.out.println("현재 작성된 리뷰가 없어용");
		}
		// 평점 옵션
		Map ratingOptions = new HashMap();
		ratingOptions.put(0, "☆☆☆☆☆");
		ratingOptions.put(1, "★☆☆☆☆");
		ratingOptions.put(2, "★★☆☆☆");
		ratingOptions.put(3, "★★★☆☆");
		ratingOptions.put(4, "★★★★☆");
		ratingOptions.put(5, "★★★★★");
		mv.addObject("ratingOptions", ratingOptions);

		return mv;
	}

	@RequestMapping(value = "id.do")
	public String show(@RequestParam(value = "uni_id") String uni_id, Model model) throws Exception {

		// 주유소 id 받아오기
		// Os os = osMapper.getOs(id);
		// model.addAttribute("os", os);

		// 화면에 보여줄 기존 리뷰들
		List<Review> reviews = db_Service.select_review(uni_id);

		if (reviews.size() != 0) {

			System.out.println("있다?");
			model.addAttribute("reviews", reviews);

		} else {

			System.out.println("없다?");

		}

		// 평점 옵션
		Map ratingOptions = new HashMap();
		ratingOptions.put(0, "☆☆☆☆☆");
		ratingOptions.put(1, "★☆☆☆☆");
		ratingOptions.put(2, "★★☆☆☆");
		ratingOptions.put(3, "★★★☆☆");
		ratingOptions.put(4, "★★★★☆");
		ratingOptions.put(5, "★★★★★");
		model.addAttribute("ratingOptions", ratingOptions);

		return "/steller/index";
	}

	// 리뷰 생성 후 작성 된 리뷰들 보여주기
	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
	public String create(@ModelAttribute Review review) throws Exception {

		db_Service.insert_review(review);
		return "redirect:/os/show/" + review.getUni_id();

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
