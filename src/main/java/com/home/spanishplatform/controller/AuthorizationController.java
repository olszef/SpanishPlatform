package com.home.spanishplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.spanishplatform.service.ConjugationService;

@Controller
@RequestMapping("/login")
public class AuthorizationController {

	@Autowired
	private ConjugationService conjugationService;
	
	@GetMapping("")
	public String showLoginForm(Model theModel) {
		return "user/account/login";
	}
}
