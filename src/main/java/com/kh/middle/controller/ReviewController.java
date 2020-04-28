package com.kh.middle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.middle.review.mapper.ReviewMapper;
import com.kh.middle.review.vo.Review;

@Controller
public class ReviewController {

	 @Autowired
	    private ReviewMapper reviewMapper;
		
	    @RequestMapping(value = "/os/show/{id}", method = RequestMethod.GET)
	    public String show(@PathVariable String id, Model model) {
	       
	    	//주유소 id 받아오기
	    	//Os os = osMapper.getOs(id);
	       // model.addAttribute("os", os);
	        
	        // 화면에 보여줄 기존 리뷰들
	        List<Review> reviews = reviewMapper.getReviews(id);
	        model.addAttribute("reviews", reviews);
	        
	        // 폼 태그에서 modelAttribute="review" 속성을 읽어올 수 있어야함.
	        //14-8 vo객체인 Review 클래스를 생성해 값을 담는다
	        Review review = new Review();
	        review.setUni_id(id);
	        model.addAttribute("review", review);
	        
	        // 평점 옵션
	        Map ratingOptions = new HashMap();
	        ratingOptions.put(0, "☆☆☆☆☆");
	        ratingOptions.put(1, "★☆☆☆☆");
	        ratingOptions.put(2, "★★☆☆☆");
	        ratingOptions.put(3, "★★★☆☆");
	        ratingOptions.put(4, "★★★★☆");
	        ratingOptions.put(5, "★★★★★");
	        model.addAttribute("ratingOptions", ratingOptions);
	        
	     
	        return "os/show";
	    }
	    
	    //리뷰 생성 후  작성 된 리뷰들 보여주기 
	    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
	    public String create(@ModelAttribute Review review) {
	    	
	    	reviewMapper.create(review);
	        return "redirect:/os/show/" + review.getUni_id();
	        
	    }

	
}
