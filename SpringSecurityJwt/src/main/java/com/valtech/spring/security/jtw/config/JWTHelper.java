package com.valtech.spring.security.jtw.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTHelper {
	
	private Key key;
	
	
	public JWTHelper(@Value("${jwt.password}") String keyPass) {
	System.out.println("password"+keyPass);
	
	
	
	key=Keys.hmacShaKeyFor(keyPass.getBytes(StandardCharsets.UTF_8));
	
	}

	public String createToken(UserDetails user) {
		Map<String,Object> claims= new HashMap<String,Object>();
		
		
		String roles=user.getAuthorities().stream().map(role-> role.getAuthority()).collect(Collectors.joining(","));
		
		claims.put("roles", roles);
		
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setClaims(claims)
				.setExpiration(DateUtils.addMinutes(new Date(), 5))
				.signWith(key).compact();
		
	}

	
	public Map<String,Object>  parseToken(String token) {
		
		return	Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		
	}
}
