package com.ssafy.ssafit.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ssafy.ssafit.model.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	@Autowired
	UserService userService;
	
	private static final String SALT = "SSAFIT";
    public JwtTokenProvider() {
    }
    
    //토큰 생성
  	public String generateToken(String claimId, String data) throws UnsupportedEncodingException {
  		//유효기간도 설정해서 해볼것 ㅎ
  		return Jwts.builder()
  				.setHeaderParam("alg", "HS256")
  				.setHeaderParam("typ", "JWT")
  				.claim(claimId, data)
  				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
  				.compact();
  	}
  	//유효성 검사
  	public void validateToken(String token) throws Exception {
  		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
  	}
 
    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
 
        if (claims.get("id") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
 
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                new ArrayList<>();
 
        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new UserDetail(userService.getUserById(claims.get("id").toString()));
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
 
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(accessToken).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
