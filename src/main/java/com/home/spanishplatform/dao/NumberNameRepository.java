package com.home.spanishplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.NumberName;

public interface NumberNameRepository extends JpaRepository<NumberName, Integer> {

}
