package com.home.spanishplatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.LanguageCard;

public interface LanguageCardRepository extends JpaRepository<LanguageCard, Integer> {
	List<LanguageCard> findByCardsGroup_GroupId(int groupId);
}
