package com.Projet.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.Projet.security.JwtService;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	  private final JwtService jwtService;
	  @Autowired
	  private  AppUserDetailsService myUserDetailsService;


	
	@Override
	  protected void doFilterInternal(
	      @NonNull HttpServletRequest request,
	      @NonNull HttpServletResponse response,
	      @NonNull FilterChain filterChain
	  ) throws ServletException, IOException {
	    final String authHeader = request.getHeader("Authorization");
	    final String jwt;
	    final String userName;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      filterChain.doFilter(request, response);
	      return;
	    }
	    jwt = authHeader.substring(7);
	    userName = jwtService.extractUsername(jwt);
	    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	      UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
	      if (jwtService.isTokenValid(jwt, userDetails)) {
	          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	              userDetails,
	              null,
	              userDetails.getAuthorities()
	          );
	          authToken.setDetails(
	              new WebAuthenticationDetailsSource().buildDetails(request)
	          );
	          SecurityContextHolder.getContext().setAuthentication(authToken);
	        }
	      }
	      filterChain.doFilter(request, response);
	    }
}
