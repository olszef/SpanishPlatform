package com.home.spanishplatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.Conjugation;
import com.home.spanishplatform.entity.keys.ConjugationId;

public interface ConjugationRepository extends JpaRepository<Conjugation, ConjugationId> {
	List<Conjugation> findByVerbId(int verbId);
	
	Conjugation findByVerbIdAndModeIdAndTenseId(int verbId, int modeId, int tenseId);
}
