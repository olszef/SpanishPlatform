package com.home.spanishplatform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.spanishplatform.entity.Conjugation;
import com.home.spanishplatform.entity.Dictionary;
import com.home.spanishplatform.service.ConjugationService;

@Controller
@RequestMapping("/tool/conjugation")
public class ConjugationToolController {

	@Autowired
	private ConjugationService conjugationService;
	
	@GetMapping("")
	public String translationPage(Model theModel) {
		theModel.addAttribute("searchStatus", "ready");
		return "user/tool/conjugation_tool";
	}

	@GetMapping("/find")
	public String findConjugation(@RequestParam String wordText, Model theModel) {
		String searchStatus = "empty";
		Optional<Dictionary> verb = conjugationService.findVerbByWordText(wordText);
		
		LinkedHashMap<Integer, List<Conjugation>> allModesForms = new LinkedHashMap<Integer, List<Conjugation>>();
		HashMap<Integer, String> tenseMap = conjugationService.findAllConjugationTensesAsMap();
		HashMap<Integer, String> modeMap = conjugationService.findAllConjugationModesAsMap();
		
		for (int modeId : modeMap.keySet()) {
			allModesForms.put(modeId, new ArrayList<Conjugation>());
		}
		
		if (verb.isPresent()) {
			List<Conjugation> conjugationData = conjugationService.findFullConjugationByVerbId(verb.get().getWordId());		
			for (Conjugation conjugation : conjugationData) {
				allModesForms.get(conjugation.getModeId()).add(conjugation);
			}

			searchStatus = "OK";
		}
		
		theModel.addAttribute("allModesForms", allModesForms);
		theModel.addAttribute("modeMap", modeMap);
		theModel.addAttribute("tenseMap", tenseMap);
		theModel.addAttribute("verbText", wordText);
		theModel.addAttribute("searchStatus", searchStatus);
		return "user/tool/conjugation_tool";
	}

}
