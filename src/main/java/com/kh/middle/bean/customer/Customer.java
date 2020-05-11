package com.kh.middle.bean.customer;

import java.sql.Date;

public class Customer {

	private int s_no;            
	private String s_title;      
	private String s_content;    
	private String s_writer;	 
	private Date s_regDate;		 
	private int s_viewCnt;
	
	public Customer() {
		super();
	}

	public Customer(int s_no, String s_title, String s_content, String s_writer, Date s_regDate, int s_viewCnt) {
		super();
		this.s_no = s_no;
		this.s_title = s_title;
		this.s_content = s_content;
		this.s_writer = s_writer;
		this.s_regDate = s_regDate;
		this.s_viewCnt = s_viewCnt;
	}



	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getS_title() {
		return s_title;
	}

	public void setS_title(String s_title) {
		this.s_title = s_title;
	}

	public String getS_content() {
		return s_content;
	}

	public void setS_content(String s_content) {
		this.s_content = s_content;
	}

	public String getS_writer() {
		return s_writer;
	}

	public void setS_writer(String s_writer) {
		this.s_writer = s_writer;
	}

	public Date getS_regDate() {
		return s_regDate;
	}

	public void setS_regDate(Date s_regDate) {
		this.s_regDate = s_regDate;
	}

	public int getS_viewCnt() {
		return s_viewCnt;
	}

	public void setS_viewCnt(int s_viewCnt) {
		this.s_viewCnt = s_viewCnt;
	}

	@Override
	public String toString() {
		return "Customer [s_no=" + s_no + ", s_title=" + s_title + ", s_content=" + s_content + ", s_writer=" + s_writer
				+ ", s_regDate=" + s_regDate + ", s_viewCnt=" + s_viewCnt + "]";
	}

}
