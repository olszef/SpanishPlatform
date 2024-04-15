package com.home.spanishplatform.service;

import java.util.List;

import com.home.spanishplatform.entity.CardsGroup;
import com.home.spanishplatform.entity.LanguageCard;

public interface LanguageCardService {

	public List<CardsGroup> findAllGroups(int userId);
	
	public List<LanguageCard> findLanguageCardsByGroupId(int groupId);
	
	public LanguageCard saveNewCardsGroupAndLanguageCard(LanguageCard languageCard, int userId);

	public LanguageCard saveLanguageCard(LanguageCard languageCard);
	
	public void removeLanguageCard(LanguageCard languageCard);
	
	public void removeCardsGroup(CardsGroup cardsGroup);
}
