package com.kh.middle.memberdb.service;

import com.kh.middle.bean.member.Member;

public interface MemberServiceInter {
	
	public Member memberLogin(Member m) throws Exception;
	public String idCheck(String id) throws Exception;
	public Member joinImple(Member m) throws Exception;
	public Member kakao_id_check(Member m) throws Exception;
	public void kakaoJoin(Member m) throws Exception;
	Object selectNickname(String nickname)throws Exception;
	
	

}
