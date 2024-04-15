package com.home.spanishplatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.spanishplatform.dao.CardsGroupRepository;
import com.home.spanishplatform.dao.LanguageCardRepository;
import com.home.spanishplatform.entity.CardsGroup;
import com.home.spanishplatform.entity.LanguageCard;
import com.home.spanishplatform.entity.User;

@Service
public class LanguageCardServiceImpl implements LanguageCardService {

	@Autowired
	private CardsGroupRepository cardsGroupRepository;
	
	@Autowired
	private LanguageCardRepository languageCardRepository;

	@Override
	public List<CardsGroup> findAllGroups(int userId) {
		return cardsGroupRepository.findAllByUser_UserId(userId);
	}

	@Override
	public List<LanguageCard> findLanguageCardsByGroupId(int groupId) {
		return languageCardRepository.findByCardsGroup_GroupId(groupId);
	}

	@Override
	@Transactional
	public LanguageCard saveNewCardsGroupAndLanguageCard(LanguageCard languageCard, int userId) {
		CardsGroup cardsGroup;
		String newGroupName = languageCard.getCardsGroup() ==  null ? null : languageCard.getCardsGroup().getGroupName();
        if (newGroupName != null && !newGroupName.isBlank()) {
            // Check if group already exists for user to prevent duplicates
            Optional<CardsGroup> existingGroup = cardsGroupRepository.findByGroupNameAndUser_UserId(newGroupName, userId);
            cardsGroup = existingGroup.orElseGet(() -> {
            	CardsGroup newCardsGroup = new CardsGroup();
            	newCardsGroup.setGroupName(newGroupName);
            	newCardsGroup.setUser(new User(userId));
                return cardsGroupRepository.save(newCardsGroup);
            });
        } else {
            cardsGroup = cardsGroupRepository.findById(languageCard.getCardsGroup().getGroupId())
                    .orElseThrow(() -> new IllegalArgumentException("Cards Group not found"));
        }

        languageCard.setCardsGroup(cardsGroup);
        return languageCardRepository.save(languageCard);		
	}

	@Override
	public LanguageCard saveLanguageCard(LanguageCard languageCard) {
		return languageCardRepository.save(languageCard);		
	}

	@Override
	public void removeLanguageCard(LanguageCard languageCard) {
		languageCardRepository.delete(languageCard);		
	}

	@Override
	public void removeCardsGroup(CardsGroup cardsGroup) {
		cardsGroupRepository.delete(cardsGroup);		
	}
		
}
