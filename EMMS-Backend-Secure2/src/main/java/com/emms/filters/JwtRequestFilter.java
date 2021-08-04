package com.emms.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emms.UserDetialsServiceImpl;
import com.emms.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetialsServiceImpl userdetailsServiceImpl;
	
	@Autowired
	JwtUtil jwtUtil;

		

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt =  null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}
		
		
			
			
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.userdetailsServiceImpl.loadUserByUsername(username);
			
			//System.out.println("Validating token.");
			
				
			
			if(jwtUtil.validateToken(jwt, userDetails)) {
				
				//System.out.println("Token validated.");
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			
			
		}
		
		
		}catch (ExpiredJwtException e) {
			// TODO: handle exception
			System.out.println("Expired JWT detected.");
			System.out.println("Details of expiration : "+ e.getMessage());
			//logger.error("Details of expiration : ", e);
		}
		
		//this was chain in the tutorial :)
		filterChain.doFilter(request, response);
		
	} // doFilterInternal
	


}
