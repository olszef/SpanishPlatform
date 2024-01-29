package com.home.spanishplatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.ConjugationTense;

public interface ConjugationTenseRepository extends JpaRepository<ConjugationTense, Integer> {
	List<ConjugationTense> findByModeIdOrderByTenseIdAsc(int modeId);
}
