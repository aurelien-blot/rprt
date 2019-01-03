package com.cgi.java.FilRouge.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("MY_TEST_REALM");
		super.afterPropertiesSet();
	}
	
	@ExceptionHandler(value = { AuthenticationException.class })
	public void commence(final HttpServletRequest request, 
    		final HttpServletResponse response, 
    		final AuthenticationException authException) throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
        
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 : " + authException.getMessage());
	}
	@ExceptionHandler(value = { AuthenticationCredentialsNotFoundException.class })
	public void commence(final HttpServletRequest request, 
    		final HttpServletResponse response, 
    		final AuthenticationCredentialsNotFoundException authException) throws IOException, ServletException {
		System.out.println("CustomBasicAuthenticationEntryPoint called");
		response.sendError(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
	}
}
