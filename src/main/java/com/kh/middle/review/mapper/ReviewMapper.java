package com.kh.middle.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.kh.middle.review.vo.Review;

public interface ReviewMapper {

	//컨트롤러에서 넘어온 요청으로 아이디, 리뷰텍스트, 별점 DB에 넣기
    @Insert("INSERT INTO reviews (review_no, content, uni_id, rating, user_id) VALUES (#{review_no}, #{content}, #{uni_id}, #{rating}, #{user_id})")
    void create(Review review);
    
    @Select("SELECT * FROM reviews WHERE  uni_id = #{uni_id} ORDER BY review_no DESC")
    @Results(value = {
            @Result(property = "review_no", column = "review_no"),
            @Result(property = "content", column = "content"),
            @Result(property = "uni_id", column = "uni_id"),
            @Result(property = "user_id", column = "user_id"),
    })
    List<Review> getReviews(String uni_id);
	
}
