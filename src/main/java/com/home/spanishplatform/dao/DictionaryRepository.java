package com.home.spanishplatform.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {
	Optional<Dictionary> findByLanguageIdAndWordText(int languageId, String wordText);
	
	Dictionary findFirstByLanguageIdAndSpeechPartIdOrderByWordIdAsc(int languageId, int speechPartId);
	Dictionary findFirstByLanguageIdAndSpeechPartIdOrderByWordIdDesc(int languageId, int speechPartId);
	Dictionary findFirstByLanguageIdAndSpeechPartIdAndWordIdGreaterThanEqualOrderByWordIdAsc(int languageId, int speechPartId, int wordId);
	
	Dictionary findByLanguageIdAndWordTextAndSpeechPartId(int languageId, String wordText, int speechPartId);
}
