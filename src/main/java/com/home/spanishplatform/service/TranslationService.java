package com.home.spanishplatform.service;

import java.util.List;

import com.home.spanishplatform.entity.Dictionary;
import com.home.spanishplatform.entity.Language;
import com.home.spanishplatform.entity.Translation;

public interface TranslationService {

	public List<Dictionary> findTranslationById(int wordId, int languageIdTo);
	
	public List<Dictionary> findTranslationByText(String wordText, int languageIdFrom, int languageIdTo);
	
	public void save(Translation translation);

	public void deleteById(int translationId);
	
	public List<Language> findAllLanguages();
	
	public Dictionary findRandomVerb();
	
	public Dictionary findVerbByLanguageAndText(int languageId, String wordText);

}
