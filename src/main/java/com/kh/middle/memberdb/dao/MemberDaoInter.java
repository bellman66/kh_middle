package com.kh.middle.memberdb.dao;

import com.kh.middle.bean.member.Member;

public interface MemberDaoInter {   

	public Member memberLogin(Member m) throws Exception;
	public String idCheck(String id) throws Exception;
	public Member kakao_id_check(Member m) throws Exception;
	public void kakaoJoin(Member m)  throws Exception;
	public Member joinImple(Member m)  throws Exception;
	String selectNickname(String nickname) throws Exception;
	void withdraw(String user_id) throws Exception;
	void updateKakaoLeaveYn(String user_id) throws Exception;
	   
} 
 