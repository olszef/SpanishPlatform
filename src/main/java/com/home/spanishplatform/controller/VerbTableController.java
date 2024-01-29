package com.home.spanishplatform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.spanishplatform.entity.Conjugation;
import com.home.spanishplatform.entity.Verb;
import com.home.spanishplatform.service.ConjugationService;

@Controller
@RequestMapping("/training/verbtable")
public class VerbTableController {

	@Autowired
	private ConjugationService conjugationService;
	
	@GetMapping("")
	public String translationPage(Model theModel) {
		//initialize the form
//		VerbTableForm verbTableForm = new VerbTableForm();
	// not needed:			
//		List<ConjugationTense> conjugationTenses = conjugationService.findAllConjugationTenses();
//		List<ConjugationMode> conjugationModes = conjugationService.findAllConjugationModes();		
//		theModel.addAttribute("conjugationModes", conjugationModes);
//		theModel.addAttribute("conjugationTenses", conjugationTenses);
//		theModel.addAttribute("verbTableForm", verbTableForm);
		theModel.addAttribute("generateStatus", "ready");
		return "user/training/verb_table";
	}

	@GetMapping("/generate")
	public String generateTable(Model theModel) {
		String generateStatus = "empty";
		
		List<Verb> randomVerbs = conjugationService.findRandomSpanishVerbs(10);
		
		List<Conjugation> randomConjugations = new ArrayList<Conjugation>();
		HashMap<Integer, String> modeMap = conjugationService.findAllConjugationModesAsMap();
		HashMap<Integer, String> tenseMap = conjugationService.findAllConjugationTensesAsMap();
		
		for (Verb verb : randomVerbs) {
			int randomMode = (int)(Math.random() * modeMap.size()) + 1;
			int randomTense = (int)(Math.random() * tenseMap.size()) + 1;
			randomConjugations.add(conjugationService.findSingleConjugation(verb.getVerbId(), randomMode, randomTense));
		}
		
		generateStatus = "OK";
		
//		theModel.addAttribute("allModesForms", allModesForms);
		theModel.addAttribute("modeMap", modeMap);
		theModel.addAttribute("tenseMap", tenseMap);
		theModel.addAttribute("randomConjugations", randomConjugations);
		theModel.addAttribute("searchStatus", generateStatus);
		return "user/training/verb_table";
	}

}
