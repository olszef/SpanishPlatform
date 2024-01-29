package com.home.spanishplatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.Verb;

public interface VerbRepository extends JpaRepository<Verb, Integer> {
	Verb findFirstByVerbIdGreaterThanEqualAndVerbIdNotInOrderByVerbIdAsc(int verbId, List<Integer> excludedVerbIds);

	Verb findFirstByOrderByVerbIdAsc();

	Verb findFirstByOrderByVerbIdDesc();
}
