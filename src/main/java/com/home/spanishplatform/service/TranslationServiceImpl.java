package com.home.spanishplatform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.spanishplatform.dao.DictionaryRepository;
import com.home.spanishplatform.dao.LanguageRepository;
import com.home.spanishplatform.dao.TranslationRepository;
import com.home.spanishplatform.entity.Dictionary;
import com.home.spanishplatform.entity.Language;
import com.home.spanishplatform.entity.Translation;

@Service
public class TranslationServiceImpl implements TranslationService {

	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private TranslationRepository translationRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Override
	public List<Dictionary> findTranslationById(int wordId, int languageIdTo) {
		List<Dictionary> translatedWords = new ArrayList<Dictionary>();		
		List<Translation> wordTranslations = translationRepository.findByWordIdFromAndLanguageIdTo(wordId, languageIdTo);

		if (wordTranslations.size() > 0) {
			List<Integer> translationToIds = wordTranslations.stream().map(Translation::getWordIdTo).collect(Collectors.toList());
			translatedWords = dictionaryRepository.findAllById(translationToIds);			
		}
		return translatedWords;
	}
	
	@Override
	public List<Dictionary> findTranslationByText(String wordText, int languageIdFrom, int languageIdTo) {
		List<Dictionary> translatedWords = new ArrayList<Dictionary>();
		Optional<Dictionary> givenWord = dictionaryRepository.findByLanguageIdAndWordText(languageIdFrom, wordText);

		if (givenWord.isPresent()) {
			translatedWords = findTranslationById(givenWord.get().getWordId(), languageIdTo);
		}
		
		return translatedWords;
	}

	@Override
	public void save(Translation translation) {
		translationRepository.save(translation);
	}

	@Override
	public void deleteById(int translationId) {
		translationRepository.deleteById(translationId);
	}

	@Override
	public List<Language> findAllLanguages() {
		return languageRepository.findAll();
	}
	
	@Override
	public Dictionary findRandomVerb() {
		int minWordId = dictionaryRepository.findFirstByLanguageIdAndSpeechPartIdOrderByWordIdAsc(1, 1).getWordId();
		int maxWordId = dictionaryRepository.findFirstByLanguageIdAndSpeechPartIdOrderByWordIdDesc(1, 1).getWordId();
		int randomRange = maxWordId - minWordId + 1;
		int randomNo = (int)(Math.random() * randomRange) + minWordId;
		return dictionaryRepository.findFirstByLanguageIdAndSpeechPartIdAndWordIdGreaterThanEqualOrderByWordIdAsc(1, 1, randomNo);
	}

	@Override
	public Dictionary findVerbByLanguageAndText(int languageId, String wordText) {
		return dictionaryRepository.findByLanguageIdAndWordTextAndSpeechPartId(languageId, wordText, 1);
	}

}
