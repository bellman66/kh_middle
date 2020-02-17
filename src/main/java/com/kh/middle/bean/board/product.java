package com.kh.middle.bean.board;

import java.util.Arrays;

public class product {
	
	private String board_code;
	private int    board_category_count;
	private String board_img_url;
	private String board_title;
	private String user_id;
	private String board_content;
	
	public product() {
		// TODO Auto-generated constructor stub
	}

	public product(String board_code, int board_category_count, String board_img_url, String board_title,
			String user_id, String board_content) {
		super();
		this.board_code = board_code;
		this.board_category_count = board_category_count;
		this.board_img_url = board_img_url;
		this.board_title = board_title;
		this.user_id = user_id;
		this.board_content = board_content;
	}

	@Override
	public String toString() {
		return "product [board_code=" + board_code + ", board_category_count=" + board_category_count
				+ ", board_img_url=" + board_img_url + ", board_title=" + board_title + ", user_id=" + user_id
				+ ", board_content=" + board_content + "]";
	}

	public String getBoard_code() {
		return board_code;
	}

	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}

	public int getBoard_category_count() {
		return board_category_count;
	}

	public void setBoard_category_count(int board_category_count) {
		this.board_category_count = board_category_count;
	}

	public String getBoard_img_url() {
		return board_img_url;
	}

	public void setBoard_img_url(String board_img_url) {
		this.board_img_url = board_img_url;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

}
