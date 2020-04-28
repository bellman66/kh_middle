package com.kh.middle.review.vo;

public class Review {
	
	Integer review_no;	//게시글 번호
	
	String content;	//리뷰 내용

	Integer rating; // 평점을 위한 필드추가
	
	String uni_id;  //주유소 아이디
	
	String user_id; //회원 아이디
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(Integer review_no, String content, Integer rating, String uni_id, String user_id) {
		super();
		this.review_no = review_no;
		this.content = content;
		this.rating = rating;
		this.uni_id = uni_id;
		this.user_id = user_id;
	}

	public Integer getReview_no() {
		return review_no;
	}

	public void setReview_no(Integer review_no) {
		this.review_no = review_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getUni_id() {
		return uni_id;
	}

	public void setUni_id(String uni_id) {
		this.uni_id = uni_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Review [review_no=" + review_no + ", content=" + content + ", rating=" + rating + ", uni_id=" + uni_id
				+ ", user_id=" + user_id + "]";
	}
	
	
	
	
}
