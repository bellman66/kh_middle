package com.kh.middle.notice.vo;

import java.sql.Date;

public class Notice {

	private int notice_num;
	private String notice_title;
	private String notice_id;
	private Date notice_date;
	private int notice_count;
	private int notice_recommend;
	private String notice_content; 
<<<<<<< HEAD

	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(int notice_num, String notice_title, String notice_id, Date notice_date, int notice_count,
			int notice_recommend, String notice_content) {
		super();
		this.notice_num = notice_num;
		this.notice_title = notice_title;
		this.notice_id = notice_id;
		this.notice_date = notice_date;
		this.notice_count = notice_count;
		this.notice_recommend = notice_recommend;
		this.notice_content = notice_content;
	}

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	public int getNotice_count() {
		return notice_count;
	}

	public void setNotice_count(int notice_count) {
		this.notice_count = notice_count;
	}

	public int getNotice_recommend() {
		return notice_recommend;
	}

	public void setNotice_recommend(int notice_recommend) {
		this.notice_recommend = notice_recommend;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	@Override
	public String toString() {
		return "Notice [notice_num=" + notice_num + ", notice_title=" + notice_title + ", notice_id=" + notice_id
				+ ", notice_date=" + notice_date + ", notice_count=" + notice_count + ", notice_recommend="
				+ notice_recommend + ", notice_content=" + notice_content + "]";
	}
=======
	private String original_filepath;
	private String rename_filepath;
	

	public Notice() {
		super();
	}

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	public int getNotice_count() {
		return notice_count;
	}

	public void setNotice_count(int notice_count) {
		this.notice_count = notice_count;
	}

	public int getNotice_recommend() {
		return notice_recommend;
	}

	public void setNotice_recommend(int notice_recommend) {
		this.notice_recommend = notice_recommend;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getOriginal_filepath() {
		return original_filepath;
	}

	public void setOriginal_filepath(String original_filepath) {
		this.original_filepath = original_filepath;
	}

	public String getRename_filepath() {
		return rename_filepath;
	}

	public void setRename_filepath(String rename_filepath) {
		this.rename_filepath = rename_filepath;
	}

	@Override
	public String toString() {
		return "Notice [notice_num=" + notice_num + ", notice_title=" + notice_title + ", notice_id=" + notice_id
				+ ", notice_date=" + notice_date + ", notice_count=" + notice_count + ", notice_recommend="
				+ notice_recommend + ", notice_content=" + notice_content + ", original_filepath=" + original_filepath
				+ ", rename_filepath=" + rename_filepath + "]";
	}

>>>>>>> branch 'master' of https://github.com/rndso15/kh_middle.git

}
