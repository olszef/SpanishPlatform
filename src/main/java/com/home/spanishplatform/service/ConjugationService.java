package com.home.spanishplatform.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.home.spanishplatform.entity.Conjugation;
import com.home.spanishplatform.entity.ConjugationMode;
import com.home.spanishplatform.entity.ConjugationTense;
import com.home.spanishplatform.entity.Dictionary;
import com.home.spanishplatform.entity.Verb;

public interface ConjugationService {

	public Optional<Dictionary> findVerbByWordText(String wordText);

	public List<Conjugation> findFullConjugationByVerbId(int verbId);
	
	public Conjugation findSingleConjugation(int verbId, int modeId, int tenseId);
	
	public List<ConjugationMode> findAllConjugationModes();
	
	public HashMap<Integer, String> findAllConjugationModesAsMap();
	
	public List<ConjugationTense> findAllConjugationTenses();
	
	public HashMap<Integer, String> findAllConjugationTensesAsMap();
	
	public Optional<Verb> findByVerbId(int verbId);

	public int getVerbIdByWordId(int wordId);
}
