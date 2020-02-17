package com.kh.middle.bean.user;

public class user_main {
	
	private String user_code ;
	private String category_number;
	private String user_id;
	private String user_password;
	private String user_name ;
	private String user_age;
	private String user_zipcode ;
	private String user_phone;
	private String user_linkcode;
	
	public user_main() {
		// TODO Auto-generated constructor stub
	}

	public user_main(String user_code, String category_number, String user_id, String user_password, String user_name,
			String user_age, String user_zipcode, String user_phone, String user_linkcode) {
		super();
		this.user_code = user_code;
		this.category_number = category_number;
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_age = user_age;
		this.user_zipcode = user_zipcode;
		this.user_phone = user_phone;
		this.user_linkcode = user_linkcode;
	}

	@Override
	public String toString() {
		return "user_main [user_code=" + user_code + ", category_number=" + category_number + ", user_id=" + user_id
				+ ", user_password=" + user_password + ", user_name=" + user_name + ", user_age=" + user_age
				+ ", user_zipcode=" + user_zipcode + ", user_phone=" + user_phone + ", user_linkcode=" + user_linkcode
				+ "]";
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getCategory_number() {
		return category_number;
	}

	public void setCategory_number(String category_number) {
		this.category_number = category_number;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_age() {
		return user_age;
	}

	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}

	public String getUser_zipcode() {
		return user_zipcode;
	}

	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_linkcode() {
		return user_linkcode;
	}

	public void setUser_linkcode(String user_linkcode) {
		this.user_linkcode = user_linkcode;
	}
	

}
