package com.home.spanishplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

}
