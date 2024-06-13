package com.home.spanishplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/footer")
public class FooterController {
	
	@GetMapping("/privacy")
	public String showFooterPrivacy() {
		return "footer/privacy";
	}
	
	@GetMapping("/terms")
	public String showFooterPolicy() {
		return "footer/terms";
	}
	
	@GetMapping("/copyright")
	public String showFooterCopyright() {
		return "footer/copyright";
	}	
}
