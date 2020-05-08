package com.kh.middle.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.middle.api.member.KakaoLogin;
import com.kh.middle.api.member.NaverCaptchaNkey;
import com.kh.middle.bean.member.Member;
import com.kh.middle.memberdb.service.MemberService;

@Controller
@RequestMapping("/member")
@Transactional(rollbackFor = { Exception.class }) // exception 발생시 rollback
public class MemberController {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Resource(name = "MemberService")   
	private MemberService memberService;
	/**
	 * 1.MethodName : LoginPage 2.ClassName : MemberController.java 3.Comment :
	 * index - loginPage 이동용 4.작성자 : 강영규 5.작성일 : 2020. 4. 23.
	 * 
	 */
	
	@RequestMapping("index.do")
	public ModelAndView index(ModelAndView mav) {
		
		mav.setViewName("member/index");
		
		return mav;
	}
	
	@RequestMapping("memberloginpage.do")
	public ModelAndView LoginPage(ModelAndView mav) {
		mav.setViewName("member/Login");
		return mav;
	}
	
	
	/**
	 * 1.MethodName : memberLogin 2.ClassName : MemberController.java 3.Comment :
	 * Login요청 처리 4.작성자 : 강영규 5.작성일 : 2020. 4. 23.
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping("memberlogin.do")
	public ModelAndView memberLogin(ModelAndView mav, HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("hello");
		Member m = new Member();

		m.setUser_id(request.getParameter("userId"));
		m.setUser_pw(request.getParameter("userPass"));

		Member mem = memberService.memberLogin(m);
		
		if (mem != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userData", mem);
			mav.setViewName("member/index");
			
		} else {
			mav.addObject("isSuccess", "false");
			mav.setViewName("member/Login");
		}

		return mav;
	}
	
	/**
		1.MethodName : joinPage
		2.ClassName : MemberController.java
		3.Comment : index page -> joinPage 이동용 메서드 
		4.작성자 : 강영규
		5.작성일 : 2020. 4. 29. 
	
	 */
	@RequestMapping("memberjoin.do")
	public ModelAndView joinPage(ModelAndView mav) {

		mav.setViewName("member/MemberJoin");
		
		return mav;
	}
	
	/**
		1.MethodName : joinImple
		2.ClassName : MemberController.java
		3.Comment : member join 요청시 DB insert하기위한 메서드 
		4.작성자 : 강영규
		5.작성일 : 2020. 4. 29. 
	 * @throws Exception 
	
	 */
	@RequestMapping("memberjoinimple.do")
	public ModelAndView joinImple(ModelAndView mav, HttpServletRequest request) throws Exception {
		
		Member m = new Member();
		m.setUser_id(request.getParameter("user_id"));
		m.setUser_pw(request.getParameter("user_pw"));
		m.setNick_name(request.getParameter("nickname"));
		m.setKind_oil(request.getParameter("oil_kind"));
		m.setEmail(request.getParameter("email"));
		m.setUser_tell(request.getParameter("user_tell"));
		
		Member res = memberService.joinImple(m);
		
		if(res != null) {
			HttpSession session = request.getSession();
			session.setAttribute("isSucccess", "true");
			session.setAttribute("userData", res);
			mav.setViewName("member/index");
			
		} else {
			mav.addObject("isSuccess", "false");
			mav.setViewName("member/MemberJoin");
		}
		
		return mav;
		
	}
	@RequestMapping("afterlogin.do")
	public ModelAndView afterLogin(ModelAndView mav,HttpServletRequest request) {
		
		mav.setViewName("member/AfterLogin");
		return mav;
	}
	
	@RequestMapping("logindo.do")
	public ModelAndView logindo(ModelAndView mav,HttpServletRequest request) {
		
		mav.setViewName("member/logindo");
		return mav;
	}
	
	@RequestMapping("nicknamecheck.do")
	public void nickNameCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("nicknameCheck");
		String check = memberService.selectNickname(request.getParameter("checkNick"));
	
		PrintWriter pw = response.getWriter();
		System.out.println(check);
		if(check == null) {
			
			pw.write("true");
			
		}else {
			pw.write("false");
		}
	}  
	
	/**
		1.MethodName : idCheck
		2.ClassName : MemberController.java
		3.Comment : member join시 중복 id체크용 메서드 
		4.작성자 : 강영규
		5.작성일 : 2020. 4. 29. 
	
	 */
	@RequestMapping("idcheck.do")
	public void idCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("user_id");
		
		
		PrintWriter pw = response.getWriter();
		String check = memberService.idCheck(id);
		
			if(check != null) {
				pw.write("false" );
			}else {
				pw.write("true");
			}
	}
	
	/**
		1.MethodName : reflashImg
		2.ClassName : MemberController.java
		3.Comment : captchar이미지 재 요청용, 기존 jpg파일 삭제 기능포함 
		4.작성자 : 강영규
		5.작성일 : 2020. 4. 29. 
	
	 */
	@ResponseBody
	@RequestMapping("reflagjimg.do")
	public Object reflashImg(HttpServletRequest request, String res) {
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root+"resources/member/captchar/"
				+request.getParameter("captcharImgSrc");
		File checkFile = new File(path);
		if(checkFile.exists()) {
			checkFile.delete();
		}
		return naverCaptchar(request,res);
	}
	
	/**
		1.MethodName : naverCaptchar
		2.ClassName : MemberController.java
		3.Comment : captchar key, jpg파일 생성용 메서드 
		4.작성자 : 강영규
		5.작성일 : 2020. 4. 29. 
	
	 */
	@RequestMapping("captcharkey.do")
	@ResponseBody
	public Object naverCaptchar(HttpServletRequest request,String res) {
		
		Map<String,String> datas = new HashMap<String, String>();
		NaverCaptchaNkey naverCaptchar = new NaverCaptchaNkey();
		String captcharKey = "";
		String getImg = "";
		captcharKey = naverCaptchar.returnKey(0,null,null,request); 
			
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(captcharKey);

		String key = jsonElement.getAsJsonObject().get("key").toString();
		
		System.out.println(key);
		
		String key2 = key.substring(1, key.lastIndexOf("\""));
		
		System.out.println(key2);
		
		getImg = naverCaptchar.getImages(key2,request);
		
		datas.put("key", key2);
		datas.put("img", getImg);
		datas.put("res",res);
		
		JSONObject jsonObj = new JSONObject(datas);
		
		return jsonObj;
	}
	
	/**
		1.MethodName : captcharkeySerti
		2.ClassName : MemberController.java
		3.Comment : captchar key, 인증코드 입력받아 true, false 반황용 메서드
		4.작성자 : 강영규
		5.작성일 : 2020. 4. 29. 
	
	 */
	@RequestMapping("captcharkeyserti.do")
	@ResponseBody
	public Object captcharkeySerti(HttpServletRequest request) {
		System.out.println("serti");
		NaverCaptchaNkey naverCaptchar = new NaverCaptchaNkey();
		String key = request.getParameter("key");
		String answer = request.getParameter("answer");
		String captcharKey =  naverCaptchar.returnKey(1,key,answer,request);
		Map<String, String> resMap = new HashMap<String, String>();
		
		// Json -> String 추출 
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(captcharKey);
		// key값 추출 
		String res = jsonElement.getAsJsonObject().get("result").toString();
		System.out.println(res);
		
		//false일 경우 img, key 재발급 
		if(res.equals("true")) {
			resMap.put("res", res);
			JSONObject jsonObj = new JSONObject(resMap);
			return jsonObj;
		}else {
			return reflashImg(request, res);
		}
	}
	
	/**
		1.MethodName : kakaoLogin
		2.ClassName : MemberController.java
		3.Comment : access_token을 얻기위한 메서드 
		4.작성자 : 강영규
		5.작성일 : 2020. 4. 29. 
	
	 */
	@RequestMapping("kakaologin.do")
	public ModelAndView kakaoLogin(@RequestParam("code") String code,ModelAndView mav,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String redirectURL = "http://localhost:7070/middle/member/kakaologin.do";
		KakaoLogin kl = new KakaoLogin();
		
		kl.setRedirectURL(redirectURL);
		
		String userCode = code;
		String userInfoJson = kl.getUserCode(userCode,0);
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(userInfoJson);
	
		
		String JsonauthorCode = jsonElement.getAsJsonObject().get("access_token").toString();
		String authorCode = JsonauthorCode.substring(1,JsonauthorCode.lastIndexOf("\""));
		String userInfo = kl.getUserCode(authorCode, 1);
		
		System.out.println(userInfo);
		jsonElement = jsonParser.parse(userInfo);
		
//		id
		String userId = jsonElement.getAsJsonObject().get("id").toString();
//		nickname
		String userProperty = jsonElement.getAsJsonObject().get("properties").toString();
		jsonElement = jsonParser.parse(userProperty);
		String userNickname = jsonElement.getAsJsonObject().get("nickname").toString();
		String nickname = userNickname.substring(1,userNickname.lastIndexOf("\""));
		
		Member m = new Member();
		
		m.setUser_id(userId);
		
		Member kakaoUser = certiIdKakao(m);
		if(kakaoUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userData", kakaoUser);
			session.setAttribute("accessToken", authorCode);
			mav.setViewName("member/index");
		}else {
			if(memberService.selectNickname(nickname) != null) {
				mav.addObject("nickNameFlag", "false");
			}else {
				m.setNick_name(nickname);
			}
			mav.addObject("userData", m);
			mav.setViewName("member/AfterKakaoJoin");
		}
		return mav;
	}
	
	private Member certiIdKakao(Member mem) throws Exception {
		
		return memberService.kakao_id_check(mem);
	}
	@RequestMapping("kakaojoin.do")
	public ModelAndView KakaoJoin(ModelAndView mav,HttpServletRequest request) throws Exception {
		
		Member m = new Member();
		
		m.setUser_id(request.getParameter("userId"));
		m.setNick_name(request.getParameter("nickName"));
		m.setKind_oil(request.getParameter("kind-oil"));
		
		memberService.kakaoJoin(m);
		
		mav.setViewName("member/Login");
		mav.addObject("kakaoJoin", "true");
		
		
		return mav;
	}
	
	@RequestMapping("logout.do")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
		HttpSession session = request.getSession(false);
		Member memberInfo = (Member)session.getAttribute("userData");
		String m_code = memberInfo.getM_code();
		session.removeAttribute("userData");
		 if(m_code.equals("1")) {
			KakaoLogin kl = new KakaoLogin();
			String accessToken = (String)session.getAttribute("accessToken");
			System.out.println(accessToken);
			String logoutId = kl.KakaoLogout(accessToken);
			System.out.println("controller : "+logoutId); 
			session.removeAttribute("accessToken");
			
		 }
			 
		response.sendRedirect("/middle/member/index.do");
	}
	
	@RequestMapping("mypage.do")
	public ModelAndView myPage(ModelAndView mav) {

		mav.setViewName("member/Mypage");
		
		return mav;
	}
	/**
		1.MethodName : handleException
		2.ClassName : MemberController.java
		3.Comment : 에러 처리용 메서드 
		4.작성자 : 강영규
		5.작성일 : 2020. 4. 29. 
	
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView();
//		String getMessage() : 발생된 예외의 메시지를 리턴한다. 
//		String toString() : 발생된 예외 클래스명과 메시지를 리턴한다. 
//		String pritnStackTrace() : 발생된 예외를 역추적하기 위해 표준 예외 스트림을 출력한다. 
//		예외 발생시 예외가 발생한 곳을 알아낼 때 주로 사용된다. 
		mav.setViewName("member/Login");

		System.out.println(e.getMessage());
		System.out.println(e.toString());
		return mav;

	}
	

}
