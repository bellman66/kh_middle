package com.kh.middle.notice.vo;

public class Comment {

	private int notice_num;
	private String comment_content;
	private int comment_num;
	private String comment_id;

	public Comment() {
		super();
	}

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
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
		return "Comment [notice_num=" + notice_num + ", comment_content=" + comment_content + ", comment_num="
				+ comment_num + ", comment_id=" + comment_id + "]";
	}

}
