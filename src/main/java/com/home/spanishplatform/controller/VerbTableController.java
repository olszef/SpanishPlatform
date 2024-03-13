package com.home.spanishplatform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.spanishplatform.entity.Conjugation;
import com.home.spanishplatform.entity.ConjugationMode;
import com.home.spanishplatform.entity.ConjugationTense;
import com.home.spanishplatform.entity.Verb;
import com.home.spanishplatform.service.ConjugationService;

@Controller
@RequestMapping("/training/verbtable")
public class VerbTableController {

	@Autowired
	private ConjugationService conjugationService;
	
	@GetMapping("")
	public String translationPage(Model theModel) {
		theModel.addAttribute("generateStatus", "ready");
		return "training/verb_table";
	}

	@GetMapping("/generate")
	public String generateTable(Model theModel) {
		String generateStatus = "empty";
		
		List<Verb> randomVerbs = conjugationService.findRandomSpanishVerbs(10);
		
		List<Conjugation> randomConjugations = new ArrayList<Conjugation>();
		List<ConjugationMode> modeMap = conjugationService.findAllConjugationModes();
		
		//TODO: uncomment, cause hardcode of 1 is for testing
//		int randomMode = (int)(Math.random() * modeMap.size()) + 1;
		int randomMode = 0;
		int randomModeId = modeMap.get(randomMode).getModeId();
		
		
		List<ConjugationTense> tenseMap = conjugationService.findAllConjugationTensesPerMode(randomModeId);
		
		for (Verb verb : randomVerbs) {			
			//TODO: uncomment, cause hardcode of 1 is for testing
//			int randomTense = (int)(Math.random() * tenseMap.size()) + 1;
			int randomTense = 0;
			int randomTenseId = tenseMap.get(randomTense).getModeId();
			Conjugation conjugation = conjugationService.findSingleConjugation(verb.getVerbId(), randomModeId, randomTenseId);		
			randomConjugations.add(removeRandomConjugationForms(conjugation));
		}
		
		generateStatus = "OK";
		
		theModel.addAttribute("modeName", modeMap.get(randomMode).getModeText());
		theModel.addAttribute("tenseMap", tenseMap);
		theModel.addAttribute("randomConjugations", randomConjugations);
		theModel.addAttribute("searchStatus", generateStatus);
		return "training/verb_table";
	}
	
	private Conjugation removeRandomConjugationForms(Conjugation conjugation) {
		Random random = new Random();
		List<Integer> randomNums = new ArrayList<Integer>();
		
		for (int i = 0; i < 3; i++) {
			int nextRandom;
			do {
				nextRandom = random.nextInt(6) + 1;
			} while (randomNums.contains(nextRandom));
			randomNums.add(nextRandom);
			
			switch (nextRandom) {
				case 1:
					conjugation.setSingle1("");
					break;
				case 2:
					conjugation.setSingle2("");
					break;
				case 3:
					conjugation.setSingle3("");
					break;
				case 4:
					conjugation.setPlural1("");
					break;
				case 5:
					conjugation.setPlural2("");
					break;
				case 6:
					conjugation.setPlural3("");
					break;
			}
		}		
		return conjugation;
	}

}
