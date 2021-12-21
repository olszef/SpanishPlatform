package com.home.spanishplatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

		if(conjugationTrainingForm.getSearchLanguageId() == 1) {
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
		if (conjugationTrainingForm.getSpanishVerb().isBlank() || conjugationTrainingForm.getSearchVerb().isBlank() || spanishVerbId == 0) {
			searchStatus = "error";
		} else {
			searchStatus = "OK";
		}
		
		//initialize the verb forms
		Optional<Verb> verbBaseForms = conjugationService.findByVerbId(spanishVerbId);
		Conjugation verbSingleConjugation = conjugationService.findSingleConjugation(spanishVerbId, conjugationTrainingForm.getSearchModeId(), conjugationTrainingForm.getSearchTenseId());

		//save data in the model
		theModel.addAttribute("verbBaseForms", verbBaseForms);
		theModel.addAttribute("verbSingleConjugation", verbSingleConjugation);
		saveFormValuesAndStatusToModel(searchStatus, conjugationTrainingForm, theModel);
		
		return "user/training/conjugation_training";
	}
	
	public void saveFormValuesAndStatusToModel(String status, ConjugationTrainingForm conjugationTrainingForm, Model theModel) {
		//get all modes and tenses and languages
		List<Language> languages = translationService.findAllLanguages();
		List<ConjugationTense> conjugationTenses = conjugationService.findAllConjugationTenses();
		List<ConjugationMode> conjugationModes = conjugationService.findAllConjugationModes();
		
		theModel.addAttribute("languages", languages);
		theModel.addAttribute("conjugationModes", conjugationModes);
		theModel.addAttribute("conjugationTenses", conjugationTenses);
		
		theModel.addAttribute("conjugationTrainingForm", conjugationTrainingForm);
		theModel.addAttribute("searchStatus", status);
	}

}
