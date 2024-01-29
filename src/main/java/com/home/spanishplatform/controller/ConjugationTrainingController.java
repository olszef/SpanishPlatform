package com.home.spanishplatform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.spanishplatform.POJO.ConjugationTrainingForm;
import com.home.spanishplatform.entity.Conjugation;
import com.home.spanishplatform.entity.ConjugationMode;
import com.home.spanishplatform.entity.ConjugationTense;
import com.home.spanishplatform.entity.Dictionary;
import com.home.spanishplatform.entity.Language;
import com.home.spanishplatform.entity.Verb;
import com.home.spanishplatform.service.ConjugationService;
import com.home.spanishplatform.service.TranslationService;

@Controller
@RequestMapping("/training/conjugation")
public class ConjugationTrainingController {

	@Autowired
	private ConjugationService conjugationService;
	
	@Autowired
	private TranslationService translationService;
	
	@GetMapping("")
	public String conjugationTrainingPage(Model theModel) {
		
		//initialize the form
		ConjugationTrainingForm conjugationTrainingForm = new ConjugationTrainingForm();
		
		saveFormValuesAndStatusToModel("ready", conjugationTrainingForm, theModel);

		return "user/training/conjugation_training";
	}
	
	@GetMapping("/init")
	public String drawVerb(@ModelAttribute ConjugationTrainingForm conjugationTrainingForm, Model theModel) {
		String searchStatus;
		String verbMergedTranslations = "";
		int languageIdFrom;
		int languageIdTo;
		int spanishWordId;
		
		Dictionary trainingVerb;
		
		if (conjugationTrainingForm.getConjugationTrainingOption().equals("random")) {
			//Get random verb in Spanish
			trainingVerb = translationService.findRandomVerb();
			languageIdFrom = 1;
			languageIdTo = conjugationTrainingForm.getSearchLanguageId();
		} else {
			//Check if this verb exists
			trainingVerb = translationService.findVerbByLanguageAndText(conjugationTrainingForm.getSearchLanguageId(), conjugationTrainingForm.getSearchVerb());
			languageIdFrom = conjugationTrainingForm.getSearchLanguageId();
			languageIdTo = 1;
		}

		if(languageIdFrom == 1) {
			conjugationTrainingForm.setSpanishVerb(trainingVerb.getWordText());
			conjugationTrainingForm.setSearchVerb(trainingVerb.getWordText());
			spanishWordId = trainingVerb.getWordId();
		} else {
			//Get translation in the given language
			List<Dictionary> translations = translationService.findTranslationById(trainingVerb.getWordId(), languageIdTo);
			
			for (int i = 0; i< translations.size(); i++) {
				if (i > 0) {
					verbMergedTranslations += " / ";
				}
				verbMergedTranslations += translations.get(i).getWordText();
			}
			
			if (conjugationTrainingForm.getConjugationTrainingOption().equals("random")) {
				//put translation into form value
				conjugationTrainingForm.setSpanishVerb(trainingVerb.getWordText());
				conjugationTrainingForm.setSearchVerb(verbMergedTranslations);
				spanishWordId = trainingVerb.getWordId();
			} else {
				conjugationTrainingForm.setSpanishVerb(verbMergedTranslations);
				conjugationTrainingForm.setSearchVerb(trainingVerb.getWordText());
				spanishWordId = translations.get(0).getWordId();
			}
		}
		
		//get word to verb mapping
		int spanishVerbId = conjugationService.getVerbIdByWordId(spanishWordId);

		//set correct state
//		if (conjugationTrainingForm.getSpanishVerb().isBlank() || conjugationTrainingForm.getSearchVerb().isBlank() || spanishVerbId == 0) {
//			searchStatus = "error";
//		} else {
//			searchStatus = "OK";
//		}
		
		//initialize the verb forms
		Optional<Verb> verbBaseForms = conjugationService.findByVerbId(spanishVerbId);
		//TODO: make conjugation optional
		Conjugation verbSingleConjugation = conjugationService.findSingleConjugation(spanishVerbId, conjugationTrainingForm.getSearchModeId(), conjugationTrainingForm.getSearchTenseId());

		if (verbBaseForms.isPresent() && verbSingleConjugation != null) {
			searchStatus = "OK";
		} else {
			searchStatus = "error";
		}
		
		//save data in the model
		theModel.addAttribute("verbBaseForms", verbBaseForms);
		theModel.addAttribute("verbSingleConjugation", verbSingleConjugation);
		saveFormValuesAndStatusToModel(searchStatus, conjugationTrainingForm, theModel);
		
		return "user/training/conjugation_training";
	}
	
    @GetMapping("/tenseDropdownOptions")
    @ResponseBody
    public List<ConjugationTense> getTenseDropdownOptions(@RequestParam String modeDropdownValue) {
        // Logic to determine the items of the second dropdown based on the first dropdown's value
    	return  conjugationService.findAllConjugationTensesPerMode(Integer.parseInt(modeDropdownValue));
//        List<ConjugationTense> tenseList =  conjugationService.findAllConjugationTensesPerMode(Integer.parseInt(modeDropdownValue));
//        return tenseList.stream().map(ConjugationTense::getTenseText).collect(Collectors.toList());
    }
	
	public void saveFormValuesAndStatusToModel(String status, ConjugationTrainingForm conjugationTrainingForm, Model theModel) {
		//get all modes and tenses and languages
		List<Language> languages = translationService.findAllLanguages();
		List<ConjugationTense> conjugationTenses = new ArrayList<ConjugationTense>();
		List<ConjugationMode> conjugationModes = conjugationService.findAllConjugationModes();
		
		if (conjugationTrainingForm.getSearchModeId() > 0) {
			conjugationTenses = conjugationService.findAllConjugationTensesPerMode(conjugationTrainingForm.getSearchModeId());
		}
		
		theModel.addAttribute("languages", languages);
		theModel.addAttribute("conjugationModes", conjugationModes);
		theModel.addAttribute("conjugationTenses", conjugationTenses);		
		theModel.addAttribute("conjugationTrainingForm", conjugationTrainingForm);
		theModel.addAttribute("searchStatus", status);
	}

}
