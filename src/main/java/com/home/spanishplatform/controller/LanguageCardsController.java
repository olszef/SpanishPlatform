package com.home.spanishplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom/cards")
public class LanguageCardsController {
	
	@GetMapping("")
	public String translationPage(Model theModel) {
//		theModel.addAttribute("searchStatus", "ready");
//		theModel.addAttribute("verbText", "");
		return "user/custom/cards";
	}
}
