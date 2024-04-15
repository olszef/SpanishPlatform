package com.home.spanishplatform.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.CardsGroup;

public interface CardsGroupRepository extends JpaRepository<CardsGroup, Integer> {
	List<CardsGroup> findAllByUser_UserId(Integer userId);
	
	Optional<CardsGroup> findByGroupNameAndUser_UserId(String cardsGroup, Integer userId);
}
