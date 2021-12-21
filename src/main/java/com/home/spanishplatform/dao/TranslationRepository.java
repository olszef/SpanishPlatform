package com.home.spanishplatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.Translation;

public interface TranslationRepository extends JpaRepository<Translation, Integer> {
	List<Translation> findByWordIdFromAndLanguageIdTo(int l, int languageIdTo);
}
