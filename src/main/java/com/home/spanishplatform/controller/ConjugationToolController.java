package com.home.spanishplatform.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.spanishplatform.entity.Dictionary;
import com.home.spanishplatform.entity.dto.FullConjugationDto;
import com.home.spanishplatform.service.ConjugationService;

@Controller
@RequestMapping("/tool/conjugation")
public class ConjugationToolController {

	@Autowired
	private ConjugationService conjugationService;
	
	@GetMapping("")
	public String translationPage(Model theModel) {
		theModel.addAttribute("searchStatus", "ready");
		theModel.addAttribute("verbText", "");
		return "user/tool/conjugation_tool";
	}

	@GetMapping("/find")
	public String findConjugation(@RequestParam String wordText, Model theModel) {
		String searchStatus = "empty";
		Optional<Dictionary> verb = conjugationService.findVerbByWordText(wordText);
		
		Map<String, List<FullConjugationDto>> allConjugationForms = null;
		if (verb.isPresent()) {
			List<FullConjugationDto> conjugationDTO = conjugationService.findAllConjugationsByVerbId(1);
	        allConjugationForms = conjugationDTO.stream().collect(Collectors.groupingBy(FullConjugationDto::getModeText, LinkedHashMap::new, 
	                        Collectors.toList()));
			searchStatus = "OK";
		}
		
		theModel.addAttribute("allConjugationForms", allConjugationForms);
		theModel.addAttribute("verbText", wordText);
		theModel.addAttribute("searchStatus", searchStatus);
		return "user/tool/conjugation_tool";
	}

}
