package com.ssafy.ssafit.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {
	private JwtTokenProvider jwtTokenProvider;
	
	
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        // 1. Request Header 에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request);
 
        // 2. validateToken 으로 토큰 유효성 검사
        try {
        	if (token != null) {
					jwtTokenProvider.validateToken(token);
	            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
	            Authentication authentication = jwtTokenProvider.getAuthentication(token);
	            SecurityContextHolder.getContext().setAuthentication(authentication);
        	}
    	} catch (Exception e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
    	}
        chain.doFilter(request, response);
    }
 
    // Request Header 에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("access-token");
        return bearerToken;
    }
}
