package com.home.spanishplatform.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.spanishplatform.dao.ConjugationModeRepository;
import com.home.spanishplatform.dao.ConjugationRepository;
import com.home.spanishplatform.dao.ConjugationTenseRepository;
import com.home.spanishplatform.dao.DictionaryRepository;
import com.home.spanishplatform.dao.VerbMappingRepository;
import com.home.spanishplatform.dao.VerbRepository;
import com.home.spanishplatform.entity.Conjugation;
import com.home.spanishplatform.entity.ConjugationMode;
import com.home.spanishplatform.entity.ConjugationTense;
import com.home.spanishplatform.entity.Dictionary;
import com.home.spanishplatform.entity.Verb;
import com.home.spanishplatform.entity.dto.FullConjugationDto;

@Service
public class ConjugationServiceImpl implements ConjugationService {

	@Autowired
	private VerbRepository verbRepository;
	
	@Autowired
	private VerbMappingRepository verbMappingRepository;
	
	@Autowired
	private DictionaryRepository dictionaryRepository;

	@Autowired
	private ConjugationRepository conjugationRepository;
	
	@Autowired
	private ConjugationTenseRepository conjugationTenseRepository;
	
	@Autowired
	private ConjugationModeRepository conjugationModeRepository;

	@Override
	public Optional<Dictionary> findVerbByWordText(String wordText) {
		return dictionaryRepository.findByLanguageIdAndWordText(1, wordText);
	}

	@Override
	public List<Conjugation> findFullConjugationByVerbId(int verbId) {
		return conjugationRepository.findByVerbId(verbId);
	}
	
	@Override
	public Conjugation findSingleConjugation(int verbId, int modeId, int tenseId) {
		return conjugationRepository.findByVerbIdAndModeIdAndTenseId(verbId, modeId, tenseId);
	}
	
	@Override
	public List<ConjugationMode> findAllConjugationModes() {
		return conjugationModeRepository.findAll();
	}

	@Override
	public HashMap<Integer, String> findAllConjugationModesAsMap() {
		HashMap<Integer, String> modes = new HashMap<Integer, String>();
		for (ConjugationMode mode : conjugationModeRepository.findAll()) {
			modes.put(mode.getModeId(), mode.getModeText());
		}
		
		return modes;
	}

	@Override
	public List<ConjugationTense> findAllConjugationTensesPerMode(int modeId) {
		return conjugationTenseRepository.findByModeIdOrderByTenseIdAsc(modeId);
	}

	@Override
	public HashMap<Integer, String> findAllConjugationTensesAsMap() {
		HashMap<Integer, String> tenses = new HashMap<Integer, String>();
		for (ConjugationTense tense : conjugationTenseRepository.findAll()) {
			tenses.put(tense.getTenseId(), tense.getTenseText());
		}
		
		return tenses;
	}

	@Override
	public int getVerbIdByWordId(int wordId) {
		return verbMappingRepository.findByWordId(wordId).getVerbId();
	}

	@Override
	public Optional<Verb> findByVerbId(int verbId) {
		// TODO Auto-generated method stub
		return verbRepository.findById(verbId);
	}
	
	@Override
	public List<Verb> findRandomSpanishVerbs(int requestCount) {
		int recordCount = (int) verbRepository.count();
		List<Verb> randomVerbs = new ArrayList<Verb>();
		
		if (requestCount >= recordCount) {
			randomVerbs = verbRepository.findAll();
		} else {
			int minWordId = verbRepository.findFirstByOrderByVerbIdAsc().getVerbId();
			int maxWordId = verbRepository.findFirstByOrderByVerbIdDesc().getVerbId();
			int randomRange = maxWordId - minWordId + 1;						
			
			List<Integer> excludedVerbIds = new ArrayList<Integer>();
			excludedVerbIds.add(0);
			
			Verb fetchedVerb;
			
			for (int i = 0; i < requestCount; i++) {
				int randomNo = (int)(Math.random() * randomRange) + minWordId;
				fetchedVerb = verbRepository.findFirstByVerbIdGreaterThanEqualAndVerbIdNotInOrderByVerbIdAsc(randomNo, excludedVerbIds);
				randomVerbs.add(fetchedVerb);
				excludedVerbIds.add(fetchedVerb.getVerbId());
			}			
		}
		
		return randomVerbs;
	}

	@Override
	public List<FullConjugationDto> findAllConjugationsByVerbId(int verbId) {
		return conjugationRepository.findFullConjugationDtosByVerbId(verbId);
	}
	
}
