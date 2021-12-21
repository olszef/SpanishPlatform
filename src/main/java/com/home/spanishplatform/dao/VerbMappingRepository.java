package com.home.spanishplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.VerbMapping;
import com.home.spanishplatform.entity.keys.VerbMappingId;

public interface VerbMappingRepository extends JpaRepository<VerbMapping, VerbMappingId> {
	VerbMapping findByWordId(int wordId);
	VerbMapping findByVerbId(int verbId);
}
