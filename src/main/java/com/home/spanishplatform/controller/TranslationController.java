package com.home.spanishplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.spanishplatform.POJO.TranslationSearch;
import com.home.spanishplatform.entity.Dictionary;
import com.home.spanishplatform.entity.Language;
import com.home.spanishplatform.service.TranslationService;

@Controller
@RequestMapping("/translation")
public class TranslationController {

	@Autowired
	private TranslationService translationService;
	
	@GetMapping("")
	public String translationPage(Model theModel) {
		List<Language> languages = translationService.findAllLanguages();
		theModel.addAttribute("languages", languages);
		theModel.addAttribute("searchStatus", "ready");
		TranslationSearch translationSearch = new TranslationSearch();
		theModel.addAttribute("translationSearch", translationSearch);
		return "user/dictionary";
	}
	
	@GetMapping("/find")
	public String findTranslation(@ModelAttribute("translationSearch") TranslationSearch translationSearch, Model theModel) {		
		List<Dictionary> translatedWords = translationService.findTranslationByText(translationSearch.getWordText(), translationSearch.getLanguageIdFrom(), translationSearch.getLanguageIdTo());
		String searchStatus; 
		if (translatedWords.isEmpty()) {
			searchStatus = "empty";
		} else {
			searchStatus = "OK";
			theModel.addAttribute("translatedWords", translatedWords);
		}

		theModel.addAttribute("searchStatus", searchStatus);		
		theModel.addAttribute("translationSearch", translationSearch);
		
		List<Language> languages = translationService.findAllLanguages();
		theModel.addAttribute("languages", languages);
		
		return "user/dictionary";
	}

}
