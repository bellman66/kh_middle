package com.kh.middle.Filter;

import java.io.IOException; 

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossDomainFilter implements Filter {
	
	@Override
	public void destroy() {
		 System.out.println("===== filter destory =====");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("===== before(filter) =====");
	
//		if (!(request instanceof HttpServletRequest)) {
//			throw new ServletException("This filter can "
//					+ " only process HttpServletRequest requests");
//		}

		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET , OPTIONS , DELETE, PUT");     //허용할 request http METHOD : POST, GET, DELETE, PUT
	    response.setHeader("Access-Control-Max-Age", "3600");     //브라우저 캐시 시간(단위: 초) : "3600" 이면 최소 1시간 안에는 서버로 재요청 되지 않음
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		chain.doFilter(request, response);
		
		System.out.println("===== after(filter) =====");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("===== init filter =====");
	}
	
}
