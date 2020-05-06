package com.kh.middle.bean.member;

import java.sql.Date;

public class Member {
	
	private String user_id;
	private String user_pw;
	private String kind_oil; // 소유 차량의 유종 입력
	private String email;
	private String user_tell;
	private String leave_yn;
	private String grade;
	private String m_code;
	private String nick_name;
;
	
	public Member() {}

	public Member(String user_id, String user_pw, String kind_oil, String email,
			String user_tell,String leave_yn,String grade, String m_code,String nick_name
) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.kind_oil = kind_oil;
		this.email = email;
		this.user_tell = user_tell;
		this.leave_yn = leave_yn;
		this.m_code = m_code;
		this.grade = grade;
		this.nick_name = nick_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getKind_oil() {
		return kind_oil;
	}

	public void setKind_oil(String kind_oil) {
		this.kind_oil = kind_oil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_tell() {
		return user_tell;
	}

	public void setUser_tell(String user_tell) {
		this.user_tell = user_tell;
	}
	
	public String getLeave_yn() {
		return leave_yn;
	}

	public void setLeave_yn(String leave_yn) {
		this.leave_yn = leave_yn;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getM_code() {
		return m_code;
	}

	public void setM_code(String m_code) {
		this.m_code = m_code;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	@Override
	public String toString() {
		return "Member [user_id=" + user_id + ", user_pw=" + user_pw + ", kind_oil=" + kind_oil + ", email=" + email
				+ ", user_tell=" + user_tell + ", leave_yn=" + leave_yn + ", grade=" + grade + ", m_code=" + m_code
				+ ", nick_name=" + nick_name + "]";
	}

	
}
