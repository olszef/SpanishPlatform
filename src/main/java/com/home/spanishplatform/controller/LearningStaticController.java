package com.home.spanishplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/learning")
public class LearningStaticController {
	
	@GetMapping("/nouns")
	public String showNounsPage() {
		return "learning/nouns";
	}
	
	@GetMapping("/verbs")
	public String showVerbsPage() {
		return "learning/verbs";
	}
	
	@GetMapping("/numbers")
	public String showNumbersPage() {
		return "learning/numbers";
	}
	
	@GetMapping("/time")
	public String showTimePage() {
		return "learning/time";
	}	
}
