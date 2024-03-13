package com.home.spanishplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthorizationController {
	
	@GetMapping("")
	public String showLoginForm(Model theModel) {
		return "user/login";
	}
}
