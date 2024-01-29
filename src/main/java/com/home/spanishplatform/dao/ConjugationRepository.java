package com.home.spanishplatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.spanishplatform.entity.Conjugation;
import com.home.spanishplatform.entity.dto.FullConjugationDto;
import com.home.spanishplatform.entity.keys.ConjugationId;

public interface ConjugationRepository extends JpaRepository<Conjugation, ConjugationId> {
	List<Conjugation> findByVerbId(int verbId);
	
	Conjugation findByVerbIdAndModeIdAndTenseId(int verbId, int modeId, int tenseId);
	
	@Query(value = "SELECT new com.home.spanishplatform.entity.dto.FullConjugationDto(c.modeText, b.tenseText, a.single1, a.single2, a.single3, a.plural1, a.plural2, a.plural3)"
			+ " FROM Conjugation a"
			+ " INNER JOIN ConjugationTense b ON b.modeId = a.modeId AND b.tenseId = a.tenseId"
			+ " INNER JOIN ConjugationMode c ON c.modeId = b.modeId"			
			+ " WHERE a.verbId = :verbId"
			+ " ORDER BY a.modeId, a.tenseId")
    List<FullConjugationDto> findFullConjugationDtosByVerbId(@Param("verbId") int verbId);
	
}
