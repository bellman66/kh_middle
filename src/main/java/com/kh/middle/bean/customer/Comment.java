package com.kh.middle.bean.customer;

public class Comment {

	private int s_no;
	private String comment_content;
	private int comment_num;
	private String comment_id;

	public Comment() {
		super();
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	@Override
	public String toString() {
		return "Comment [s_no=" + s_no + ", comment_content=" + comment_content + ", comment_num=" + comment_num
				+ ", comment_id=" + comment_id + "]";
	}

	
}
