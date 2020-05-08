package com.kh.middle.api.member;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class KakaoLogin {
 
	private final String RESTKEY = "c618b7fc9b808f184bdafc0406409848";
	private String redirectURL;
	
	public KakaoLogin() {} 
	
	public String getUserCode(String userCode,int flag) throws IOException {
		String responseBody = "";
		String apiURL = "";
		switch(flag) {
		case 0 :
			apiURL = 
			"https://kauth.kakao.com/oauth/token?grant_type=authorization_code&"
			+ "client_id="+RESTKEY+"&"
			+ "redirect_uri="+redirectURL+"&"
			+ "code="+userCode;
			break;
		case 1:
			apiURL = 
			"https://kapi.kakao.com/v2/user/me";
			break;
		}
		
		
		HttpURLConnection con = connect(apiURL);
		
		con.setRequestMethod("POST");
		if(flag == 1) {
			
			con.setRequestProperty("Authorization", "Bearer "+userCode);
		}
		
		int responseCode = con.getResponseCode();
		
		if(responseCode ==  HttpURLConnection.HTTP_OK) {
			responseBody = readBody(con.getInputStream());
		} else {
			responseBody = readBody(con.getErrorStream());
		}
		return responseBody;
	}

	private String readBody(InputStream inputStream) throws IOException {
		
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(inputStream));
		
		StringBuilder responseBody = new StringBuilder();
		
		String line;
		
		while ((line = br.readLine()) != null) {
			responseBody.append(line);
		}
		return responseBody.toString();
	}

	public String KakaoLogout(String accessToken) throws IOException {
		
		String apiURL = "https://kapi.kakao.com/v1/user/logout";
		String responseBody = "";
		HttpURLConnection con = connect(apiURL);
		
		con.setRequestMethod("POST");
		
		con.setRequestProperty("Authorization", "Bearer "+accessToken);
		
		int responseCode = con.getResponseCode();
		
		if(responseCode ==  HttpURLConnection.HTTP_OK) {
			responseBody = readBody(con.getInputStream());
		} else {
			responseBody = readBody(con.getErrorStream());
		}
		return responseBody;
	}
	
	public HttpURLConnection connect(String apiURL) throws IOException {
		URL url = new URL(apiURL);
		return (HttpURLConnection) url.openConnection();
	}
	
	
	public String getRedirectURL() {
		return redirectURL;
	}


	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	
	
	
}
