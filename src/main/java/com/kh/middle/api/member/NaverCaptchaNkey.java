package com.kh.middle.api.member;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;import javax.servlet.http.HttpServletRequest;

public class NaverCaptchaNkey {
	private final String clientId = "W75AROAme7SVoj2XQXEZ";
	private final String clientSecret = "gtoBjWL5IW";

	public String returnKey(int flag,String key, String value, HttpServletRequest path) {

		int code = flag;// 키 발급시 0, 캡차 이미지 비교시 1로 세팅
		String apiURL = "";
		switch(code) {
		case  0:
			apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
			break;
		case 1 :
			apiURL = "https://openapi.naver.com/v1/captcha/nkey?"
					+ "code=" + code + "&key=" + key + "&value=" + value;
		}
		

		Map<String, String> setRequestHeader = new HashMap<String, String>();
		setRequestHeader.put("X-Naver-Client-Id", clientId);
		setRequestHeader.put("X-Naver-Client-Secret", clientSecret);

		String responseBody = getKey(apiURL, setRequestHeader);

		return responseBody;
	}

	private String getKey(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private HttpURLConnection connect(String apiURL) {
		try {
			URL url = new URL(apiURL); // 넘겨받는 url주소의 객체 생성 
			return (HttpURLConnection) url.openConnection(); //넘겨받은 url의 연결통로 생성
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiURL, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiURL, e);
		}
	}

	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try {
			BufferedReader lineReader = new BufferedReader(streamReader);

			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

	public String getImages(String captcharKey, HttpServletRequest request) {
		String key = captcharKey; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
		// 헤더설정 시작 
		Map<String, String> requestHeaders = new HashMap<String, String>();
	
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		// 헤더설정 끝
		// 
		String responseBody = getImg(apiURL, requestHeaders,request);
		return responseBody;
	}

	private String getImg(String apiURL, Map<String, String> requestHeaders, HttpServletRequest request) {
		System.out.println("getImgURL"+apiURL);
		// 넘겨받는 url의 통로 생성 
		HttpURLConnection con = connect(apiURL);
		BufferedInputStream bis = null;
		try {
//			연결된 통로의 method설정 
			con.setRequestMethod("GET");
//			넘겨받은 Map객체를 각각 1:1로 매칭하고 그 값을 RequestProperty에 담아준다 
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
//			통신 후 웹에서 보내는 통신코드를 받아준다 
			int responseCode = con.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
//				연결된 통로에서 보대누즌 Stream을 getImage메서드에 입력
				bis = new BufferedInputStream(con.getInputStream());
				return getImage(bis,request);
			} else { // 에러 발생
				return error(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private String getImage(BufferedInputStream bis, HttpServletRequest request) {
		 int read = 0;
		 String res = "";
		 
		 FileOutputStream fos = null;
	        // 랜덤한 이름으로  파일 생성
	        String filename = Long.valueOf(new Date().getTime()).toString();
	        String root = request.getSession().getServletContext().getRealPath("/");
	        String path = root+"resources/member/captchar/";
	        // 새로운 파일 생성 
	        try{
	        	fos = new FileOutputStream(path+filename+".jpg");
	        	
	            while ((read = bis.read()) != -1) {
	                fos.write(read);
	            }
	            fos.flush();
	            
	            res = filename+".jpg";
	            return res;
	        } catch (IOException e) {
	            throw new RuntimeException("이미지 캡차 파일 생성에 실패 했습니다.",e);
	        }finally {
	        	try {
					fos.close();
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}

	private String error(InputStream errorStream) {
		// TODO Auto-generated method stub
		return null;
	}

}
