package com.valtech.spring.security.jtw.config;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	public JWTHelper jwtHelper;
	
	
 public JWTFilter(JWTHelper jwtHelper) {
	this.jwtHelper=jwtHelper;
}


 	@Override
 		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
 				throws ServletException, IOException {
 		System.out.println(request.getHeader(HttpHeaders.AUTHORIZATION));
 		
 		if (request.getHeader(HttpHeaders.AUTHORIZATION)==null && request.getRequestURI().startsWith("/hello")) {
			filterChain.doFilter(request, response);
			return;
		}
 		
 		String token=request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer", "");
 		
 		
 		Map<String, Object> claims=jwtHelper.parseToken(token);
 		String username =(String) claims.get(Claims.SUBJECT);
 		
 		
 		List<String> roles =Arrays.asList(claims.get("roles").toString().split(","));
 		
 		
 		List<GrantedAuthority> authorities=roles.stream()
 				.map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
 		
 		
 		UsernamePasswordAuthenticationToken authToken =new  UsernamePasswordAuthenticationToken(username, null,authorities);
 		
 		SecurityContextHolder.getContext().setAuthentication(authToken);
 		
 		try{
 			
 		
 		filterChain.doFilter(request, response);
 		}
 		catch(AccessDeniedException ae) {
 			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
 		}
}
 	@Override
 		protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
 			return request.getRequestURI().startsWith("/login");
}
 

}
