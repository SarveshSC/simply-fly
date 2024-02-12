package com.hexaware.simplyfly.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hexaware.simplyfly.service.JwtService;
import com.hexaware.simplyfly.service.UserInfoUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserInfoUserDetailService userInfoUserDetailService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token=null;
		String username=null;
		String authHeader=request.getHeader("Authorization");
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			  username = jwtService.extractUsername(token);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userdetails=userInfoUserDetailService.loadUserByUsername(username);
			if(jwtService.validateToken(token, userdetails)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
