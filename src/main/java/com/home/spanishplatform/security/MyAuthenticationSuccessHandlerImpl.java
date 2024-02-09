package com.home.spanishplatform.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.home.spanishplatform.dao.UserRepository;
 
@Component
public class MyAuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		userRepository.updateLastLoginByUserId(((MyUserPrincipal)authentication.getPrincipal()).getUserId(), LocalDateTime.now());
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
