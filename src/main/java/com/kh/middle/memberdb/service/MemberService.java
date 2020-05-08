package com.kh.middle.memberdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.middle.bean.member.Member;
import com.kh.middle.memberdb.dao.MemberDao;

@Service("MemberService")
public class MemberService implements MemberServiceInter{
	@Autowired
	private MemberDao memberDao;
	   
	@Override
	public Member memberLogin(Member m) throws Exception {
		
		return memberDao.memberLogin(m);
	} 
	@Override
	public String idCheck(String id) throws Exception {
		return memberDao.idCheck(id);
	}
	@Override
	public Member joinImple(Member m) throws Exception {
		
		
		return memberDao.joinImple(m);
	}
	@Override
	public Member kakao_id_check(Member m) throws Exception {
		  
		
		return memberDao.kakao_id_check(m);
	}
	@Override
	public void kakaoJoin(Member m) throws Exception {
		memberDao.kakaoJoin(m);
	}
	@Override
	public String selectNickname(String nickname) {
		return memberDao.selectNickname(nickname);
	}
	@Override
	public void withdraw(String user_id) {
		memberDao.withdraw(user_id);
		
	}
	@Override
	public void updateKakaoLeaveYn(String user_id) {
		memberDao.updateKakaoLeaveYn(user_id);
		
	}

}
